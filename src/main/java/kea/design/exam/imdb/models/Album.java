package kea.design.exam.imdb.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Album {
    private String mbid;
    private String title;
    private String type;
    private List<String> annotations;
    private List<Track> tracks;
    private LocalDate releaseDate;

    public Album(){}

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

    public List<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Album{" +
                "mbid='" + mbid + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", annotations=" + annotations +
                ", tracks=" + tracks +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
