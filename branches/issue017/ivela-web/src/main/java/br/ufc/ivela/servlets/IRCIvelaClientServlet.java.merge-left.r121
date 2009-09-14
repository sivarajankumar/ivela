/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.servlets;

import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.context.SecurityContextHolder;

/**
 *
 * @author jefferson
 */
public class IRCIvelaClientServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        Long oid = 0L;
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj != null && obj instanceof SystemUser) {
            oid = ((SystemUser) obj).getId();
            System.out.println("oid: " +oid);
        }
        String email = (String)request.getSession().getAttribute("USER_EMAIL");
        
        if(email==null || email.equals("")){
           
            SystemUserRemote systemUserRemote = getUserVoiceRemote();
            if(systemUserRemote!=null){
               SystemUser systemUser = systemUserRemote.get(oid);
               email = systemUser.getEmail();
               System.out.println("email from db: " + email);
               request.getSession().putValue("USER_EMAIL", email);
            }
        }
        
        String at = "@";
        int pos = 0;
        for(int i=0; i < email.length(); i++){
            if(email.charAt(i) == at.charAt(0)) pos = i;
        }
        email = email.substring(0,pos);
        System.out.println("nick from session: " + email);
        response.setCharacterEncoding("ISO-8859-1");
        response.getWriter().println(   "<html>\n" +
                                        "<head>\n" +
                                        "<title>IRC Ivela Client - #room_dis"+request.getParameter("discipline.id")+"_course"+request.getParameter("course.id")+"</title>\n" +
                                        "</head>\n" +
                                        "<body>\n" +
                                        "<h1>IRC Ivela Client - #room_dis"+request.getParameter("discipline.id")+"_course"+request.getParameter("course.id")+"</h1><hr>\n" +
                                        "<applet code=\"org.jdamico.ircivelaclient.view.HandleApplet\"\n" +
                                        "archive=\"http://"+request.getServerName()+"/public_content/ircivelaclient/ircivelaclient.jar\" \n" +
                                        "width=\"820\" height=\"480\">\n" +
                                        "<param name=\"server\" value=\"200.17.41.212\" >\n" +
                                        "<param name=\"channel\" value=\"#room_dis"+request.getParameter("discipline.id")+"_course"+request.getParameter("course.id")+"\" >\n" +
                                        "<param name=\"nick\" value=\""+email+"\" >\n" +
                                        "<param name=\"teacher\" value=\"damico\" >\n" +
                                        "<param name=\"bgcolor\" value=\"FDF1D9\" >" +
                                        "</applet> \n" +
                                        "</body>\n" +
                                        "</html>\n");
        
        
    }
    
    private SystemUserRemote getUserVoiceRemote() {
        SystemUserRemote systemUserRemote = null;

        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("SystemUserBean#br.ufc.ivela.ejb.interfaces.SystemUserRemote");
            systemUserRemote = (SystemUserRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, SystemUserRemote.class);
        } catch (NamingException e) {
            e.printStackTrace();
            systemUserRemote = null;
        }

        return systemUserRemote;
    }
}
