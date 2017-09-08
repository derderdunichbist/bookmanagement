package info.ka.bookmanagement.entities;

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
    @NamedQuery(name = ApplicationUser.QRY_GET_USR_BY_ID, query = "SELECT a FROM ApplicationUser AS a WHERE a.id = :" + ApplicationUser.PARAM_ID),
    @NamedQuery(name = ApplicationUser.QRY_GET_USR_BY_NAME, query = "SELECT a FROM ApplicationUser AS a WHERE a.name = :" + ApplicationUser.PARAM_NAME)
})
public class ApplicationUser extends BaseEntity {

    private static final long serialVersionUID = -7157069352718548618L;

    public static final String PARAM_ID = "id";
    public static final String QRY_GET_USR_BY_ID = "ApplicationUser.getUserById";
    public static final String PARAM_NAME = "name";
    public static final String QRY_GET_USR_BY_NAME = "ApplicationUser.getUserByNAme";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Book> books;

    private String name;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
