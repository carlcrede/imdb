$(document).ready(function () {
    setArtistTopRatings();
    setAlbumTopRatings();
});

function setAlbumTopRatings() {
    $.each($(".album"), function (index, value) {
        let tag = $(value).children();
        let first = $(tag).first();
        let last = $(tag).last();
        let mbid = first[0].id;

        $.get("/rating/getRatingsByAlbum", {mbid: mbid}, function (data) {
            //console.log(data);
            $(first).append(" | Average rating: " + data.toFixed(2) + "/10");
        });
    })
}

function setArtistTopRatings() {
    $.each($(".artist"), function (index, value) {
        let tag = $(value).children();
        let first = $(tag).first();
        let last = $(tag).last();
        let mbid = first[0].id;

        $.get("/rating/getRatingsByArtist", {mbid: mbid}, function (data) {
            $(first).append(" | Average rating: " + data.toFixed(2) + "/10");
        });
    })
}