package kea.design.exam.imdb.controller.restcontroller;

import kea.design.exam.imdb.models.*;
import kea.design.exam.imdb.repository.internal.service.ArtistService;
import kea.design.exam.imdb.repository.internal.service.FavoriteService;
import kea.design.exam.imdb.repository.internal.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserDetailsServiceImpl userService;
    @Autowired
    ArtistService artistService;
    @Autowired
    FavoriteService favoriteService;

    @GetMapping("/favorites/addArtist")
    public ResponseEntity<Object> addArtistToFavorites(@RequestParam String mbid, @RequestParam String userName) {
        User user = getUser(userName);
        Artist artist = artistService.findByid(mbid);
        // check if artist aleready is in favorites
        //TODO: delete from favorites if it already exists - would be neat.
        List<FavoriteArtist> favoriteArtistList = user.getFavoriteArtists();

        for (FavoriteArtist favorite : favoriteArtistList) {
            if (favorite.getArtist().equals(artist)) {
                favoriteArtistList.remove(favorite);
                favoriteService.delete(favorite);
                userService.saveUser(user);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        FavoriteArtist favorite = new FavoriteArtist();
        favorite.setUser(user);
        favorite.setArtist(artist);
        favoriteService.save(favorite);
        user.getFavoriteArtists().add(0, favorite);
        userService.saveUser(user);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    private User getUser(String userName) {
        MyUserDetails userDetails = (MyUserDetails) userService.loadUserByUsername(userName);
        return userDetails.getUser();
    }
}
