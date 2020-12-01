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
import java.util.UUID;

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



        albums.add(new Album(UUID.fromString("76df3287-6cda-33eb-8e9a-044b5e15ffdd"), "Watch The Throne", "album",tracks,null));
        albums.add(new Album(UUID.fromString("03f03619-385a-4ed9-9974-cdcdf6404cf5"),"The Life of Pablo", "album", tracks, null));
        albums.add(new Album(UUID.fromString("76df3287-6cda-33eb-8e9a-044b5e15ffdd"),"Watch The Throne", "album",tracks,null));
        albums.add(new Album(UUID.fromString("76df3287-6cda-33eb-8e9a-044b5e15ffdd"),"The Life of Pablo", "album", tracks, null));
        albums.add(new Album(UUID.fromString("76df3287-6cda-33eb-8e9a-044b5e15ffdd"),"Watch The Throne", "album",tracks,null));
        albums.add(new Album(UUID.fromString("76df3287-6cda-33eb-8e9a-044b5e15ffdd"),"The Life of Pablo", "album", tracks, null));
        albums.add(new Album(UUID.fromString("76df3287-6cda-33eb-8e9a-044b5e15ffdd"),"Watch The Throne", "album",tracks,null));
        albums.add(new Album(UUID.fromString("76df3287-6cda-33eb-8e9a-044b5e15ffdd"),"The Life of Pablo", "album", tracks, null));
        albums.add(new Album(UUID.fromString("76df3287-6cda-33eb-8e9a-044b5e15ffdd"),"Watch The Throne", "album",tracks,null));
        albums.add(new Album(UUID.fromString("76df3287-6cda-33eb-8e9a-044b5e15ffdd"),"The Life of Pablo", "album", tracks, null));

        model.addAttribute("artist", artist);
        model.addAttribute("album", albums);
        return "artist";
    }

    @GetMapping("/album")
    public String album(Model model) {
        Artist artist = new Artist("10", "Kanye West", LocalDate.of(2010, 04, 20), LocalDate.now(), "Person", "Male", "Chicago",null, "10");
        ArrayList<Album> albums = new ArrayList<>();
        ArrayList<Track> tracks = new ArrayList<Track>();
        tracks.add(new Track("3:40", "hey there"));
        tracks.add(new Track("10:20", "rapping raps"));

        albums.add(new Album(UUID.fromString("76df3287-6cda-33eb-8e9a-044b5e15ffdd"), "Watch The Throne", "album",tracks,null));

        model.addAttribute("artist", artist);
        model.addAttribute("album", albums);
        return "album";
    }
}
