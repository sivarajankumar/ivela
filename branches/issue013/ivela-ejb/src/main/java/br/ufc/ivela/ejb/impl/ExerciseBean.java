/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.ejb.interfaces.ExerciseRemote;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.Exercise;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.QuestionExercise;
import br.ufc.ivela.commons.model.StudentExercise;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.ejb.Stateless;

/**
 *
 * @author Maristella Myrian
 */
@Stateless(mappedName="ExerciseBean")
public class ExerciseBean implements ExerciseRemote {

    private GenericDao<Exercise> daoExercise = DaoFactory.getInstance(Exercise.class);
    private GenericDao<QuestionExercise> daoQuestionExercise = DaoFactory.getInstance(QuestionExercise.class);
    private GenericDao<StudentExercise> daoStudentExercise = DaoFactory.getInstance(StudentExercise.class);
    private GenericDao<Question> daoQuestion = DaoFactory.getInstance(Question.class);
    private GradeBean gradeBean = new GradeBean();
    private GenericDao<UnitContent> daoUnitContent = DaoFactory.getInstance(UnitContent.class);
    private GenericDao<Unit> daoUnit = DaoFactory.getInstance(Unit.class);
    private GenericDao<Discipline> daoDiscipline = DaoFactory.getInstance(Discipline.class);
    /**
     * Connect to the server and get the exercise by id
     * @param id
     * @return
     */
    public Exercise get(Long id) {
        if (id == null) {
            return null;
        }

        return daoExercise.get(new Long(id));
    }

    /**
     * Connect to the server and add a new exercise
     * @param exercise
     * @return true if the new exercise can be add, and false if can't
     */
    public Long add(Exercise exerciseObj) {
        exerciseObj.setCreatedAt(new Date());

        return (Long) daoExercise.save(exerciseObj);
    }

    /**
     * Connect to the server and remove an exercise by id
     * @param id
     * @return true if the new exercise can be remove, and false if can't
     */
    public boolean remove(Long id) {
        return daoExercise.remove(daoExercise.get(new Long(id)));
    }

    /**
     * try to connect to the server and get all exercises and lists
     *
     * @return a list of exercises
     */
    public List<Exercise> getAll() {
        return daoExercise.getAll();
    }

    public List<Exercise> getListExerciseByUnitContent(Long idUnitContent) {
        return daoExercise.find("from Exercise e where e.unitContentId = ? and e.active = ? order by e.order", new Object[]{idUnitContent, true});

    }

    public List<Question> getQuestions(Long exerciseId) {
        return (List<Question>) daoQuestionExercise.find("select qe.question from QuestionExercise qe where qe.exercise.id = ? and qe.active=? order by qe.id", new Object[]{exerciseId, true});
    }

    //enquanto a pg tá no pau.
    public Page getQuestionPageByExerciseGambi(Long exerciseId, int page, int pageSize) {

        String countQuery = "select count(qe.id) from QuestionExercise qe where qe.exercise.id = ? and qe.active=?";
        Object params[] = new Object[]{exerciseId,true};
        int count = daoQuestionExercise.getCount(countQuery, params);



        String query = "select qe.question.id from QuestionExercise qe where qe.exercise.id = ? and qe.active=? order by qe.id";
        List<Long> allQuestion = daoQuestionExercise.find(query, params);

         


        List<Question> res = new Vector<Question>();
        Long currentId = allQuestion.get(page - 1);
        
        Question currentQuestion = daoQuestion.get(currentId);
        res.add(currentQuestion);

        Page p = new Page(res, page, pageSize, count);

        /*
        
        Page p = new Page(query, countQuery, params, params, page, pageSize);*/

        return p;
    }

    public Page getQuestionPageByExercise(Long exerciseId, int page, int pageSize) {

        /*String countQuery = "select count(qe.id) from QuestionExercise qe where qe.exercise.id = ?";
        Object params[] = new Object[]{exerciseId};
        
        String query = "select qe.question from QuestionExercise qe where qe.exercise.id = ? order by order_n";
        
        
        Page p = new Page(query, countQuery, params, params, page, pageSize);*/
        Page p = getQuestionPageByExerciseGambi(exerciseId, page, pageSize);
        return p;
    }

    /**
     * try to connect to the server and update a exercise
     * @param exercise
     * @return
     */
//    public boolean update(Exercise exercise) {
//        Exercise exe = daoExercise.get(exercise.getId());
//        exe.setCreatedAt(exercise.getCreatedAt());
//        exe.setTitle(exercise.getTitle());
//        exe.setCreatedBy(exercise.getCreatedBy());
//        
////        exe.setFinals(exercise.getFinals());
//        return daoExercise.update(exe);
//    }
    
    public boolean update(Exercise exercise) {
        return daoExercise.update(exercise);
    }

    public int getMaxOrderNByUnitContent(Long unitContentId) {
        Object[] params = new Object[]{unitContentId};

        List list = daoExercise.find("select max(e.order) from Exercise e where e.unitContentId =?  ", params);
        //System.out.print(list);
        if(list!=null && list.size()>0 && list.get(0)!=null){
            return new Integer(list.get(0).toString());
        }
        else{
            return 0;
        }


    }

//    public List<Exercise> getExercisesByUnitContent(Long idUnitContent) {
//        return daoExercise.getByFK("unitContentId", idUnitContent);
//    }

    public boolean isExerciseFinishedForStudent(Long idExercise, Long idStudent) {
       String query = "select count(se.exercise) " +
                      "from StudentExercise se " +
                      "where se.exercise.id = ? and " +
                      "se.student.id = ? and " +
                      "se.status = " + Constants.STUDENT_EXERCISE_FINISHED;
       Object params[] = new Object[]{idExercise,idStudent};
       int size = this.daoStudentExercise.getCount(query, params);
       if(size == 0)
           return false;
       return true;
    }
    
    //ad requisite
     public void addRequisite(Long exerciseId, Long requisiteId){
         Exercise exercise = daoExercise.get(exerciseId);
         Exercise requisite = daoExercise.get(requisiteId);
         if(exercise!=null && requisite!=null){
             Set<Exercise> requisites = exercise.getRequisites();
             
             if(requisites!=null ){
                 if(!requisites.contains(requisite)){
                    requisites.add(requisite); 
                 }
                    
             }
                     
         }
         
         daoExercise.update(exercise);
     }
     
     public Long addQuestion(QuestionExercise questionExercise) {
        return (Long) daoQuestionExercise.save(questionExercise);
     }

     public void updateStudentExerciseChances(Long studentId, Long exerciseId){
        String query = "select se " +
                       "from StudentExercise se " +
                       "where se.student.id=? and se.exercise.id=?";
        Object[] params = new Object[]{studentId,exerciseId};
        List<StudentExercise> studentExerciseList = daoStudentExercise.find(query, params);
        Exercise exercise = daoExercise.get(exerciseId);
        
        if(studentExerciseList.size()==1){
        
            StudentExercise studentExercise= studentExerciseList.get(0);
            if(exercise.getChances()!=10){
                //System.out.println("-->"+exercise.getChances());
                studentExercise.setChances(exercise.getChances()-1);
            }
            else
                studentExercise.setChances(10);
             daoStudentExercise.update(studentExercise);
             
        }else if(studentExerciseList.size()>1){
            int bigChance = this.getBiggerChance(studentExerciseList);
            for(StudentExercise studentExercise  : studentExerciseList){
               if(bigChance!=10){
                   if(bigChance==0){
                       studentExercise.setChances(0);
                   }else          
                        studentExercise.setChances(bigChance-1);
                daoStudentExercise.update(studentExercise);
               }else if(bigChance==10) {
                   if(studentExercise.getChances()!=10){
                       studentExercise.setChances(10);
                       daoStudentExercise.update(studentExercise);
                   }
               }
                       
            }
        }
                
        
        /*for(StudentExercise studentExercise  : studentExerciseList){
            if(studentExercise.getChances()<0){
                Exercise exercise = daoExercise.get(exerciseId);
                studentExercise.setChances(exercise.getChances()-1);
            }else if (studentExercise.getChances()!=0 && studentExercise.getChances()==10){
                
                studentExercise.setChances(studentExercise.getChances()-1);
            }
            daoStudentExercise.update(studentExercise);
        }*/
     }

    public int getChancesStudentExercise(Long studentId, Long exerciseId,Long courseId) {
        Grade g = gradeBean.getActiveByStudentByCourse(studentId, courseId);
        String query = "select se " +
                       "from StudentExercise se " +
                       "where se.student.id=? and se.exercise.id=? and se.grade.id =?";
        Object[] params = new Object[]{studentId,exerciseId,g.getId()};
        List<StudentExercise> studentExerciseList = daoStudentExercise.find(query, params);
       
        if(studentExerciseList!=null && studentExerciseList.size()>0){
            
            return studentExerciseList.get(0).getChances();
        }else{
             Exercise exercise = daoExercise.get(exerciseId);
             return exercise.getChances();
        }
        
        
    }
    
    private int getBiggerChance(List<StudentExercise> list){
        int big = -100;
        for(StudentExercise es: list){
            if(es.getChances()>big){
                big = es.getChances();
            }
        }
        
        return big;
    }
    
    public void setGradeForStudentExerciseId(Long studentExerciseId, double grade){
        StudentExercise se = daoStudentExercise.get(studentExerciseId);
        se.setScore(new Double(grade));
        daoStudentExercise.update(se);
    }
    
    
    
    public int finishedExerciseForUnitContent(Long studentId, Long unitContentId, Long gradeId){
        
        UnitContent unitContent = daoUnitContent.get(unitContentId);
        Unit unit = daoUnit.get(unitContent.getUnitId());
        Discipline discipline = daoDiscipline.get(unit.getDisciplineId());
        Long courseId = discipline.getCourseId();
        
        Grade grade = gradeBean.getActiveByStudentByCourse(studentId,courseId);
        
        
        
        //deu null? entao esse cara nao ta mais ativo, finalizou os exames
        if(grade==null){
            return 2;
        }else if(grade.getId().longValue()!=gradeId.longValue()){
            //System.out.println("::"+grade.getId());
            return 2;
        }
        
        int res = 1;
        String allExerciseSql = "from Exercise e where e.unitContentId=?";
        Object[] params = new Object[]{unitContentId};
        List<Exercise> exercises = daoExercise.find(allExerciseSql, params);
        if(exercises!= null && exercises.size()!=0){
            for(Exercise e: exercises){
                String sqlSE = "select se.id from StudentExercise se where se.student.id=? and se.exercise.id=?";
                Object[] paramsSE = new Object[]{studentId,e.getId()};
                List<Long> doneExercises = daoStudentExercise.find(sqlSE, paramsSE);
                if(doneExercises==null || doneExercises.size()==0)
                    return 0;
            }
        }else
            return 1;
        return res;
    }
    
     
}
