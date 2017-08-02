package info.ka.bookmanagement.testdata;

import info.ka.bookmanagement.entities.Book;
import info.ka.bookmanagement.entities.Author;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ka
 */
public class InsertTestData {

    private static final String LOCAL_PERSISTENCE_UNIT_NAME = "bookmanagement_test_ds";
    EntityManagerFactory emf;
    EntityManager em;
    
    public InsertTestData() {
        
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory(LOCAL_PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }

    @Test
    public void generateTestData() {
        Book book = new Book();
        book.setTitle("Ein Buchtitel");

        Author authorOne = new Author();
        authorOne.setDateOfBirth(LocalDate.of(1995, Month.MARCH, 10));
        authorOne.setVorname("Hans");
        authorOne.setNachname("Müller");

        Author authorTwo = new Author();
        authorTwo.setDateOfBirth(LocalDate.of(2001, Month.OCTOBER, 28));
        authorTwo.setVorname("Karl Gustav");
        authorTwo.setNachname("Jäger");

        List<Author> authors = new ArrayList<>(Arrays.asList(authorOne, authorTwo));

        book.setAuthors(authors);

        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
        em.close();
    }

}
