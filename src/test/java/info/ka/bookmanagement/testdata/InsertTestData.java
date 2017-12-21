package info.ka.bookmanagement.testdata;

import info.ka.bookmanagement.entities.ApplicationUser;
import info.ka.bookmanagement.entities.Book;
import info.ka.bookmanagement.entities.Author;
import info.ka.bookmanagement.entities.ISBN;
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
        Author authorOne = new Author();
        authorOne.setDateOfBirth(LocalDate.of(1995, Month.MARCH, 10));
        authorOne.setFirstName("Hans");
        authorOne.setLastName("Müller");

        Author authorTwo = new Author();
        authorTwo.setDateOfBirth(LocalDate.of(2001, Month.OCTOBER, 28));
        authorTwo.setFirstName("Karl Gustav");
        authorTwo.setLastName("Jäger");
       
        ApplicationUser appUserOne = new ApplicationUser();
        appUserOne.setName("AppUserOne");
        appUserOne.setBooks(new ArrayList<Book>());
        
        ApplicationUser appUserTwo = new ApplicationUser();
        appUserTwo.setName("AppUserTwo");
        appUserTwo.setBooks(new ArrayList<Book>());

        List<Author> authors = new ArrayList<>(Arrays.asList(authorOne, authorTwo));
        
        em.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setTitle("Booktitle " + i);
            book.setAuthors(authors);
            book.setPublisher("Publisher " + i);
            book.setYearPublished(2015);

            if (i % 2 == 0) {
                appUserOne.getBooks().add(book);
            } else {
                appUserTwo.getBooks().add(book);
            }
            em.persist(book);
            
        }
        
        em.getTransaction().commit();
        
        Book book = new Book();
        
        Author markManson = new Author();
        markManson.setFirstName("Mark");
        markManson.setLastName("Manson");
        markManson.setDateOfBirth(LocalDate.of(1984, Month.MARCH, 9));
        
        book.setAuthors(new ArrayList<>(Arrays.asList(markManson)));
        book.setTitle("The Subtle Art of Not Giving a F*ck");
        ISBN isbn = ISBN.of("978-19-2548-359-8");
        book.setIsbn(isbn);
        book.setPublisher("Harper One");
        book.setYearPublished(2016);
        appUserOne.getBooks().add(book);
        
        em.getTransaction().begin();
        em.persist(appUserOne);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        em.persist(appUserTwo);
        em.getTransaction().commit();
        
        em.close();
    }

}
