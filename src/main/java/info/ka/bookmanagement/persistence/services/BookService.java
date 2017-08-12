package info.ka.bookmanagement.persistence.services;

import info.ka.bookmanagement.entities.Book;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ka
 */
@ManagedBean
@Named
public class BookService {
    
    @Inject
    BookPs bookps;
    
    public void saveBook(Book book) {
        bookps.persistBook(book);
    }
    
    public Book getBookById(long id) {
        return bookps.getBookById(id);
    }
    
    public List<Book> getBookByTitle(String title) {
        return bookps.getBookByTitle(title);
    }
    
}
