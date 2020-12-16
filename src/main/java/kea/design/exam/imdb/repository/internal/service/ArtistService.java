package kea.design.exam.imdb.repository.internal.service;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.repository.external.musicbrainz.MbArtist;
import kea.design.exam.imdb.repository.internal.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


@Service
public class ArtistService implements CrudService<Artist, String>{
    @Autowired
    MbArtist externalRepo;
    @Autowired
    ArtistRepository internalRepo;

    @Override
    public Artist findByid(String id) {
        Optional<Artist> artist = internalRepo.findById(id);
        if(artist.isPresent() && artist.get().isCompleteInfo()){
            return artist.get();
        }
        Artist exArtist = externalRepo.getById(id);
        if(exArtist.getType().toLowerCase().equals("group")){
            exArtist.setBandMembers(externalRepo.getBands(exArtist));
            exArtist.getBandMembers().forEach(mem -> {
                Optional<Artist> opMem = internalRepo.findById(mem.getId());
                if(opMem.isEmpty()) {save(mem);}else{mem = opMem.get();}
            });
        }else if(exArtist.getType().toLowerCase().equals("person")){
            exArtist.setAssociatedBands(externalRepo.getBands(exArtist));
            exArtist.getAssociatedBands().forEach(mem -> {
                Optional<Artist> opMem = internalRepo.findById(mem.getId());
                if(opMem.isEmpty()) { save(mem); }else{mem = opMem.get();}
            });
        }
        return save(exArtist);
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
