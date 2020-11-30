var accessToken;

$(document).ready(function(){
    $.get("/spotify", function(data){accessToken = "Bearer " +data}, "text")
});

function searchSpotify(query, type){
    let endpoint  = "https://api.spotify.com/v1/search";
    $.ajax(endpoint, {
        headers: {Authorization: accessToken},
        data: {q: query, type:type},
        success: function(data){
            console.log(data);
        }
    })
}

function getArtistById(id){
    let endpoint = "https://api.spotify.com/v1/artists/"+ id;
    $.ajax(endpoint, {
        headers: {Authorization: accessToken},
        success: function(data){
            console.log(data);
            // hardcoded data to test artist-page
            //TODO: spotify har egentlig ikke ret meget metadata - overveje andre datakilder, fx. last.fm, discogs mv. rovi?
            $("#image").attr("src", data.images[0].url);
            $("#name").html(data.name);
            $.each(data.genres, function (index, value) {
                $("#genres").append("<li class='list-group-item'>" + value + "</li>");
            })
        }
    })
}

function spptifyArtistParser(){

}