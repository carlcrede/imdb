package kea.design.exam.imdb.controller;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.models.User;
import kea.design.exam.imdb.repository.external.SpotifyRepository;
import kea.design.exam.imdb.repository.external.musicbrainz.MbArtist;
import kea.design.exam.imdb.repository.internal.service.AlbumService;
import kea.design.exam.imdb.repository.internal.service.ArtistService;
import kea.design.exam.imdb.repository.internal.service.TrackService;
import kea.design.exam.imdb.repository.internal.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sound.midi.Track;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@Controller
public class Home {
    SpotifyRepository spotifyRepo;
    @Autowired
    ArtistService artistService;
    @Autowired
    AlbumService albumService;
    @Autowired
    UserDetailsServiceImpl userService;
    @Autowired
    TrackService trackService;

    Home() {
        spotifyRepo = new SpotifyRepository("1d1caf5e2f0048abaaf3a5c6c6db18d9", "f0b2c475ad1f47aca16104715212a5b7");
    }
//TODO: add footer
    //frontpage
    @GetMapping("")
    public String index(){
        return "index";
    }

    @GetMapping("/artist")
    public String artist(@RequestParam String id, Model model) {
        Artist artist = artistService.findByid(id);
        model.addAttribute("artist", artist);
        model.addAttribute("album", albumService.findAlbumTypeByArtist(artist, "album"));
        return "artist";
    }

    @GetMapping("/album")
    public String album(Model model, @RequestParam String albumId, @RequestParam String artistId) {
        Album album = albumService.findByid(albumId);
        album = trackService.addAlbumTrackList(album);
        Collections.sort(album.getTracks());
        
        model.addAttribute("artist", artistService.findByid(artistId));
        model.addAttribute("album", album);
        return "album";
    }

    @GetMapping("/profile")
    public String userprofile() {
        return "/userprofile";
    }

    @GetMapping("/news")
    public String newsSite(Model model, @RequestParam(required = false) String artist){
        model.addAttribute("artistName", artist);
        return "/news";
    }

    @GetMapping("/concerts")
    public String concerts() {
        return "/concerts";
    }

    @GetMapping("/concerts/artist")
    public String concertsForArtist(@RequestParam String artist, Model model) {
        model.addAttribute("artist", artist);
        return "/concerts";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.signUpUser(user);
        return "redirect:/login";
    }
}
