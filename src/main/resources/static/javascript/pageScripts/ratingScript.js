$( document ).ready(function() {

    // submit form
    $("#ratingform").submit(function (event) {
        // Prevents the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost() {

        // form data
        var formData = {
            stars: $("#star-1").val()
        };

        // post
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "rating/save",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (result) {
                $("#showtext").append("<p>" + result.data.stars + "</p>");
                console.log(result);
            },
            error: function (e) {
                alert("Error!")
            }
        });
    }
})