package kea.design.exam.imdb.repository.internal.service;

import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.repository.external.musicbrainz.MbArtist;
import kea.design.exam.imdb.repository.internal.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
        Artist artist = internalRepo.findById(id).orElseGet(() -> save(externalRepo.getById(id)));
        if (artist.getType() != null && artist.getType().toLowerCase().equals("group") && (artist.getBandMembers() == null || artist.getBandMembers().isEmpty())) {
            artist = externalRepo.getById(id);
            artist.getBandMembers().forEach(this::save);
            save(artist);
        }
        return artist;
    }

    @Override
    public List<Artist> findAmountByQuery(String query, int amount) {
        List<Artist> artists = internalRepo.findAmountByName(query, amount);
        if (artists.isEmpty() || artists.size() < amount) {
            artists = saveAll(externalRepo.findByQuery(query, amount));
        }
        return artists;
    }

    public List<Artist> saveAll(List<Artist> artists){
        List<Artist> newArtists = new ArrayList<>();
        internalRepo.saveAll(artists).forEach(newArtists::add);
        return newArtists;
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
