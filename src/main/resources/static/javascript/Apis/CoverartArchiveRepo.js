function getFrontCover(imag) {
    $.get("http://coverartarchive.org/release-group/" + imag.id, {}, function (data){
        imag.src = data.images[0].image;
    });
}