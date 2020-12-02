package kea.design.exam.imdb.repository.internal.service;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Track;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AlbumService implements CrudService<Album, String> {
    @Override
    public Album findByid(String s) {
        return null;
    }

    @Override
    public List<Album> findAmountByQuery(String query, int amount) {
        ArrayList<Album> albums = new ArrayList<>();
        ArrayList<Track> tracks = new ArrayList<Track>();
        tracks.add(new Track("3:40", "hey there"));
        tracks.add(new Track("10:20", "rapping raps"));

        albums.add(new Album(UUID.fromString("196bb188-d579-4f00-ac29-9a83a7dc1b9c"), "Watch The Throne", "album",tracks,null));
        albums.add(new Album(UUID.fromString("03f03619-385a-4ed9-9974-cdcdf6404cf5"),"The Life of Pablo", "album", tracks, null));
        albums.add(new Album(UUID.fromString("196bb188-d579-4f00-ac29-9a83a7dc1b9c"),"Watch The Throne", "album",tracks,null));
        albums.add(new Album(UUID.fromString("03f03619-385a-4ed9-9974-cdcdf6404cf5"),"The Life of Pablo", "album", tracks, null));
        albums.add(new Album(UUID.fromString("196bb188-d579-4f00-ac29-9a83a7dc1b9c"),"Watch The Throne", "album",tracks,null));
        albums.add(new Album(UUID.fromString("03f03619-385a-4ed9-9974-cdcdf6404cf5"),"The Life of Pablo", "album", tracks, null));
        albums.add(new Album(UUID.fromString("196bb188-d579-4f00-ac29-9a83a7dc1b9c"),"Watch The Throne", "album",tracks,null));
        albums.add(new Album(UUID.fromString("03f03619-385a-4ed9-9974-cdcdf6404cf5"),"The Life of Pablo", "album", tracks, null));
        albums.add(new Album(UUID.fromString("196bb188-d579-4f00-ac29-9a83a7dc1b9c"),"Watch The Throne", "album",tracks,null));
        albums.add(new Album(UUID.fromString("03f03619-385a-4ed9-9974-cdcdf6404cf5"),"The Life of Pablo", "album", tracks, null));

        ArrayList<String> annotations = new ArrayList<>();
        annotations.add("This is an annotation");
        annotations.add("This also");
        annotations.add("Hey bro!");

        albums.add(new Album(UUID.fromString("03f03619-385a-4ed9-9974-cdcdf6404cf5"),"The Life of Pablo", "album", tracks, null));
        return albums;
    }

    @Override
    public List<Album> findByQuery(String query) {
        return null;
    }
}
