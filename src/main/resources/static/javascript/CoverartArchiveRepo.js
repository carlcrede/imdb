function getFrontCover(image){
 $.get("coverartarchive.org/release/"+image.value, {},function(data){
     if(data.front) {
         image.src = data.image;
     }
 }, "json")
}