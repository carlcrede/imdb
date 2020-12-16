package kea.design.exam.imdb.repository.internal.service;

import kea.design.exam.imdb.models.Album;
import kea.design.exam.imdb.models.Artist;
import kea.design.exam.imdb.models.Track;
import kea.design.exam.imdb.repository.external.musicbrainz.MbAlbum;
import kea.design.exam.imdb.repository.internal.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlbumService implements CrudService<Album, String> {

    @Autowired
    MbAlbum externalRepo;
    @Autowired
    AlbumRepository internalRepo;

    @Override
    public Album findByid(String id) {
        Optional<Album> album = internalRepo.findById(id);
        if(album.isPresent() && album.get().isCompleteInfo()){
            return album.get();
        }
        return save(externalRepo.getById(id));
    }

    public List<Album> findAlbumTypeByArtist(Artist artist, String type){
        List<Album> albums = internalRepo.findAlbumsByArtistAndType(artist, type);
        if(!albums.isEmpty()){
            return albums;
        }
        return saveAll(externalRepo.findAlbumByArtistAndType(artist, type));
    }

    public List<Album> findAllAlbumsByArtist(Artist artist){
        List<Album> albums = internalRepo.findAlbumsByArtist(artist);
        if(!albums.isEmpty()){
            return albums;
        }
        return saveAll(externalRepo.findAlbumsByArtist(artist));
    }


    @Override
    public List<Album> findAmountByQuery(String query, int amount) {
        List<Album> albums = internalRepo.findAmountByTitle(query, amount);
        if(albums.isEmpty() || albums.size() < amount){
            return externalRepo.findByQuery(query, amount);
        }
        return albums;
    }

    @Override
    public Album save(Album album) {
        return internalRepo.save(album);
    }

    public List<Album> saveAll(List<Album> albums){
        List<Album> newAlbum = new ArrayList<>();
        internalRepo.saveAll(albums).forEach(newAlbum::add);
        return newAlbum;
    }

    @Override
    public void delete(Album album) {
        internalRepo.delete(album);
    }

}
