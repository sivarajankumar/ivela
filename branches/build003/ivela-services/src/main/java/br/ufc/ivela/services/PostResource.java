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
import br.ufc.ivela.commons.model.Post;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Topic;
import com.thoughtworks.xstream.XStream;
import java.util.Date;
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
@Path("post")
public class PostResource {

    @Context
    private UriInfo context;
    
    private XStream xStream = new XStream();
    private GenericDao<Post> daoPost = new GenericDaoImpl(Post.class);
    private GenericDao<SystemUser> daoSystemUser = new GenericDaoImpl(SystemUser.class);
    private GenericDao<Topic> daoTopic = new GenericDaoImpl(Topic.class);

    /** Creates a new instance of PostResource */
    public PostResource() {
    }
    
    /**
     * add a post on the database
     * @param topicId
     * @param title
     * @param createdBy
     * @param post
     * @return
     */
    @GET
    @Path("/add/{topicId}/{title}/{createdBy}/{message}")
    @ProduceMime("application/xml")
    public String add(@PathParam("topicId") String topicId,
            @PathParam("title") String title,
            @PathParam("createdBy") String createdBy,
            @PathParam("message") String message) {
        
        // instantiates the object and assign their attributes
        Post p = new Post();
        p.setTitle(title);
        p.setCreatedBy(daoSystemUser.get(Long.valueOf(createdBy)));
        p.setCreatedAt(new Date());
        p.setMessage(message);
        p.setTopic(daoTopic.get(new Long(topicId)));  
        
        // saves the object just created
        daoPost.save(p);
        
        // TODO: implementing this call
        // call the method to attach the files
        
        return "ok";
    }
    
    /**
     * retrieves a list of Posts by topicId
     * 
     * @param topicId
     * @return
     */
    @GET
    @Path("/getByTopic/{topicId}")
    @ProduceMime("application/xml")
    public String getByTopic(@PathParam("topicId") String topicId) {
        
        // instantiates the object and assign their attributes
        Post p = new Post();
        p.setTopic(daoTopic.get(Long.valueOf(topicId)));  
        
        // saves the object just created
        return xStream.toXML(daoPost.getByFK("topic.id", topicId));
    
    }

    /**
     * retrieves a post of the database by id
     * @param postId
     * @return
     */
    @GET
    @Path("/get/{postId}")
    @ProduceMime("application/xml")
    public String get(@PathParam("postId") String postId) {
        Post post = daoPost.get(Long.valueOf(postId));        
         
        xStream.alias("post", Post.class);
        return xStream.toXML(post);
    }

    /**
     * removes a post of the database
     * @param postId
     * @return
     */
    @GET
    @Path("/remove/{postId}")
    @ProduceMime("application/xml")
    public String remove(@PathParam("postId") String postId) {
        // removes the post according to the parameter postId
        daoPost.remove(daoPost.get(Long.valueOf(postId)));
        
        // returns 
        return "";
    }
}