package kea.design.exam.imdb.models;

import java.util.ArrayList;
import java.util.UUID;

public class Album {
    private UUID mbid;
    private String title;
    private String type;
    ArrayList<String> annotations;
    ArrayList<Track> tracks;

    public Album(UUID mbid, String title, String type, ArrayList<Track> tracks, ArrayList<String> annotations) {
        this.mbid = mbid;
        this.title = title;
        this.type = type;
        this.tracks = tracks;
        this.annotations = annotations;
    }

    public UUID getMbid() {
        return mbid;
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

    public ArrayList<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(ArrayList<String> annotations) {
        this.annotations = annotations;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }
}
