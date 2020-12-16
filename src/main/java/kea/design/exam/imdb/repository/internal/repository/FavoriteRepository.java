package kea.design.exam.imdb.repository.internal.repository;


import kea.design.exam.imdb.models.Favorite.Favorite;
import kea.design.exam.imdb.models.Favorite.FavoriteAlbum;
import kea.design.exam.imdb.models.Favorite.FavoriteArtist;
import kea.design.exam.imdb.models.Favorite.FavoriteTrack;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

    @Query(value = "SELECT * FROM favorite WHERE (user_user_id = ?1 AND favorite_type = 'Artist')", nativeQuery = true)
    List<FavoriteArtist> getFavoriteArtistsByUserId(Long id);

    @Query(value = "SELECT * FROM favorite WHERE (user_user_id = ?1 AND favorite_type = 'Album')", nativeQuery = true)
    List<FavoriteAlbum> getFavoriteAlbumsByUserId(Long id);

    @Query(value = "SELECT * FROM favorite WHERE (user_user_id = ?1 AND favorite_type = 'Track')", nativeQuery = true)
    List<FavoriteTrack> getFavoriteTracksByUserId(Long id);
}
