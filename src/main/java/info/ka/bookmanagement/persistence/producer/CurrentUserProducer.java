/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.ka.bookmanagement.persistence.producer;

import info.ka.bookmanagement.entities.ApplicationUser;
import info.ka.bookmanagement.persistence.qualifier.BookManagementEntityManager;
import info.ka.bookmanagement.persistence.qualifier.CurrentUser;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author ka
 */
@ApplicationScoped
public class CurrentUserProducer {

    private static final String DEFAULT_APP_USER_NAME = "AppUserOne";

    @Inject
    @BookManagementEntityManager
    EntityManager em;

    @Produces
    @CurrentUser
    public ApplicationUser getDefaultApplicationUser() {
        ApplicationUser applicationUser = (ApplicationUser) em.createNamedQuery(
                ApplicationUser.QRY_GET_USR_BY_NAME)
                .setParameter(ApplicationUser.PARAM_NAME, DEFAULT_APP_USER_NAME).getSingleResult();
        return applicationUser;
    }

}
