package kea.design.exam.imdb.repository.internal.repository;

import kea.design.exam.imdb.models.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingRepository extends CrudRepository<Rating, Long> {

    @Query(value = "SELECT SUM(rating) / COUNT(rating_id) FROM ratings WHERE artist_id = ?1", nativeQuery = true)
    Double getAverageRatingByArtist(String mbid);

    @Query(value = "select * from ratings group by artist_id order by sum(rating)/COUNT(rating) DESC LIMIT 5", nativeQuery = true)
    List<Rating> getTopRatedArtists();
}
