package kea.design.exam.imdb.repository.internal.service;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Track;
import kea.design.exam.imdb.repository.external.musicbrainz.MbAlbum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AlbumService implements CrudService<Album, String> {
    @Autowired
    MbAlbum externalRepo;

    @Override
    public Album findByid(String s) {
        return externalRepo.getById(s);
    }


    @Override
    public List<Album> findAmountByQuery(String query, int amount) {
        return externalRepo.findByQuery(query, amount);
    }

    @Override
    public void save(Album album) {

    }

    @Override
    public void delete(Album album) {

    }
}
