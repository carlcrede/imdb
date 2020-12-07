package kea.design.exam.imdb.repository.internal.repository;

import kea.design.exam.imdb.models.Rating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Long> {
}
