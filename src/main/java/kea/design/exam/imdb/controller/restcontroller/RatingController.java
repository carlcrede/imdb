package kea.design.exam.imdb.controller.restcontroller;


import kea.design.exam.imdb.models.*;
import kea.design.exam.imdb.repository.internal.service.ArtistService;
import kea.design.exam.imdb.repository.internal.service.RatingService;
import kea.design.exam.imdb.repository.internal.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;

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
    public ResponseEntity<Object> sendRating(@RequestParam int rating, @RequestParam String mbid, @RequestParam String userName){
        MyUserDetails userDetails = (MyUserDetails) userService.loadUserByUsername(userName);
        User user = userDetails.getUser();
        List<Rating> ratingList = user.getRatings();
        for (Rating r : ratingList) {
            if (r.getArtist().getId().equals(mbid)) {
                r.setRating(rating);
                ratingService.save(r);
                return new ResponseEntity<>(ratingService.getRatingsByArtist(mbid), HttpStatus.BAD_REQUEST);
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
        return ResponseEntity.ok(ratingService.getRatingsByArtist(mbid));
    }

}
