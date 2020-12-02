package kea.design.exam.imdb.controller;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.models.Track;
import kea.design.exam.imdb.repository.external.SpotifyRepository;
import kea.design.exam.imdb.repository.internal.service.AlbumService;
import kea.design.exam.imdb.repository.internal.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Controller
public class Home {
    SpotifyRepository spotifyRepo;
    @Autowired
    ArtistService artistService;
    @Autowired
    AlbumService albumService;

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

        model.addAttribute("artist", artistService.findByid(""));
        model.addAttribute("album", albumService.findAmountByQuery("", 10));
        return "artist";
    }

    @GetMapping("/album")
    public String album(Model model) {
        model.addAttribute("artist", artistService.findByid(" "));
        model.addAttribute("album", albumService.findAmountByQuery(" ", 10));
        return "album";
    }
}
