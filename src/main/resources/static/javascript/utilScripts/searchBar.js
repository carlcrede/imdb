$(document).ready(function() {
    createSearchbar();
    $("#searchQuery").keypress(function(){
        getSearchResults($("#searchQuery").val(), $("#searchType").val());
    })
})

function hideResults(){
    $("#searchResults").hide();
}

function createSearchbar(){
    $("body").prepend(
        "<div class='container-fluid' style='position:fixed; z-index:1000; top:0; background-color:#000000;'>" +
        "        <div class='row'>" +
        "            <div class='navbar-brand'>" +
        "                <a href='#'><img src='https://upload.wikimedia.org/wikipedia/commons/6/69/IMDB_Logo_2016.svg' class='img-fluid d-inline-block align-top' style='max-height: 2em' alt=''></a>" +
        "            </div>" +
        "            <div class='col input-group' style='text-align: center'>" +
        "                <input id='searchQuery' class='form-control input-group-text' type='search' placeholder='Search' aria-label='Search' style='border:none; height:3em; background-color:#000000'>" +
        "                <div class='input-group-append'>" +
        "                    <select class='custom-select' id='searchType' style='height:3em; border:none; background-color: black'>" +
        "                        <option selected value='artist'>Artist</option>" +
        "                        <option disabled='disabled' value='album'>Album</option>" +
        "                        <option disabled='disabled' value='track'>Track</option>" +
        "                    </select>" +
        "                </div>" +
        "            </div>" +
        "       </div>" +
        "       <div id='searchResults' class='row bg-item' style='display: none'>" +
        "           <ul id='searchList' class='p-0 m-0 w-100'>" +
        "           </ul>" +
        "           <div class='w-100 p-0' style='cursor:pointer; text-align:center; height:1em' onclick='hideResults()'><img class='align-top' style='height:100%; width:auto;' src='/img/triangle.png'></div>" +
        "       </div>" +
        "    </div>")
}

function getSearchResults(query, type) {
    if (query.length > 2) {
        $.ajax({
            url: "/search/" + type,
            data: {query: query, amount: 10},
            success: function (data) {
                $("#searchList").empty();
                $.each(data, function (index, value) {
                    searchSpotify(value.name, 'artist', function (data) {
                        $("#searchList").append("" +
                            "<li style='height:5em;' class='borderx-dark container-fluid p-1'>" +
                            "<div class='container-fluid row' style='height:100%'>" +
                            "<img class='mx-1' style='border-radius:100%; max-height: 100%; width:auto;' src='" + data.artists.items[0].images[0].url + "'>" +
                            "<div class='text-light col'>" +
                            "<h4>" + value.name + "</h4>" +
                            "<p style='color:#D7B8B8'>" + value.type + " \t</p>" +
                            "</div>" +
                            "</div>" +
                            "</li>");
                    });
                })
                $("#searchResults").show();
            }
        })
    }else{
        hideResults();
    }
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