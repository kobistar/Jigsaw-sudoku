package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class RatingServiceJPA implements RatingService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setRating(Rating rating) {
        entityManager.persist(rating);
    }

    @Override
    public int getAverageRating(String game) {
        return ((Double) entityManager.createNamedQuery("Rating.getAverageRating").setParameter("game", game).getSingleResult()).intValue();
    }

    @Override
    public int getRating(String game, String username) throws RatingException {
        int maxRating = 0;
        List<Rating> ratignList = entityManager.createNamedQuery("Rating.getRating").setParameter("game",game).setParameter("username", username).getResultList();
        try{
            for (Rating aRatignList : ratignList) {
                if (aRatignList.getRating() > maxRating)
                    maxRating = aRatignList.getRating();
            }
        }catch (Exception e){
            throw new RatingException("Error getRating, any results not found!", e);
        }
        return maxRating;
    }
}

