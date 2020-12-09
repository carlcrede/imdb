package kea.design.exam.imdb.repository.external.musicbrainz;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Track;
import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.controller.*;
import org.musicbrainz.model.TrackListWs2;
import org.musicbrainz.model.TrackWs2;
import org.musicbrainz.model.entity.ArtistWs2;
import org.musicbrainz.model.entity.ReleaseGroupWs2;
import org.musicbrainz.model.entity.ReleaseWs2;
import org.musicbrainz.model.entity.WorkWs2;
import org.musicbrainz.model.searchresult.ArtistResultWs2;
import org.musicbrainz.model.searchresult.ReleaseGroupResultWs2;
import org.musicbrainz.model.searchresult.ReleaseResultWs2;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MbAlbum {

    public Album getById(String id){
        ReleaseGroup releaseGroup = new ReleaseGroup();
        try {
            return parseWebSearch(releaseGroup.lookUp(id), null);
        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Album> findByQuery(String query, int amount){
        ReleaseGroup releaseGroup = new ReleaseGroup();
        releaseGroup.search(query);
        releaseGroup.getSearchFilter().setLimit((long) amount);
        List<Album> albums = new ArrayList<>();
        try {
            List<ReleaseGroupResultWs2> releases = releaseGroup.getFirstSearchResultPage();
            for(ReleaseGroupResultWs2 release : releases) {
                    albums.add(parseWebSearch(release.getReleaseGroup(), null));
            }
        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        return albums;
    }

    public List<Album> findAlbumByArtistAndType(kea.design.exam.imdb.models.Artist  artist, String type){
        ArrayList<Album> albums = new ArrayList<>();
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
        return albums;
    }

    public List<Album> findAlbumsByArtist(kea.design.exam.imdb.models.Artist artist){
        ArrayList<Album> albums = new ArrayList<>();
        Artist artistEx = new Artist();

        try {
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

    private Album parseWebSearch(ReleaseGroupWs2 release, kea.design.exam.imdb.models.Artist artist) throws MBWS2Exception {
        Album album = new Album();

        album.setArtist(artist);
        album.setTitle(release.getTitle());
        album.setMbid(release.getId());

        if(release.getType() != null) {
            album.setType(release.getTypeString());
        }

        if(release.getFirstReleaseDate() != null) {
            album.setReleaseDate(release.getFirstReleaseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        return album;
    }
}
