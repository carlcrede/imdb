package kea.design.exam.imdb.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "album")
public class Album implements IModel{

    @Id
    private String spotifyId;

    private String title;

}
