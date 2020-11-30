$(document).ready(function() {
    createSearchbar();
    console.log("hello world")
    $("#searchbar").change(function(){
        getSearchResults($("#searchQuery").val(), $("#searchType").val());
    })
})

function createSearchbar(){
    $("body").prepend(
        "<nav class='navbar navbar-light bg-light d-flex justify-content-center'>" +
            "<a class='navbar-brand' href='#'>" +
                "<img src='https://upload.wikimedia.org/wikipedia/commons/6/69/IMDB_Logo_2016.svg' width='100' class='d-inline-block align-top' alt=''>" +
            "</a>" +
            "<form id='searchForm' class='form-inline'>" +
                "<div class='input-group mr-2'>" +
                    "<input id='searchQuery' class='form-control' type='search' placeholder='Search' aria-label='Search'>" +
                    "<select class='custom-select' id='searchType'>" +
                        "<option selected>Choose...</option>" +
                        "<option value='artist'>Artist</option>" +
                        "<option disabled='disabled' value='album'>Album</option>" +
                        "<option disabled='disabled' value='track'>Track</option>" +
                    "</select>" +
                "</div>" +
                "<div class='input-group-append'>" +
                    "<button id='searchBtn' class='btn btn-outline-warning my-2 my-sm-0' type='submit'>Search</button>" +
                "</div>" +
            "</form>" +
        "</nav>")
}

function getSearchResults(query, type) {
    //if (query.length < 2) return;
    $.ajax({
        url: "/search/" + type,
        data: {query: query},
        success: function (data) {
            $("#searchResults").empty();
            $.each(data, function (index, value) {
                console.log(value);
                $("#searchResults").append("<div onclick='"+value.spotifyId+"'>" + value.name + "</div>");
            })
        }
    })
}