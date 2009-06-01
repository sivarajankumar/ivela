/*
 *  DisciplineResource
 *
 * Created on September 24, 2008, 8:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.ufc.ivela.services;

import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.ejb.interfaces.DisciplineRemote;
import com.thoughtworks.xstream.XStream;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ProduceMime;
import javax.ws.rs.ConsumeMime;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author mir0
 */

@Path("discipline")
public class DisciplineResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of DisciplineResource */
    public DisciplineResource() {
    }

    /**
     * Retrieves representation of an instance of br.ufc.ivela.services.DisciplineResource
     * @return an instance of java.lang.String
     */
    @Path("/get/{id}/{xmlStr}")
    @GET
    @ProduceMime("application/xml")
    public String getXml( @PathParam("id") String id, @PathParam("xmlStr") String xmlStr ) {
        
        XStream xstream = new XStream();
        Discipline discipline = null;
        //System.out.println(xmlStr);
        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("DisciplineBean#br.ufc.ivela.ejb.interfaces.DisciplineRemote");
            DisciplineRemote disciplineRemote = (DisciplineRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, DisciplineRemote.class);
            discipline = disciplineRemote.get(new Long(id));
            
        }
        catch (NamingException e) {
            e.printStackTrace();
        }
        xstream.alias("discipline", Discipline.class);
        xstream.omitField(Discipline.class, "exerciseDisciplineCollection");
        return xstream.toXML(discipline);
    }

    /**
     * PUT method for updating or creating an instance of DisciplineResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @ConsumeMime("application/xml")
    public void putXml(String content) {
    }
}
