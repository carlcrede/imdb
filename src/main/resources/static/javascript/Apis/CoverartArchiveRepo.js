function getFrontCover(imag) {
    $.get("http://coverartarchive.org/release-group/" + imag.id, {}, function (data){
        imag.src = data.images[0].image;
    });
}

function getCover(id, callback) {
    $.get("http://coverartarchive.org/release-group/" + id, {}, function (data){
        callback(data.images[0].image);
    });
}