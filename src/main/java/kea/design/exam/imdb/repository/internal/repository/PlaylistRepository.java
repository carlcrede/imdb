package kea.design.exam.imdb.repository.internal.repository;

import kea.design.exam.imdb.models.Favorite.FavoriteArtist;
import kea.design.exam.imdb.models.Playlist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaylistRepository extends CrudRepository<Playlist, Long> {

    @Query(value = "SELECT * FROM playlist WHERE user_user_id = ?", nativeQuery = true)
    List<Playlist> getAllPlaylistsByUserId(Long id);

}
