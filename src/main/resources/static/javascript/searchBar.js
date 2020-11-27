function getSearchResults(query, type) {
    $.ajax({
        url: "/search/" + type,
        data: {query: query},
        success: function (data) {
            $.each(data, function (index, value) {
                console.log(value);
                $("#searchResults").append("<li onclick='"+value.spotifyId+"'>" + value.name + "</li>");
            })
        }
    })
}