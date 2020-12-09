package kea.design.exam.imdb.repository.internal.service;


import kea.design.exam.imdb.models.Rating;
import kea.design.exam.imdb.repository.internal.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingService implements CrudService<Rating, Integer> {

    @Autowired
    RatingRepository ratingRepository;

    public Double getRatingsByArtist(String mbid) {
        return ratingRepository.getRatingsByArtist(mbid);
    }

    @Override
    public Rating findByid(Integer integer) {
        return null;
    }

    @Override
    public List<Rating> findAmountByQuery(String query, int amount) {
        return null;
    }

    @Override
    public Rating save(Rating rating) {
        ratingRepository.save(rating);
        return rating;
    }

    @Override
    public void delete(Rating rating) {

    }
}
