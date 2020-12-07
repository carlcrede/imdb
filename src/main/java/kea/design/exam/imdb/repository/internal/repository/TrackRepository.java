package kea.design.exam.imdb.repository.internal.repository;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Track;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackRepository extends CrudRepository<Track, String> {
    @Override
    Optional<Track> findById(String id);

    List<Track> findTracksByAlbum(Album album);

    @Query(value = "SELECT * FROM track WHERE title LIKE %?1% LIMIT ?2", nativeQuery = true)
    List<Track> findAmountByTitle(String title, int amount);
}
