$(document).ready(function(){
    $(".album").each(function(index, image) {
        getFrontCover(image);
    });

    getNews($("#artistName").text(), news);
    searchSpotify($("#artistName").text(), 'artist', artistImage);

    $("body").niceScroll( {cursorborder:'none', cursor:"#C0C0C0", cursoropacitymax: 0.5});
    $(".scroller").niceScroll({cursorborder:'none', cursor:"#C0C0C0", cursoropacitymax: 0.5});
});

let artistImage = function setArtistImage(data){
    $("#artistImage").attr("src", data.artists.items[0].images[0].url);
};

let news = function setNews(data) {
    $.each(data.value, function (index, value) {
        $("#news").append(
            "<div class='col-3 news'>" +
            "   <div class='card'>" +
            "       <div class='card-body text-light'>" +
            "           <p id='title' class='card-title text-center'>" + value.title + "</p>" +
            "           <img style='max-height: 200px' src='" + value.image.thumbnail + "' class='newsArticle card-img-top' alt='No image'>"  +
            "           <div class='card-text'>" + "<i>Source: " + value.provider.name +"</i></div>" +
            "           <div class='card-text'>" + "<i>Published: " + $.format.date(value.datePublished, 'dd/MM/yyyy HH:mm') + "</i></div>" +
            "           <a href=" + value.url + ">Read more</a>" +
            "       </div>" +
            "   </div>" +
            "</div>"
        );
    })
    $("body").niceScroll().resize();
};