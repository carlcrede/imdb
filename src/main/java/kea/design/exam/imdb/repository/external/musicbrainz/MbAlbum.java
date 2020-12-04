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
import org.musicbrainz.model.searchresult.ArtistResultWs2;
import org.musicbrainz.model.searchresult.ReleaseGroupResultWs2;
import org.musicbrainz.model.searchresult.ReleaseResultWs2;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MbAlbum {
    ReleaseGroup releaseGroup;
    Recording recording;
    Artist artist;

    public MbAlbum(){
        artist = new Artist();
        artist.getIncludes().setAliases(false);
        artist.getIncludes().setArtistRelations(false);
        artist.getIncludes().setWorkRelations(false);
        artist.getIncludes().setAnnotation(false);

        artist.getIncludes().setReleaseGroups(true);
        artist.getIncludes().setReleases(false);
        artist.getIncludes().setRecordings(false);
        artist.getIncludes().setVariousArtists(false);
        artist.getIncludes().setWorks(false);
        artist.getReleaseGroupIncludes().setReleases(true);

        releaseGroup = new ReleaseGroup();
        releaseGroup.getIncludes().setReleases(true);
    }

    public Album getById(String id){
        try {
            return parseWebSearch(releaseGroup.lookUp(id));
        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Album> findByQuery(String query, int amount){
        releaseGroup.search(query);
        releaseGroup.getSearchFilter().setLimit((long) amount);
        List<Album> albums = new ArrayList<>();

        List<ReleaseGroupResultWs2> releases = releaseGroup.getFirstSearchResultPage();
        for(ReleaseGroupResultWs2 release : releases) {
            albums.add(parseWebSearch(release.getReleaseGroup()));
        }
        return albums;
    }

    public List<Album> getAlbumFromArtist(String artistMbid){
        ArrayList<Album> albums = new ArrayList<>();

        try {
            ArtistWs2 artis = artist.getComplete(artistMbid);
            List<ReleaseGroupWs2> releaseGroupWs2s = artis.getReleaseGroups();

            for (ReleaseGroupWs2 releaseWs2 : releaseGroupWs2s){
                    albums.add(parseWebSearch(releaseWs2));
            }
        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        return albums;
    }

    private Album parseWebSearch(ReleaseGroupWs2 releaseGroup){
        Album album = new Album();

        album.setTitle(releaseGroup.getTitle());
        album.setMbid(releaseGroup.getId());
        if(releaseGroup.getType() != null) {
            album.setType(releaseGroup.getTypeString());
        }

        // TODO: 04/12/2020 finde en hurtigere måde at load tracklisten ind på hvert album mangler stadig
        /*if(album.getType().toLowerCase().equals("album")) {
            album.setTracks(parseTrackList(releaseGroup));
        }*/

        if(releaseGroup.getFirstReleaseDate() != null) {
            album.setReleaseDate(releaseGroup.getFirstReleaseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }

        return album;
    }

    private List<Track> parseTrackList(ReleaseGroupWs2 releaseGroup){
        List<ReleaseWs2> releaseWs2 = releaseGroup.getReleases();
        if(!releaseWs2.isEmpty()) {
            int biggest = 0;
            int index = 0;
            for (int i = 0; i < releaseWs2.size(); i++) {
                if (releaseWs2.get(i).getTracksCount() > biggest) {
                    index = i;
                }
                ;
            }
            ;

            List<TrackWs2> trackList = null;
            if (!releaseWs2.isEmpty()) {
                trackList = releaseWs2.get(index).getMediumList().getCompleteTrackList();
            }

            List<Track> tracks = new ArrayList<>();
            for (TrackWs2 trackWeb : trackList) {
                Track track = new Track();
                track.setLength(trackWeb.getDuration());
                track.setName(trackWeb.getTitle());
                track.setId(trackWeb.getId());
            }
            return tracks;
        }
        return null;
    }
}
