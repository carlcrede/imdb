package kea.design.exam.imdb.repository.external.musicbrainz;

import kea.design.exam.imdb.models.Artist;
import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.model.LifeSpanWs2;
import org.musicbrainz.model.entity.ArtistWs2;
import org.musicbrainz.model.searchresult.ArtistResultWs2;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MbArtist {
    org.musicbrainz.controller.Artist artist;

    public MbArtist(){
        artist = new org.musicbrainz.controller.Artist();

        artist.getIncludes().setReleaseGroups(false);
        artist.getIncludes().setReleases(false);
        artist.getIncludes().setRecordings(false);
        artist.getIncludes().setVariousArtists(false);
        artist.getIncludes().setWorks(false);
    }

    public Artist getById(String id){
        try {
            return parseWebSearch(artist.getComplete(id));
        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Artist> findByQuery(String query, int amount){
        artist.search(query);
        artist.getSearchFilter().setLimit((long) amount);
        List<ArtistResultWs2> artistWs2s = artist.getFirstSearchResultPage();
        List<Artist> artists = new ArrayList<>();
        for(ArtistResultWs2 artistWs2 : artistWs2s) {
            artists.add(parseWebSearch(artistWs2.getArtist()));
        }
        return artists;

    }

    private Artist parseWebSearch(ArtistWs2 artistWs2){
       Artist artist = new Artist();
       artist.setId(artistWs2.getId());
       artist.setName(artistWs2.getName());

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

        if(artistWs2.getType() != null) { artist.setType(artistWs2.getType().substring(artistWs2.getType().indexOf("#")+1)); }

       artist.setGender(artistWs2.getGender());
       if(artistWs2.getBeginArea() != null) {
           artist.setFounded(artistWs2.getBeginArea().getName());
       }
       return artist;
    }
}
