function addArtistToFavorites(mbid, username) {
    $.get("/favorites/addArtist", {mbid: mbid, userName: username})
        .done(function (data) {
            console.log("Favorited artist");
            $("#favoriteIcon").removeClass('far fa-heart');
            $("#favoriteIcon").addClass('fas fa-heart');
            $("#feedbackText").text('Added to favorites!');
            $("#favoriteFeedback").show();
        })
        .fail(function (data) {
            console.log("already favorited - favorite deleted");
            $("#favoriteFeedback").hide();
            $("#favoriteIcon").removeClass('fas fa-heart');
            $("#favoriteIcon").addClass('far fa-heart');
            $("#feedbackText").text("Removed from favorites");
            $("#favoriteFeedback").show();
            //$("#favoriteFeedback").prepend("Allready added to favorites").show();
        })
}

function addAlbumToFavorites(mbid, username) {

}

function addTrackToFavorites(mbid, username) {

}

// function for adding multiple tracks at one - maybe useful
function addTracksToFavorites() {

}