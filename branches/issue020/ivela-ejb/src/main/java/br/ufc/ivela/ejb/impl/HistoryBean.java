/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Exam;
import br.ufc.ivela.commons.model.Exercise;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.History;
import br.ufc.ivela.commons.model.Message;
import br.ufc.ivela.commons.model.Transcript;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.ejb.interfaces.HistoryRemote;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Class of ejb which implements the interface HistoryLocal
 */
@Stateless(mappedName="HistoryBean")
public class HistoryBean implements HistoryRemote {
    
    private GenericDao<History> daoHistory = DaoFactory.getInstance(History.class);
    private GenericDao<Transcript> daoTranscript = DaoFactory.getInstance(Transcript.class);
    private GenericDao<Course> daoCourse = DaoFactory.getInstance(Course.class);
    private GradeBean gradeBean = new GradeBean();
    private AnswerStudentExerciseBean answerStudentExerciseBean = new AnswerStudentExerciseBean();
    private AnswerStudentExamBean answerStudentExamBean = new AnswerStudentExamBean();
    private SystemUserBean systemUserBean = new SystemUserBean();
    private DisciplineBean disciplineBean = new DisciplineBean();
    private UnitBean unitBean = new UnitBean();
    private UnitContentBean unitContentBean = new UnitContentBean();
    private ExerciseBean exerciseBean = new ExerciseBean();
    private ExamBean examBean = new ExamBean();
    
    

    public boolean remove(Long id) {
        return daoHistory.remove(daoHistory.get(id));
    }

    public History get(Long id) {
        return daoHistory.get(id);
    }

    public Long add(History history) {
        return (Long) daoHistory.save(history);
    }

    public List<History> getAll() {
        return daoHistory.getAll();
    }

    public List<History> getBySystemUser(Long systemUserId) {
        return daoHistory.getByFK("systemUser.id", systemUserId);
    }
    
    public boolean update(History history) {
        return daoHistory.update(history);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long addTranscript(Long gradeId, Long studentId){
        Transcript transcript = new Transcript();
        Grade grade = gradeBean.get(gradeId);
        transcript.setSystemUser(systemUserBean.get(studentId));
        transcript.setGrade(grade);
        transcript.setScore(0.0);
        transcript.setAverageExam(0.0);
        transcript.setAverageExercise(0.0);
        //if(grade.getStatus()==Constants.GRADE_REGISTRATION_FINISHED){
            transcript.setStatus(Constants.TRANSCRIPT_ENROLLMENT);
        //}
        return (Long)daoTranscript.save(transcript);
    }
    
    public List<Transcript> getTranscriptsByStudent(Long studentId){
        
        //  TEMPORARIO calculando para cada turma
        List<Grade> l =gradeBean.getGradesActiveByStudent(studentId);
        for (Grade g : l) {
            this.calcAverageCourse(g.getId(),studentId);
        }
        
        //
        List<Transcript> list = daoTranscript.getByFK("systemUser.id", studentId);
        if(list!=null && list.size()>0){
            for (int i =0;i<list.size();i++) {
                Course course = daoCourse.get(((Transcript)list.get(i)).getGrade().getCourseId());
                ((Transcript)list.get(i)).getGrade().setCourse(course);
            }
        }
        return list;
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Transcript> getTranscriptsByStudentByGrade(Long studentId, Long gradeId){
        //  TEMPORARIO calculando para CADA turma
        // this.calcAverageCourse(gradeId,studentId);
        
        //
        return daoTranscript.find("from Transcript t where t.systemUser.id = ? and t.grade.id = ?", new Object[]{studentId,gradeId});
    }
    
   @TransactionAttribute(TransactionAttributeType.SUPPORTS)
   public Transcript getTranscript(Long transcriptId){
       return daoTranscript.get(transcriptId);
   } 
    
   public void calcAverageCourse(Long gradeId, Long studentId){
       Transcript transcript;
       List list = daoTranscript.find("from Transcript t where t.grade.id = ? and t.systemUser.id = ?", new Object[]{gradeId, studentId});
       if(list==null || list.size()==0){
            Long idTranscript = this.addTranscript(gradeId, studentId);
            transcript = this.getTranscript(idTranscript);
       }
       else{
            transcript = (Transcript)list.get(0);
       }
       
       transcript.setAverageExam(answerStudentExamBean.averageExamByGrade(transcript.getGrade().getId(), studentId));
       transcript.setAverageExercise(answerStudentExerciseBean.averageExercisesByGrade(transcript.getGrade().getId(), studentId));
       if (transcript.getAverageExam() < 0.0) {
           transcript.setScore(transcript.getAverageExercise());
        }else if(transcript.getAverageExercise() < 0.0){
            transcript.setScore(transcript.getAverageExam());
           }else{           
            transcript.setScore((transcript.getAverageExam()+transcript.getAverageExercise())/2.0);
       }
       if(transcript.getGrade().getStatus()==Constants.GRADE_FINISHED){
            if(transcript.getScore() > Constants.AVERAGE_FOR_APPROVAL){
                transcript.setStatus(Constants.TRANSCRIPT_APPROVED);
            }
            else {
                transcript.setStatus(Constants.TRANSCRIPT_FAILED);
            }
       }
       this.updateTranscript(transcript);
       
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Boolean updateTranscript(Transcript transcript){
        return daoTranscript.update(transcript);
   }
   
    public String getScoresStudent(Long gradeId,Long studentId){
        StringBuffer jsonBuffer = new StringBuffer();
        jsonBuffer.append("{");
        jsonBuffer.append("\"unitContents\":[");
        Grade g = gradeBean.get(gradeId);
        List<Discipline> disCourse = disciplineBean.getByCourse(g.getCourseId());
        for (Discipline discipline : disCourse) {
            List<Unit> unitCourse = unitBean.getByDiscipline(discipline.getId());
            for (Unit unit : unitCourse) {
                List<UnitContent> unitContentCourse = unitContentBean.getByUnit(unit.getId());
                for (UnitContent unitContent : unitContentCourse) {                    
                    List<Exercise> exerciseCourse = exerciseBean.getListExerciseByUnitContent(unitContent.getId());
                    List<Exam> examCourse = examBean.getListExamByUnitContent(unitContent.getId());
                    if((exerciseCourse!=null&&exerciseCourse.size()>0) || (examCourse!=null && examCourse.size()>0)){
                     jsonBuffer.append("{\"id\":\"" + unitContent.getId()+"\",");
                     jsonBuffer.append("\"name\":\"" + unitContent.getTitle()+"\"");
                     if (exerciseCourse!=null){
                        jsonBuffer.append(",\"exercises\":[");
                        for (Exercise exercise : exerciseCourse) {
                                List list = answerStudentExerciseBean.averageMark(studentId, exercise.getId(),g.getId(),false);
                                jsonBuffer.append("{\"id\":\""+exercise.getId()+"\",");
                                jsonBuffer.append("\"name\":\""+exercise.getTitle()+"\",");
                                jsonBuffer.append("\"score\":\""+((Double)list.get(0)).doubleValue()+"\",");
                                jsonBuffer.append("\"manualScore\":\""+((Double)list.get(2)).doubleValue()+"\",");
                                jsonBuffer.append("\"hasSubjective\":\""+((Boolean)list.get(1)).booleanValue()+"\"},");
                                
                        }
                        if(jsonBuffer.charAt(jsonBuffer.length()-1)==','){
                            jsonBuffer.deleteCharAt(jsonBuffer.length()-1);
                            jsonBuffer.trimToSize();
                        }
                        if(jsonBuffer.charAt(jsonBuffer.length()-1)=='['){
                            jsonBuffer.deleteCharAt(jsonBuffer.length()-1);
                            jsonBuffer.trimToSize();
                            jsonBuffer.append("\"\"");
                        }   
                        else jsonBuffer.append("]");

                     }
                    if (examCourse!=null){
                        jsonBuffer.append(",\"exams\":[");
                        for (Exam exam : examCourse) {
                                List list = answerStudentExamBean.averageMark(studentId, exam.getId(),g.getId() ,false);
                                jsonBuffer.append("{\"id\":\""+exam.getId()+"\",");
                                jsonBuffer.append("\"name\":\""+exam.getTitle()+"\",");
                                jsonBuffer.append("\"score\":\""+((Double)list.get(0)).doubleValue()+"\",");
                                jsonBuffer.append("\"manualScore\":\""+((Double)list.get(2)).doubleValue()+"\",");
                                jsonBuffer.append("\"hasSubjective\":\""+((Boolean)list.get(1)).booleanValue()+"\"},");
                        }
                        if(jsonBuffer.charAt(jsonBuffer.length()-1)==','){
                            jsonBuffer.deleteCharAt(jsonBuffer.length()-1);
                            jsonBuffer.trimToSize();
                        }
                        if(jsonBuffer.charAt(jsonBuffer.length()-1)=='['){
                            jsonBuffer.deleteCharAt(jsonBuffer.length()-1);
                            jsonBuffer.trimToSize();
                            jsonBuffer.append("\"\"");
                        }   
                        else jsonBuffer.append("]");

                     }
                    
                    
                    jsonBuffer.append("},");          
                   }
                }
            }
        }
        if(jsonBuffer.charAt(jsonBuffer.length()-1)==','){
             jsonBuffer.deleteCharAt(jsonBuffer.length()-1);
             jsonBuffer.trimToSize();
        }
        if(jsonBuffer.charAt(jsonBuffer.length()-1)=='['){
            jsonBuffer.deleteCharAt(jsonBuffer.length()-1);
            jsonBuffer.trimToSize();
            jsonBuffer.append("\"\"");
        }   
        else jsonBuffer.append("]");
      
        jsonBuffer.append("}");
            System.out.println("tr"+jsonBuffer.toString());
        return jsonBuffer.toString();
    }
   
    
}
