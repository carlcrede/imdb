package kea.design.exam.imdb.repository.internal.repository;

import kea.design.exam.imdb.models.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Long> {

    @Query(value = "SELECT SUM(rating) / COUNT(rating_id) FROM ratings WHERE artist_id = ?1", nativeQuery = true)
    Double getRatingsByArtist(String mbid);
}
