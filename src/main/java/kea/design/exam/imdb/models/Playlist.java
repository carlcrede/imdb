package kea.design.exam.imdb.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Playlist {

    @Id
    private long id;
    private String name;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Track> playlistTracks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Track> getPlaylistTracks() {
        return playlistTracks;
    }

    public void setPlaylistTracks(List<Track> playlistTracks) {
        this.playlistTracks = playlistTracks;
    }
}
