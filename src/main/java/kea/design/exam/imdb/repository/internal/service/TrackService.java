package kea.design.exam.imdb.repository.internal.service;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.models.Track;
import kea.design.exam.imdb.repository.external.musicbrainz.MbTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService implements CrudService<Track, String>{
    @Autowired
    MbTrack externalRepo;


    @Override
    public Track findByid(String id) {
        return externalRepo.getTrackById(id);
    }

    public List<Track> findAlbumTrackList(Album album){
        return externalRepo.getTracksForAlbum(album);
    }
    @Override
    public List<Track> findAmountByQuery(String query, int amount) {
        return null;
    }

    @Override
    public void save(Track track) {

    }

    @Override
    public void delete(Track track) {

    }
}
