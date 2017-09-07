package info.ka.bookmanagement.controller;

import info.ka.bookmanagement.entities.ApplicationUser;
import info.ka.bookmanagement.persistence.qualifier.CurrentUser;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ka
 */
@Named
@SessionScoped
public class ApplicationUserBean implements Serializable{
    
    @Inject
    @CurrentUser
    private ApplicationUser currentUser;

    public ApplicationUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(ApplicationUser currentUser) {
        this.currentUser = currentUser;
    }

}
