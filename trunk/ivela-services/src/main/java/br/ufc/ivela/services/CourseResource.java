/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.services;

import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.GenericDaoImpl;
import br.ufc.ivela.commons.model.Course;
import com.thoughtworks.xstream.XStream;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ProduceMime;

/**
 *
 * @author maristella
 */
@Path("course")
public class CourseResource {

    @Context
    private UriInfo context;
    private XStream xStream = new XStream();
    private GenericDao<Course> daoCourse = new GenericDaoImpl(Course.class);


    /** Creates a new instance of CourseResource */
    public CourseResource() {
        xStream.alias("course", Course.class);
    }

    /**
     * retrieves a course of the database by id
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
        Course course = daoCourse.get(new Long(id));
        if (course == null) {
            return xStream.toXML(null);
        }
        xStream.alias("course", Course.class);
        return xStream.toXML(course);

    }

    /**
     * retrieves a course of the database    
     * @return
     */
    @Path("/get")
    @GET
    @ProduceMime("application/xml")
    public String get() {
        List<Course> courseList = daoCourse.getAll();
        if (courseList == null) {
            return xStream.toXML(null);
        }
        xStream.alias("list", List.class);
        xStream.alias("course", Course.class);
        return xStream.toXML(courseList);
    }

    /**
     * remove a course of the database by id
     * @param id
     * @return
     */
    @Path("/remove/{id}")
    @GET
    @ProduceMime("application/xml")
    public String remove(@PathParam("id") String id) {
        if (id == null || "".equals(id.trim())) {
            return xStream.toXML(null);     // removes the post according to the parameter id
        }
        boolean result = daoCourse.remove(daoCourse.get(new Long(id)));
        return xStream.toXML(result);
    }

    /**
     * add a course on the database
     * @param gradeId
     * @param name
     * @return
     */
    @Path("/add/{name}")
    @GET
    @ProduceMime("application/xml")
    public String add(
           @PathParam("name") String name) {

        Course courseObj = new Course();
        
        courseObj.setName(name);

        Long result = (Long) daoCourse.save(courseObj);
        return xStream.toXML(result);
    }
    /**
     * Retrieves representation of an instance of br.ufc.ivela.services.CourseResource
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
     * PUT method for updating or creating an instance of CourseResource
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

