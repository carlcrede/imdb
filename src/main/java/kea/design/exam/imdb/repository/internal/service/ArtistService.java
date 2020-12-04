package kea.design.exam.imdb.repository.internal.service;

import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.repository.external.musicbrainz.MbArtist;
import kea.design.exam.imdb.repository.internal.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class ArtistService implements CrudService<Artist, String>{
    @Autowired
    MbArtist externalRepo;

    @Override
    public Artist findByid(String id) {
        return externalRepo.getById(id);
    }

    @Override
    public List<Artist> findAmountByQuery(String query, int amount) {
        return externalRepo.findByQuery(query, amount);
    }

    @Override
    public void save(Artist artist) {

    }

    @Override
    public void delete(Artist artist) {

    }


}
