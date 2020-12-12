$(document).ready(function () {
    $.each($(".artist"), function (index, value) {
        console.log(value);
        let tag = $(value).children();
        console.log(tag);
        let first = $(tag).first();
        console.log(first);
        let last = $(tag).last();
        console.log(last);
        let mbid = first[0].id;
        console.log(mbid);
        $.get("/rating/getRatingsByArtist", {mbid: mbid}, function (data) {
            //console.log(data);
            $(first).append(" | Average rating: " + data.toFixed(2) + "/10");
        });
    })
});