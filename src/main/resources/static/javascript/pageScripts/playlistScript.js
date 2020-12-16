function makeNewPlaylist(id, name) {
    $.get("/playlist/makeplaylist", {name:name})
        .done(function (data) {
          console.log(data);
          $("#showplaylist ul").empty();
          $.each(data, function (index, playlist){
              playlist = "+ " + playlist.name + "<br>";
              $("#showplaylist ul").append(playlist)
          });
        });
}
