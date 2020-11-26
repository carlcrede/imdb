package kea.design.exam.imdb.repo;

import kea.design.exam.imdb.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, String> {
}
