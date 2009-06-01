/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.servlets;

import br.ufc.ivela.commons.model.Exercise;
import br.ufc.ivela.ejb.impl.ExerciseBean;
import br.ufc.ivela.ejb.interfaces.AnswerStudentExerciseRemote;
import br.ufc.ivela.ejb.interfaces.ExerciseRemote;
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
public class VoiceServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String paramGrade = request.getParameter("voice_param");
        //to create student exercise
       /* String exerciseId = request.getParameter("exerciseId");
        String studentId = request.getParameter("studentId");
        
        //grade
        String grade = request.getParameter("grade");
        
        String studentExerciseId = null;
        
        ExerciseRemote exerciseRemote = getExerciseRemote();
        Exercise exercise = exerciseRemote.get(2L);*/
        //System.out.println("SERVLET: " + paramGrade);
    }
    
    private ExerciseRemote getExerciseRemote() {
        ExerciseRemote exerciseRemote = null;
        
        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("ExerciseBean#br.ufc.ivela.ejb.interfaces.ExerciseRemote");
            exerciseRemote = (ExerciseRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, ExerciseRemote.class);
        } catch (NamingException e) {
            e.printStackTrace();
            exerciseRemote = null;
        }
        
        return exerciseRemote;
    }
    
    private AnswerStudentExerciseRemote getAnswerStudentExerciseRemote() {
        AnswerStudentExerciseRemote answerStudentExerciseRemote = null;
        
        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("AnswerStudentExerciseBean#br.ufc.ivela.ejb.interfaces.AnswerStudentExerciseRemote");
            answerStudentExerciseRemote = (AnswerStudentExerciseRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, ExerciseRemote.class);
        } catch (NamingException e) {
            e.printStackTrace();
            answerStudentExerciseRemote = null;
        }
        
        return answerStudentExerciseRemote;
    }
}
