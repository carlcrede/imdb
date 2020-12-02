package kea.design.exam.imdb.repository.internal.service;

import kea.design.exam.imdb.models.Artist;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistService implements CrudService<Artist, String>{
    @Override
    public Artist findByid(String s) {
        return new Artist("10", "Kanye West", LocalDate.of(2010, 04, 20), LocalDate.now(), "Person", "Male", "Chicago",null, "10");
    }

    @Override
    public List<Artist> findAmountByQuery(String query, int amount) {
        ArrayList<Artist> artists = new ArrayList<>();
        artists.add(new Artist("10", "Kanye West", LocalDate.of(2010, 04, 20), LocalDate.now(), "Person", "Male", "Chicago",null, "10"));
        artists.add(new Artist("10", "Kanye West", LocalDate.of(2010, 04, 20), LocalDate.now(), "Person", "Male", "Chicago",null, "10"));
        return artists;
    }

    @Override
    public List<Artist> findByQuery(String query) {
        return null;
    }
}
