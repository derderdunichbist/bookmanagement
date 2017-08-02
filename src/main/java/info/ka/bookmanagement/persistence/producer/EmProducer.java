package info.ka.bookmanagement.persistence.producer;

import info.ka.bookmanagement.persistence.qualifier.BookEntityManager;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ka
 */
@ApplicationScoped
public class EmProducer {
    
    @PersistenceContext(unitName = "bookmanagement_ds")
    @Produces
    @BookEntityManager
    EntityManager emBook;
    
}
