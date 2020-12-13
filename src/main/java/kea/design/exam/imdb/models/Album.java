package kea.design.exam.imdb.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Album {
    @Id
    private String mbid;

    private String title;
    private String type;
    private LocalDate releaseDate;
    private String label;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Annotation> annotations;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Track> tracks;

    @OneToMany
    private List<AlbumRating> ratings;

    @ManyToOne
    private Artist artist;

    public List<AlbumRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<AlbumRating> ratings) {
        this.ratings = ratings;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Set<Annotation> annotations) {
        this.annotations = annotations;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
