package kea.design.exam.imdb.repository.internal.service;

import kea.design.exam.imdb.models.Favorite;
import kea.design.exam.imdb.repository.internal.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService implements CrudService<Favorite, Long> {
    @Autowired
    FavoriteRepository favoriteRepository;

    @Override
    public Favorite findByid(Long aLong) {
        return null;
    }

    @Override
    public List<Favorite> findAmountByQuery(String query, int amount) {
        return null;
    }

    @Override
    public Favorite save(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public void delete(Favorite favorite) {
        favoriteRepository.delete(favorite);
    }
}
