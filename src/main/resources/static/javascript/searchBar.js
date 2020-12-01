$(document).ready(function() {
    createSearchbar();
    $("#searchbar").change(function(){
        getSearchResults($("#searchQuery").val(), $("#searchType").val());
    })
})

function createSearchbar(){
    $("body").prepend(
        "<nav class='navbar navbar-light d-flex w-100 p-0' style='background-color:#383838'>" +
            "<div class='col-2'>" +
                "<a href='#'>" +
                    "<img src='https://upload.wikimedia.org/wikipedia/commons/6/69/IMDB_Logo_2016.svg' class='img-fluid d-inline-block align-top' alt=''>" +
                "</a>" +
            "</div>" +
            "<form id='searchForm' class='form-inline justify-content-center p-2'>" +
                "<input id='searchQuery' class='form-control bg-dark text-light' type='search' placeholder='Search' aria-label='Search'>" +
                "<select class='custom-select m-3 bg-dark text-light' id='searchType'>" +
                    "<option selected>Choose...</option>" +
                    "<option value='artist'>Artist</option>" +
                    "<option disabled='disabled' value='album'>Album</option>" +
                    "<option disabled='disabled' value='track'>Track</option>" +
                "</select>" +
                "<div class='input-group-append justify-content-end'>" +
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

//makes a loading symbol, when the html is not fully loaded, so the user is only presented with the finished information
//makes use of css in the main style.css
document.onreadystatechange = function() {
    if (document.readyState !== "complete") {
        document.querySelector(
            "body").style.visibility = "hidden";
        document.querySelector(
            "#loader").style.visibility = "visible";
    } else {
        document.querySelector(
            "#loader").style.display = "none";
        document.querySelector(
            "body").style.visibility = "visible";
    }
};