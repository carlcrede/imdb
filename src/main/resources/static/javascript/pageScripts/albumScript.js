let id;

$(document).ready(function(){
    id = $("#id").val();
    console.log(id);
    getCover(id, setMast);

    getAverageRatingForOneAlbum(id);

    $("#albumRatingForm").submit(function (event) {
        event.preventDefault();
        let mbid = $("#mbid").val();
        let rating = $('input[name="rating"]:checked').val();
        let userName = $("#userName").text();
        console.log(mbid);
        console.log(rating);
        console.log(userName);
        submitAlbumRating(mbid, rating, userName);
    })

});

let setMast = function(url){
    $("#cover")[0].src=url;
    $("body").css({
        "background": "url('"+url+"') no-repeat center center fixed",
        "-webkit-background-size": "cover", "-moz-background-size": "cover", "background-size": "cover", "-o-background-size": "cover"
    });
}

