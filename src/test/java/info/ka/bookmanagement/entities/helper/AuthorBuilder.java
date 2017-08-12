package info.ka.bookmanagement.entities.helper;

import info.ka.bookmanagement.entities.Author;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author ka
 */
public class AuthorBuilder {
    
    private static final long DEFAULT_ID = 1;
    private static final String DEFAULT_FIRST_NAME = "Hans";
    private static final String DEFAULT_LAST_NAME = "Schmidt";
    private static final LocalDate DEFAULT_DOB = LocalDate.of(1995, Month.AUGUST, 20);
        
    private Author author;
       
    public AuthorBuilder() {
        author = new Author();
        author.setFirstName(DEFAULT_FIRST_NAME);
        author.setLastName(DEFAULT_LAST_NAME);
        author.setDateOfBirth(DEFAULT_DOB);
    }

    public AuthorBuilder withId(long id) {
        author.setId(id);
        return this;
    }
    
    public AuthorBuilder withFirstName(String firstName) {
        author.setFirstName(firstName);
        return this;
    }
    
    public AuthorBuilder withLastName(String lastName) {
        author.setLastName(lastName);
        return this;
    }
    
    public AuthorBuilder withDOB(LocalDate dateOfBirth) {
        author.setDateOfBirth(dateOfBirth);
        return this;
    }

    public Author create() {
        return author;
    }
    
}
