/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.servlets;

import br.ufc.ivela.ejb.interfaces.UserVoiceRemote;
import java.io.IOException;
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
public class NpdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserVoiceRemote userVoiceRemote = this.getUserVoiceRemote();

        if (userVoiceRemote != null) {
            String phrase = request.getParameter("phrase");
            String tries = request.getParameter("tries");
            //System.out.println("----"+phrase);
            //System.out.println("----"+tries);
            if(phrase!=null && tries!=null){
                Long id = userVoiceRemote.addNpdUser();
                String[] phrasesSplit = phrase.split("A");
                String[] triesSplit = tries.split("A");
                for(int i=0;i<phrasesSplit.length;i++){
                    userVoiceRemote.addNpdPhraseForNpdUser(id, new Integer(phrasesSplit[i]), new Integer(triesSplit[i]));
                }
                
            }
            
        }
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
