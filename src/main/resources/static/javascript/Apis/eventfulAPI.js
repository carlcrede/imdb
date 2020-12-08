let key_event = "kqfXDcg7Bf7v6Lrm";
function getConcertsByArtist(artist, pageSize, location, sortOrder, date, callback) {
    let url = requestBuilder(artist, pageSize, location, sortOrder, date);
    console.log("URL:  " + url);
    $.get(url, {}, function (data) {
        let dat = JSON.parse(data);
        callback(dat);
    });
}

function requestBuilder(artist, pageSize, location, sortOrder, date) {
    let endpoint_event = "https://corspasser-1010.herokuapp.com/http://api.eventful.com/json/events/search?";
    if (artist) { endpoint_event += "q=performer%3A%22" + artist.replaceAll(' ', '+') + "%22&l="; }
    if (location) { endpoint_event += "&location=" + location.replaceAll(' ', '+'); }
    if (sortOrder) { endpoint_event +=  "&sort_order=" + sortOrder; }
    if (date) { endpoint_event += "&date=" + date; }
    if (pageSize) { endpoint_event += "&page_size=" + pageSize; }
    endpoint_event += "&c=music";
    endpoint_event += "&app_key=" + key_event;
    return endpoint_event;
}