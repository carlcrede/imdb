package kea.design.exam.imdb.repo;

import kea.design.exam.imdb.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, String> {
}
