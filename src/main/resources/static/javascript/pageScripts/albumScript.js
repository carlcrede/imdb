let id;

$(document).ready(function(){
    id = $("#id").val();
    console.log(id);
    getCover(id, setMast)
});

let setMast = function(url){
    $("#cover")[0].src=url;
    $("body").css({
        "background": "url('"+url+"') no-repeat center center fixed",
        "-webkit-background-size": "cover", "-moz-background-size": "cover", "background-size": "cover", "-o-background-size": "cover"
    });
}

