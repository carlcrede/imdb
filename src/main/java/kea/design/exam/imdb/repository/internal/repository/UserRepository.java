package kea.design.exam.imdb.repository.internal.repository;

import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.models.Favorite;
import kea.design.exam.imdb.models.FavoriteArtist;
import kea.design.exam.imdb.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM favorite WHERE (user_user_id = ?1 AND favorite_type = 'Artist')", nativeQuery = true)
    List<FavoriteArtist> getFavoriteArtistsByUserId(Long id);

}

