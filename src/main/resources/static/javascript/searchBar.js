function getSearchResults(query, type) {
    console.log(query);
    $.ajax({
        url: "/search/" + type,
        data: {query: query},
        success: function (data) {
            console.log(data);
            $.each(data, function (index, value) {
                $("#searchResults").append("<div>" + value.name + "</div>")
            })
        }
    })
}