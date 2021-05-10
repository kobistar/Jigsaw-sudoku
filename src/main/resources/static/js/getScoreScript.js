$(document).ready(function () {
    $.ajax({
        url: "/api/score/jigsaw",
    }).done(function (json) {
        for (var i = 0; i < json.length; i++) {
            var score = json[i];
            $("#clientSideScoreTable1 tbody").append($("<tr><td>" + score.username + "<td>" + score.points + "<td>" + score.playedOn + "</tr>"));
        }
    });
});