/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.services;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.model.Authentication;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import br.ufc.ivela.ejb.interfaces.WsEnabledRemote;
import com.thoughtworks.xstream.XStream;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ProduceMime;
import javax.ws.rs.core.HttpHeaders;

/**
 *
 * @author Leonardo Oliveira Moreira
 */
@Path("systemUser")
public class SystemUserResource {

    @Context
    private UriInfo context;
    private XStream xStream = new XStream();
    private SystemUserRemote systemUserRemote = getSystemUserRemote();
    private WsEnabledRemote wsEnabledRemote = getWsEnabledRemote();
    

    private SystemUserRemote getSystemUserRemote() {
        //<jee:jndi-lookup jndi-name="br.ufc.ivela.ejb.interfaces.SystemUserRemote"
        //id="systemUserRemote" expected-type="br.ufc.ivela.ejb.interfaces.SystemUserRemote"/>        
        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("SystemUserBean#br.ufc.ivela.ejb.interfaces.SystemUserRemote");
            systemUserRemote = (SystemUserRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, SystemUserRemote.class);
        }
        catch (NamingException e) {
            e.printStackTrace();
            systemUserRemote = null;
        }
        return systemUserRemote;
    }
    
        private WsEnabledRemote getWsEnabledRemote() {
        //<jee:jndi-lookup jndi-name="br.ufc.ivela.ejb.interfaces.WsEnabledRemote"
        //id="wsEnabledRemote" expected-type="br.ufc.ivela.ejb.interfaces.WsEnabledRemote"/>        
        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("WsEnabledBean#br.ufc.ivela.ejb.interfaces.WsEnabledRemote");
            wsEnabledRemote = (WsEnabledRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, WsEnabledRemote.class);
        }
        catch (NamingException e) {
            e.printStackTrace();
            wsEnabledRemote = null;
        }
        return wsEnabledRemote;
    }

    /** Creates a new instance of SystemUserResource */
    public SystemUserResource() {
       
    }

    /**
     * retrieves a course of the database by id
     * @param id
     * @return
     */
    @Path("/saveXml/{email}/{socialNumber}/{username}/{password}")
    @GET
    @ProduceMime("application/xml")
    public String saveXml(@PathParam("email") String email, @PathParam("socialNumber") String socialNumber, @PathParam("username") String username, @PathParam("password") String password, @Context HttpServletRequest request) {
        String ipRequest = request.getRemoteAddr();
        WsEnabledRemote wsEnabledRemote = getWsEnabledRemote();
        if (!wsEnabledRemote.ipEnabled(ipRequest)) {
            String xml = "<result>null</result>";
            return xml;
        }        
        boolean result = false;
        if (email != null && ! "".equals(email.trim()) &&
            socialNumber != null && ! "".equals(socialNumber.trim()) &&
            username != null && ! "".equals(username.trim()) &&
            password != null && ! "".equals(password.trim())) {
            List<SystemUser> systemUsers = systemUserRemote.getByUsername(username);
            if (systemUsers == null || systemUsers.size() == 0) {
                SystemUser su = new SystemUser();
                su.setSocialNumber(socialNumber);
                su.setEmail(email);
                su.setUsername(username);
                su.setEnabled(true);
                su.setPassword(password);
                su.setAuthentication(new Authentication(Constants.ROLE_USER));        
                Long id = systemUserRemote.add(su);
                result = (id != null && id > 0);
            }
            else {
                SystemUser su = systemUsers.get(0);
                su.setSocialNumber(socialNumber);
                su.setEmail(email);
                su.setUsername(username);
                su.setEnabled(true);
                su.setPassword(password);
                su.setAuthentication(new Authentication(Constants.ROLE_USER));
                result = systemUserRemote.update(su);
            }
        }
        String xml = "";
        xml += "<result>";
            xml += result;
        xml += "</result>";
        return xml;
    }  
    
    /**
     * retrieves a course of the database by id
     * @param id
     * @return
     */
    @Path("/disableXml/{username}")
    @GET
    @ProduceMime("application/xml")
    public String disableXml(@PathParam("username") String username, @Context HttpServletRequest request) {
        String ipRequest = request.getRemoteAddr();
        WsEnabledRemote wsEnabledRemote = getWsEnabledRemote();
        if (!wsEnabledRemote.ipEnabled(ipRequest)) {
            String xml = "<result>null</result>";
            return xml;
        }        
        boolean result = false;
        if (username != null && ! "".equals(username.trim())) {
            List<SystemUser> systemUsers = systemUserRemote.getAll();
            for (SystemUser su : systemUsers) {
                if (su.getUsername().equalsIgnoreCase(username)) {
                    boolean enabled = su.getEnabled();
                    su.setEnabled(!enabled);
                    result = systemUserRemote.update(su);
                }
            }
        }
        String xml = "";
        xml += "<result>";
            xml += result;
        xml += "</result>";
        return xml;
    }  
    
    /**
     * retrieves a course of the database by id
     * @param id
     * @return
     */
    @Path("/addJson/{email}/{socialNumber}/{username}/{password}")
    @GET
    @ProduceMime("application/json")
    public String addJson(@PathParam("email") String email, @PathParam("socialNumber") String socialNumber, @PathParam("username") String username, @PathParam("password") String password) {
        boolean result = false;
        if (email != null && ! "".equals(email.trim()) &&
            socialNumber != null && ! "".equals(socialNumber.trim()) &&
            username != null && ! "".equals(username.trim()) &&
            password != null && ! "".equals(password.trim())) {
            List<SystemUser> systemUsers = systemUserRemote.getByUsername(username);
            if (systemUsers == null || systemUsers.size() == 0) {
                SystemUser su = new SystemUser();
                su.setSocialNumber(socialNumber);
                su.setEmail(email);
                su.setUsername(username);
                su.setEnabled(true);
                password = systemUserRemote.encrypt(password);
                su.setPassword(password);
                su.setAuthentication(new Authentication(Constants.ROLE_USER));        
                Long id = systemUserRemote.add(su);
                result = (id != null && id > 0);
            }
            else {
                SystemUser su = systemUsers.get(0);
                su.setSocialNumber(socialNumber);
                su.setEmail(email);
                su.setUsername(username);
                su.setEnabled(true);
                su.setPassword(password);
                su.setAuthentication(new Authentication(Constants.ROLE_USER));
                result = systemUserRemote.update(su);
            }
        }
        String xml = "";
        xml += "{";
            xml += "\"result\":\"" + result + "\"";
        xml += "}";
        return xml;
    }    

}

