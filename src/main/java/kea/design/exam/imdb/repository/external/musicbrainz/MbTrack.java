package kea.design.exam.imdb.repository.external.musicbrainz;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Track;
import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.controller.Recording;
import org.musicbrainz.controller.ReleaseGroup;
import org.musicbrainz.model.entity.RecordingWs2;
import org.musicbrainz.model.entity.ReleaseWs2;
import org.musicbrainz.model.searchresult.RecordingResultWs2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MbTrack {
    public Track getTrackById(String id){
        Recording recording = new Recording();
        try {
            return parseRecording(recording.lookUp(id), null);
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
            String releaseId = releaseList.get(index).getId();

            Recording recSearch = new Recording();
            recSearch.search("reid:"+releaseId);
            List<RecordingResultWs2> recordings = recSearch.getFirstSearchResultPage();

            for(int i = 0; i < recordings.size(); i++){
                RecordingWs2 recording = recordings.get(i).getRecording();
                tracks.add(parseRecording(recording, album));
                tracks.get(i).setPosition(i+1);
            }
            return tracks;
        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Track parseRecording(RecordingWs2 recording, Album album){
        Track track = new Track();

        recording.getRelationList().getRelations().forEach(System.out::println);

        //recording.getArtistCredit().getNameCredits();
        //track.setFeatures();
        track.setAlbum(album);
        track.setIsrc(recording.getIsrcString());
        track.setId(recording.getId());
        track.setName(recording.getTitle());
        track.setLength(recording.getDuration());

        return track;
    }
}
