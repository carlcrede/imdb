package kea.design.exam.imdb.models.Favorite;

import kea.design.exam.imdb.models.Album;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "FavoriteAlbum")
@DiscriminatorValue("Album")
public class FavoriteAlbum extends Favorite {

    @ManyToOne
    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
