function getAverageRatingByArtist(mbid) {
    $.get("/rating/getRatingsByArtist", {mbid: mbid}, function (data) {
        //console.log(data);
        $("#artistRating").text(data.toFixed(2));
    });
}
// mbid for artist, user id, rating
function submitRating(mbid, rating, userName) {
    // post
    $.get("/rating/save", {mbid: mbid, rating: rating, userName: userName})
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