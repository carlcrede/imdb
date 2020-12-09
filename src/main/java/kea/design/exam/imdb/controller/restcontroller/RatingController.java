package kea.design.exam.imdb.controller.restcontroller;


import kea.design.exam.imdb.models.AjaxRequest;
import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.models.Rating;
import kea.design.exam.imdb.models.User;
import kea.design.exam.imdb.repository.internal.service.ArtistService;
import kea.design.exam.imdb.repository.internal.service.RatingService;
import kea.design.exam.imdb.repository.internal.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    ArtistService artistService;
    @Autowired
    UserDetailsServiceImpl userService;
    @Autowired
    RatingService ratingService;

    /*@GetMapping("/save")
    public ResponseEntity<String> rate(@RequestParam int rating) {
        System.out.println("Controller: rating = " + rating);
        return ResponseEntity.ok("Well done");
    }*/

    @GetMapping("/getRatingsByArtist")
    public Double getRatingsByArtist(@RequestParam String mbid) {
        return ratingService.getRatingsByArtist(mbid);
    }

    @GetMapping("/save")
    public Double sendRating(@RequestParam int rating, @RequestParam String mbid){
        System.out.println("We're in the method now");
        //ratingService.save(rating);
        System.out.println(rating + ", " + mbid);
        // find user, find artist
        // ny rating
        Artist artist = artistService.findByid(mbid);
        Rating rating1 = new Rating();
        rating1.setRating(rating);
        rating1.setArtist(artist);

        ratingService.save(rating1);
        //User user = (User) userService.loadUserByUsername(username);
        /*Rating rating1 = new Rating();
        rating1.setRating(rating);
        rating1.setUser(user);
        rating1.setArtist(artist);*/
        return ratingService.getRatingsByArtist(mbid);
    }

}
