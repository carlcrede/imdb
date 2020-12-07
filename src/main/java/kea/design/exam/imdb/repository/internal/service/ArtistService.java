package kea.design.exam.imdb.repository.internal.service;

import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.repository.external.musicbrainz.MbArtist;
import kea.design.exam.imdb.repository.internal.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ArtistService implements CrudService<Artist, String>{
    @Autowired
    MbArtist externalRepo;
    @Autowired
    ArtistRepository internalRepo;

    @Override
    public Artist findByid(String id) {
        return internalRepo.findById(id).orElseGet(() -> save(externalRepo.getById(id)));
    }

    @Override
    public List<Artist> findAmountByQuery(String query, int amount) {
        internalRepo.findAmountByName(query, amount);
        return externalRepo.findByQuery(query, amount);
    }

    @Override
    public Artist save(Artist artist) {
        return internalRepo.save(artist);
    }

    @Override
    public void delete(Artist artist) {
        internalRepo.delete(artist);
    }


}
