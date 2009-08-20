/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.services;

import br.ufc.ivela.commons.model.Dictionary;
import br.ufc.ivela.ejb.interfaces.DictionaryRemote;
import br.ufc.ivela.ejb.interfaces.ProfileRemote;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
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
@Path("dictionary")
public class DictionaryResource {

    @Context
    private UriInfo context;
    private DictionaryRemote dictionaryRemote = getDictionaryRemote();
    private XStream xStream = new XStream(new JettisonMappedXmlDriver());

    /** Creates a new instance of CourseResource */
    public DictionaryResource() {
       
    }

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
        
        // do some stuff
        
        return xml;
    }
    
    @Path("/get/{word}")
    @GET
    @ProduceMime("application/json")
    public String get(@PathParam("word") String word) {
                 
        Dictionary dictionary = dictionaryRemote.get(word);
        
        xStream.alias("busca", String.class);
                       
        return (dictionary != null) ? xStream.toXML(dictionary.getDescription()) : "";
    }
    
    private DictionaryRemote getDictionaryRemote() {
        DictionaryRemote p = null;
        
        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("DictionaryBean#br.ufc.ivela.ejb.interfaces.DictionaryRemote");
            p = (DictionaryRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, DictionaryRemote.class);
        } catch (NamingException e) {
            e.printStackTrace();
            p = null;
        }
        return p;
    }
}

