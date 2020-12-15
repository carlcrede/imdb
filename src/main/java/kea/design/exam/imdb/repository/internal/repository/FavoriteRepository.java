package kea.design.exam.imdb.repository.internal.repository;


import kea.design.exam.imdb.models.Favorite;
import org.springframework.data.repository.CrudRepository;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {
}
