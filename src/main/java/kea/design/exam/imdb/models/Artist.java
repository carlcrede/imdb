package kea.design.exam.imdb.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Artist {

    private String id;
    private String name;

    private LocalDate beginDate;
    private LocalDate endDate;
    private String type;
    private String gender;

    private String founded;
    private ArrayList<String> annotations;

    private String spotifyId;

    public Artist(String id, String name, LocalDate beginDate, LocalDate endDate, String type, String gender, String founded, ArrayList<String> annotations, String spotifyId) {
        this.id = id;
        this.name = name;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.type = type;
        this.gender = gender;
        this.founded = founded;
        this.annotations = annotations;
        this.spotifyId = spotifyId;
    }

    public Artist(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }

    public ArrayList<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(ArrayList<String> annotations) {
        this.annotations = annotations;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }
}
