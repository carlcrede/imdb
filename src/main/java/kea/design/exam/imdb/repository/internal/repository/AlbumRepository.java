package kea.design.exam.imdb.repository.internal.repository;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends CrudRepository<Album, String>{

    @Override
    Optional<Album> findById(String id);

    List<Album> findAlbumsByArtist(Artist artist);
    List<Album> findAlbumsByArtistAndType(Artist artist, String type);
    @Query(value = "SELECT * FROM album WHERE title LIKE %?1% LIMIT ?2", nativeQuery = true)
    List<Album> findAmountByTitle(String title, int amount);
}
