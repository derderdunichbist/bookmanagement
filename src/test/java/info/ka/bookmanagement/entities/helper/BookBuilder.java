package info.ka.bookmanagement.entities.helper;

import info.ka.bookmanagement.entities.Author;
import info.ka.bookmanagement.entities.Book;

/**
 *
 * @author ka
 */
public class BookBuilder {
    
    private final Book book;
    
    private static final String DEFAULT_TITLE = "Default Title";
    private static final long DEFAULT_ID = 1;
    
    public BookBuilder() {
        book = new Book();
        book.setTitle(DEFAULT_TITLE);
        book.setId(DEFAULT_ID);
    }
    
    
    public BookBuilder withId(long id) {
        book.setId(id);
        return this;
    }
    
    public BookBuilder withTitle(String title) {
        book.setTitle(title);
        return this;
    }
    
    public BookBuilder withAuthor(Author author) {
        book.addAuthor(author);
        return this;
    }

    public Book create() {
        return book;
    }
    
        
}
