package kea.design.exam.imdb.repository.external.musicbrainz;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Track;
import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.controller.*;
import org.musicbrainz.model.ArtistCreditWs2;
import org.musicbrainz.model.TrackListWs2;
import org.musicbrainz.model.TrackWs2;
import org.musicbrainz.model.entity.ArtistWs2;
import org.musicbrainz.model.entity.ReleaseGroupWs2;
import org.musicbrainz.model.entity.ReleaseWs2;
import org.musicbrainz.model.entity.WorkWs2;
import org.musicbrainz.model.entity.listelement.ReleaseListWs2;
import org.musicbrainz.model.searchresult.ArtistResultWs2;
import org.musicbrainz.model.searchresult.ReleaseGroupResultWs2;
import org.musicbrainz.model.searchresult.ReleaseResultWs2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MbAlbum {

    MbArtist mbArtist;

    public MbAlbum(){
        mbArtist = new MbArtist();
    }

    public Album getById(String id){
        ReleaseGroup releaseGroup = new ReleaseGroup();
        releaseGroup.getIncludes().setReleases(true);
        try {
            ReleaseGroupWs2 releaseGWs2 = releaseGroup.lookUp(id);
            kea.design.exam.imdb.models.Artist artist = mbArtist.parseWebSearch(releaseGWs2.getArtistCredit().getNameCredits().get(0).getArtist());
            return parseWebSearch(releaseGWs2, artist);
        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Album> findByQuery(String query, int amount){
        ReleaseGroup releaseGroup = new ReleaseGroup();
        releaseGroup.getIncludes().setReleases(true);
        releaseGroup.search(query);
        releaseGroup.getSearchFilter().setLimit((long) amount);
        List<Album> albums = new ArrayList<>();

        List<ReleaseGroupResultWs2> releases = releaseGroup.getFirstSearchResultPage();
        for(ReleaseGroupResultWs2 release : releases) {
            kea.design.exam.imdb.models.Artist artist = mbArtist.parseWebSearch(release.getReleaseGroup().getArtistCredit().getNameCredits().get(0).getArtist());
            albums.add(parseWebSearch(release.getReleaseGroup(), artist));
        }
        return albums;
    }

    public List<Album> findAlbumByArtistAndType(kea.design.exam.imdb.models.Artist  artist, String type){
        List<Album> albums = new ArrayList<>();
        Artist artistEx = new Artist();

        try {
            artistEx.lookUp(artist.getId());

            List<ReleaseGroupWs2> releaseGroupWs2s = artistEx.getFullReleaseGroupList();

            for (ReleaseGroupWs2 releaseWs2 : releaseGroupWs2s){
                if(releaseWs2.getType() != null) {
                    if (releaseWs2.getTypeString().toLowerCase().equals(type.toLowerCase())) {
                        albums.add(parseWebSearch(releaseWs2, artist));
                    }
                }
            }

        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        albums = albums.stream().filter((album -> album.getType().toLowerCase().equals(type.toLowerCase()))).collect(Collectors.toList());
        return albums;
    }

    public List<Album> findAlbumsByArtist(kea.design.exam.imdb.models.Artist artist){
        ArrayList<Album> albums = new ArrayList<>();
        Artist artistEx = new Artist();

        try {
            artistEx.getReleaseGroupIncludes().setReleases(true);
            artistEx.lookUp(artist.getId());

            List<ReleaseGroupWs2> releaseGroupWs2s = artistEx.getFullReleaseGroupList();

            for (ReleaseGroupWs2 releaseWs2 : releaseGroupWs2s){
                albums.add(parseWebSearch(releaseWs2, artist));
            }

        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        return albums;
    }

    private Album parseWebSearch(ReleaseGroupWs2 releaseGroup, kea.design.exam.imdb.models.Artist artist){
        Album album = new Album();

        album.setArtist(artist);

        album.setTitle(releaseGroup.getTitle());
        album.setMbid(releaseGroup.getId());

        if(releaseGroup.getType() != null) {
            album.setType(releaseGroup.getTypeString());
            if(!releaseGroup.getSecondaryTypes().isEmpty()) {
                album.setType(releaseGroup.getSecondaryTypes().get(0));
            }
        }


        if(releaseGroup.getFirstReleaseDate() != null) {
            album.setReleaseDate(releaseGroup.getFirstReleaseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        return album;
    }
}
