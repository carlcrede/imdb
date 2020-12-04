package kea.design.exam.imdb.repositoryTest;

import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.repository.external.musicbrainz.MbAlbum;
import kea.design.exam.imdb.repository.external.musicbrainz.MbArtist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MbRepoTest {

    @Test
    public void mbGetSingleArtistTest(){
        MbArtist artistRepo = new MbArtist();
        Artist artist = artistRepo.getById("f82bcf78-5b69-4622-a5ef-73800768d9ac");
        Assertions.assertEquals(artist.getName().toLowerCase(), "jay-z");
    }

    @Test
    public void mbGetMultipleArtists(){
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
    public void mbGetArtistsAlbums(){
        MbArtist artistRepo = new MbArtist();
        Artist artist = artistRepo.getById("f82bcf78-5b69-4622-a5ef-73800768d9ac");

        MbAlbum albumRepo = new MbAlbum();
        albumRepo.getAlbumFromArtist(artist.getId()).forEach(System.out::println);
    }
}
