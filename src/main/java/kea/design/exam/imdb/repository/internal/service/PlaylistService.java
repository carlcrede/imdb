package kea.design.exam.imdb.repository.internal.service;

import kea.design.exam.imdb.models.Playlist;
import kea.design.exam.imdb.repository.internal.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService implements CrudService<Playlist, Long>{

    @Autowired
    PlaylistRepository playlistRepository;


    @Override
    public Playlist findByid(Long aLong) {
        return null;
    }

    @Override
    public List<Playlist> findAmountByQuery(String query, int amount) {
        return null;
    }

    @Override
    public Playlist save(Playlist playlist) {
        return null;
    }

    @Override
    public void delete(Playlist playlist) {

    }
}
