package kea.design.exam.imdb.repository.internal.service;


import kea.design.exam.imdb.models.AbstractRating;
import kea.design.exam.imdb.models.ArtistRating;
import kea.design.exam.imdb.models.Rating;
import kea.design.exam.imdb.repository.internal.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingService implements CrudService<AbstractRating, Integer> {

    @Autowired
    RatingRepository ratingRepository;

    public Double getAverageRatingByAlbum(String mbid) {
        return ratingRepository.getAverageRatingByAlbum(mbid);
    }

    public List<ArtistRating> getTopRatedArtists() {
        return ratingRepository.getTopRatedArtists();
    }

    public Double getAverageRatingByArtist(String mbid) {
        return ratingRepository.getAverageRatingByArtist(mbid);
    }

    @Override
    public AbstractRating findByid(Integer integer) {
        return null;
    }

    @Override
    public List<AbstractRating> findAmountByQuery(String query, int amount) {
        return null;
    }

    @Override
    public AbstractRating save(AbstractRating abstractRating) {
        ratingRepository.save(abstractRating);
        return abstractRating;
    }

    @Override
    public void delete(AbstractRating abstractRating) {

    }
}
