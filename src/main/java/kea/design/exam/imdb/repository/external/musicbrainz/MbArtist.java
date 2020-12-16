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
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MbArtist {


    public Artist getById(String id){
        org.musicbrainz.controller.Artist search = new org.musicbrainz.controller.Artist();

        try {
            Artist artist = parseWebSearch(search.getComplete(id));
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

    protected Artist parseWebSearch(ArtistWs2 artistWs2){
        Artist artist = new Artist();
        artist.setId(artistWs2.getId());
        artist.setName(artistWs2.getName());
        artist.setDisambiguation(artistWs2.getDisambiguation());

        LifeSpanWs2 life = artistWs2.getLifeSpan();
        if(life != null) {
            try {
                if(life.getBegin() != null) {
                    artist.setBeginDate(LocalDate.parse(life.getBegin()));
                }
                if (life.getEnded()) {
                    artist.setEndDate(LocalDate.parse(artistWs2.getLifeSpan().getEnd()));
                }
            } catch (DateTimeParseException exception) {
            }
        }
        //retrives the relation with the id (689870a4-a1e4-4912-b17f-7b2664215698) which is a wikipedia relationship returning a link
        List<RelationWs2> wiki = artistWs2.getRelationList().getRelations().stream().filter((relation -> relation.getTypeId().equals("689870a4-a1e4-4912-b17f-7b2664215698") || relation.getTypeId().equals("29651736-fa6d-48e4-aadc-a557c6add1cb"))).collect(Collectors.toList());
        if(!wiki.isEmpty()){
            artist.setWiki(wiki.get(0).getTargetId());
        }
        //checks if artist has a type identifier and sets the type if not null
        if(artistWs2.getType() != null) { artist.setType(artistWs2.getType().substring(artistWs2.getType().indexOf("#")+1));}

//        if(artist.getType() != null && artist.getType().toLowerCase().equals("person")) {
//            List<Artist> associatedBands = new ArrayList<>();
//            artistWs2.getRelationList().getRelations().stream().filter((relation)-> relation.getTypeId().equals("5be4c609-9afa-4ea0-910b-12ffb71e3821")).forEach((member) -> associatedBands.add(getAssociatedBands(member.getTargetId())));
//            artist.setAssociatedBands(associatedBands);
//
//        }

        //finds band members if artist type equals group
        if(artist.getType() != null && artist.getType().toLowerCase().equals("group")){
            List<Artist> bandMembers = new ArrayList<>();
            artistWs2.getRelationList().getRelations().stream().filter((relation)->relation.getTypeId().equals("-")).forEach((member) -> bandMembers.add(getBandMember(member.getTargetId())));
            artist.setBandMembers(bandMembers);

        }

        artist.setGender(artistWs2.getGender());
        if(artistWs2.getBeginArea() != null) {
           artist.setFounded(artistWs2.getBeginArea().getName());
        }
        return artist;
    }


//    protected Artist BandRelationWebSearch(ArtistWs2 artistWs2){
//        Artist artist = new Artist();
//        artist.setId(artistWs2.getId());
//        artist.setName(artistWs2.getName());
//        artist.setDisambiguation(artistWs2.getDisambiguation());
//
//        LifeSpanWs2 life = artistWs2.getLifeSpan();
//        if(life != null) {
//            try {
//                if(life.getBegin() != null) {
//                    artist.setBeginDate(LocalDate.parse(life.getBegin()));
//                }
//                if (life.getEnded()) {
//                    artist.setEndDate(LocalDate.parse(artistWs2.getLifeSpan().getEnd()));
//                }
//            } catch (DateTimeParseException exception) {
//            }
//        }
//        //retrives the relation with the id (689870a4-a1e4-4912-b17f-7b2664215698) which is a wikipedia relationship returning a link
//        List<RelationWs2> wiki = artistWs2.getRelationList().getRelations().stream().filter((relation -> relation.getTypeId().equals("689870a4-a1e4-4912-b17f-7b2664215698") || relation.getTypeId().equals("29651736-fa6d-48e4-aadc-a557c6add1cb"))).collect(Collectors.toList());
//        if(!wiki.isEmpty()){
//            artist.setWiki(wiki.get(0).getTargetId());
//        }
//        //checks if artist has a type identifier and sets the type if not null
//        if(artistWs2.getType() != null) { artist.setType(artistWs2.getType().substring(artistWs2.getType().indexOf("#")+1));}
//
//        if(artist.getType() != null && artist.getType().toLowerCase().equals("person")) {
//            List<Artist> associatedBands = new ArrayList<>();
//            artistWs2.getRelationList().getRelations().stream().filter((relation)-> relation.getTypeId().equals("5be4c609-9afa-4ea0-910b-12ffb71e3821")).forEach((member) -> associatedBands.add(getBandMember(member.getTargetId())));
//            artist.setAssociatedBands(associatedBands);
//
//        }
//
//        artist.setGender(artistWs2.getGender());
//        if(artistWs2.getBeginArea() != null) {
//            artist.setFounded(artistWs2.getBeginArea().getName());
//        }
//        return artist;
//    }

    private Artist getBandMember(String memberId){
        org.musicbrainz.controller.Artist artist = new org.musicbrainz.controller.Artist();
//        artist.getIncludes().setReleaseGroups(false);
//        artist.getIncludes().setReleases(false);
//        artist.getIncludes().setRecordings(false);
//        artist.getIncludes().setVariousArtists(false);
//        artist.getIncludes().setWorks(false);
        artist.getIncludes().setAliases(false);
        artist.getIncludes().setWorkRelations(false);
        artist.getIncludes().setAnnotation(false);

        artist.getIncludes().setReleaseGroups(false);
        artist.getIncludes().setReleases(false);
        artist.getIncludes().setRecordings(false);
        artist.getIncludes().setVariousArtists(false);
        artist.getIncludes().setWorks(false);
        try {
            return parseWebSearch(artist.lookUp(memberId));
        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
    return null;
    }

//    private Artist getAssociatedBands(String associatedId){
//        org.musicbrainz.controller.Artist artist2 = new org.musicbrainz.controller.Artist();
//        artist2.getIncludes().setReleaseGroups(false);
//        artist2.getIncludes().setReleases(false);
//        artist2.getIncludes().setRecordings(false);
//        artist2.getIncludes().setWorks(false);
//        try {
//            return BandRelationWebSearch(artist2.lookUp(associatedId));
//        } catch (MBWS2Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
