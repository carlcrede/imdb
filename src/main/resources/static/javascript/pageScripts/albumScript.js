let id;

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();

    id = $("#id").val();

    getCover(id, setMast);
    getAverageRatingForOneAlbum(id);

    $("#favoriteTrackForm").submit(function (event) {
        event.preventDefault();
        let userName = $("#userName").text();
        $.each($("input[name='favorite_tracks']:checked"), function (index, value) {
            addOrRemoveFavoriteTracks($(value).val(), userName, "track");
        });
    });

    $("#albumRatingForm").submit(function (event) {
        event.preventDefault();
        let mbid = $("#mbid").val();
        let rating = $('input[name="rating"]:checked').val();
        let userName = $("#userName").text();
        console.log(mbid);
        console.log(rating);
        console.log(userName);
        submitAlbumRating(mbid, rating, userName);
    });

    var favorited;
    $("#favoriteIcon").hover(
        function () {
            favorited = $("#favoriteIcon").hasClass('fas fa-heart');
            if ($(this).hasClass('far fa-heart')) {
                $(this).removeClass('far fa-heart').addClass('fas fa-heart');
            }
        },
        function () {
            if (!favorited) {
                $(this).removeClass('fas fa-heart').addClass('far fa-heart');
            }
        });

    $("#favoriteAlbum").click(function () {
        let mbid = $("#mbid").val();
        let username = $("#userName").text();
        addOrRemoveFavorite(mbid, username, "album");
    });

    //sets description
    let wiki = $("#descText")[0].getAttribute("data-value");
    if(wiki != null) {
        wiki = wiki.split("/")[4];
        getSummary(wiki, setDescripition);
    }

});

let setMast = function(url){
    $("#cover")[0].src=url;
    $("body").css({
        "background": "url('"+url+"') no-repeat center center fixed",
        "-webkit-background-size": "cover", "-moz-background-size": "cover", "background-size": "cover", "-o-background-size": "cover"
    });
}

let setDescripition = function(description){
    $("#descText").text(description);
}

