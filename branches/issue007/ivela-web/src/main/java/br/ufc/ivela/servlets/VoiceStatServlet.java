/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.servlets;

import br.ufc.ivela.commons.model.UserVoice;
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
public class VoiceStatServlet extends HttpServlet{

    public static final int WORD_1 = 1; 
    public static final int WORD_2 = 2; 
    public static final int WORD_3 = 3; 
    public static final int WORD_4 = 4; 
    public static final int WORD_5 = 5; 
    public static final int WORD_6 = 6; 
    public static final int WORD_7 = 7; 
    public static final int WORD_8 = 8; 
    public static final int WORD_9 = 9; 
    public static final int WORD_10 = 10; 
    
    
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException{
        
          
          
          UserVoiceRemote userVoiceRemote = this.getUserVoiceRemote();
          
          if(userVoiceRemote!=null){
          
              String uv_name = request.getParameter("uv_name");
              //System.out.println("-->"+uv_name);
              
              if(uv_name == null)
                  uv_name = "anomymous";
              
                  
          
              UserVoice userVoice = new UserVoice();
              userVoice.setName(uv_name);
              Long uvId = userVoiceRemote.add(userVoice);
              
               
              
              String words = request.getParameter("words");
              if(words!=null){
                  String wordsIdAndTries[] = words.split("B");
                  for(int i=0;i<wordsIdAndTries.length;i++){
               
                      String[] wr = wordsIdAndTries[i].split("A");
                      
                      int wordId = Integer.parseInt(wr[0]);
                      String wordText = this.getWord(wordId);
                      
                      int tries = Integer.parseInt(wr[1]);
                      
                       
                       userVoiceRemote.addWordVoiceForUserVoice(uvId, wordText, tries);
                      
                  }
                  
              }
          }
          
          
        
    }
    
    private String getWord(int word){
        String res = "";
        switch(word){
            case WORD_1:
                res = "name";
               break;
            case WORD_2:
                res = "england";
               break;
            case WORD_3:
                res = "dial";
               break;
               
            case WORD_4:
                res = "drink";
               break;
               
            case WORD_5:
                res = "student";
               break;
               
            case WORD_6:
                res = "computer";
               break;
               
            case WORD_7:
                res = "delete";
               break;
               
            case WORD_8:
                res = "dictionary";
               break;
               
            case WORD_9:
                res = "element";
               break;
               
            case WORD_10:
                res = "work";
               break;
        }
        return res;
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
