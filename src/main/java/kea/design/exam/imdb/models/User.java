package kea.design.exam.imdb.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String role = "ROLE_USER";
    private boolean enabled = true;

    @OneToMany
    private List<Playlist> playlists;

    @OneToMany()
    private List<ArtistRating> artistRatings;

    @OneToMany
    private List<AlbumRating> albumRatings;

    @OneToMany
    private List<FavoriteArtist> favoriteArtists;

    public List<FavoriteArtist> getFavoriteArtists() {
        return favoriteArtists;
    }

    public void setFavoriteArtists(List<FavoriteArtist> favoriteArtists) {
        this.favoriteArtists = favoriteArtists;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<ArtistRating> getArtistRatings() {
        return artistRatings;
    }

    public void setArtistRatings(List<ArtistRating> artistRatings) {
        this.artistRatings = artistRatings;
    }

    public List<AlbumRating> getAlbumRatings() {
        return albumRatings;
    }

    public void setAlbumRatings(List<AlbumRating> albumRatings) {
        this.albumRatings = albumRatings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
