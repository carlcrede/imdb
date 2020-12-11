$(document).ready(function() {
    $("#searchQuery").keypress(function(){
        doSearch($("#searchQuery").val(), $("#searchType").val());
    })

    $(".headerLink").each(function(){
        let path = document.location.pathname;
        if($(this).attr("href") === path){
            $(this).css("color", "white");
        }
    })
})

var delayTimer;
function doSearch(query, type) {
    clearTimeout(delayTimer);
    delayTimer = setTimeout(function() {
        getSearchResults(query, type)
    }, 300);
}

function hideResults() {
    $("#searchResults").hide();
}

function getSearchResults(query, type) {
    let display;
    switch(type){
        case "artist":
            display = displayArtist;
            break;
        case "album":
            display = displayAlbum;
            break;
        default:
            //display = displayArtist;
    }
    if (query.length > 2) {
        $.ajax({
            url: "/search/" + type,
            data: {query: query, amount: 5},
            success: function (data) {
                $("#searchList").empty();
                console.log(data);
                display(data);
                $("#searchResults").show();
            }
        })
    }else{hideResults();}
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

//Callbacks for appropriatly displaying the data
let displayArtist = function artistDisplay(data) {
    $.each(data, function (index, artist) {
        let id = artist.id;
        searchSpotify(artist.name, 'artist', function (artistPic) {
            $("#searchList").append("" +
                "<a href='artist?id=" + id + "'>" +
                "<li style='height:5em;' class='borderx-dark container-fluid p-1'>" +
                "<div class='container-fluid row' style='height:100%'>" +
                "<img class='mx-1' style='border-radius:100%; max-height: 100%; width:auto;' src='" + artistPic.artists.items[0].images[0].url + "'>" +
                "<div class='text-light col'>" +
                "<h4>" + artist.name + "</h4>" +
                "<p style='color:#D7B8B8'>" + artist.type + " \t</p>" +
                "</div>" +
                "</div>" +
                "</li>" +
                "</a>");
        });
    })
}
let displayAlbum = function albumDisplay(data){
    $.each(data, function (index, album) {
        $.get("http://coverartarchive.org/release-group/" + album.mbid, {}, function (cover){
            let src = cover.images[0].image;
            $("#searchList").append(
                "<a href='artist?id=" + album.mbid + "'>" +
                "<li style='height:5em;' class='borderx-dark container-fluid p-1'>" +
                    "<div class='container-fluid row' style='height:100%'>" +
                        "<img class='mx-1' style='border-radius:100%; max-height: 100%; width:auto;' src='"+src+"'>" +
                        "<div class='text-light col'>" +
                            "<h4>" + album.title + "</h4>" +
                            "<p style='color:#D7B8B8'>" + album.type + " \t</p>" +
                        "</div>" +
                    "</div>" +
                "</li>" +
                "</a>");
            });
        });
}