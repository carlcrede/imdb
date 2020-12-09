$( document ).ready(function() {
    getRatingsByArtist($("#mbid").val());
    // submit form
    $("#ratingform").submit(function (event) {
        // Prevents the form from submitting via the browser.
        event.preventDefault();
        let mbid = $("#mbid").val();
        let rating = $('input[name="rating"]:checked').val();
        let userId = $("#userId").text();
        console.log("User: " + userId);
        submitRating(mbid, rating);
    });
});

function getRatingsByArtist(mbid) {
    $.get("/rating/getRatingsByArtist", {mbid: mbid}, function (data) {
        console.log(data);
        $("#artistRating").text(data);
    });
}
// mbid for artist, user id, rating
function submitRating(mbid, rating) {
    // post
    $.get("/rating/save", {mbid: mbid, rating: rating}, function (data) {
        //TODO: give user feedback after rating is submitted
        console.log(data);
        $("#artistRating").text(data);
    },"json");

    /*$.ajax({
        type: "POST",
        contentType: "application/json",
        url: "rating/save",
        data: JSON.stringify(formData),
        dataType: 'json',
        success: function (result) {
            $("#showtext").append("<p>" + result.data.stars + "</p>");
            console.log(result);
        },
        error: function (result) {
            console.log(result);
            alert("Error!")
        }
    });*/
}