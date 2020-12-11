package kea.design.exam.imdb.controller.restcontroller;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.repository.internal.service.AlbumService;
import kea.design.exam.imdb.repository.internal.service.ArtistService;
import kea.design.exam.imdb.repository.internal.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    ArtistService artistService;
    @Autowired
    AlbumService albumService;

    @GetMapping("/artist")
    public List<Artist> getArtistsFromQuery(String query, Integer amount){
        return artistService.findAmountByQuery(query, amount);
    }

    @GetMapping("/album")
    public List<Album> getAlbumFromQuery(String query, int amount){
        return albumService.findAmountByQuery(query, amount);
    }
}
