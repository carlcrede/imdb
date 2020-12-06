let key_event = "kqfXDcg7Bf7v6Lrm";
function getConcertsByArtist(artist, pageSize, callback) {
    let url = "https://cors-anywhere.herokuapp.com/http://api.eventful.com/json/events/search?q=performer%3A%22" +
        artist.replaceAll(' ', '+') + "%22&l=&page_size=" + pageSize + "&sort_order=date&c=music&app_key=" + key_event;
    $.get(url, {}, function (data) {
        let dat = JSON.parse(data);
        callback(dat);
    })
}