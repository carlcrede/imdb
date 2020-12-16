package kea.design.exam.imdb.models;

import kea.design.exam.imdb.models.Rating.ArtistRating;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Artist {
    @Id
    private String id;
    private String name;

    private String wiki;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String type;
    private String gender;
    private String founded;
    private String disambiguation;
    private boolean completeInfo;

    @ManyToMany
    private List<Artist> bandMembers;

    @OneToMany
    private List<Artist> associatedBands;


    @OneToMany
    private List<ArtistRating> ratings;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Annotation> annotations;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Album> albums;

    private String spotifyId;

    public List<ArtistRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<ArtistRating> ratings) {
        this.ratings = ratings;
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

    public List<Artist> getBandMembers() {
        return bandMembers;
    }

    public void setBandMembers(List<Artist> bandMembers) {
        this.bandMembers = bandMembers;
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

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public String getDisambiguation() {
        return disambiguation;
    }

    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

    public boolean isCompleteInfo() {
        return completeInfo;
    }

    public void setCompleteInfo(boolean completeInfo) {
        this.completeInfo = completeInfo;
    }

    public List<Artist> getAssociatedBands() {
        return associatedBands;
    }

    public void setAssociatedBands(List<Artist> associatedBands) {
        this.associatedBands = associatedBands;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", wiki='" + wiki + '\'' +
                ", albums=" + albums +
                '}';
    }
}
