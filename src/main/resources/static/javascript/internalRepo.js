function repository(){
    this.search = new function(query, type){
        $.ajax({
            url: "/search/" + type,
            data: {query: query},
            success: function (data) {
                return data;
                /*$("#searchResults").empty();
                $.each(data, function (index, value) {
                    console.log(value);
                    $("#searchResults").append("<div onclick='"+value.spotifyId+"'>" + value.name + "</div>");
                })*/

            }
    });
}
}