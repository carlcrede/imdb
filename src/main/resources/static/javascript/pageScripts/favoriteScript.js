function addOrRemoveFavorite(mbid, username, type) {
    $.get("/favorites/addOrRemoveFavorite", {mbid: mbid, userName: username, type: type})
        .done(function (data) {
            console.log("Favorited " + type);
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

function addOrRemoveFavoriteTracks(tracks, username, type) {
    console.log(JSON.stringify(tracks));
    $.get("/favorites/addOrRemoveFavorite", {tracks: JSON.stringify(tracks), userName: username, type: type})
        .done(function (data) {
            console.log("success");
        })
        .fail(function (data) {
            console.log("fail");
        })
}

// function for adding multiple tracks at one - maybe useful
function addTracksToFavorites() {

}