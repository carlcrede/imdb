package kea.design.exam.imdb.controller.restcontroller;

import kea.design.exam.imdb.models.*;
import kea.design.exam.imdb.models.Favorite.FavoriteAlbum;
import kea.design.exam.imdb.models.Favorite.FavoriteArtist;
import kea.design.exam.imdb.models.Favorite.FavoriteTrack;
import kea.design.exam.imdb.models.User.MyUserDetails;
import kea.design.exam.imdb.models.User.User;
import kea.design.exam.imdb.repository.internal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserDetailsServiceImpl userService;
    @Autowired
    ArtistService artistService;
    @Autowired
    AlbumService albumService;
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    PlaylistService playlistService;
    @Autowired
    TrackService trackService;

    private ResponseEntity<Object> addOrRemoveAlbumFromFavorites(String mbid, User user) {
        Album album = albumService.findByid(mbid);
        List<FavoriteAlbum> favoriteAlbumList = user.getFavoriteAlbums();
        for (FavoriteAlbum favorite : favoriteAlbumList) {
            if (favorite.getAlbum().equals(album)) {
                favoriteAlbumList.remove(favorite);
                favoriteService.delete(favorite);
                userService.saveUser(user);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        FavoriteAlbum favorite = new FavoriteAlbum();
        favorite.setUser(user);
        favorite.setAlbum(album);
        favoriteService.save(favorite);
        favoriteAlbumList.add(0, favorite);
        userService.saveUser(user);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    private ResponseEntity<Object> addOrRemoveTracksFromFavorites(String[] tracks, User user) {

        System.out.println(Arrays.toString(tracks));
        List<FavoriteTrack> favoriteTracks = user.getFavoriteTracks();
        // loop users favorite tracklist
        // compare each existing favorite track to each track in submitted array
        // if match, delete favorite
        for (FavoriteTrack favorite : favoriteTracks) {
            for (String trackId: tracks) {
                if (favorite.getTrack().getId().equals(trackId)) {
                    favoriteTracks.remove(favorite);
                    favoriteService.delete(favorite);
                } else {
                    Track track = trackService.findByid(trackId);
                    FavoriteTrack favoriteTrack = new FavoriteTrack();
                    favoriteTrack.setUser(user);
                    favoriteTrack.setTrack(track);
                    favoriteService.save(favoriteTrack);
                }
            }
        }
        // else add track to favorites
        userService.saveUser(user); // Test if this works - maybe it needs to be in the outer loop
        return ResponseEntity.ok(HttpEntity.EMPTY);
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
        favoriteArtistList.add(0, favorite);
        userService.saveUser(user);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @GetMapping("/favorites/addOrRemoveFavorite")
    public ResponseEntity<Object> addOrRemoveFavorite(@RequestParam(required = false) String mbid, @RequestParam(required = false) String[] tracks , @RequestParam String userName, @RequestParam String type) {
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
                responseEntity = addOrRemoveTracksFromFavorites(tracks, user);
                break;
        }
        return responseEntity;
    }

    @GetMapping("/playlist/makeplaylist")
    public ResponseEntity<Object> makeNewPlaylist(@RequestParam Long id, Playlist playlist){
        playlistService.save(playlist);
        System.out.println("debug");
        return ResponseEntity.ok(playlistService.getAllPlaylists(id));
    }

    private User getUser(String userName) {
        MyUserDetails userDetails = (MyUserDetails) userService.loadUserByUsername(userName);
        return userDetails.getUser();
    }
}
