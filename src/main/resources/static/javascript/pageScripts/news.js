$(document).ready(function(){
    if($("#artistName").val()){
        getNews($("#artistName").val(), 10, loadNews)
    }
    
    $("#searchNews").submit(function(event){
        event.preventDefault();
        getNews($("#artistName").val(), 10, loadNews);
    });
})

function loadNews(data){
    let articles = data.articles;
    console.log(articles);
    $.each(articles, function(index, article){
        let newsElem =  createNewsElement(article);
        console.log(newsElem);
        $("#newsContainer").append(newsElem)
    })
}

function createNewsElement(article){
    let articleHTML = $("" +
        "<li class='list-group-item row bg-transparent my-2'>" +
            "<div class='col'>" +
                "<div class='row'><h2>"+article.title+"</h2></div>" +
                "<div class='row'>" +
                    "<div class='col-3'>" +
                        "<img class='img-fluid align-middle' src='"+article.urlToImage+"'/>" +
                    "</div>" +
                    "<div class='col-8'>" +
                        "<h6 class='align-middle'>"+article.description+"</h6>" +
                    "</div>" +
                "</div>" +
                "<footer class='row text-muted'>" +
                    "<span class='text-left'>"+article.source.name+"</span>" +
                    "<span class='text-center'>"+article.author+"</span>" +
                    "<span class='text-right'>"+$.format.date(article.publishedAt, 'dd-MM-yyyy') +"</span>" +
                "</footer>" +
            "</div>" +
        "</li>"
    )
    return articleHTML;
}