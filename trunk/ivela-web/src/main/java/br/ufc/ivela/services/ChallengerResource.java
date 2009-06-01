/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.services;

import br.ufc.ivela.commons.model.ChallengeItems;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.ejb.interfaces.ChallengeItemsRemote;
import java.util.Date;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.ConsumeMime;
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
@Path("challenger")
public class ChallengerResource {

    @Context
    private UriInfo context;
    private ChallengeItemsRemote challengeItemsRemote = getCourseRemote();

    /**
     * adds a xml word definition
     * @param xml
     * @return
     */
    @Path("/add/{xml}")
    @GET
    @ConsumeMime("text/plain")
    @ProduceMime("text/plain")
    public String add(@PathParam("xml") String xml) {
        
        ChallengeItems items = new ChallengeItems();
        items.setName(xml);
        items.setXml(xml);
        items.setDependency(1L);
        items.setTimestamp(new Date());
        items.setCourse(new Course(1L));
        items.setDiscipline(new Discipline(1L));
        items.setUnit(new Unit(1L));
        
        challengeItemsRemote.add(items);
        
        return "added!";
    }
    
    @Path("/get/{name}")
    @GET
    @ProduceMime("text/plain")
    public String get(@PathParam("name") String name) {
                 
        ChallengeItems items = challengeItemsRemote.get(name);
        
        return "name: "+items.getName()+ "\ntimestamp: "+items.getTimestamp();
    }
    
    private ChallengeItemsRemote getCourseRemote() {
        ChallengeItemsRemote challengerRemote;
        
        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("br.ufc.ivela.ejb.interfaces.ChallengeItemsRemote");
            challengerRemote = (ChallengeItemsRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, ChallengeItemsRemote.class);
        }
        catch (NamingException e) {
            e.printStackTrace();
            challengerRemote = null;
        }
        return challengerRemote;
    }
}

