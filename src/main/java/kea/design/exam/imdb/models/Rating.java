package kea.design.exam.imdb.models;

import javax.persistence.*;

@Entity
@Table(name ="rating")
public class Rating {

    @Id
    @Column(name = "rating_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private int rating;

}
