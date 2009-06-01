/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action.admin;

import br.ufc.ivela.commons.model.AnswerStudentExam;
import br.ufc.ivela.commons.model.AnswerStudentExercise;
import br.ufc.ivela.commons.model.AnswerSubjectiveQuestionStudentExam;
import br.ufc.ivela.commons.model.AnswerSubjectiveQuestionStudentExercise;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Exam;
import br.ufc.ivela.commons.model.Exercise;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.StudentExam;
import br.ufc.ivela.commons.model.StudentExercise;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Transcript;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.AnswerStudentExamRemote;
import br.ufc.ivela.ejb.interfaces.AnswerStudentExerciseRemote;
import br.ufc.ivela.ejb.interfaces.ExerciseRemote;
import br.ufc.ivela.ejb.interfaces.HistoryRemote;
import br.ufc.ivela.web.action.GenericAction;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emanuelle
 */
public class ScorecardAction extends GenericAction {

    private Exercise exercise;
    private Exam exam;
    private ExerciseRemote exerciseRemote;
    private InputStream inputStream;
    private Grade grade;
    private SystemUser student;
    private Course course;
    private UnitContent unitContent;
    private Transcript transcript;
    private XStream xStream = new XStream(new JettisonMappedXmlDriver());
    private String requisitesString;
    private AnswerSubjectiveQuestionStudentExercise answerSubjectiveQuestionStudentExercise;
    private AnswerSubjectiveQuestionStudentExam answerSubjectiveQuestionStudentExam;
    private AnswerStudentExerciseRemote answerStudentExerciseRemote;
    private AnswerStudentExamRemote answerStudentExamRemote;
    private StudentExercise studentExercise;
    private StudentExam studentExam;
    private String score;

    public String show() {
        String result = getHistoryRemote().getScoresStudent(grade.getId(), student.getId());
        setInputStream(new ByteArrayInputStream(result.getBytes()));
        return "json";
    }
    
    
    public String showTranscript(){
        StringBuilder result = new StringBuilder();
        List<Transcript> list = getHistoryRemote().getTranscriptsByStudentByGrade(student.getId(), grade.getId());
        if(list!=null && list.size()>0){
            result = new StringBuilder("{\"transcript\":{");
            result.append("\"id\":\""+list.get(0).getId()+"\",");
            result.append("\"averageExam\":\""+list.get(0).getAverageExam()+"\",");
            result.append("\"averageExercise\":\""+list.get(0).getAverageExercise()+"\",");
            result.append("\"score\":\""+list.get(0).getScore()+"\",");
            result.append("\"manualScore\":\""+list.get(0).getManualScore()+"\",");
            result.append("\"status\":\""+list.get(0).getStatus()+"\"");
            result.append("}}");
        }
        setInputStream(new ByteArrayInputStream(result.toString().getBytes()));
        
        return "json";
    }
    

    public String showQuestionsSubjectivesExercise() {

        List<AnswerSubjectiveQuestionStudentExercise> list = new ArrayList<AnswerSubjectiveQuestionStudentExercise>();
        List<StudentExercise> listStudentExercise = answerStudentExerciseRemote.getStudentExercise(student.getId(), exercise.getId(),grade.getId());

        StringBuilder json = new StringBuilder("{\"list\":");
        if (!listStudentExercise.isEmpty()) {
            StudentExercise se = listStudentExercise.get(0);
            List<AnswerStudentExercise> listASE = answerStudentExerciseRemote.getListAnswerStudentExerciseTypeSubjective(se.getId());
            json.append("{\"asqse\":");
            if (!listASE.isEmpty()) {
                json.append("[");
                for (AnswerStudentExercise answerStudentExercise : listASE) {
                    AnswerSubjectiveQuestionStudentExercise temp = answerStudentExerciseRemote.getListAnswerSubjectiveQuestionStudentExercise(answerStudentExercise.getId()).get(0);
                    json.append("{\"id\":\"" + temp.getId() + "\",");
                    json.append("\"question\":\"" + temp.getQuestion().getQuestion() + "\",");
                    json.append("\"score\":\"" + temp.getScore() + "\",");
                    json.append("\"answer\":\"" + temp.getAnswer() + "\"},");

                    list.add(temp);
                }
                json = new StringBuilder(json.substring(0, json.length() - 1));
                json.append("]");
            } else {
                json.append("\"\"");
            }
            json.append("}");
        } else {
            json.append("\"\"");
        }

        json.append("}");

        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));

        return "json";
    }
    public String showQuestionsSubjectivesExam() {

        List<AnswerSubjectiveQuestionStudentExam> list = new ArrayList<AnswerSubjectiveQuestionStudentExam>();
        List<StudentExam> listStudentExam = answerStudentExamRemote.getStudentExam(student.getId(), exam.getId(),grade.getId());

        StringBuilder json = new StringBuilder("{\"list\":");
        if (!listStudentExam.isEmpty()) {
            StudentExam se = listStudentExam.get(0);
            List<AnswerStudentExam> listASE = answerStudentExamRemote.getListAnswerStudentExamTypeSubjective(se.getId());
            json.append("{\"asqse\":");
            if (!listASE.isEmpty()) {
                json.append("[");
                for (AnswerStudentExam answerStudentExam : listASE) {
                    AnswerSubjectiveQuestionStudentExam temp = answerStudentExamRemote.getListAnswerSubjectiveQuestionStudentExam(answerStudentExam.getId()).get(0);
                    json.append("{\"id\":\"" + temp.getId() + "\",");
                    json.append("\"question\":\"" + temp.getQuestion().getQuestion() + "\",");
                    json.append("\"score\":\"" + temp.getScore() + "\",");
                    json.append("\"answer\":\"" + temp.getAnswer() + "\"},");

                    list.add(temp);
                }
                json = new StringBuilder(json.substring(0, json.length() - 1));
                json.append("]");
            } else {
                json.append("\"\"");
            }
            json.append("}");
        } else {
            json.append("\"\"");
        }

        json.append("}");

        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));

        return "json";
    }

    public String updateScoreSubjectiveQuestionExercise() {
        AnswerSubjectiveQuestionStudentExercise temp = answerStudentExerciseRemote.getAnswerSubjectiveQuestionStudentExerciseById(answerSubjectiveQuestionStudentExercise.getId());
        
        if(!score.equals("null"))
            temp.setScore(new BigDecimal(score));
        else
            temp.setScore(null);
        
        
        StringBuilder json = new StringBuilder("{\"update\":");
        if (answerStudentExerciseRemote.updateAnswerSubjectiveQuestionStudentExercise(temp)) {
            json.append("true");
        } else {
            json.append("false");
        }
        
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));

        return "json";
    }
    
    public String updateScoreSubjectiveQuestionExam() {
        AnswerSubjectiveQuestionStudentExam temp = answerStudentExamRemote.getAnswerSubjectiveQuestionStudentExamById(answerSubjectiveQuestionStudentExam.getId());
        
        if(!score.equals("null"))
            temp.setScore(new BigDecimal(score));
        else
            temp.setScore(null);

        StringBuilder json = new StringBuilder("{\"update\":");
        if (answerStudentExamRemote.updateAnswerSubjectiveQuestionStudentExam(temp)) {
            json.append("true");
        } else {
            json.append("false");
        }
        
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));

        return "json";
    }

    
    public String updateStudentexercise(){
        StudentExercise se = (StudentExercise)answerStudentExerciseRemote.getStudentExercise(studentExercise.getStudent().getId(),studentExercise.getExercise().getId(),studentExercise.getGrade().getId()).get(0);
        se.setManualScore(studentExercise.getManualScore());
        Boolean result = answerStudentExerciseRemote.updateAllStudentExercise(se);
        setInputStream(new ByteArrayInputStream(result.toString().getBytes()));
        return "json";
    }
    
    public String updateStudentexam(){
        StudentExam se = (StudentExam)answerStudentExamRemote.getStudentExam(studentExam.getStudent().getId(),studentExam.getExam().getId(),studentExam.getGrade().getId() ).get(0);
        se.setManualScore(studentExam.getManualScore());
        Boolean result = answerStudentExamRemote.updateAllStudentExam(se);
        setInputStream(new ByteArrayInputStream(result.toString().getBytes()));
        return "json";
    }
    
    public String updateTrascript(){
        Transcript t = getHistoryRemote().getTranscript(transcript.getId());
        t.setManualScore(transcript.getManualScore());
        Boolean resul = getHistoryRemote().updateTranscript(t);
        setInputStream(new ByteArrayInputStream(resul.toString().getBytes()));
        return "json";
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public ExerciseRemote getExerciseRemote() {
        return exerciseRemote;
    }

    public void setExerciseRemote(ExerciseRemote exerciseRemote) {
        this.exerciseRemote = exerciseRemote;
    }

    public String getRequisitesString() {
        return requisitesString;
    }

    public void setRequisitesString(String requisitesString) {
        this.requisitesString = requisitesString;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public XStream getXStream() {
        return xStream;
    }

    public void setXStream(XStream xStream) {
        this.xStream = xStream;
    }

    public UnitContent getUnitContent() {
        return unitContent;
    }

    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public AnswerStudentExerciseRemote getAnswerStudentExerciseRemote() {
        return answerStudentExerciseRemote;
    }

    public void setAnswerStudentExerciseRemote(AnswerStudentExerciseRemote answerStudentExerciseRemote) {
        this.answerStudentExerciseRemote = answerStudentExerciseRemote;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
    

    public SystemUser getStudent() {
        return student;
    }

    public void setStudent(SystemUser student) {
        this.student = student;
    }

    public AnswerSubjectiveQuestionStudentExercise getAnswerSubjectiveQuestionStudentExercise() {
        return answerSubjectiveQuestionStudentExercise;
    }
    
    public void setAnswerSubjectiveQuestionStudentExercise(AnswerSubjectiveQuestionStudentExercise answerSubjectiveQuestionStudentExercise) {
        this.answerSubjectiveQuestionStudentExercise = answerSubjectiveQuestionStudentExercise;
    }

    public StudentExercise getStudentExercise() {
        return studentExercise;
    }

    public void setStudentExercise(StudentExercise studentExercise) {
        this.studentExercise = studentExercise;
    }
        public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public AnswerStudentExamRemote getAnswerStudentExamRemote() {
        return answerStudentExamRemote;
    }

    public void setAnswerStudentExamRemote(AnswerStudentExamRemote answerStudentExamRemote) {
        this.answerStudentExamRemote = answerStudentExamRemote;
    }

    public AnswerSubjectiveQuestionStudentExam getAnswerSubjectiveQuestionStudentExam() {
        return answerSubjectiveQuestionStudentExam;
    }

    public void setAnswerSubjectiveQuestionStudentExam(AnswerSubjectiveQuestionStudentExam answerSubjectiveQuestionStudentExam) {
        this.answerSubjectiveQuestionStudentExam = answerSubjectiveQuestionStudentExam;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public StudentExam getStudentExam() {
        return studentExam;
    }

    public void setStudentExam(StudentExam studentExam) {
        this.studentExam = studentExam;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }
    
    
}
