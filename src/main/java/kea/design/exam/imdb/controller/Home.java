package kea.design.exam.imdb.controller;

import kea.design.exam.imdb.models.*;
import kea.design.exam.imdb.models.User.MyUserDetails;
import kea.design.exam.imdb.models.User.User;
import kea.design.exam.imdb.repository.external.SpotifyRepository;
import kea.design.exam.imdb.repository.internal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.util.Collections;

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
    @Autowired
    RatingService ratingService;
    @Autowired
    PlaylistService playlistService;
    @Autowired
    FavoriteService favoriteService;


    Home() {
        spotifyRepo = new SpotifyRepository("1d1caf5e2f0048abaaf3a5c6c6db18d9", "f0b2c475ad1f47aca16104715212a5b7");
    }
//TODO: add footer
    //frontpage
    @GetMapping("")
    public String index(Model model){
        model.addAttribute("topArtists", ratingService.getTopRatedArtists());
        model.addAttribute("topAlbums", ratingService.getTopRatedAlbums());
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
    public String album(Model model, @RequestParam String id) {
        Album album = albumService.findByid(id);
        album = trackService.addAlbumTrackList(album);
        if(!album.getTracks().isEmpty()){Collections.sort(album.getTracks());}

        model.addAttribute("album", album);
        return "album";
    }

    @GetMapping("/profile")
    public String userprofile(Model model, Authentication auth) {
        MyUserDetails userDetails = (MyUserDetails) userService.loadUserByUsername(auth.getName());
        User user = userDetails.getUser();
        model.addAttribute("favoriteArtists", favoriteService.getFavoriteArtists(user.getId()));
        model.addAttribute("favoriteAlbums", favoriteService.getFavoriteAlbums(user.getId()));
        //TODO: add users ratings maybe?
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

    @PostMapping("/playlist")
    public String makePlaylist(Playlist playlist){
//        playlistService.save(playlist);
        System.out.println("Mapping virker");
        return "redirect:/profile";
    }



}
