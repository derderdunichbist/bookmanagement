package info.ka.bookmanagement.services;

import info.ka.bookmanagement.entities.Author;
import info.ka.bookmanagement.entities.Book;
import info.ka.bookmanagement.entities.helper.AuthorBuilder;
import info.ka.bookmanagement.entities.helper.BookBuilder;
import info.ka.bookmanagement.persistence.services.BookService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ka
 */
public class TestBookService extends TestPersistenceBase{
    private Author defaultAuthor;
    private Book defaultBook;
    private BookService bookService;
    
    @Before
    public void setUp() {
        defaultAuthor = new AuthorBuilder().withId(123).create();
        defaultBook = new BookBuilder().withAuthor(defaultAuthor).create();
        bookService = new BookService();
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void persistBook() {
        bookService.saveBook(defaultBook);
        
        Book b = bookService.getBookById(defaultBook.getId());
        
        assertEquals(b.getId(), defaultBook.getId());
    }

}
