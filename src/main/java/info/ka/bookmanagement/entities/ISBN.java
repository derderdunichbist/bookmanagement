package info.ka.bookmanagement.entities;

import java.io.Serializable;
import java.util.regex.Pattern;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author ka with ressources from 
 * https://gist.github.com/kymmt90/a45ae122faeb78096b2c
 */
@Entity
public class ISBN implements Serializable {

    public static final int ISBN_13_LENGTH = 13;
    public static final int ISBN_10_LENGTH = 10;
    private static final long serialVersionUID = -7677143038205747732L;
    
    @Id
    private String normalizedISBN;    // hyphens are removed
    
    private String originalISBN;
    
    @Transient
    private String prefixDigits;
    
    @Transient
    private String groupDigits;
    
    @Transient
    private String publisherDigits;
    
    @Transient
    private String bookNameDigits;
    
    @Transient
    private String checkDigit;
    
    @OneToOne
    private Book book;

    /**
     * Is Public for Hibernate/CDI
     */
    public ISBN() {

    }

    private ISBN(String originalISBN) {
        assert null != originalISBN;

        this.originalISBN = originalISBN;
        this.normalizedISBN = removeHyphen(this.originalISBN);
        
        String[] numbers = this.originalISBN.split("-");
        if (numbers.length == 5) {
            this.prefixDigits = numbers[0];
            this.groupDigits = numbers[1];
            this.publisherDigits = numbers[2];
            this.bookNameDigits = numbers[3];
            this.checkDigit = numbers[4];
        } else {
            this.prefixDigits = "";
            this.groupDigits = "";
            this.publisherDigits = "";
            this.bookNameDigits = "";
            this.checkDigit = "";
        }
    }

    /**
     * Check whether the number sequence is valid as ISBN. Check method is:
     * http://en.wikipedia.org/wiki/International_Standard_Book_Number#Check_digits
     *
     * @param numberSequence the number sequence which you want to check. This
     * sequence is allowed to include hyphens
     * @return true if the number sequence is valid as ISBN, otherwise false
     */
    public static boolean isValid(String numberSequence) {
        if (numberSequence == null) {
            throw new NullPointerException();
        }
        if (!Pattern.matches("^\\d+(-?\\d+)*$", numberSequence)) {
            return false;
        }

        String normalizedSequence = removeHyphen(numberSequence);
        if (normalizedSequence.length() == 13) {
            return isValidAsISBN13(normalizedSequence);
        } else if (normalizedSequence.length() == 10) {
            return isValidAsISBN10(normalizedSequence);
        } else {
            return false;
        }
    }

    /**
     * Check whether the 13-digits number is valid as 13-digits ISBN.
     *
     * @param number 13-digits number which you want to check. This must not
     * include hyphens
     * @return true if the 13-digits number is valid as ISBN, otherwise false
     * @throws IllegalArgumentException number is not 13-digits
     */
    private static boolean isValidAsISBN13(String number) {
        number = removeHyphen(number);
        if (number == null) {
            throw new NullPointerException();
        }
        if (!Pattern.matches("^\\d{" + ISBN_13_LENGTH + "}$", number)) {
            throw new IllegalArgumentException();
        }

        char[] digits = number.toCharArray();
        final int myDigit = computeISBN13CheckDigit(digits);
        int checkDigit = digits[ISBN_13_LENGTH - 1] - '0';
        return myDigit == 10 && checkDigit == 0 || myDigit == checkDigit;
    }

    /**
     * Compute the check digits of 13-digits ISBN. Both full 13-digits and
     * check-digit-less 12-digits are allowed as the argument.
     *
     * @param digits the array of each digit in ISBN.
     * @return check digit
     * @throws IllegalArgumentException the length of the argument array is
     * neither 12 nor 13 or the element of digits is negative
     */
    private static int computeISBN13CheckDigit(char[] digits) {
        if (digits == null) {
            throw new NullPointerException();
        }
        if (digits.length != ISBN_13_LENGTH && digits.length != ISBN_13_LENGTH - 1) {
            throw new IllegalArgumentException();
        }
        for (char c : digits) {
            if (c < '0' || '9' < c) {
                throw new IllegalArgumentException();
            }
        }

        int[] weights = {1, 3};
        int sum = 0;
        for (int i = 0; i < ISBN_13_LENGTH - 1; ++i) {
            sum += (digits[i] - '0') * weights[i % 2];
        }
        return 10 - sum % 10;
    }

    /**
     * Check whether the 10-digits number is valid as 10-digits ISBN.
     *
     * @param number 10-digits number which you want to check. This must not
     * include hyphens
     * @return true if the 10-digits number is valid as ISBN, otherwise false
     * @throws IllegalArgumentException number is not 10-digits
     */
    private static boolean isValidAsISBN10(String number) {
        number = removeHyphen(number);
        if (number == null) {
            throw new NullPointerException();
        }
        if (!Pattern.matches("^\\d{" + ISBN_10_LENGTH + "}$", number)) {
            throw new IllegalArgumentException();
        }

        char[] digits = number.toCharArray();
        final int myDigit = computeISBN10CheckDigit(digits);
        if (myDigit == 10) {
            return digits[9] == 'X';
        }
        final int checkDigit = digits[9] - '0';
        return myDigit == 11 && checkDigit == 0 || myDigit == checkDigit;
    }

    /**
     * Compute the check digits of 10-digits ISBN. Both full 10-digits and
     * check-digit-less 9-digits are allowed as the argument.
     *
     * @param digits the array of each digit in ISBN.
     * @return check digit
     * @throws IllegalArgumentException the length of the argument array is
     * neither 9 nor 10 / the element in digits is negative
     */
    private static int computeISBN10CheckDigit(char[] digits) {
        if (digits == null) {
            throw new NullPointerException();
        }
        if (digits.length != ISBN_10_LENGTH && digits.length != ISBN_10_LENGTH - 1) {
            throw new IllegalArgumentException();
        }
        for (char c : digits) {
            if (c < '0' || '9' < c) {
                throw new IllegalArgumentException();
            }
        }

        int sum = 0;
        for (int i = 0, weight = 10; i < 9; ++i, --weight) {
            sum += (digits[i] - '0') * weight;
        }
        return 11 - sum % 11;
    }

    /**
     * Convert 10-digits ISBN to 13-digits ISBN. Check digit is re-computed.
     *
     * @param isbn10 10-digits ISBN. It can include hyphens
     * @return 13-digits ISBN
     * @throws IllegalArgumentException the number of digits of the argument is
     * not 10
     */
    public static String toISBN13(String isbn10) {
        if (isbn10 == null) {
            throw new NullPointerException();
        }
        String normalizedNumber = removeHyphen(isbn10);
        if (normalizedNumber.length() != ISBN_10_LENGTH) {
            throw new IllegalArgumentException();
        }

        // Compute check digit
        String isbn13 = "978" + normalizedNumber.substring(0, ISBN_10_LENGTH - 1);
        final int checkDigit = computeISBN13CheckDigit(isbn13.toCharArray());

        // Compose 13-digits ISBN from 10-digits ISBN
        if (isbn10.contains("-")) {
            return "978-" + isbn10.substring(0, isbn10.length() - 2) + "-" + String.valueOf(checkDigit);
        } else {
            return "978" + isbn10.substring(0, isbn10.length() - 1) + String.valueOf(checkDigit);
        }
    }

    /**
     * Remove hyphens in the argument string.
     *
     * @param s
     * @return string where hyphens are removed
     */
    private static String removeHyphen(String s) {
        if (s == null) {
            throw new NullPointerException();
        }
        return s.replace("-", "");
    }

    /**
     * Static factory.
     *
     * @param number ISBN which you want to instantiate.
     * @return ISBN Object
     * @throws IllegalArgumentException if the argument is invalid as ISBN
     */
    public static ISBN of(String number) throws IllegalArgumentException {
        if (number == null) {
            throw new NullPointerException();
        }
        if (!isValid(number)) {
            throw new IllegalArgumentException();
        }

        if (removeHyphen(number).length() == ISBN_10_LENGTH) {
            return new ISBN(toISBN13(number));
        } else {
            return new ISBN(number);
        }
    }

    public String getISBN() {
        return originalISBN;
    }

    public String getPrefixDigits() {
        return prefixDigits;
    }

    public String getGroupDigits() {
        return groupDigits;
    }

    public String getPublisherDigits() {
        return publisherDigits;
    }

    public String getBookNameDigits() {
        return bookNameDigits;
    }

    public String getCheckDigit() {
        return checkDigit;
    }

    public String getNormalizedISBN() {
        return normalizedISBN;
    }

    public void setNormalizedISBN(String normalizedISBN) {
        this.normalizedISBN = normalizedISBN;
    }

    public String getOriginalISBN() {
        return originalISBN;
    }

    public void setOriginalISBN(String originalISBN) {
        this.originalISBN = originalISBN;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    

    /**
     * @return original description of ISBN. It can include hyphens like
     * 978-4-***-*****-*.
     */
    @Override
    public String toString() {
        return originalISBN;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ISBN)) {
            return false;
        }
        ISBN other = (ISBN) obj;
        return other.normalizedISBN.equals(normalizedISBN);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + normalizedISBN.hashCode();
        return result;
    }

}
