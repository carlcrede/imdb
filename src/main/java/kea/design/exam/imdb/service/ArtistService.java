package kea.design.exam.imdb.service;

import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.repository.internal.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ArtistService implements CrudService<Artist, String> {

    @Autowired
    private ArtistRepository repository;

    @Override
    public void save(Artist artist) {

    }

    @Override
    public void delete(Artist artist) {

    }

    @Override
    public ArrayList<Artist> findByQuery(String query) {
        return null;
    }

    @Override
    public Artist findById(String id) {
        return null;
    }
}
