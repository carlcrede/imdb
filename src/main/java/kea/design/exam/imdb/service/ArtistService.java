package kea.design.exam.imdb.service;

import kea.design.exam.imdb.repo.ArtistRepository;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

    private final ArtistRepository repository;

    public ArtistService(ArtistRepository repository) {
        this.repository = repository;
    }
}
