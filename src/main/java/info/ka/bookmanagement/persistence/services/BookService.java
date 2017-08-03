/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.ka.bookmanagement.persistence.services;

import info.ka.bookmanagement.entities.Book;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ka
 */
@ManagedBean
@Named
public class BookService {
    
    @Inject
    BookPs bookps;
    
    public String saveBook() {
        Book b = new Book();
        b.setTitle("Inserted");
        bookps.persistBook(b);
        
        return "";
    }
    
}
