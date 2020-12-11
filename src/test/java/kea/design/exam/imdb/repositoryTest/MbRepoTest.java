package kea.design.exam.imdb.repositoryTest;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.models.Track;
import kea.design.exam.imdb.repository.external.musicbrainz.MbAlbum;
import kea.design.exam.imdb.repository.external.musicbrainz.MbArtist;
import kea.design.exam.imdb.repository.external.musicbrainz.MbTrack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.musicbrainz.MBWS2Exception;

import java.util.List;

public class MbRepoTest {

    @Test
    public void mbGetSingleArtistTest(){
        MbArtist artistRepo = new MbArtist();
        Artist artist = artistRepo.getById("f82bcf78-5b69-4622-a5ef-73800768d9ac");
        //Assertions.assertEquals(artist.getName().toLowerCase(), "jay-z");
    }

    @Test
    public void mbGetMultipleArtists() throws MBWS2Exception {
        MbArtist artistRepo = new MbArtist();
        Assertions.assertEquals(20, artistRepo.findByQuery("justin", 20).size());
    }

    @Test
    public void mbGetSingleAlbum(){
        MbAlbum albumRepo = new MbAlbum();
        Assertions.assertEquals("the black album", albumRepo.getById("77befaa0-662e-3d84-ba45-c862e16dc109").getTitle().toLowerCase());
    }

    @Test
    public void mbGetMultipleAlbums(){
        MbAlbum albumRepo = new MbAlbum();
        Assertions.assertEquals(20, albumRepo.findByQuery("the", 20).size());
    }

    @Test
    public void mbGetArtistsAlbums() {
        MbArtist artistRepo = new MbArtist();
        Artist artist = artistRepo.getById("f82bcf78-5b69-4622-a5ef-73800768d9ac");

        MbAlbum albumRepo = new MbAlbum();
        Assertions.assertTrue(albumRepo.findAlbumsByArtist(artist).size() > 150);
    }

    @Test
    public void mbGetTracksFromAlbum(){
        MbAlbum albumRepo = new MbAlbum();
        //getter Jay-Z's the black album
        Album album = albumRepo.getById("77befaa0-662e-3d84-ba45-c862e16dc109");

        //checks if the black album has a song called "allure" which it does
        MbTrack trackRepo = new MbTrack();
        boolean hasAllure = false;
        for (Track track: trackRepo.getTracksForAlbum(album)) {
            if(track.getName().toLowerCase().equals("allure")){
                hasAllure = true;
            }
        }
        Assertions.assertTrue(hasAllure);
    }

    @Test
    public void getTrackLabel(){
        MbAlbum albumRepo = new MbAlbum();
        //getter Jay-Z's the black album
        Album album = albumRepo.getById("77befaa0-662e-3d84-ba45-c862e16dc109");
        MbTrack track = new MbTrack();
        List<Track> trackList = track.getTracksForAlbum(album);

        trackList.forEach(System.out::println);
    }
}
