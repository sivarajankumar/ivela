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
import br.ufc.ivela.commons.model.ObjectiveQuestion;
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
 * Question application
 */
@Path("objectiveQuestion")
public class ObjectiveQuestionResource {

    @Context
    private UriInfo context;
    private XStream xStream = new XStream();
    private GenericDao<ObjectiveQuestion> daoObjectiveQuestion = new GenericDaoImpl(ObjectiveQuestion.class);
    private GenericDao<ObjectiveAnswer> daoObjectiveAnswer = new GenericDaoImpl(ObjectiveAnswer.class);
    private GenericDao<Question> daoQuestion = new GenericDaoImpl(Question.class);

    /** Creates a new instance of QuestionResource */
    public ObjectiveQuestionResource() {
    }

     /**
     * Retrieves a objective question of the database by id
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

        ObjectiveQuestion result = daoObjectiveQuestion.get(new Long(id));

        if (result == null) {
            return xStream.toXML(null);
        }
        return xStream.toXML(result);
    }

     /**
     * Retrieves a objective question of the database by questionId
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

        List<ObjectiveQuestion> list = daoObjectiveQuestion.getByFK("question.id",question);
        if (list == null) {
            return xStream.toXML(null);
        }
        return xStream.toXML(list);
    }

    
     /**
     * edit a objective question on the database
     * @param id
     * @param correct_answer
     * @return
     */
    @Path("/edit/{id}/{correct_answer}")
    @GET
    @ProduceMime("application/xml")
    public String edit(
            @PathParam("id") String id,
            @PathParam("correct_answer") String correct_answer) {
        if (id == null || "".equals(id.trim())) {
            return xStream.toXML(null);
        }

        ObjectiveQuestion objectiveQuestion = daoObjectiveQuestion.get(new Long(id));
        ObjectiveAnswer objectiveAnswer = daoObjectiveAnswer.get(new Long(correct_answer));
        objectiveQuestion.setCorrectAnswer(objectiveAnswer);
        boolean result = daoObjectiveQuestion.update(objectiveQuestion);
        return xStream.toXML(result);
    }

     /**
     * This method provides the functionality to add a objective question
     * specifying its parameters
     * @param question
     * @param correct_answer
     * @return
     */
    @Path("/add/{question}/{correct_answer}")
    @GET
    @ProduceMime("application/xml")
    public String add(
            @PathParam("question") String question,
            @PathParam("correct_answer") String correct_answer) {


        Question questionObj = daoQuestion.get(new Long(question));
        ObjectiveAnswer objectiveAnswer = daoObjectiveAnswer.get(new Long(correct_answer));

        ObjectiveQuestion objectiveQuestion = new ObjectiveQuestion();
        objectiveQuestion.setQuestion(questionObj);
        objectiveQuestion.setCorrectAnswer(objectiveAnswer);

        Long result = (Long) daoObjectiveQuestion.save(objectiveQuestion);
        return xStream.toXML(result);
    }
}
