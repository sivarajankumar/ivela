/*
 *  QuestionResource
 *
 * Created on May 20, 2008, 8:57 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.ufc.ivela.services;

import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.GenericDaoImpl;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.SystemUser;
import com.thoughtworks.xstream.XStream;
import java.util.Date;
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
 * @author leonardo Moreira/Emanuelle Vieira
 * 
 * This service provides the functionality of interaction with the question application
 * 
 * Default to question type is
 *  1 -> Question subjective
 *  2 -> Question objective
 *  3 -> Question auditive 
 *  
 */
@Path("question")
public class QuestionResource {

    @Context
    private UriInfo context;
    private XStream xStream = new XStream();
    private GenericDao<SystemUser> daoSystemUser = new GenericDaoImpl(SystemUser.class);
    private GenericDao<Question> daoQuestion = new GenericDaoImpl(Question.class);

    /** Creates a new instance of QuestionResource */
    public QuestionResource() {
    }

    /**
     * Retrieves a question of the database by id
     *@param id
     *@return
     */ 
    @Path("/get/{id}")
    @GET
    @ProduceMime("application/xml")
    public String get(@PathParam("id") String id) {
        if (id == null || "".equals(id.trim())) {
            return xStream.toXML(null);
        }
        Question question = daoQuestion.get(new Long(id));
        if (question == null) {
            return xStream.toXML(null);
        }
        return xStream.toXML(question);
    }

    /**
     * Retrieves all question of specific type of the database 
     * @param type
     * @return
     */
    @Path("/getByType/{type}")
    @GET
    @ProduceMime("application/xml")
    public String getByType(@PathParam("type") String type) {
        if (type == null || "".equals(type.trim())) {
            return xStream.toXML(null);
        }
        Question questionObj = new Question();
        questionObj.setType(Integer.parseInt(type));
        List<Question> result = daoQuestion.getByExample(questionObj);
        if (result == null) {
            return xStream.toXML(null);
        }
        return xStream.toXML(result);
    }

    /**
     * Retrieves all question of the database 
     * @return
     */
    @Path("/getAll")
    @GET
    @ProduceMime("application/xml")
    public String getAll() {
        
        List<Question> result = daoQuestion.getAll();
        if (result == null) {
            return xStream.toXML(null);
        }
        return xStream.toXML(result);
    }

    /**
     * Remove a question of the database by id
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
        boolean result = daoQuestion.remove(new Long(id));
        return xStream.toXML(result);
    }
    
     /**
     * edit a question on the database
     * @param id
     * @param question
     * @return
     */
    @Path("/edit/{id}/{description}")
    @GET
    @ProduceMime("application/xml")
    public String edit(
            @PathParam("id") String id,
            @PathParam("description") String description) {
        if (id == null || "".equals(id.trim())) {
            return xStream.toXML(null);
        }
        Question questionObj = daoQuestion.get(new Long(id));
        questionObj.setQuestion(description);
        boolean result = daoQuestion.update(questionObj);
        return xStream.toXML(result);
    }

    /**
     * This method provides the functionality to add a question
     * specifying its parameters
     * Case question type is subjective,add too a subjective question into db 
     * @param question
     * @param type
     * @param createdBy
     * @return
     */
    @Path("/add/{description}/{type}/{createdBy}")
    @GET
    @ProduceMime("application/xml")
    public String add(
            @PathParam("description") String description,
            @PathParam("type") String type,
            @PathParam("createdBy") String createdBy) {

        Question questionObj = new Question();
        SystemUser createdByObj = daoSystemUser.get(new Long(createdBy));

        questionObj.setQuestion(description);
        questionObj.setType(Integer.parseInt(type));
        questionObj.setCreatedBy(createdByObj);
        questionObj.setCreatedAt(new Date());
        Long result = (Long) daoQuestion.save(questionObj);
        /*
        if (Integer.parseInt(type) == 1) {
            SubjectiveQuestion subjectiveQuestion = new SubjectiveQuestion();
            subjectiveQuestion.setQuestion(questionObj);
            daoSubjectiveQuestion.save(subjectiveQuestion);
        }*/

        return xStream.toXML(result);
    }
}
