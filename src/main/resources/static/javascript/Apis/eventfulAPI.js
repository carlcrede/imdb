let key_event = "kqfXDcg7Bf7v6Lrm";
var endpoint_event = "https://cors-anywhere.herokuapp.com/http://api.eventful.com/json/events/search?";
function getConcertsByArtist(artist, pageSize, location, sortOrder, date, callback) {
    requestBuilder();
    $.get(endpoint_event, {}, function (data) {
        let dat = JSON.parse(data);
        callback(dat);
    });
}

function requestBuilder(artist, pageSize, location, sortOrder, date) {
    if (artist) { endpoint_event += "q=performer%3A%22" + artist.replaceAll(' ', '+') + "%22&l="; }
    if (location) { endpoint_event += "&location=" + location.replaceAll(' ', '+'); }
    if (sortOrder) { endpoint_event +=  "&sort_order=" + sortOrder; }
    if (date) { endpoint_event += "&date=" + date; }
    endpoint_event += "&c=music";
    endpoint_event += "&app_key=" + key_event;
    console.log(endpoint_event);
}