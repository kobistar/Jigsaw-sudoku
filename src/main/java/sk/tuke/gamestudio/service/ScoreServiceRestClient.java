package sk.tuke.gamestudio.service;

import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Score;

import java.util.Arrays;
import java.util.List;

public class ScoreServiceRestClient implements ScoreService {
    private final String url = "http://localhost:8080/api/score";


    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void addScore(Score score) {
        restTemplate.postForEntity(url, score, Score.class);
    }

    @Override
    public List<Score> getBestScores(String game) throws ScoreException {
        return Arrays.asList(restTemplate.getForEntity(url + "/" + game, Score[].class).getBody());
    }
}
