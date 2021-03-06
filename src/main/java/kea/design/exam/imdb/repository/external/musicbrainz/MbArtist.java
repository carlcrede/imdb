package kea.design.exam.imdb.repository.external.musicbrainz;

import kea.design.exam.imdb.models.Artist;
import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.model.LifeSpanWs2;
import org.musicbrainz.model.RelationListWs2;
import org.musicbrainz.model.RelationWs2;
import org.musicbrainz.model.entity.ArtistWs2;
import org.musicbrainz.model.searchresult.ArtistResultWs2;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MbArtist {


    public Artist getById(String id){
        org.musicbrainz.controller.Artist search = new org.musicbrainz.controller.Artist();
        search.getReleaseGroupIncludes().excludeAll();
        search.getIncludes().excludeAll();
        search.getIncludes().setUrlRelations(true);
        search.getIncludes().setArtistRelations(true);
        try {
            Artist artist = parseWebSearch(search.lookUp(id));
            artist.setCompleteInfo(true);
            return artist;
        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Artist> findByQuery(String query, int amount){
        org.musicbrainz.controller.Artist artist = new org.musicbrainz.controller.Artist();
        artist.getIncludes().setReleaseGroups(true);
        artist.getIncludes().setReleases(false);
        artist.getIncludes().setRecordings(false);
        artist.getIncludes().setVariousArtists(false);
        artist.getIncludes().setWorks(false);

        artist.getSearchFilter().setMinScore((long)100);
        artist.search(query);
        artist.getSearchFilter().setLimit((long) amount);
        List<ArtistResultWs2> artistWs2s = artist.getFirstSearchResultPage();
        List<Artist> artists = new ArrayList<>();
        for(ArtistResultWs2 artistWs2 : artistWs2s) {
            artists.add(parseWebSearch(artistWs2.getArtist()));
        }
        return artists;
    }

    public List<Artist> getBandMembers(ArtistWs2 artistWs2){
        List<Artist> bandMembers = new ArrayList<>();
            artistWs2.getRelationList().getRelations().stream().
                    filter((relation)-> relation.getTypeId().equals("5be4c609-9afa-4ea0-910b-12ffb71e3821")).
                    forEach((member) -> {
                        Artist bmem = new Artist();
                        bmem.setId(member.getTargetId());
                        bmem.setName(member.getTarget().toString());
                        bandMembers.add(bmem);
                    });
        return bandMembers;
    }

    public List<Artist> getBands(Artist artist){
        org.musicbrainz.controller.Artist search = new org.musicbrainz.controller.Artist();
        search.getIncludes().excludeAll();
        search.getReleaseGroupIncludes().excludeAll();
        search.getIncludes().setArtistRelations(true);

        List<Artist> associatedBands = new ArrayList<>();
        try {
            search.lookUp(artist.getId()).getRelationList().getRelations().stream().
                    filter((relation)-> relation.getTypeId().equals("5be4c609-9afa-4ea0-910b-12ffb71e3821")).
                    forEach((member) -> {
                        Artist band = new Artist();
                        band.setId(member.getTargetId());
                        band.setName(member.getTarget().toString());
                        associatedBands.add(band);
                    });
        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        return associatedBands;
    }

    protected Artist parseWebSearch(ArtistWs2 artistWs2){
        Artist artist = new Artist();
        artist.setId(artistWs2.getId());
        artist.setName(artistWs2.getName());
        artist.setDisambiguation(artistWs2.getDisambiguation());
        if(artistWs2.getArea() != null) {
            artist.setArea(artistWs2.getArea().getName());
        }


        LifeSpanWs2 life = artistWs2.getLifeSpan();
        if(life != null) {
            try {
                if(life.getBegin() != null) {
                    artist.setBeginDate(LocalDate.parse(life.getBegin()));
                }
                if (life.getEnded()) {
                    artist.setEndDate(LocalDate.parse(artistWs2.getLifeSpan().getEnd()));
                }
            } catch (DateTimeParseException e){
            }
        }
        //retrives the relation with the id (689870a4-a1e4-4912-b17f-7b2664215698) which is a wikipedia relationship returning a link
        List<RelationWs2> wiki = artistWs2.getRelationList().getRelations().stream().filter((relation -> relation.getTypeId().equals("689870a4-a1e4-4912-b17f-7b2664215698") || relation.getTypeId().equals("29651736-fa6d-48e4-aadc-a557c6add1cb"))).collect(Collectors.toList());
        if(!wiki.isEmpty()){ artist.setWiki(wiki.get(0).getTargetId()); }
        //checks if artist has a type identifier and sets the type if not null
        if(artistWs2.getType() != null) {
            artist.setType(artistWs2.getType().substring(artistWs2.getType().indexOf("#")+1));
            //sets bandmembers if it's a group
            if(artist.getType().toLowerCase().equals("group")){
                artist.setBandMembers(getBandMembers(artistWs2));
            }
        }
        artist.setGender(artistWs2.getGender());
        if(artistWs2.getBeginArea() != null) { artist.setFounded(artistWs2.getBeginArea().getName()); }
        return artist;
    }
}
