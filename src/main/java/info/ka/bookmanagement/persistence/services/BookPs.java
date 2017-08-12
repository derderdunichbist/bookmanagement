package info.ka.bookmanagement.persistence.services;

import info.ka.bookmanagement.entities.Book;
import info.ka.bookmanagement.persistence.qualifier.BookEntityManager;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ka
 */
@Stateless
@Transactional
public class BookPs {
    
    @Inject
    @BookEntityManager
    private EntityManager bookEm;
    
    public void persistBook(@NotNull Book book) {
        bookEm.persist(book);
    }

    public Book getBookById(long id) {
        Query query = bookEm.createNamedQuery(Book.QUERY_GET_BOOK_BY_ID).setParameter(Book.ATTRIBUTE_BOOK_ID, id);
        Book book = (Book)query.getSingleResult();
        
        return book;
    }
    
    public List<Book> getBookByTitle(String title) {
        Query query = bookEm.createNamedQuery(Book.QUERY_GET_BOOK_BY_TITLE).setParameter(Book.ATTRIBUTE_BOOK_TITLE, title);
        List<Book> books = query.getResultList();
        
        return books;
    }
    
}
