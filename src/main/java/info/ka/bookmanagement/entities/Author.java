package info.ka.bookmanagement.entities;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author ka
 */
@Entity
public class Author extends BaseEntity {

    private static final long serialVersionUID = -5684429322454333625L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String vorname;
    private String nachname;
    private LocalDate dateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authors")
    private List<Book> books;

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
