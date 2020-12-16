let id;

$(document).ready(function(){
    id = $("#id").val();

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

