var accessToken;

$(document).ready(function(){

    $.get("/spotify", function(data){accessToken = "Bearer " +data}, "text")
})

function search(query, type){
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
    let endpoint = "https://api.spotify.com/v1/artists/"+ id
    $.ajax(endpoint, {
        headers: {Authorization: accessToken},
        success: function(data){
            console.log(data);
        }
    })
}