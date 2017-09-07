package info.ka.bookmanagement.persistence.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import info.ka.bookmanagement.persistence.qualifier.BookManagementEntityManager;

/**
 *
 * @author ka
 */
@ApplicationScoped
public class EmProducer {
    
    @PersistenceContext(unitName = "bookmanagement_ds")
    @Produces
    @BookManagementEntityManager
    EntityManager emBook;
    
}
