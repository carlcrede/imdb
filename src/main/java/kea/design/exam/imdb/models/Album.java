package kea.design.exam.imdb.models;

import javax.persistence.*;

//@SecondaryTables({
//        @SecondaryTable(name=""),http://www.thejavageek.com/2014/09/18/jpa-secondarytable-annotation-example/
//        @SecondaryTable(name="")
//})

@Entity
@Table(name = "release_group")
public class Album {

    @Id
    @Column(name = "id")
    private String Id;

    @Column(name = "gid")
    private String gid;

    @Column(name = "spotifyId")
    private String spotifyId;

    @Column(name = "name")
    private String title;

    @Column(name = "comment")
    private String comment;

    @Column(name = "")
    private

    @Column(name = "")
    private

    @Column(table = "", name = "")
    private

    @Column(name = "")
    private

    @Column(name = "")
    private
}
