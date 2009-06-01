/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.ejb.interfaces.ExamRemote;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.Exam;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.QuestionExam;
import br.ufc.ivela.commons.model.StudentExam;
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
@Stateless(mappedName="ExamBean")
public class ExamBean implements ExamRemote {

    private GenericDao<Exam> daoExam = DaoFactory.getInstance(Exam.class);
    //private GenericDao<Grade> daoGrade = DaoFactory.getInstance(Grade.class);
    private GenericDao<QuestionExam> daoQuestionExam = DaoFactory.getInstance(QuestionExam.class);
    private GenericDao<StudentExam> daoStudentExam = DaoFactory.getInstance(StudentExam.class);
    private GenericDao<Question> daoQuestion = DaoFactory.getInstance(Question.class);
    private GenericDao<Unit> daoUnit = DaoFactory.getInstance(Unit.class);
    private GenericDao<Discipline> daoDiscipline = DaoFactory.getInstance(Discipline.class);
    private GenericDao<UnitContent> daoUnitContent = DaoFactory.getInstance(UnitContent.class);


    private GradeBean gradeBean = new GradeBean();

    /**
     * Connect to the server and get the exam by id
     * @param id
     * @return exam
     */
    public Exam get(Long id) {
        if (id == null) {
            return null;
        }

        Exam exam = daoExam.get(id);

        return exam;
    }

    /**
     * Connect to the server and add a new exam
     * @param exam
     * @return true if the new exam can be add, and false if can't
     */
    public Long add(Exam examObj) {
        examObj.setCreatedAt(new Date());
        return (Long) daoExam.save(examObj);
    }

    /**
     * Connect to the server and remove an exam by id
     * @param id
     * @return true if the new exam can be remove, and false if can't
     */
    public boolean remove(Long id) {
        return daoExam.remove(daoExam.get(id));
    }

    /**
     * get questions by exam
     * @param examId
     * @return
     */
    public List<Question> getQuestions(Long examId) {
        return (List<Question>) daoQuestionExam.find("select qe.question from QuestionExam qe where qe.exam.id = ? and qe.active =? order by qe.id", new Object[]{examId,true});
    }

    /**
     * try to connect to the server and get all exams and lists
     *
     * @return a list of exams
     */
    public List<Exam> getAll() {
        return daoExam.getAll();
    }

    public List<Exam> getListAllExamByUnitContent(Long idUnitContent){
        
        return daoExam.find("from Exam e where e.unitContentId = ? and e.active = ? order by e.order", new Object[]{idUnitContent, true});
    }
    
    public List<Exam> getListExamByUnitContent(Long idUnitContent) {
        Date date = new Date();
        return daoExam.find("from Exam e where e.unitContentId = ? and e.active = ? and ? between e.startDatetime and e.endDatetime  order by e.order", new Object[]{idUnitContent, true,date});
    }

    /**
     * try to connect to the server and update a exam
     * @param exam
     * @return
     */
    public boolean update(Exam exam) {
        return daoExam.update(exam);
    }
    
    public boolean updateJson(Exam exam) {
        return daoExam.update(exam);
    }

    public List<Exam> listByUnit(Long unitId) {
        List<Exam> results = daoExam.find("select e from Exam e, ExamUnit eu " +
                "where eu.exam = e.id and eu.unit.id = ?", new Object[]{unitId});

        return results;
    }

    public Long addQuestion(QuestionExam questionExam) {
        //System.out.print("dsfjkdshf" + questionExam.getQuestion().getId());
        return (Long) daoQuestionExam.save(questionExam);
    }

    public Page getQuestionPageByExam(Long examId, int page, int pageSize) {

        /*String countQuery = "select count(qe.id) from QuestionExam qe where qe.exam.id = ?";
        Object params[] = new Object[]{examId};
        
        String query = "select qe.question from QuestionExam qe where qe.exam.id = ? order by order_n";
        
        
        Page p = new Page(query, countQuery, params, params, page, pageSize);*/
        Page p = getQuestionPageByExamGambi(examId, page, pageSize);
        return p;
    }
    //enquanto a pg tá no pau.
    public Page getQuestionPageByExamGambi(Long examId, int page, int pageSize) {

        String countQuery = "select count(qe.id) from QuestionExam qe where qe.exam.id = ? and qe.active=?";
        Object params[] = new Object[]{examId,true};
        int count = daoQuestionExam.getCount(countQuery, params);



        String query = "select qe.question.id  from QuestionExam qe where qe.exam.id = ? and qe.active = ? order by qe.id";
        List<Long> allQuestion = daoQuestionExam.find(query, params);
        
        List<Question> res = new Vector<Question>();
        Long currentId = allQuestion.get(page - 1);
        
        Question currentQuestion = daoQuestion.get(currentId);
        res.add(currentQuestion);
        
        Page p = new Page(res, page, pageSize, count);

        /*
        
        Page p = new Page(query, countQuery, params, params, page, pageSize);*/

        return p;
    }

    public int getMaxOrderNByUnitContent(Long unitContentId) {
        Object[] params = new Object[]{unitContentId};

        List list = daoExam.find("select max(e.order) from Exam e where e.unitContentId =?  ", params);
        //System.out.println(list);
        //System.out.println(list.size());
        if (list != null && list.size() > 0 && list.get(0) != null) {
            return new Integer(list.get(0).toString());
        } else {
            return 0;
        }


    }

    public void addRequisite(Long examId, Long requisiteId) {
        Exam exam = daoExam.get(examId);
        Exam requisite = daoExam.get(requisiteId);
        if (exam != null && requisite != null) {
            Set<Exam> requisites = exam.getRequisites();

            if (requisites != null) {
                if (!requisites.contains(requisite)) {
                    requisites.add(requisite);
                }

            }

        }
        daoExam.update(exam);
    }
    
    public boolean isExamFinishedForStudent(Long idExam, Long idStudent,Long courseId) {
       Grade g = gradeBean.getActiveByStudentByCourse(idStudent, courseId);
       String query = "select count(se.exam) " +
                      "from StudentExam se " +
                      "where se.exam.id = ? and " +
                      "se.student.id = ? and " +
                      "se.grade.id = ? and  "+
                      "se.status = " + Constants.STUDENT_EXAM_FINISHED;
       Object params[] = new Object[]{idExam,idStudent,g.getId()};
       int size = this.daoStudentExam.getCount(query, params);
       if(size == 0)
           return false;
       return true;
    }

     public boolean isExamFinishedForStudent(Long idExam, Long idStudent) {
       
       String query = "select count(se.exam) " +
                      "from StudentExam se " +
                      "where se.exam.id = ? and " +
                      "se.student.id = ? and " +
                      "se.status = " + Constants.STUDENT_EXAM_FINISHED;
       Object params[] = new Object[]{idExam,idStudent};
       int size = this.daoStudentExam.getCount(query, params);
       if(size == 0)
           return false;
       return true;
    }
    
    public int getQuestionNumber(Long idExam) {
        String query = "select count(qe.exam) " +
                       "from QuestionExam qe " +
                       "where qe.exam.id = ? and qe.active = ? ";
        Object params[] = new Object[]{idExam,true};
        int size = this.daoQuestionExam.getCount(query, params);
        return size;
    }
    
    public int finishedExamsForUnitContent(Long studentId, Long unitContentId, Long gradeId){
        
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
        Date date = new Date();
        String allExamSql = "from Exam e where e.unitContentId=? and e.active = ? and ? between e.startDatetime and e.endDatetime";
        Object[] params = new Object[]{unitContentId, true, date};
        List<Exam> exams = daoExam.find(allExamSql, params);
        if(exams!= null && exams.size()!=0){
            for(Exam e: exams){
                
                String sqlSE = "select se.id from StudentExam se where se.student.id=? and se.exam.id=?";
                Object[] paramsSE = new Object[]{studentId,e.getId()};
                List<Long> doneExams = daoStudentExam.find(sqlSE, paramsSE);
                if(doneExams==null || doneExams.size()==0){
                    //System.out.println("[Exam]: " + e.getTitle());
                    return 0;
                }
                    
            }
        }else
            return 1;
        return res;
    }
}

