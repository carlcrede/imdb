package kea.design.exam.imdb.repository.internal.repository;

import kea.design.exam.imdb.models.AbstractRating;
import kea.design.exam.imdb.models.AlbumRating;
import kea.design.exam.imdb.models.ArtistRating;
import kea.design.exam.imdb.models.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingRepository extends CrudRepository<AbstractRating, Long> {

    @Query(value = "SELECT SUM(rating) / COUNT(id) FROM abstract_rating WHERE album_mbid = ?1", nativeQuery = true)
    Double getAverageRatingByAlbum(String mbid);

    @Query(value = "SELECT SUM(rating) / COUNT(id) FROM abstract_rating WHERE artist_id = ?1", nativeQuery = true)
    Double getAverageRatingByArtist(String mbid);

    @Query(value = "select * from abstract_rating where rating_type = 'Artist' order by sum(rating)/COUNT(rating) DESC LIMIT 5", nativeQuery = true)
    List<ArtistRating> getTopRatedArtists();

    @Query(value = "select * from abstract_rating where rating_type = 'Album' order by sum(rating)/COUNT(rating) DESC LIMIT 5", nativeQuery = true)
    List<AlbumRating> getTopRatedAlbums();
}
