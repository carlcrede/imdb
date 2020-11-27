package kea.design.exam.imdb.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "artist")
public class Artist {

    @Id
    private String spotifyId;

    public String getSpotifyId() {
        return spotifyId;
    }

    private String name;

    public String getName() {
        return name;
    }
}
