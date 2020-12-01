$(document).ready(function() {
    createSearchbar();
    $("#searchbar").change(function(){
        getSearchResults($("#searchQuery").val(), $("#searchType").val());
    })
})

function createSearchbar(){
    $("body").prepend(
        "<nav class='navbar navbar-light d-flex w-100 p-0' style='background-color:#000000;'>" +
            "<div class='col-2'>" +
                "<a href='#'>" +
                    "<img src='https://upload.wikimedia.org/wikipedia/commons/6/69/IMDB_Logo_2016.svg' class='img-fluid d-inline-block align-top' style='max-height: 2em' alt=''>" +
                "</a>" +
            "</div>" +
            "<div style='height:100%;' class='col-10'>" +
                "<form id='searchForm'>" +
                    "<div class='input-group row'>" +
                        "<input id='searchQuery' class='form-control col-10' type='search' placeholder='Search' aria-label='Search' style='background-color:#000000'>" +
                            "<select class='custom-select input-group-append' id='searchType' style='background-color: black'>" +
                                "<option selected>Choose...</option>" +
                                "<option value='artist'>Artist</option>" +
                                "<option disabled='disabled' value='album'>Album</option>" +
                                "<option disabled='disabled' value='track'>Track</option>" +
                            "</select>" +
                    "</div>" +
                "</form>" +
            "</div>" +
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