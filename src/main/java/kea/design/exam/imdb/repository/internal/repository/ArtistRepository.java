package kea.design.exam.imdb.repository.internal.repository;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, String>{
    @Override
    Optional<Artist> findById(String id);

    @Query(value = "SELECT * FROM artist WHERE name LIKE %?1% LIMIT ?2", nativeQuery = true)
    List<Artist> findAmountByName(String name, int amount);
}
