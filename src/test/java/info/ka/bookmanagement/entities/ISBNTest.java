package info.ka.bookmanagement.entities;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 *
 * @author ka
 */
public class ISBNTest {
    
    public static final String VALID_ISBN_13 = "9781925483598"; //The Art of not giving a f*ck
    public static final String VALID_ISBN_10 = "0670899240"; //Gettings Things Done
    
    public static final String INVALID_ISBN_13 = "9781925483597"; //From above; last digit changed
    public static final String INVALID_ISBN_10 = "0670899241"; //From above; last digit changed
    
    @Test
    public void testIsValidIsbn() {
        assertThat(ISBN.isValid(VALID_ISBN_10), is(true));
        assertThat(ISBN.isValid(VALID_ISBN_13), is(true));
        assertThat(ISBN.isValid(INVALID_ISBN_10), is(false));
        assertThat(ISBN.isValid(INVALID_ISBN_13), is(false));
    }
    
    @Test
    public void testMethodOfWithValidIsbn() {
        ISBN.of(VALID_ISBN_13);
        ISBN.of(VALID_ISBN_10);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMethodOfWithInvalidIsbn13() {
        ISBN.of(INVALID_ISBN_13);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMethodOfWithInvalidIsbn10() {
        ISBN.of(INVALID_ISBN_10);
    }

}
