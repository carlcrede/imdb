package kea.design.exam.imdb.repository.external.musicbrainz;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Track;
import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.controller.Disc;
import org.musicbrainz.controller.Recording;
import org.musicbrainz.controller.Release;
import org.musicbrainz.controller.ReleaseGroup;
import org.musicbrainz.discid.DiscIdException;
import org.musicbrainz.discid.DiscInfo;
import org.musicbrainz.model.MediumListWs2;
import org.musicbrainz.model.MediumWs2;
import org.musicbrainz.model.TrackListWs2;
import org.musicbrainz.model.TrackWs2;
import org.musicbrainz.model.entity.DiscWs2;
import org.musicbrainz.model.entity.ReleaseWs2;
import org.musicbrainz.model.entity.listelement.DiscListWs2;
import org.musicbrainz.model.entity.listelement.ReleaseListWs2;

import java.util.ArrayList;
import java.util.List;

public class MbTrack {
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
            release.getIncludes().setRecordings(true);
            release.getIncludes().setMedia(true);
            ReleaseWs2 fullAlbum = release.lookUp(releaseList.get(index));
            List<TrackWs2> websearch = fullAlbum.getMediumList().getCompleteTrackList();
            for (TrackWs2 webTrack: websearch) {
                tracks.add(parseWebsearch(webTrack));
            }
            return tracks;
        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Track parseWebsearch(TrackWs2 webTrack){
        Track track = new Track();
        track.setId(webTrack.getRecording().getId());
        track.setName(webTrack.getRecording().getTitle());
        track.setLength(webTrack.getDuration());
        return track;
    }
}
