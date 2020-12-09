$(document).ready(function () {
    let artist = $("#artistName").val();

    if (artist.length > 0) {
        getConcertsByArtist(artist, 25, false, false, false, concerts);
    }
    $("#searchConcerts").submit(function (event) {
        event.preventDefault();
        $(".concerts").empty();
        console.log("Artist name: " + $("#artistName").val());
        console.log("Value for location:" + $("#location").val());
        //TODO fix script for artist page and when linking to concerts from artist. Also implement more search options
        getConcertsByArtist($("#artistName").val(), 20, $("#location").val(), "date", false, concerts);
    })
});

let concerts = function setConcerts(data) {
    if (data.events) {
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
    } else {
        $(".concerts").append(
            "<li class='list-group-item bg-transparent card'>" +
            "<div class='col text-light'>" +
            "<div class='card-body'>" +
            "<h5 class='card-title'>No search results</h5>" +
            "</div>" +
            "</div>" +
            "</li>"
        )
    }
};