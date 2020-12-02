function getNews(q, callback) {
    if (!DEBUG) {
        let endpoint = "https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/search/NewsSearchAPI?pageSize=10&q="
            + q.replaceAll(' ', '+') + "&autoCorrect=false&pageNumber=1&toPublishedDate=null&fromPublishedDate=null&withThumbnails=true";
        $.ajax(endpoint, {
            async: true,
            crossDomain: true,
            method: "GET",
            headers: {
                "x-rapidapi-key": "7a31670570mshbe0a8f1713c6a85p1c1d4djsnac6d7f00ea34",
                "x-rapidapi-host": "contextualwebsearch-websearch-v1.p.rapidapi.com"
            },
            success: function (data) {
                callback(data);
            }
        })
    }
}

/*function searchNewsByQuery(q) {
    var url = 'http://newsapi.org/v2/top-headlines?' +
        'q=' + q.replaceAll(' ', '+') + '&' +
        'apiKey=60da648bf5674318bb72968b1dbbc553';

    var req = new Request(url);
    console.log(url);
    fetch(req)
        .then(function(response) {
            console.log(response.json());
        })
}*/