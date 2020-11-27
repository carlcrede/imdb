package kea.design.exam.imdb.repository.internal;

import kea.design.exam.imdb.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, String> {
    @Query(value = "SELECT * FROM artist WHERE name LIKE %?1%", nativeQuery = true)
    ArrayList<Artist> findAllByName(String query);
}
