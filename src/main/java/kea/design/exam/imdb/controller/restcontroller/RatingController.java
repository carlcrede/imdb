package kea.design.exam.imdb.controller.restcontroller;


import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.models.Rating;
import kea.design.exam.imdb.repository.internal.service.ArtistService;
import kea.design.exam.imdb.repository.internal.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingController {

    @Autowired
    ArtistService artistService;

    @Autowired
    RatingService ratingService;

    @GetMapping("/rating")
    public String sendRating(){
        return "artist";
    }


}
