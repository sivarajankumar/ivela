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
import br.ufc.ivela.commons.model.ObjectiveAnswer;
import br.ufc.ivela.commons.model.Question;
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
 * @author Emanuelle
 * 
 * This service provides the functionality of interaction with the Objective 
 * Answer application
 * 
 */

@Path("objectiveAnswer")
public class ObjectiveAnswerResource {
    @Context
    private UriInfo context;
    private XStream xStream = new XStream();
    private GenericDao<ObjectiveAnswer> daoObjectiveAnswer = new GenericDaoImpl(ObjectiveAnswer.class);
    private GenericDao<Question> daoQuestion = new GenericDaoImpl(Question.class);
    

    /** Creates a new instance of QuestionResource */
    public ObjectiveAnswerResource() {
    
    }
    
     /**
     * Retrieves a objective answer of the database by id
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
        ObjectiveAnswer objectiveAnswer = daoObjectiveAnswer.get(new Long(id));
        if (objectiveAnswer == null) {
            return xStream.toXML(null);
        }
        return xStream.toXML(objectiveAnswer);
    }
    
    /**
     * Retrieves all question's objective answer of the database 
     * @param question
     * @return
     */
    @Path("/getByQuestion/{question}")
    @GET
    @ProduceMime("application/xml")
    public String getByQuestion(@PathParam("question") String question) {
        if (question == null || "".equals(question.trim())) {
            return xStream.toXML(null);
        }
        Question questioObj = daoQuestion.get(new Long(question));
        if(questioObj==null)
        {
            return xStream.toXML(null);
        }
        ObjectiveAnswer objectiveAnswer = new ObjectiveAnswer();
        objectiveAnswer.setQuestion(questioObj);
        List<ObjectiveAnswer> list = daoObjectiveAnswer.getByExample(objectiveAnswer);
        if (list == null) {
            return xStream.toXML(null);
        }
        return xStream.toXML(list);
    }

    /**
     * Remove a objective answer of the database by id
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
        boolean result = daoObjectiveAnswer.remove(new Long(id));
        return xStream.toXML(result);
    }
    
     /**
     * edit a objective answer on the database
     * @param id
     * @param answer
     * @return
     */
    @Path("/edit/{id}/{answer}")
    @GET
    @ProduceMime("application/xml")
    public String edit(
            @PathParam("id") String id,
            @PathParam("answer") String answer) {
        if (id == null || "".equals(id.trim())) {
            return xStream.toXML(null);
        }
        ObjectiveAnswer objectiveAnswer = daoObjectiveAnswer.get(new Long(id));
        objectiveAnswer.setAnswer(answer);
        boolean result = daoObjectiveAnswer.update(objectiveAnswer);
        return xStream.toXML(result);
    }
    
    /**
     * This method provides the functionality to add a objective answer
     * specifying its parameters
     * @param question
     * @param answer
     * @return
     */
    @Path("/add/{question}/{answer}")
    @GET
    @ProduceMime("application/xml")
    public String add(
            @PathParam("question") String question,
            @PathParam("answer") String answer) {
        
        
        Question questionObj = daoQuestion.get(new Long(question));
        
        ObjectiveAnswer objectiveAnswer = new ObjectiveAnswer();
        objectiveAnswer.setQuestion(questionObj);
        objectiveAnswer.setAnswer(answer);
        Long result = (Long) daoObjectiveAnswer.save(objectiveAnswer);
        return xStream.toXML(result);
    }    

    
}
