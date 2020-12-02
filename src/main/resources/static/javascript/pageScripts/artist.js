$(document).ready(function(){
    $(".album").each(function(index, image) {
        getFrontCover(image);
    });
    getNews($("#artistName").text(), news);
    searchSpotify($("#artistName").text(), 'artist', artistImage);
    $("body").niceScroll({cursorborder:'none', cursor:"#C0C0C0", cursoropacitymax: 0.5});
    $("#albumScroll").niceScroll({cursorborder:'none', cursor:"#C0C0C0", cursoropacitymax: 0.5});

});

let artistImage = function setArtistImage(data){
    console.log(data.artists.items[0].images[0].url);
    $("#artistImage").attr("src", data.artists.items[0].images[0].url);
};

let news = function setNews(data) {
    console.log(data);
    $.each(data.value, function (index, value) {
        console.log(value.title);
        $("#news").append(
            "<div class='col-2 news'>" +
            "   <div class='card'>" +
            "       <div class='card-body'>" +
            "           <p id='title' class='card-title text-light'>" + value.title + "</p>" +
            "           <a class='text-light' href=" + value.url + ">Read more</a>" +
            "       </div>" +
            "   </div>" +
            "</div>"
        );
    })
};