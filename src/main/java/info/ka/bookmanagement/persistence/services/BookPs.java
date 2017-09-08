package info.ka.bookmanagement.persistence.services;

import info.ka.bookmanagement.entities.Book;
import info.ka.bookmanagement.persistence.qualifier.BookManagementEntityManager;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
    @BookManagementEntityManager
    private EntityManager bookEm;
    
    public void persistBook(@NotNull Book book) {
        bookEm.persist(book);
    }
    
}
