$( document ).ready(function() {

    // submit form
    $("#ratingform").submit(function (event) {
        // Prevents the form from submitting via the browser.
        event.preventDefault();
        let mbid = $("#mbid").val();
        let rating = $('input[name="rating"]:checked').val();
        let userId = $("#userId").text();
        console.log("User: " + userId);
        ajaxPost(mbid, rating, userId);
    });

});
// mbid for artist, user id, rating
function ajaxPost(mbid, rating, username) {
    // post
    $.get("/rating/save", {mbid: mbid, rating: rating, username: username}, function (data) {
        console.log(data);
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