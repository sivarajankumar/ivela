/*
 *  PostResource
 *
 * Created on June 12, 2008, 1:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.ufc.ivela.services;

import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.GenericDaoImpl;
import br.ufc.ivela.commons.model.SystemUser;
import com.thoughtworks.xstream.XStream;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ProduceMime;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author rodrigo
 */
@Path("systemUser")
public class SystemUserResource {

    @Context
    private UriInfo context;
    
    private XStream xStream = new XStream();
    private GenericDao<SystemUser> daoSystemUser = new GenericDaoImpl(SystemUser.class);

    /** Creates a new instance of PostResource */
    public SystemUserResource() {
    }
    
    /**
     * add a SystemUser on the database
     * @param topicId
     * @param title
     * @param createdBy
     * @param post
     * @return
     */
    @GET
    @Path("/add/{name}/{email}/{userName}/{password}/{salt}")
    @ProduceMime("application/xml")
    public String add(@PathParam("name") String name,
            @PathParam("email") String email,
            @PathParam("userName") String username,
            @PathParam("password") String password,
            @PathParam("salt") String salt) {
        
        // instantiates the object and assign their attributes
        SystemUser su = new SystemUser();
        su.setEmail(email);
        su.setUsername(username);
        su.setPassword(password);
        
        // saves the object just created
        daoSystemUser.save(su);
        
        // TODO: implementing this call
        // call the method to attach the files
        
        return "ok";
    }
    
    /**
     * retrieves a list of SystemUser
     * @param topicId
     * @return
     */
    @GET
    @Path("/list")
    @ProduceMime("application/xml")
    public String list() {        
        // retrieves a list of all SystemUser
        return xStream.toXML(daoSystemUser.getAll());    
    }

    /**
     * retrieves a SystemUser of the database by id
     * @param systemUserId
     * @return
     */
    @GET
    @Path("/get/{systemUserId}")
    @ProduceMime("application/xml")
    public String get(@PathParam("systemUserId") String systemUserId) {
        SystemUser su = daoSystemUser.get(Long.valueOf(systemUserId));        
         
        xStream.alias("systemUser", SystemUser.class);
        return xStream.toXML(su);
    }

    /**
     * removes a SystemUser of the database
     * @param systemUserId
     * @return
     */
    @GET
    @Path("/remove/{systemUserId}")
    @ProduceMime("application/xml")
    public String remove(@PathParam("systemUserId") String systemUserId) {
        // removes the SystemUser according to the parameter systemUserId
        daoSystemUser.remove(daoSystemUser.get(Long.valueOf(systemUserId)));
        
        // returns 
        return "ok";
    }
}