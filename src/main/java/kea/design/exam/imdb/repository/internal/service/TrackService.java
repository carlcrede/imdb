package kea.design.exam.imdb.repository.internal.service;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Track;
import kea.design.exam.imdb.repository.external.musicbrainz.MbTrack;
import kea.design.exam.imdb.repository.internal.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TrackService implements CrudService<Track, String>{
    @Autowired
    MbTrack externalRepo;
    @Autowired
    TrackRepository internalRepo;

    @Autowired
    AlbumService albumService;


    @Override
    public Track findByid(String id) {
        Optional<Track> track = internalRepo.findById(id);
        if(track.isPresent() && track.get().isCompleteInfo()){
            return track.get();
        }
        return save(externalRepo.getTrackById(id));
    }

    public Album addAlbumTrackList(Album album){
        List<Track> tracks = album.getTracks();
        if(tracks == null || tracks.isEmpty()){
            tracks = internalRepo.findTracksByAlbumOrderByPosition(album);
            if (tracks.isEmpty()) {
                tracks = saveAll(externalRepo.getTracksForAlbum(album));
            }
            album.setTracks(tracks);
            album = albumService.save(album);
        }
        return album;
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

    public List<Track> saveAll(List<Track> tracks){
        internalRepo.saveAll(tracks);
        return tracks;
    }

    @Override
    public void delete(Track track) {
        internalRepo.delete(track);
    }
}
