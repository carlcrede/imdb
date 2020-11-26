package kea.design.exam.imdb.service;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.repository.internal.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AlbumService implements CrudService<Album, String>{

    @Autowired
    private AlbumRepository repository;

    @Override
    public void save(Album album) {

    }

    @Override
    public void delete(Album album) {

    }

    @Override
    public ArrayList<Album> findByQuery(String query) {
        return null;
    }

    @Override
    public Album findById(String id) {
        return null;
    }
}
