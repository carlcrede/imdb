package kea.design.exam.imdb.controller.restcontroller;

import kea.design.exam.imdb.models.*;
import kea.design.exam.imdb.models.Favorite.FavoriteArtist;
import kea.design.exam.imdb.models.User.MyUserDetails;
import kea.design.exam.imdb.models.User.User;
import kea.design.exam.imdb.repository.internal.service.ArtistService;
import kea.design.exam.imdb.repository.internal.service.FavoriteService;
import kea.design.exam.imdb.repository.internal.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserDetailsServiceImpl userService;
    @Autowired
    ArtistService artistService;
    @Autowired
    FavoriteService favoriteService;

    private ResponseEntity<Object> addOrRemoveAlbumFromFavorites(String mbid, User user) {
        return null;
    }

    private ResponseEntity<Object> addOrRemoveTrackFromFavorites(String mbid, User user) {
        return null;
    }

    private ResponseEntity<Object> addOrRemoveArtistFromFavorites(String mbid, User user) {
        Artist artist = artistService.findByid(mbid);
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

    @GetMapping("/favorites/addArtist")
    public ResponseEntity<Object> addArtistToFavorites(@RequestParam String mbid, @RequestParam String userName, @RequestParam String type) {
        ResponseEntity<Object> responseEntity = ResponseEntity.of(Optional.empty());
        User user = getUser(userName);
        switch (type) {
            case "artist":
                responseEntity = addOrRemoveArtistFromFavorites(mbid, user);
                break;
            case "album":
                responseEntity = addOrRemoveAlbumFromFavorites(mbid, user);
                break;
            case "track":
                responseEntity = addOrRemoveTrackFromFavorites(mbid, user);
                break;
        }
        return responseEntity;
    }

    private User getUser(String userName) {
        MyUserDetails userDetails = (MyUserDetails) userService.loadUserByUsername(userName);
        return userDetails.getUser();
    }
}
