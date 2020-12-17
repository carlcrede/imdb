package kea.design.exam.imdb.repository.external.musicbrainz;

import kea.design.exam.imdb.models.Album;
import org.musicbrainz.MBWS2Exception;
import org.musicbrainz.controller.*;
import org.musicbrainz.model.entity.ReleaseGroupWs2;
import org.musicbrainz.model.entity.ReleaseWs2;
import org.musicbrainz.model.searchresult.ReleaseGroupResultWs2;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MbAlbum {

    MbArtist mbArtist;

    public MbAlbum(){
        mbArtist = new MbArtist();
    }

    public Album getById(String id){
        ReleaseGroup releaseGroup = new ReleaseGroup();

        try {
            ReleaseGroupWs2 releaseGWs2 = releaseGroup.getComplete(id);
            kea.design.exam.imdb.models.Artist artist = mbArtist.parseWebSearch(releaseGWs2.getArtistCredit().getNameCredits().get(0).getArtist());
            Album album = parseWebSearch(releaseGWs2, artist);
            album.setCompleteInfo(true);
            return album;
        } catch (MBWS2Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Album> findByQuery(String query, int amount){
        ReleaseGroup releaseGroup = new ReleaseGroup();
        releaseGroup.getIncludes().setReleases(true);
        releaseGroup.getSearchFilter().setLimit((long) amount);
        releaseGroup.getReleaseIncludes().setArtistCredits(false);
        List<Album> albums = new ArrayList<>();

        releaseGroup.search("releasegroup:"+query);
        List<ReleaseGroupResultWs2> releases = releaseGroup.getFirstSearchResultPage();


        for(ReleaseGroupResultWs2 release : releases) {
            kea.design.exam.imdb.models.Artist artist = mbArtist.parseWebSearch(release.getReleaseGroup().getArtistCredit().getNameCredits().get(0).getArtist());
            albums.add(parseWebSearch(release.getReleaseGroup(), artist));
        }
        return albums;
    }

    public List<Album> findAlbumByArtistAndType(kea.design.exam.imdb.models.Artist  artist, String type){
        List<Album> albums = new ArrayList<>();
        ReleaseGroup releaseGroup = new ReleaseGroup();
        releaseGroup.getIncludes().setReleases(true);
        String searchQuery = "arid:"+artist.getId()+" AND primarytype:"+type;
        releaseGroup.search(searchQuery);

        List<ReleaseGroupResultWs2> releaseGroupWs2s = releaseGroup.getFullSearchResultList();

        for (ReleaseGroupResultWs2 result : releaseGroupWs2s){
            ReleaseGroupWs2 release = result.getReleaseGroup();
            if(release.getType() != null) {
                if (release.getTypeString().toLowerCase().equals(type.toLowerCase())) {
                    albums.add(parseWebSearch(release, artist));
                }
            }
        }

        albums = albums.stream().filter((album -> album.getType().toLowerCase().equals(type.toLowerCase()))).collect(Collectors.toList());
        return albums;
    }

    public List<Album> findAlbumsByArtist(kea.design.exam.imdb.models.Artist artist){
        List<Album> albums = new ArrayList<>();
        ReleaseGroup releaseGroup = new ReleaseGroup();
        String searchQuery = "arid:"+artist.getId();
        releaseGroup.search(searchQuery);

        List<ReleaseGroupResultWs2> releaseGroupWs2s = releaseGroup.getFullSearchResultList();

        for (ReleaseGroupResultWs2 result : releaseGroupWs2s){
            ReleaseGroupWs2 release = result.getReleaseGroup();
            albums.add(parseWebSearch(release, artist));
        }
        return albums;
    }

    private Album parseWebSearch(ReleaseGroupWs2 releaseGroup, kea.design.exam.imdb.models.Artist artist){
        Album album = new Album();
        if(!releaseGroup.getReleases().isEmpty()) {
            ReleaseWs2 releaseWs2 = releaseGroup.getReleases().get(0);
            if(releaseWs2.getLabelInfoList() != null) {
                album.setLabel(releaseWs2.getLabelInfoList().getLabelInfos().get(0).getLabelName());
            }
        }
        if(releaseGroup.getTags() != null) {
            StringBuilder genres = new StringBuilder();
            releaseGroup.getTags().forEach((tag) -> genres.append(tag.getName()).append(", "));
            album.setGenre(genres.toString());
        }
        releaseGroup.getRelationList().getRelations().stream().filter((v) -> v.getTypeId().equals("b988d08c-5d86-4a57-9557-c83b399e3580")).forEach((r) -> album.setWiki(r.getTargetId()));
        album.setArtist(artist);
        album.setTitle(releaseGroup.getTitle());
        album.setMbid(releaseGroup.getId());

        if(releaseGroup.getType() != null) {
            album.setType(releaseGroup.getTypeString());
            if(!releaseGroup.getSecondaryTypes().isEmpty()) {
                album.setType(releaseGroup.getSecondaryTypes().get(0));
            }
        }
        if(releaseGroup.getFirstReleaseDate() != null) {
            album.setReleaseDate(releaseGroup.getFirstReleaseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        return album;
    }
}
