package info.ka.bookmanagement.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author ka
 */
public abstract class TestPersistenceBase {
    
    private static final String LOCAL_PERSISTENCE_UNIT_NAME = "bookmanagement_test_ds";
    
    private static EntityManagerFactory emf;
    protected EntityManager em;
    
    @BeforeClass
    public static void initialize() {
        emf = Persistence.createEntityManagerFactory(LOCAL_PERSISTENCE_UNIT_NAME);
    }
    
    @Before
    public void setUpTest() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    @After
    public void tearDownTest() {
        em.close();
    }
}
