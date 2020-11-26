var accessToken;

$(document).ready(function(){

    $.get("/spotify", function(data){accessToken = data}, "text")
})

function search(query, type){
    let endpoint  = "https://api.spotify.com/v1/search";
    $.ajax(endpoint, {
        headers: {Authorization: "Bearer "+ accessToken},
        data: {q: query, type:type},
        success: function(data){
            console.log(data);
        }
    })
}