package kea.design.exam.imdb.controller.restcontroller;


import kea.design.exam.imdb.models.*;
import kea.design.exam.imdb.repository.internal.service.ArtistService;
import kea.design.exam.imdb.repository.internal.service.RatingService;
import kea.design.exam.imdb.repository.internal.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Double getAverageRatingByArtist(@RequestParam String mbid) {
        return ratingService.getAverageRatingByArtist(mbid);
    }

    @GetMapping("/save")
    public ResponseEntity<Object> sendRating(@RequestParam double rating, @RequestParam String mbid, @RequestParam String userName){
        MyUserDetails userDetails = (MyUserDetails) userService.loadUserByUsername(userName);
        User user = userDetails.getUser();
        List<Rating> ratingList = user.getRatings();
        for (Rating r : ratingList) {
            if (r.getArtist().getId().equals(mbid)) {
                r.setRating(rating);
                ratingService.save(r);
                return new ResponseEntity<>(ratingService.getAverageRatingByArtist(mbid), HttpStatus.BAD_REQUEST);
            }
        }
        Artist artist = artistService.findByid(mbid);
        Rating rating1 = new Rating();
        rating1.setUser(userDetails.getUser());
        rating1.setRating(rating);
        rating1.setArtist(artist);
        userDetails.getUser().getRatings().add(rating1);
        ratingService.save(rating1);
        userService.saveUserRating(userDetails.getUser());
        return ResponseEntity.ok(ratingService.getAverageRatingByArtist(mbid));
    }

}
