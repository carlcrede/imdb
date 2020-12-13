function getAverageRatingByArtist(mbid) {
    $.get("/rating/getRatingsByArtist", {mbid: mbid}, function (data) {
        //console.log(data);
        $("#artistRating").text(data.toFixed(2));
    });
}

function getAverageRatingsByAlbum() {
    let album = $(".album-rating");
    $.each(album, function (index, value) {
        console.log("MBID: " + value.id);
        $.get("/rating/getRatingsByAlbum", {mbid: value.id}, function (data) {
           console.log("Average: " + data)
            $("#albumRating_" + value.id).text(data.toFixed(2));
        })
    })
}

function getAverageRatingForOneAlbum(mbid) {
    $.get("/rating/getRatingsByAlbum", {mbid: mbid}, function (data) {
        console.log("Average for one album: " + data);
        $("#albumRating_" + mbid).text(data.toFixed(2));
    })
}

function submitAlbumRating(mbid, rating, userName) {
    $.get("/rating/rateAlbum", {mbid: mbid, rating: rating, userName: userName})
        .done(function (data) {
            console.log(data);
            $("#albumRatingFeedback_" + mbid).text("Rating submitted!").show();
            $("#albumRating_" + mbid).text(data);
        })
        .fail(function (data) {
            console.log(data);
            $("#albumRatingFeedback_" + mbid).text("Your rating has been updated!").show();
            getAverageRatingForOneAlbum(mbid);
        })
}

// mbid for artist, user id, rating
function submitArtistRating(mbid, rating, userName) {
    // post
    $.get("/rating/rateArtist", {mbid: mbid, rating: rating, userName: userName})
        .done(function (data) {
            console.log(data);
            $("#ratingFeedback").text("Your rating has been submitted!");
            $("#ratingFeedback").show();
            $("#artistRating").text(data);
        })
        .fail(function (data) {
            console.log(data);
            $("#ratingFeedback").text("Your rating has been updated!");
            $("#ratingFeedback").show();
            getAverageRatingByArtist(mbid);
        });
}