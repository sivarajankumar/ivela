/*
 *  ExamResource
 *
 * Created on May 20, 2008, 10:44 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.ufc.ivela.services;

import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.GenericDaoImpl;
import br.ufc.ivela.commons.model.Exam;
import br.ufc.ivela.commons.model.Grade;
import com.thoughtworks.xstream.XStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ProduceMime;

/**
 * REST Web Service
 *
 * @author leoomoreira e maristella
 */
@Path("exam")
public class ExamResource {

    @Context
    private UriInfo context;
    private XStream xStream = new XStream();
    private GenericDao<Exam> daoExam = new GenericDaoImpl(Exam.class);
    private GenericDao<Grade> daoGrade = new GenericDaoImpl(Grade.class);

    /** Creates a new instance of ExamResource */
    public ExamResource() {
        xStream.alias("exam", Exam.class);
    }

    /**
     * retrieves a exam of the database by id
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
        Exam exam = daoExam.get(new Long(id));
        if (exam == null)
            return xStream.toXML(null);
        xStream.alias("exam", Exam.class);
        return xStream.toXML(exam);

    }

    /**
     * retrieves a exam of the database    
     * @return
     */
    @Path("/get")
    @GET
    @ProduceMime("application/xml")
    public String get() {
        List<Exam> examList = daoExam.getAll();
        if (examList == null)
            return xStream.toXML(null);
        xStream.alias("list", List.class);
        xStream.alias("exam", Exam.class);
        return xStream.toXML(examList);
    }

    /**
     * remove a exam of the database by id
     * @param id
     * @return
     */
    @Path("/remove/{id}")
    @GET
    @ProduceMime("application/xml")
    public String remove(@PathParam("id") String id) {
        if (id == null || "".equals(id.trim()))
            return xStream.toXML(null);     // removes the post according to the parameter id
        boolean result = daoExam.remove(daoExam.get(new Long(id)));
        return xStream.toXML(result);
    }

    /**
     * add a exam on the database
     * @param gradeId
     * @param title
     * @param startDatetime
     * @param endDatetime
     * @return
     */
    @Path("/add/{gradeId}/{title}/{startDatetime}/{endDatetime}")
    @GET
    @ProduceMime("application/xml")
    public String add(
            @PathParam("gradeId") String gradeId,
            @PathParam("title") String title,
            @PathParam("startDatetime") String startDatetime,
            @PathParam("endDatetime") String endDatetime) {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

        Exam examObj = new Exam();

        examObj.setGrade(daoGrade.get(new Long(gradeId)));
        examObj.setTitle(title);

        try {
            examObj.setStartDatetime(dateFormat.parse(startDatetime));
            examObj.setEndDatetime(dateFormat.parse(endDatetime));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Long result = (Long) daoExam.save(examObj);
        return xStream.toXML(result);
    }    //id, grade, title, startDatetime, endDateTime
    /**
     * Retrieves representation of an instance of br.ufc.ivela.services.ExamResource
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
     * PUT method for updating or creating an instance of ExamResource
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
