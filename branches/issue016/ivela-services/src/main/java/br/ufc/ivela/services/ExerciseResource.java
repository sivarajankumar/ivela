/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.services;

import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.GenericDaoImpl;
import br.ufc.ivela.commons.model.Exercise;
import br.ufc.ivela.commons.model.Grade;
import com.thoughtworks.xstream.XStream;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ProduceMime;

/**
 *REST Web Service
 * 
 * @author maristella
 */
@Path("exercise")
public class ExerciseResource {

    @Context
    private UriInfo context;
    private XStream xStream = new XStream();
    private GenericDao<Exercise> daoExercise = new GenericDaoImpl(Exercise.class);
    private GenericDao<Grade> daoGrade = new GenericDaoImpl(Grade.class);

    /**
     * Creates a new instance of ExerciseResource
     */
    public ExerciseResource() {
        xStream.alias("exercise", Exercise.class);
    }

    /**
     * retrieves a exercise of the database by id
     * @param id
     * @return
     */
    @Path("/get/{id}")
    @GET
    @ProduceMime("application/xml")
    public String get(@PathParam("id") String id) {
        if (id == null || "".equals(id.trim()))
            return xStream.toXML(null);
        Exercise exercise = daoExercise.get(new Long(id));
        if (exercise == null)
            return xStream.toXML(null);
        xStream.alias("exercise", Exercise.class);
        return xStream.toXML(exercise);

    }

    /**
     * retrieves a exercise of the database    
     * @return
     */
    @Path("/get")
    @GET
    @ProduceMime("application/xml")
    public String get() {
        List<Exercise> exerciseList = daoExercise.getAll();
        if (exerciseList == null)
            return xStream.toXML(null);
        xStream.alias("list", List.class);
        xStream.alias("exercise", Exercise.class);
        return xStream.toXML(exerciseList);
    }

    /**
     * remove a exercise of the database by id
     * @param id
     * @return
     */
    @Path("/remove/{id}")
    @GET
    @ProduceMime("application/xml")
    public String remove(@PathParam("id") String id) {
        if (id == null || "".equals(id.trim()))
            return xStream.toXML(null);         //removes the post according to the parameter id
    
        boolean result = daoExercise.remove(daoExercise.get(new Long(id)));
        return xStream.toXML(result);
    }

    /**
     * add a exercise on the database
     * @param gradeId
     * @param title
     * @param createdBy
     * @param createdAt
     * @return
     */
    @Path("/add/{gradeId}/{title}/{createdBy}")
    @GET
    @ProduceMime("application/xml")
    public String add(
            @PathParam("gradeId") String gradeId,
            @PathParam("title") String title,
            @PathParam("createdBy") String createdBy) {

        Exercise exerciseObj = new Exercise();

        exerciseObj.setGrade(daoGrade.get(new Long(gradeId)));
        exerciseObj.setTitle(title);
        //exerciseObj.setCreatedBy(new Long(createdBy));
        exerciseObj.setCreatedAt(new Date());

        Long result = (Long) daoExercise.save(exerciseObj);
        return xStream.toXML(result);
    }    //id, grade, title, startDatetime, endDateTime
    /**
     * Retrieves representation of an instance of br.ufc.ivela.services.ExerciseResource
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
     * PUT method for updating or creating an instance of ExerciseResource
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

