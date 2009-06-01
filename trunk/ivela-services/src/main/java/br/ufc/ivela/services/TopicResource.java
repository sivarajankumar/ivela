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
import br.ufc.ivela.commons.model.Topic;
import br.ufc.ivela.commons.model.Forum;
import br.ufc.ivela.commons.model.SystemUser;
import com.thoughtworks.xstream.XStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * This service provides the functionality of interaction with the topic application
 */
@Path("topic")
public class TopicResource {

    @Context
    private UriInfo context;
    private XStream xStream = new XStream();
    private GenericDao<Topic> daoTopic = new GenericDaoImpl(Topic.class);
    private GenericDao<Forum> daoForum = new GenericDaoImpl(Forum.class);
    private GenericDao<SystemUser> daoSystemUser = new GenericDaoImpl(SystemUser.class);

    /** Creates a new instance of ForumResource */
    public TopicResource() {
    }

    /**
     * This method provides the functionality to redeem a list of topics by an identifier
     * 
     * @param id
     * @return
     */    
    @Path("/get/{id}")
    @GET
    @ProduceMime("application/xml")
    public String get(@PathParam("id") String id) {
        if (id == null || "".equals(id.trim())) {
            return xStream.toXML(null);
        }
        Topic topic = daoTopic.get(new Long(id));
        if (topic == null) {
            return xStream.toXML(null);
        }
        xStream.alias("forum", Forum.class);
        xStream.alias("topic", Topic.class);
        return xStream.toXML(topic);
    }

    /**
     * This method provides the functionality to redeem a list of topics by an forum identifier
     * 
     * @param forum
     * @return
     */
    @Path("/getByForum/{forum}")
    @GET
    @ProduceMime("application/xml")
    public String getByForum(@PathParam("forum") String forum) {
        if (forum == null || "".equals(forum.trim())) {
            return xStream.toXML(null);
        }
        Forum f = daoForum.get(new Long(forum));
        Topic t = new Topic();
        t.setForum(f);
        List<Topic> result = daoTopic.getByExample(t);
        if (result == null) {
            return xStream.toXML(null);
        }
        xStream.alias("forum", Forum.class);
        xStream.alias("topic", Topic.class);
        xStream.alias("list", List.class);
        return xStream.toXML(result);
    }

    /**
     * This method provides the functionality to remove a topic for an identifier
     * 
     * @param id
     * @return
     */
    @Path("/remove/{id}")
    @GET
    @ProduceMime("application/xml")
    public String remove(@PathParam("id") String id) {
        if (id == null || "".equals(id.trim())) {
            return xStream.toXML(null);
        }
        boolean result = daoTopic.remove(new Long(id));
        return xStream.toXML(result);
    }

    @Path("/add/{forum}/{title}/{createdBy}/{description}")
    @GET
    @ProduceMime("application/xml")
    public String add(
            @PathParam("forum") String forum,
            @PathParam("title") String title,
            @PathParam("createdBy") String createdBy,
            @PathParam("description") String description) {

        Topic topic = new Topic();
        Forum f = daoForum.get(new Long(forum));
        SystemUser su = daoSystemUser.get(new Long(createdBy));
        //SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

        topic.setForum(f);
        topic.setTitle(title);
        topic.setCreatedBy(su);
        topic.setCreatedAt(new Date());
        topic.setDescription(description);

        Long result = (Long) daoTopic.save(topic);
        return xStream.toXML(result);
    }
    
}
