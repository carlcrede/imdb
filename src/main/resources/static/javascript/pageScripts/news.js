function appendSites(domainsUsedInApi){
    domainsUsedInApi.forEach(a => $("#domainsUsedInApi").append("<button class='btn' style='color: white; opacity: 40%; background: #191919'>-" + a + "</button>"));
}

$(document).ready(function(){

    //controls the dropdown list that displays the sources used ny the newsApi
    $('#sidebar').toggleClass('active');
    document.getElementById('sidebar').style.transition = "all 0.7s";
    $('#sidebarCollapse').on('click', function () {

        $('#sidebar').toggleClass('active');
    });

    appendSites(domains);
    if($("#artistName").val()){
        getNews($("#artistName").val(), 10, loadNews)
    }
    
    $("#searchNews").submit(function(event){

        event.preventDefault();
        getNews($("#artistName").val(), 10, loadNews);
        //
        // for(i = 0; i < 10; i++){
        //     let newsElem =  createNewsElement(i);
        //     $("#newsContainer").append(newsElem);
        // }
    });
})

function loadNews(data){
    let articles = data.articles;
    // appendSites(domainsUsedInApi);
    console.log(articles);
    $("#newsContainer").empty();
    $.each(articles, function(index, article){
        let newsElem =  createNewsElement(article, index);
        console.log(newsElem);
        $("#newsContainer").append(newsElem);
    })
}


//TODO: the comment under this todo, should be used instead of the test function, that is populated with mockup data

//this is a test function with mockup data
// function createNewsElement(index){
//
//     let testTitle = "T-Mobile activates 988 for emergency mental health services";
//     let testArticleLink = "https://www.engadget.com/t-mobile-988-emergency-mental-health-services-nspl-154041420.html";
//     let testImage = "https://o.aolcdn.com/images/dims?resize=1200%2C630&crop=1200%2C630%2C0%2C0&quality=95&image_uri=https%3A%2F%2Fs.yimg.com%2Fos%2Fcreatr-uploaded-images%2F2020-11%2Fa94688a0-2b42-11eb-97de-40ca186f0af4&client=amp-blogside-v2&signature=69f5720b09326b768c479c84153fa7879d072922";
//
//     let testDescription = "T-Mobile customers can now call 988 to access free emergency mental health services. It added support for the number more than 18 months ahead of the deadline that the Federal Communications Commission set for providers to do so. In July, the FCC voted to des…";
//     let testSourceName = "Engadget";
//     let testAuthor = "Kris Holt";
//     let testDate = "20-11-2020";
//
//     let imageElement = "";
//     if(testImage !== "null"){
//          imageElement = "<div id='demo' class='col-3 img-wrap'><img  class='img-fluid align-middle' src='"+testImage+"' alt=''/></div>";
//     }
//
//     let articleHTML = $("" +
//         "<li ='removeElementIfNull("+testImage+")' class='list-group-item row bg-transparent my-2'>" +
//             "<div class='col'>" +
//                 "<div id='Title' class='text-center row' ><a href='"+testArticleLink+"' target='_blank' rel='noopener noreferrer'><h2>"+testTitle+"</h2></a></div>" +
//                 "<div class='row'>" +
//                 imageElement +
//                     "<div class='col'>" +
//                         "<h6 id='description"+index+"' class='align-middle'>"+testDescription+"</h6>" +
//                         "<a id='seeMore' href='"+testArticleLink+"' target='_blank' rel='noopener noreferrer'><h6>see more</h6></a>" +
//                     "</div>" +
//                 "</div>" +
//                 "<footer class='row text-muted'>" +
//                     "<span class='col'>From: "+testSourceName+"</span>" +
//                     "<span class='col'>Author: "+testAuthor+"</span>" +
//                     "<span class='col'>Publish "+$.format.date(testDate, 'dd-MM-yyyy')+"</span>" +
//                 "</footer>" +
//             "</div>" +
//         "</li>"
//     )
//     return articleHTML;
// }


function createNewsElement(article, index){

let imageElement = "";
console.log(article.urlToImage)
if(article.urlToImage+"" !== "null"){
     imageElement = "<div id='demo' class='col-3 img-wrap'><img  class='img-fluid align-middle' src='"+article.urlToImage+"' alt=''/></div>";
}
let sourceName = article.source.name || '';
let author = article.author || '';
let publishDate = article.publishedAt || '';

let articleHTML = $("" +
    "<li class='list-group-item row bg-transparent my-2'>" +
    "<div class='col'>" +
    "<div id='Title' class='row'><a href='"+article.url+"' target='_blank' rel='noopener noreferrer'><h2>"+article.title+"</h2></a></div>" +
    "<div class='row newsArticle'>" +
    // "<div class='col-3 img-wrap'>" +
    // "<img class='img-fluid align-middle' src='"+article.urlToImage+"' alt=''/>" +
    imageElement +
    // "</div>" +
    "<div class='col-8'>" +

    "<h6 id='description"+index+"' class='align-middle'>"+article.description+"</h6>" +
    "<a id='seeMore' href='"+article.url+"' target='_blank' rel='noopener noreferrer'><h6>see more</h6></a>" +
    "</div>" +
    "</div>" +
    "<footer class='row text-muted'>" +
    "<span class='text-left'>"+sourceName+"</span>" +
    "<span class='text-center px-1'>"+author+"</span>" +
    "<span class='text-right'>"+$.format.date(publishDate, 'dd-MM-yyyy')+"</span>" +
    "</footer>" +
    "</div>" +
    "</li>"
)
return articleHTML;
}
