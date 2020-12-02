let key = "kqfXDcg7Bf7v6Lrm";
function getConcertsByArtist(artist, callback) {
    let url = "https://cors-anywhere.herokuapp.com/http://api.eventful.com/json/events/search?q=performer%3A%22" + artist.replaceAll(' ', '+') + "%22&l=&c=music&app_key=" + key;
    $.get(url, {}, function (data) {
        let dat = JSON.parse(data);
        callback(dat);
    })
}