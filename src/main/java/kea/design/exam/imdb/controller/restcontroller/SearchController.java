package kea.design.exam.imdb.controller.restcontroller;

import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.repository.external.SpotifyRepository;
import kea.design.exam.imdb.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;


//Search controller til at hente fra jpa database
// Eller returnerer access token, så klient kan søge externt
@RestController
@RequestMapping("/search")
public class SearchController {
    ArtistService artistService;

    //søger database
    // -input: En query til at søge artist tabelen med
    // -output: Json Resultat der blev fundet
    @GetMapping("/artist")
    public ArrayList<Artist> search(String query){
        return artistService.findByQuery(query);
    }
}
