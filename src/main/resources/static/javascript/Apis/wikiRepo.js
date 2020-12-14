let getSummary = function(wikiId){
    let url = "https://corspasser-1010.herokuapp.com/https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1&pageids="+wikiId;
    console.log(url);
    $.get(url, {}, function (data){
        console.log(data);
    })
}