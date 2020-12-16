package kea.design.exam.imdb.models.Favorite;

import kea.design.exam.imdb.models.Track;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "FavoriteTrack")
@DiscriminatorValue("Track")
public class FavoriteTrack extends Favorite {

    @ManyToOne
    private Track track;

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
