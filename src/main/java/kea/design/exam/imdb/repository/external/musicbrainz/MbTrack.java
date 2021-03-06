package kea.design.exam.imdb.repository.external.musicbrainz;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Track;
import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.controller.Recording;
import org.musicbrainz.controller.ReleaseGroup;
import org.musicbrainz.model.NameCreditWs2;
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
            Track track = parseRecording(recording.getComplete(id), null);
            track.setCompleteInfo(true);
            return track;
        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Track> getTracksForAlbum(Album album){
        try {
            ReleaseGroup releaseGroup = new ReleaseGroup();
            releaseGroup.getReleaseIncludes().excludeAll();
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
            recSearch.getIncludes().excludeAll();

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

        List<NameCreditWs2> features = recording.getArtistCredit().getNameCredits();
        StringBuilder credits = new StringBuilder();
        if(!features.isEmpty()) {
            credits = new StringBuilder(features.get(0).getArtistName());
            for (int i = 1; i < features.size(); i++) {
                credits.append(", "+ features.get(i).getArtistName());
            }
        }

        track.setFeatures(credits.toString());
        track.setAlbum(album);
        track.setIsrc(recording.getIsrcString());
        track.setId(recording.getId());
        track.setName(recording.getUniqueTitle());
        track.setLength(recording.getDuration());

        return track;
    }
}
