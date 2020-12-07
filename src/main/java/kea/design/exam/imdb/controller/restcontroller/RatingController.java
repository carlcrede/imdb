package kea.design.exam.imdb.controller.restcontroller;


import kea.design.exam.imdb.models.AjaxRequest;
import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.models.Rating;
import kea.design.exam.imdb.repository.internal.service.ArtistService;
import kea.design.exam.imdb.repository.internal.service.RatingService;
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
    RatingService ratingService;

    @PostMapping("/save")
    public AjaxRequest sendRating(@RequestBody Rating rating){
        System.out.println("We're in the method now");
        ratingService.save(rating);
        AjaxRequest ajaxRequest = new AjaxRequest(rating);
        return ajaxRequest;
    }

}
