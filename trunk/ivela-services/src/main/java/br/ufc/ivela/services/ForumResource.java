/*
 *  ForumResource
 *
 * Created on May 14, 2008, 1:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.ufc.ivela.services;

import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.GenericDaoImpl;
import br.ufc.ivela.commons.model.Forum;
import br.ufc.ivela.commons.model.Grade;
import com.thoughtworks.xstream.XStream;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ProduceMime;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Leonardo Oliveira Moreira / Emanuelle Vieira
 * 
 * This service provides the functionality of interaction with the forum application
 */
@Path("forum")
public class ForumResource {
    @Context
    private UriInfo context;
    private XStream xStream = new XStream();
    private GenericDao<Forum> daoForum = new GenericDaoImpl(Forum.class);
    private GenericDao<Grade> daoGrade = new GenericDaoImpl(Grade.class);

    /** Creates a new instance of ForumResource */
    public ForumResource() {
        
    }

    /**
     * This method provides the functionality to redeem a list of forums by an identifier
     * 
     * @param id
     * @return
     */
    @Path("/get/{id}")
    @GET
    @ProduceMime("application/xml")
    public String get(@PathParam("id") String id) {
        if (id == null || "".equals(id.trim()))
            return xStream.toXML(null);
        
        Forum forum = daoForum.get(new Long(id));
        if (forum == null)
            return xStream.toXML(null);
        xStream.alias("forum", Forum.class);
        return xStream.toXML(forum);
    }
    
    /**
     * This method provides the functionality to redeem a list of all forums
     * 
     * @return
     */
    @Path("/get")
    @GET
    @ProduceMime("application/xml")
    public String get() {
        List<Forum> forumList = daoForum.getAll();
        if (forumList == null)
            return xStream.toXML(null);
        xStream.alias("list", List.class);
        xStream.alias("forum", Forum.class);
        return xStream.toXML(forumList);
    }

    /**
     * This method provides the functionality to remove a forum for an identifier
     * 
     * @param id
     * @return
     */
    @Path("/remove/{id}")
    @GET
    @ProduceMime("application/xml")
    public String remove(@PathParam("id") String id) {
        if (id == null || "".equals(id.trim()))
            return xStream.toXML(null);
        boolean result = daoForum.remove(daoForum.get(new Long(id)));
        return xStream.toXML(result);
    }

    /**
     * This method provides the functionality to add a forum specifying its parameters
     * 
     * @param gradeId
     * @param title
     * @param studentCreatedTopic
     * @param studentUploadPost
     * @param studentUploadRepository
     * @param studentLinkPost
     * @return
     */
    @Path("/add/{gradeId}/{title}/{studentCreatedTopic}/{studentUploadPost}/{studentUploadRepository}/{studentLinkPost}")
    @GET
    @ProduceMime("application/xml")
    public String add(
            @PathParam("gradeId") String gradeId, 
            @PathParam("title") String title, 
            @PathParam("studentCreatedTopic") String studentCreatedTopic, 
            @PathParam("studentUploadPost") String studentUploadPost, 
            @PathParam("studentUploadRepository") String studentUploadRepository, 
            @PathParam("studentLinkPost") String studentLinkPost) {
        
        Forum forum = new Forum();
        br.ufc.ivela.commons.model.Grade grade = daoGrade.get(new Long(gradeId));
        
        forum.setGrade(grade);
        forum.setTitle(title);
        forum.setStudentCreateTopic(new Boolean(studentCreatedTopic));
        forum.setStudentUploadPost(new Boolean(studentUploadPost));
        forum.setStudentUploadRepository(new Boolean(studentUploadRepository));
        forum.setStudentLinkPost(new Boolean(studentLinkPost));

        Long id = (Long) daoForum.save(forum);
        return xStream.toXML(id);
    }

    /**
     * Retrieves representation of an instance of br.ufc.ivela.services.ForumResource
     * @return an instance of java.lang.String
     */
    /*
    @GET
    @ProduceMime("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    */

    /**
     * PUT method for updating or creating an instance of ForumResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    /*
    @PUT
    @ConsumeMime("application/xml")
    public void putXml(String content) {
        
    }
    */
}
