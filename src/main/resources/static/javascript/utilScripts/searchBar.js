$(document).ready(function() {
    createSearchbar();
    $("#searchbar").change(function(){
        getSearchResults($("#searchQuery").val(), $("#searchType").val());
    })
})

function createSearchbar(){
    $("body").prepend(
        "<div class='container-fluid' style='background-color:#000000;'>" +
        "        <div class='row'>" +
        "            <div class='navbar-brand'>" +
        "                <a href='#'><img src='https://upload.wikimedia.org/wikipedia/commons/6/69/IMDB_Logo_2016.svg' class='img-fluid d-inline-block align-top' style='max-height: 2em' alt=''></a>" +
        "            </div>" +
        "            <div class='col input-group' style='text-align: center'>" +
        "                <input id='searchQuery' class='form-control input-group-text' type='search' placeholder='Search' aria-label='Search' style='border:none; height:3em; background-color:#000000'>" +
        "                <div class='input-group-append'>" +
        "                    <select class='custom-select' id='searchType' style='height:3em; border:none; background-color: black'>" +
        "                        <option selected>Choose...</option>" +
        "                        <option value='artist'>Artist</option>" +
        "                        <option disabled='disabled' value='album'>Album</option>" +
        "                        <option disabled='disabled' value='track'>Track</option>" +
        "                    </select>" +
        "                </div>" +
        "            </div>" +
        "        </div>" +
        "    </div>")
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