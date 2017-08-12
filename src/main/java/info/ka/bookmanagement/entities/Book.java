package info.ka.bookmanagement.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author ka
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Book.QUERY_GET_BOOK_BY_ID, query = "SELECT b FROM Book b WHERE b.id=:" + Book.ATTRIBUTE_BOOK_ID),
    @NamedQuery(name = Book.QUERY_GET_BOOK_BY_TITLE, query = "SELECT b FROM Book b WHERE b.title:" + Book.ATTRIBUTE_BOOK_TITLE)
})
public class Book implements Serializable {
    
    public static final String QUERY_GET_BOOK_BY_ID = "book.getBookById";
    public static final String QUERY_GET_BOOK_BY_TITLE = "book.getBookByTItle";
    public static final String ATTRIBUTE_BOOK_TITLE = "id";
    public static final String ATTRIBUTE_BOOK_ID = "title";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String title;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Author> authors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }
    
}
