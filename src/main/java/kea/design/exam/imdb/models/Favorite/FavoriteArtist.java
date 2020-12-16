package kea.design.exam.imdb.models.Favorite;

import kea.design.exam.imdb.models.Artist;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "FavoriteArtist")
@DiscriminatorValue("Artist")
public class FavoriteArtist extends Favorite {

    @ManyToOne
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
