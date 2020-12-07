$(document).ready(function() {
    //createSearchbar();
    $("#searchQuery").keypress(function(){
        getSearchResults($("#searchQuery").val(), $("#searchType").val());
    })
})

function hideResults() {
    $("#searchResults").hide();
}

function getSearchResults(query, type) {
    if (query.length > 2) {
        $.ajax({
            url: "/search/" + type,
            data: {query: query, amount: 5},
            success: function (data) {
                $("#searchList").empty();
                $.each(data, function (index, value) {
                    let id = value.id;
                    searchSpotify(value.name, 'artist', function (data) {
                        $("#searchList").append("" +
                            "<a href='artist?id="+id+"'>" +
                            "<li style='height:5em;' class='borderx-dark container-fluid p-1'>" +
                            "<div class='container-fluid row' style='height:100%'>" +
                            "<img class='mx-1' style='border-radius:100%; max-height: 100%; width:auto;' src='" + data.artists.items[0].images[0].url + "'>" +
                            "<div class='text-light col'>" +
                            "<h4>" + value.name + "</h4>" +
                            "<p style='color:#D7B8B8'>" + value.type + " \t</p>" +
                            "</div>" +
                            "</div>" +
                            "</li>" +
                            "</a>");
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