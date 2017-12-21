package info.ka.bookmanagement.persistence.services;

import info.ka.bookmanagement.entities.Book;
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
    
}
