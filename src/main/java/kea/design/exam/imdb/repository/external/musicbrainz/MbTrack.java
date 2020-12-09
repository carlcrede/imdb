package kea.design.exam.imdb.repository.external.musicbrainz;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Track;
import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.controller.Recording;
import org.musicbrainz.controller.Release;
import org.musicbrainz.controller.ReleaseGroup;
import org.musicbrainz.model.TrackWs2;
import org.musicbrainz.model.entity.RecordingWs2;
import org.musicbrainz.model.entity.ReleaseWs2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MbTrack {
    public Track getTrackById(String id){
        Recording recording = new Recording();
        try {
            return parseRecording(recording.lookUp(id));
        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Track> getTracksForAlbum(Album album){
        try {
            ReleaseGroup releaseGroup = new ReleaseGroup();
            releaseGroup.getReleaseIncludes().excludeAll();
            releaseGroup.getReleaseIncludes().setMedia(true);
            releaseGroup.lookUp(album.getMbid());

            //finds the release with the most tracks and makes it the primary release
            List<ReleaseWs2> releaseList = releaseGroup.getFullReleaseList();

            int biggestSize = 0;
            int index = 0;
            for (int i = 0; i < releaseList.size(); i++) {
                if(releaseList.get(i).getTracksCount() > biggestSize){
                    index = i;
                    biggestSize = releaseList.get(i).getTracksCount();
                }
            }
            List<Track> tracks = new ArrayList<>();

            Release release = new Release();
            release.getIncludes().excludeAll();
            release.getIncludes().setRecordings(false);
            release.getIncludes().setMedia(true);
            ReleaseWs2 fullAlbum = release.lookUp(releaseList.get(index));


            List<TrackWs2> websearch = fullAlbum.getMediumList().getCompleteTrackList();
            for (TrackWs2 webTrack: websearch) {
                tracks.add(parseTrack(webTrack, album));
            }
            return tracks;
        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Track parseRecording(RecordingWs2 recording){
        Track track = new Track();

        System.out.println(recording.getIsrcString());
        track.setFeatures(recording.getArtistCredit().getNameCredits().toString());
        track.setId(recording.getId());
        track.setName(recording.getTitle());
        track.setLength(recording.getDuration());

        return track;
    }

    private Track parseTrack(TrackWs2 webTrack, Album album){
        Track track = new Track();

        track.setAlbum(album);

        track.setPosition(webTrack.getPosition());
        track.setId(webTrack.getRecording().getId());
        track.setName(webTrack.getRecording().getTitle());
        track.setLength(webTrack.getDuration());
        return track;
    }
}
