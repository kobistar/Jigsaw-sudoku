$(document).ready(function () {
    $.ajax({
        url: "/api/comment/jigsaw",
    }).done(function (json) {
        for (var i = 0; i < json.length; i++) {
            var comment = json[i];
            $("#clientSideCommentTable1 tbody").append($("<tr><td>" + comment.username + "<td>" + comment.comment + "<td>" + comment.commentedOn + "</tr>"));
        }
    });});