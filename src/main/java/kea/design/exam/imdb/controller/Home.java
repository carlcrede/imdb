package kea.design.exam.imdb.controller;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.models.Track;
import kea.design.exam.imdb.repository.external.SpotifyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;

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
    public String artist(Model model) {
        Artist artist = new Artist("10", "Kanye West", LocalDate.of(2010, 04, 20), LocalDate.now(), "Person", "Male", "Chicago",null, "10");
        ArrayList<Album> albums = new ArrayList<>();
        ArrayList<Track> tracks = new ArrayList<Track>();
        tracks.add(new Track("3:40", "hey there"));
        tracks.add(new Track("10:20", "rapping raps"));

        albums.add(new Album("Watch The Throne", "album",tracks,null));
        albums.add(new Album("The Life of Pablo", "album", tracks, null));

        model.addAttribute("artist", artist);
        model.addAttribute("album", albums);
        return "artist";
    }


    //returns a new spotify accesstoken to a session
    @GetMapping("/spotify")
    @ResponseBody
    public String getToken(){
        return spotifyRepo.createToken();
    }
}
