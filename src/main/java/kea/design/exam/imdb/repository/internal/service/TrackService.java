package kea.design.exam.imdb.repository.internal.service;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.models.Track;
import kea.design.exam.imdb.repository.external.musicbrainz.MbTrack;
import kea.design.exam.imdb.repository.internal.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackService implements CrudService<Track, String>{
    @Autowired
    MbTrack externalRepo;
    @Autowired
    TrackRepository internalRepo;


    @Override
    public Track findByid(String id) {
        return internalRepo.findById(id).orElseGet(() -> save(externalRepo.getTrackById(id)));
    }

    public List<Track> findAlbumTrackList(Album album){
        internalRepo.findTracksByAlbum(album);
        return externalRepo.getTracksForAlbum(album);
    }
    @Override
    public List<Track> findAmountByQuery(String query, int amount) {
        internalRepo.findAmountByTitle(query, amount);
        return null;
    }

    @Override
    public Track save(Track track) {
        return internalRepo.save(track);
    }

    @Override
    public void delete(Track track) {
        internalRepo.delete(track);
    }
}
