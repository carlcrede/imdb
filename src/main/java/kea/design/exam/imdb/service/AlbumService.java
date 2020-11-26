package kea.design.exam.imdb.service;

import kea.design.exam.imdb.repo.AlbumRepository;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    private final AlbumRepository repository;

    public AlbumService(AlbumRepository repository) {
        this.repository = repository;
    }
}
