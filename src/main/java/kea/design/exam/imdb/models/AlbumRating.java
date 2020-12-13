package kea.design.exam.imdb.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "AlbumRating")
@DiscriminatorValue("Album")
public class AlbumRating extends AbstractRating {

    @ManyToOne
    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
