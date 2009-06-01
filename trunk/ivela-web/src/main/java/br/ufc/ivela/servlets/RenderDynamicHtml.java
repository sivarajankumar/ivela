/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.servlets;

import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.UnitContentRemote;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 *
 * @author jdamico
 */
public class RenderDynamicHtml extends HttpServlet {
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setDateHeader("Expires",System.currentTimeMillis(  ) + 24*60*60*1000);
        UnitContentRemote unitContentRemote = this.getUnitContentRemote();
        UnitContent unitContent = unitContentRemote.get(new Long(request.getParameter("unitContent.id")));
        String htmlString = unitContent.getDescription();
        htmlString = htmlString.replaceAll(">", ">\n");
        response.getWriter().println(htmlString);
     }


     private UnitContentRemote getUnitContentRemote() {
        UnitContentRemote unitContentRemote = null;
        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("UnitContentBean#br.ufc.ivela.ejb.interfaces.UnitContentRemote");
            unitContentRemote = (UnitContentRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, UnitContentRemote.class);
        } catch (NamingException e) {
            e.printStackTrace();
            unitContentRemote = null;
        }

        return unitContentRemote;
    }
}
