package kea.design.exam.imdb.models;

import java.util.ArrayList;

public class Album {
    private String title;
    private String type;
    ArrayList<String> annotations;
    ArrayList<Track> tracks;

    public Album(String title, String type, ArrayList<Track> tracks,ArrayList<String> annotations) {
        this.title = title;
        this.type = type;
        this.tracks = tracks;
        this.annotations = annotations;
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

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }
}
