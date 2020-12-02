/****************************************************
 ****** EVERYTHING THAT MUST BE SET ON LOAD *********
 ****************************************************/

// EVERY FUNCTION (or at least those that have limits)  IS SUPPOSE TO HAVE AN IF-STATEMENT THAT CHECKS FOR THIS CONSTANT!
// this variable is used to prevent unneccessary API calls. When developing / testing a specific function or functions,
    // start by not having an if-statement that checks for this constant.
const DEBUG = true;

$(document).ready(function(){
    let artistName = $("#artistName").text();
    // makes call to coverArtArchive API and then sets cover art for each album.
    // the search is done using the MBID for the release-group, and then we choose the first result.
    // should be fairly accurate
    //TODO: this is not done as the others, where we give a function as parameter - consider changing this for
    // consistency
    $(".album").each(function(index, image) {
        getFrontCover(image);
    });

    // makes call to Web Search API and then calls our callback function 'setNews'
    searchSpotify(artistName, 'artist', artistImage);

    // makes call to spotify API's search endpoint, and then calls out callback function 'setArtistImage'
    // there is a limit of 500 requests / day - so if it is exceeded, we need a new API key.
    getNews(artistName, news);

    // makes call to Eventfull API search endpoint, to find future concerts for the artist
    // then calls the callback function 'setConcerts'
    getConcertsByArtist(artistName, concerts);

    // $("body").niceScroll( {cursorborder:'none', cursor:"#FFFFFF", cursoropacitymax: 1});
    $(".scroller").niceScroll({cursorborder:'none', cursor:"#FFFFFF", cursoropacitymax: 1});
});

/******************************
***** CALLBACK FUNCTIONS ******
 *******************************/

// Description:
// callback function for setting artist image on load.
// It connects with spotify API to search the artist name, and selects the
// image from the first result - not the best solution, but should get the right image mostly.
// Input: json data received after call to the API
// Output: none
let artistImage = function setArtistImage(data){
    $("#artistImage").attr("src", data.artists.items[0].images[0].url);
};

// callback funtion for setting artist news on load.
// connects with Web Search API - limit of 500 request / day.
let news = function setNews(data) {
    $.each(data.value, function (index, value) {
        $("#news").append(
            "<div class='col-4 news'>" +
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

// callback function for setting artist concerts on load.
// connects with Eventful API
let concerts = function setConcerts(data) {
    let events = data.events.event;
    console.log(events);
    $.each(data.events.event, function (index, value) {
        $(".concerts").append(
            "<li class='list-group-item bg-transparent'>" +
                "<img alt='No thumb' src="+ value.image.url +">" +
                "<div>" + value.title + "</div>" +
                "<div>" + value.city_name + "</div>" +
            "</li>"
        )
    })
};