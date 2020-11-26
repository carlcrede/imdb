package kea.design.exam.imdb.controller;

import kea.design.exam.imdb.repository.external.SpotifyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Home {
    SpotifyRepository spotifyRepo;

    Home() {
        spotifyRepo = new SpotifyRepository("1d1caf5e2f0048abaaf3a5c6c6db18d9", "f0b2c475ad1f47aca16104715212a5b7");
    }

    //frontpage
    @GetMapping("")
    public String index(){
        return "index";
    }

    @GetMapping("/artist")
    public String artist() {
        return "artist";
    }


    //returns a new spotify accesstoken to a session
    @GetMapping("/spotify")
    @ResponseBody
    public String getToken(){
        return spotifyRepo.createToken();
    }
}
