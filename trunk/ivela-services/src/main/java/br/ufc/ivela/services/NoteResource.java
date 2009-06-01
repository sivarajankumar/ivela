/*
 *  NoteResource
 *
 * Created on June 17, 2008, 1:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.ufc.ivela.services;

import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.GenericDaoImpl;
import br.ufc.ivela.commons.model.Note;
import br.ufc.ivela.commons.model.SystemUser;
import com.thoughtworks.xstream.XStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ProduceMime;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author nelson
 */
@Path("note")
public class NoteResource {

    @Context
    private UriInfo context;
    private XStream xStream = new XStream();
    private GenericDao<Note> daoNote = new GenericDaoImpl(Note.class);
    private GenericDao<SystemUser> daoSystemUser = new GenericDaoImpl(SystemUser.class);

    /** Creates a new instance of NoteResource */
    public NoteResource() {
        xStream.alias("note", Note.class);
    }

    /**
     * Method of consultation from an identification key
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
        
        Note note = daoNote.get(new Long(id));
        
        if (note == null) {
            return xStream.toXML(null);
        }
        return xStream.toXML(note);
    }

    /**
     * Method of consultation from identification user's
     * @param systemUser
     * @return
     */
    @Path("/getByUser/{systemUser}")
    @GET
    @ProduceMime("application/xml")
    public String getByUser(@PathParam("systemUser") String systemUser) {

        List<Note> noteList = daoNote.getByFK("systemUser.id", systemUser);

        if (noteList == null) {
            return xStream.toXML(null);
        }
        
        return xStream.toXML(noteList);
    }

    /**
     * Method of consultation from date
     * @param datetime
     * @return
     */
    @Path("/getByDate/{datetime}")
    @GET
    @ProduceMime("application/xml")
    public String getByDate(@PathParam("datetime") String datetime) {

        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Note note = new Note();

        try {
            note.setDatetime(dateTimeFormat.parse(datetime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Note> noteList = daoNote.getByExample(note);

        if (noteList == null) {
            return xStream.toXML(null);
        }
        
        return xStream.toXML(noteList);
    }

    /**
     * Method that returns all Notes
     * @return
     */
    @Path("/get")
    @GET
    @ProduceMime("application/xml")
    public String get() {
        List<Note> noteList = daoNote.getAll();
        if (noteList == null) {
            return xStream.toXML(null);
        }
        return xStream.toXML(noteList);
    }

    /**
     * removal from the key to identification
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
        boolean result = daoNote.remove(daoNote.get(new Long(id)));
        return xStream.toXML(result);
    }

    /**
     * adds a new Note
     * @param systemUser
     * @param title
     * @param description
     * @param datetime
     * @param alert
     * @param isDropable
     * @param createdBy
     * @return
     */
    @Path("/add/{systemUser}/{title}/{description}/{datetime}/{alert}/{isDropable}/{createdBy}")
    @GET
    @ProduceMime("application/xml")
    public String add(
            @PathParam("systemUser") String systemUser,
            @PathParam("title") String title,
            @PathParam("description") String description,
            @PathParam("datetime") String datetime,
            @PathParam("alert") String alert,
            @PathParam("isDropable") String isDropable,
            @PathParam("createdBy") String createdBy) {

        
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.US);

        Note n = new Note();

        n.setSystemUser(daoSystemUser.get(new Long(systemUser)));
        n.setNote(title);
        n.setDescription(description);

        try {
            n.setDatetime(dateTimeFormat.parse(datetime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        n.setAlert(new Boolean(alert));
        n.setIsDropable(new Boolean(isDropable));
        //n.setCreatedBy(new Long(createdBy));
        n.setCreatedAt(new Date());

        Long id = (Long) daoNote.save(n);

        return xStream.toXML(id);
    }
}
