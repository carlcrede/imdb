package kea.design.exam.imdb.models;

import javax.persistence.*;

@Entity
@Table(name = "artist")
@SecondaryTables({
        @SecondaryTable(name = "artist_type"),
        @SecondaryTable(name = "gender")
})
public class Artist {

    @Id
    private String id;
    private String gid;
    private String name;
    private String sort_name;
    private String begin_date_year;
    private String begin_date_month;
    private String begin_date_day;
    private String end_date_year;
    private String end_date_month;
    private String end_date_day;
    @Column(name = "name", table = "artist_type")
    private String type;
    @Column(name = "name", table = "gender")
    private String gender;
    @Column(name = "spotifyId")
    private String spotifyId;

    public String getName() {
        return name;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public String getId() {
        return id;
    }

    public String getGid() {
        return gid;
    }

    public String getSort_name() {
        return sort_name;
    }

    public String getBegin_date_year() {
        return begin_date_year;
    }

    public String getBegin_date_month() {
        return begin_date_month;
    }

    public String getBegin_date_day() {
        return begin_date_day;
    }

    public String getEnd_date_year() {
        return end_date_year;
    }

    public String getEnd_date_month() {
        return end_date_month;
    }

    public String getEnd_date_day() {
        return end_date_day;
    }

    public String getType() {
        return type;
    }

    public String getGender() {
        return gender;
    }
}
