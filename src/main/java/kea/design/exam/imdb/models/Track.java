package kea.design.exam.imdb.models;

import javax.persistence.*;

@Entity
public class Track implements Comparable<Track>{
    @Id
    private String id;
    private String length;
    private String name;
    private String features;
    private int position;
    @ManyToOne
    private Album album;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }


    @Override
    public int compareTo(Track t) {
        return this.position - t.position;
    }
}
