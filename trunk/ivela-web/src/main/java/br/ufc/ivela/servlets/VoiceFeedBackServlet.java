/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.servlets;

import br.ufc.ivela.ejb.interfaces.UserVoiceRemote;
import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefferson
 */
public class VoiceFeedBackServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        
        //System.out.println("SERVLET");
        
        String mark = request.getParameter("mark");
        String coment = request.getParameter("coment");
        //System.out.println("mark: " + mark);
        //System.out.println("coment: " + coment);
        
        //System.out.println("SERVLET MARK: " + mark);
        //System.out.println("SERVLET COMENT: " + coment);
        
        if(mark!=null && coment!=null){
            UserVoiceRemote userVoiceRemote = this.getUserVoiceRemote();
            userVoiceRemote.addVoiceFeedBack(Integer.parseInt(mark), coment);
        }
    
        /*PrintWriter printWriter = response.getWriter();
        String html = "<html><body><h2>Resultados enviados. <br />Obrigado por participar.<h2/></body></html>";
        printWriter.print(html);
        printWriter.flush();
        printWriter.close();*/
        /*response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.print("mark: " + mark + " coment " + coment);
        printWriter.flush();
        printWriter.close();*/
    }
    
    private UserVoiceRemote getUserVoiceRemote() {
        UserVoiceRemote userVoiceRemote = null;
        
        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("UserVoiceBean#br.ufc.ivela.ejb.interfaces.UserVoiceRemote");
            userVoiceRemote = (UserVoiceRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, UserVoiceRemote.class);
        } catch (NamingException e) {
            e.printStackTrace();
            userVoiceRemote = null;
        }
        
        return userVoiceRemote;
    }
    
}
