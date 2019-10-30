package es.uvigo.mei.reddit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import es.uvigo.mei.reddit.entidades.Comment;
import es.uvigo.mei.reddit.entidades.DirectMessage;
import es.uvigo.mei.reddit.entidades.Subreddit;
import es.uvigo.mei.reddit.entidades.User;
import es.uvigo.mei.reddit.entidades.UserFollowSubreddit;
import es.uvigo.mei.reddit.entidades.UserModerateSubreddit;

public class Main {
	private static EntityManagerFactory emf;
	
    public static final void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("reddit_PU");

        testCreateEntities();

        emf.close();
    }
	
    private static final void testCreateEntities() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            tx.commit();

        } catch (Exception e) {
            System.err.println("Error on testCreateEntities");
            e.printStackTrace(System.err);

            if ((tx != null) && tx.isActive()) {
                tx.rollback();
            }
        }

        em.close();
    }
}
