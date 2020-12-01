$(document).ready(function(){
    $(".album").each(function(index, image) {
        getFrontCover(image);
    });
    searchSpotify($("#artistName").text(), 'artist', artistImage);
});
let artistImage = function setArtistImage(data){
    console.log(data.artists.items[0].images[0].url);
    $("#artistImage").attr("src", data.artists.items[0].images[0].url);
};