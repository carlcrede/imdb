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

import java.util.ArrayList;
import java.util.List;

public class MbRepoTest {

    @Test
    public void mbGetSingleArtistTest(){
        MbArtist artistRepo = new MbArtist();
        Artist artist = artistRepo.getById("f181961b-20f7-459e-89de-920ef03c7ed0");
        Assertions.assertEquals(artist.getName().toLowerCase(), "the strokes");
    }

    @Test
    public void mbGetArtistWiki(){
        MbArtist artistRepo = new MbArtist();
        Artist artist = artistRepo.getById("73e5e69d-3554-40d8-8516-00cb38737a1c");
        Assertions.assertEquals(artist.getName().toLowerCase(), "rihanna");
        System.out.println(artist.getWiki());
    }

    @Test
    public void mbSearchMultipleArtists(){
        MbArtist artistRepo = new MbArtist();
        List<Artist> artists =  artistRepo.findByQuery("the", 20);
        Assertions.assertEquals(20, artists.size());
    }

    @Test
    public void mbGetSingleAlbum(){
        MbAlbum albumRepo = new MbAlbum();
        Assertions.assertEquals("the life of pablo", albumRepo.getById("8c18657a-6338-490d-a952-897663596b96").getTitle().toLowerCase());
    }

    @Test
    public void mbSearchMultipleAlbums(){
        MbAlbum albumRepo = new MbAlbum();
        List<Album> albums = albumRepo.findByQuery("the", 20);
        Assertions.assertEquals(20, albums.size());
    }

    @Test
    public void mbGetArtistAlbumsOfType(){
        MbArtist artistRepo = new MbArtist();
        Artist artist = artistRepo.getById("f82bcf78-5b69-4622-a5ef-73800768d9ac");

        MbAlbum albumRepo = new MbAlbum();
        List<Album> albums = albumRepo.findAlbumByArtistAndType(artist, "Album");
    }
    @Test
    public void mbGetBandMembers(){
        MbArtist artistRepo = new MbArtist();
        //rolling stones
        Artist artist = artistRepo.getById("b071f9fa-14b0-4217-8e97-eb41da73f598");
        Assertions.assertTrue(artist.getBandMembers().stream().anyMatch((v) -> v.getName().toLowerCase().equals("mick jagger")));
    }

    @Test
    public void mbGetAssociatedBand(){
        MbArtist artistRepo = new MbArtist();
        //paul mccartneys id
        Artist artist = artistRepo.getById("ba550d0e-adac-4864-b88b-407cab5e76af");
        Assertions.assertTrue(artistRepo.getBands(artist).stream().anyMatch((v) -> v.getName().toLowerCase().equals("the beatles")));
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
