let getSummary = function(wikiId, callback){
    let wikiUrl = "https://corspasser-1010.herokuapp.com/https://www.wikidata.org/w/api.php?action=wbgetentities&props=sitelinks&ids="+wikiId+"&sitefilter=enwiki&format=json&languages=en";
    $.get(wikiUrl, {}, function (data){
        for(let key in data.entities){
            let title = data.entities[key].sitelinks.enwiki.title.replaceAll(" ", "_");
            let url = "https://corspasser-1010.herokuapp.com/https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1&titles="+title;
            $.get(url, {}, function(wikiData){
                let page = wikiData.query.pages;
                for(let key in page){
                    callback(page[key].extract);
                }
            })
        }
    });
}