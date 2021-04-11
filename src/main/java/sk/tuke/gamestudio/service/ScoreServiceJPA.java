package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Score;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ScoreServiceJPA implements ScoreService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addScore(Score score) throws ScoreException {
        entityManager.persist(score);
    }
/*
    @Override
    public List<Score> getBestScores(String game) throws ScoreException {
        return entityManager.createNamedQuery("Score.getBestScores")
                .setParameter("game", game).setMaxResults(10).getResultList();
    }*/

    @Override
    public List<Score> getBestScores (String game) {
        return entityManager.createQuery("select s from Score s order by s.points desc")
                .setMaxResults(10).getResultList();
    }
}