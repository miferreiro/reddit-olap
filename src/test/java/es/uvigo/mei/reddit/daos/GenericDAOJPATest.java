package es.uvigo.mei.reddit.daos;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;

public class GenericDAOJPATest {
	private static EntityManagerFactory EMF;
	
    @BeforeClass
    public static void initialize() {
    	EMF = Persistence.createEntityManagerFactory("reddit_test_PU");
    	
    	EntityManager em = EMF.createEntityManager();
    	
    	
    	 em.getTransaction().commit();
         em.close();
    }
	
    @AfterClass
    public static void finish() {
        EMF.close();
    }
	
}
