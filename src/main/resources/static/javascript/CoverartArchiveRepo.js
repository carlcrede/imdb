function getFrontCover(imag) {
    $.get("http://coverartarchive.org/release/" + imag.id, {}, function (data){
        imag.src = data.images[0].image;
    });
}