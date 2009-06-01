/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons.security;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Authentication;
import br.ufc.ivela.commons.model.Functionality;
import br.ufc.ivela.commons.model.SystemUser;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

/**
 *
 * @author marcus
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * method to load a user (SystemUser) using the username
     * @param username  - the user's username
     * @return the SystemUser object
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        GenericDao<SystemUser> dao = DaoFactory.getInstance(SystemUser.class);
        Set<Functionality> grantedFunctionalities = new HashSet<Functionality>();
        SystemUser systemUser = null;

        // named query to get a user by username 
        List list = dao.getByNamedQuery("SystemUser.findByUsername", new String[]{"username"}, new Object[]{username});

        // if no user was found
        if (list == null || list.size() == 0 || list.get(0) == null) {
            throw new UsernameNotFoundException("Username or Password are incorrect!");
        } else {
            systemUser = (SystemUser) list.get(0);
        }

        // get the list of user's authentications
        if (systemUser.getAuthentication() != null) {
            GrantedAuthority[] authorities = new GrantedAuthority[1];

            authorities[0] = new GrantedAuthorityImpl(systemUser.getAuthentication().getName());

            // get the default functionalities for each authentication
            if (systemUser.getAuthentication().getFunctionalities() != null) {
                for (Functionality functionality : systemUser.getAuthentication().getFunctionalities()) {
                    grantedFunctionalities.add(functionality);
                }
            }

            //get the personal set of functionalities 
            if (systemUser.getFunctionalities() != null) {
                grantedFunctionalities.addAll(systemUser.getFunctionalities());
            }

            //replace the original set of user's functionalities 
            //by the sum of the defaults functionalities and his functionalities
            systemUser.setFunctionalities(grantedFunctionalities);

            //set the array of granted authorities
            systemUser.setAuthorities(authorities);
        } else {
            systemUser.setAuthorities(new GrantedAuthority[]{new GrantedAuthorityImpl("ROLE_USER")});
        }
        return systemUser;
    }
}
