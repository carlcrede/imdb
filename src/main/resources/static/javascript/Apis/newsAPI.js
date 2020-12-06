let domains = [
    "rollingstone.com", "billboard.com", "metacritic.com", "music-news.com",
    "pitchfork.com", "fm.tv", "edm.com", "metalunderground.com", "ultimateclassicrock.com", "youredm.com", "nme.com",
    "mtv.com", "digitalmusicnews.com"
    ];
let key_news = "60da648bf5674318bb72968b1dbbc553";
let endpoint = "https://newsapi.org/v2/everything?qInTitle=%22";

function getNews(q, pageSize, callback) {
    if (!DEBUG) {
        let url = endpoint + q.replaceAll(' ', '+') + "%22&" + pageSize + "&" + domains.join(',') + "&language=en" + "&apiKey=" + key_news;
        $.ajax(url, {
            async: true,
            method: "GET",
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