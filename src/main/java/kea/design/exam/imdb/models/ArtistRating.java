package kea.design.exam.imdb.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "ArtistRating")
@DiscriminatorValue("Artist")
public class ArtistRating extends AbstractRating {

    @ManyToOne
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
