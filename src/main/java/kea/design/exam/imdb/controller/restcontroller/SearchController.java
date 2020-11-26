package kea.design.exam.imdb.controller.restcontroller;

import kea.design.exam.imdb.repository.external.SpotifyRepository;
import kea.design.exam.imdb.service.AlbumService;
import kea.design.exam.imdb.service.ArtistService;
import kea.design.exam.imdb.service.CrudService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


//Search controller til at hente fra jpa database
// Eller returnerer access token, så klient kan søge externt
@RestController
@RequestMapping("/search")
public class SearchController {
    SearchController(){
        SpotifyRepository spot = new SpotifyRepository("1d1caf5e2f0048abaaf3a5c6c6db18d9", "f0b2c475ad1f47aca16104715212a5b7");
    }


    //søger database
    // -input: En query til at søge artist tabelen med
    // -output: Json Resultat der blev fundet
    @GetMapping("")
    public ArrayList search(String query, String type){
        CrudService service;
        switch(type){
            case "artist":
                service = new ArtistService();
                break;
            case "album":
                service = new AlbumService();
                break;
            default:
                return null;
        }
        return service.findByQuery(query);
    }
}
