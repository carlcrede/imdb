var accessToken;

$(document).ready(function(){
    $.ajax(
        "/spotify", {
            async: false,
            success: function (data) {accessToken = "Bearer " + data}
            })
        });

function searchSpotify(query, type, callback){
    console.log(accessToken);
    let endpoint  = "https://api.spotify.com/v1/search";
    $.ajax(endpoint, {
        headers: {Authorization: accessToken},
        data: {q: query, type:type},
        success: function(data){
            callback(data);}
    })
}

function getAlbumByNameAndArtist(name, artist, callback){
    let endpoint  = "https://api.spotify.com/v1/search";
    $.ajax(endpoint, {
        headers: {Authorization: accessToken},
        data: {q: name, type:"album"},
        success: function(data){
            for(let i = 0; i < data.length; i++){
                if(data.artist.name.toLowerCase() === name.toLowerCase()){
                    callback(data);
                    return;
                }
            }
            console.log("no matches");
        }
    })
}
function getArtistById(id, callback){
    let endpoint = "https://api.spotify.com/v1/artists/"+ id;
    $.ajax(endpoint, {
        headers: {Authorization: accessToken},
        success: function(data){callback(data)}
    })
}
function getAlbumById(id, callback){
    let endpoint = "https://api.spotify.com/v1/album/"+ id;
    $.ajax(endpoint, {
        headers: {Authorization: accessToken},
        success: function(data){callback(data)}
    })
}