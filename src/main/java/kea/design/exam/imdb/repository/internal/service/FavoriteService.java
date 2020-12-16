package kea.design.exam.imdb.repository.internal.service;

import kea.design.exam.imdb.models.Favorite.Favorite;
import kea.design.exam.imdb.models.Favorite.FavoriteAlbum;
import kea.design.exam.imdb.models.Favorite.FavoriteArtist;
import kea.design.exam.imdb.models.Favorite.FavoriteTrack;
import kea.design.exam.imdb.repository.internal.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService implements CrudService<Favorite, Long> {
    @Autowired
    FavoriteRepository favoriteRepository;

    public List<FavoriteArtist> getFavoriteArtists(Long id) {
        return favoriteRepository.getFavoriteArtistsByUserId(id);
    }

    public List<FavoriteAlbum> getFavoriteAlbums(Long id) {
        return favoriteRepository.getFavoriteAlbumsByUserId(id);
    }

    public List<FavoriteTrack> getFavoriteTracks(Long id) {
        return favoriteRepository.getFavoriteTracksByUserId(id);
    }

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
