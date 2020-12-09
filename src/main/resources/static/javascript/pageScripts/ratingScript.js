$( document ).ready(function() {
    getRatingsByArtist($("#mbid").val());
    // submit form
    $("#ratingform").submit(function (event) {
        // Prevents the form from submitting via the browser.
        event.preventDefault();
        let mbid = $("#mbid").val();
        let rating = $('input[name="rating"]:checked').val();
        let userName = $("#userName").text();
        console.log("User: " + userName);
        submitRating(mbid, rating, userName);
    });
});

function getRatingsByArtist(mbid) {
    $.get("/rating/getRatingsByArtist", {mbid: mbid}, function (data) {
        console.log(data);
        $("#artistRating").text(data);
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
            getRatingsByArtist(mbid);
        });
        //TODO: give user feedback after rating is submitted
}