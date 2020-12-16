package kea.design.exam.imdb.controller.restcontroller;


import kea.design.exam.imdb.models.*;
import kea.design.exam.imdb.models.Rating.AlbumRating;
import kea.design.exam.imdb.models.Rating.ArtistRating;
import kea.design.exam.imdb.models.User.MyUserDetails;
import kea.design.exam.imdb.models.User.User;
import kea.design.exam.imdb.repository.internal.service.AlbumService;
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
    @Autowired
    AlbumService albumService;

    @GetMapping("/getRatingsByAlbum")
    public Double getAverageRatingByAlbum(@RequestParam String mbid) {
        return ratingService.getAverageRatingByAlbum(mbid);
    }

    @GetMapping("/getRatingsByArtist")
    public Double getAverageRatingByArtist(@RequestParam String mbid) {
        return ratingService.getAverageRatingByArtist(mbid);
    }

    @GetMapping("/rateArtist")
    public ResponseEntity<Object> rateArtist(@RequestParam double rating, @RequestParam String mbid, @RequestParam String userName){
        User user = getUser(userName);
        List<ArtistRating> artistRatings = user.getArtistRatings();
        for (ArtistRating r : artistRatings) {
            if (r.getArtist().getId().equals(mbid)) {
                r.setRating(rating);
                ratingService.save(r);
                return new ResponseEntity<>(ratingService.getAverageRatingByArtist(mbid), HttpStatus.BAD_REQUEST);
            }
        }
        Artist artist = artistService.findByid(mbid);
        ArtistRating rating1 = new ArtistRating();
        rating1.setUser(user);
        rating1.setRating(rating);
        rating1.setArtist(artist);
        user.getArtistRatings().add(rating1);
        ratingService.save(rating1);
        userService.saveUserRating(user);
        return ResponseEntity.ok(ratingService.getAverageRatingByArtist(mbid));
    }

    @GetMapping("/rateAlbum")
    public ResponseEntity<Object> rateAlbum(@RequestParam double rating, @RequestParam String mbid, @RequestParam String userName){
        User user = getUser(userName);
        List<AlbumRating> albumRatings = user.getAlbumRatings();
        for (AlbumRating r : albumRatings) {
            if (r.getAlbum().getMbid().equals(mbid)) {
                r.setRating(rating);
                ratingService.save(r);
                return new ResponseEntity<>(ratingService.getAverageRatingByAlbum(mbid), HttpStatus.BAD_REQUEST);
            }
        }
        Album album = albumService.findByid(mbid);
        AlbumRating albumRating = new AlbumRating();
        albumRating.setUser(user);
        albumRating.setRating(rating);
        albumRating.setAlbum(album);
        user.getAlbumRatings().add(albumRating);
        ratingService.save(albumRating);
        userService.saveUserRating(user);;
        return ResponseEntity.ok(ratingService.getAverageRatingByAlbum(mbid));
    }

    private User getUser(String userName) {
        MyUserDetails userDetails = (MyUserDetails) userService.loadUserByUsername(userName);
        return userDetails.getUser();
    }

}
