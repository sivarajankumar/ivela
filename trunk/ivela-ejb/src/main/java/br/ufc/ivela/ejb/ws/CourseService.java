/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.ws;

import br.ufc.ivela.ejb.interfaces.CourseRemote;
import com.thoughtworks.xstream.XStream;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Maristella Myrian
 */
@WebService
@Stateless
public class CourseService {

    @EJB
    private CourseRemote courseLocal;
    private XStream xStream = new XStream();
  

    @WebMethod(operationName = "get")
    public String get(@WebParam(name = "id") String id) {

        return xStream.toXML(courseLocal.get(new Long(id)));
    }

    @WebMethod(operationName = "getAll")
    public String getAll() {
        return xStream.toXML(courseLocal.getAll());
    }
}

