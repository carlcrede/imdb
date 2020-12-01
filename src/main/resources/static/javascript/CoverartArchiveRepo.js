function getFrontCover(imag) {
    console.log(imag.id);
    $.get("http://coverartarchive.org/release/" + imag.id, {}, function (data){
        console.log(data.images[0].image);
        imag.src = data.images[0].image;
    });
}