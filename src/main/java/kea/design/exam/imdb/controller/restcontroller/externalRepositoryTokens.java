package kea.design.exam.imdb.controller.restcontroller;

import kea.design.exam.imdb.repository.external.SpotifyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//responsible for creating tokens for use by the client's to access external data
@RestController
public class externalRepositoryTokens {
    SpotifyRepository spotRepo;

    public externalRepositoryTokens(){
        spotRepo = new SpotifyRepository("1d1caf5e2f0048abaaf3a5c6c6db18d9", "f0b2c475ad1f47aca16104715212a5b7");
    }

    @GetMapping("/spotify")
    public String spotify(){
        return spotRepo.createToken();
    }
}
