$(document).ready(function () {
    let artist = $("#artist").text();
    console.log(artist);
    console.log(artist.length);
    if (artist.length > 0) {
        getConcertsByArtist(artist, 25, concerts);
    }
});

let concerts = function setConcerts(data) {
    let events = data.events.event;
    console.log(events);
    // TODO: add more event info
    $.each(data.events.event, function (index, value) {
        $(".concerts").append(
            "<li class='list-group-item bg-transparent card col-sm-6'>" +
                "<div class='text-light'>" +
                    "<div class='card-body'>" +
                        "<h5 class='card-title'>" + value.title + ' @ ' + value.venue_name + "</h5>" +
                        "<div class='card-text'>" + "<strong>Where: </strong>" + value.city_name + ', ' + value.country_name + "</div>" +
                        "<div class='card-text'>" + "<strong>When: </strong>" + $.format.date(value.start_time, 'd/MMM/yyyy HH:mm') + "</div>" +
                        "<a class='btn btn-sm btn-warning' target='_blank' href=" + value.url + ">Link to event</a> " +
                        "<a class='btn btn-sm btn-secondary' target='_blank' href=" + value.venue_url + ">Link to venue</a> " +
                    "</div>" +
                "</div>" +
            "</li>"
        )
    })
};