function getFrontCover(imag){
    console.log(imag.id);
    let url = "http://www.coverartarchive.org/release/" + imag.id + "/front";
    $.ajax({
        method: 'GET',
        url: url,
        success: function (data) {
            imag.src = data.location;
            console.log(imag.src);
        }
    });
}

function getFrontCover(imag) {
    $.getJSON("http://coverartarchive.org/release" + imag.id + "/front", {}, function (data){
        imag.src = data.location;
    } {

    })

}