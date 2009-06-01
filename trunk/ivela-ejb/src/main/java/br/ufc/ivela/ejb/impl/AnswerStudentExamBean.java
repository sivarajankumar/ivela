/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.AnswerAuditiveQuestionStudentExam;
import br.ufc.ivela.commons.model.AnswerStudentExam;
import br.ufc.ivela.commons.model.AnswerSubjectiveQuestionStudentExam;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Exam;
import br.ufc.ivela.commons.model.Exercise;
import br.ufc.ivela.commons.model.ExternalAnswerStudentExam;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.ObjectiveAnswer;
import br.ufc.ivela.commons.model.ObjectiveAnswerStudentExam;
import br.ufc.ivela.commons.model.ObjectiveQuestion;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.QuestionExam;
import br.ufc.ivela.commons.model.StudentExam;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.AnswerStudentExamRemote;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author emanuelle
 */
@Stateless(mappedName="AnswerStudentExamBean")
public class AnswerStudentExamBean implements AnswerStudentExamRemote {

    private GenericDao<StudentExam> daoStudentExam = DaoFactory.getInstance(StudentExam.class);
    private GenericDao<AnswerStudentExam> daoAnswerStudentExam = DaoFactory.getInstance(AnswerStudentExam.class);
    private GenericDao<AnswerAuditiveQuestionStudentExam> daoAnswerAuditive = DaoFactory.getInstance(AnswerAuditiveQuestionStudentExam.class);
    private GenericDao<AnswerSubjectiveQuestionStudentExam> daoAnswerSubjective = DaoFactory.getInstance(AnswerSubjectiveQuestionStudentExam.class);
    private GenericDao<ExternalAnswerStudentExam> daoAnswerExternal = DaoFactory.getInstance(ExternalAnswerStudentExam.class);
    private GenericDao<ObjectiveAnswerStudentExam> daoAnswerObjective = DaoFactory.getInstance(ObjectiveAnswerStudentExam.class);
    private GenericDao<ObjectiveQuestion> daoObjectiveQuestion = DaoFactory.getInstance(ObjectiveQuestion.class);
    private GenericDao<Exam> daoExam = DaoFactory.getInstance(Exam.class);
    private GenericDao<SystemUser> daoSystemUser = DaoFactory.getInstance(SystemUser.class);
    private GenericDao<Question> daoQuestion = DaoFactory.getInstance(Question.class);
    private GenericDao<QuestionExam> daoQuestionExam = DaoFactory.getInstance(QuestionExam.class);
    private GenericDao<ObjectiveAnswer> daoObjectiveAnswer = DaoFactory.getInstance(ObjectiveAnswer.class);
    private DisciplineBean disciplineBean = new DisciplineBean();
    private UnitBean unitBean = new UnitBean();
    private UnitContentBean unitContentBean = new UnitContentBean();
    private ExamBean examBean = new ExamBean();
    private GradeBean gradeBean = new GradeBean();

//    public Long addStudentExam(Long examId, Long userId) {
//        StudentExam studentExam = new StudentExam();
//        studentExam.setExam(daoExam.get(examId));
//        studentExam.setStudent(daoSystemUser.get(userId));
//        studentExam.setStatus(Constants.STUDENT_EXAM_FINISHED);
//        studentExam.setScore(new Double(-100));
//        studentExam.setManualScore(new Double(-1));
//         
//        return (Long) daoStudentExam.save(studentExam);
//    }
//    
    
    public Long addStudentExam(Long examId, Long userId,Long courseId) {
        StudentExam studentExam = new StudentExam();
        Grade grade = gradeBean.getActiveByStudentByCourse(userId, courseId);
        studentExam.setGrade(grade);
        studentExam.setExam(daoExam.get(examId));
        studentExam.setStudent(daoSystemUser.get(userId));
        studentExam.setStatus(Constants.STUDENT_EXAM_FINISHED);
        studentExam.setScore(new Double(-100));
        studentExam.setManualScore(new Double(-1));
         
        return (Long) daoStudentExam.save(studentExam);
    }

//    public List<StudentExam> getStudentExam(Long userId, Long examId) {
//        return (List<StudentExam>) daoStudentExam.find("from StudentExam se where se.student.id = ? and se.exam.id = ?", new Object[]{userId, examId});
//
//
//    }
    
    public List<StudentExam> getStudentExam(Long userId, Long examId, Long gradeId) {
        return (List<StudentExam>) daoStudentExam.find("from StudentExam se where se.student.id = ? and se.exam.id = ? and se.grade.id =?", new Object[]{userId, examId, gradeId});


    }

    public boolean getNumberStudentExam(Long userId, Long examId) {
        List list = daoStudentExam.find("select count(*) from StudentExam where student.id = ? and exam.id = ?", new Object[]{userId, examId});
        //System.out.println("kkkkkkkkkkkkkkkkk:"+list.get(0).toString());
        if (list.get(0).toString().equals("0")) {
            return false;
        } else {
            return true;
        }

    }

    public AnswerStudentExam getAnswerStudentExam(Long id) {
        return daoAnswerStudentExam.get(id);
    }

    public ObjectiveAnswerStudentExam getObjectiveAnswerStudentExam(Long id) {
        return daoAnswerObjective.get(id);
    }

    public ObjectiveAnswerStudentExam getObjectiveAnswerStudentExamByAnswerStudentExam(Long answerStudentExamId) {
        return (ObjectiveAnswerStudentExam) (daoAnswerObjective.find("from ObjectiveAnswerStudentExam oa " +
                "where oa.answerStudentExam.id = ?",
                new Object[]{answerStudentExamId}).get(0));

    }

    public AnswerSubjectiveQuestionStudentExam getAnswerSubjectiveQuestionStudentExam(Long anwerStudentExam, Long questionId) {
        return (AnswerSubjectiveQuestionStudentExam) (daoAnswerSubjective.find("from AnswerSubjectiveQuestionStudentExam asq " +
                "where asq.question.id = ? and asq.answerStudentExam.id = ?", new Object[]{questionId, anwerStudentExam}).get(0));
    }
    
    public AnswerSubjectiveQuestionStudentExam getAnswerSubjectiveQuestionStudentExamById(Long id) {
        return (AnswerSubjectiveQuestionStudentExam) (daoAnswerSubjective.find("from AnswerSubjectiveQuestionStudentExam asq " +
                "where asq.id = ?", new Object[]{id}).get(0));
    }

    public AnswerAuditiveQuestionStudentExam getAnswerAuditiveQuestionStudentExam(Long anwerStudentExam, Long questionId) {
        return (AnswerAuditiveQuestionStudentExam) (daoAnswerSubjective.find("from AnswerAuditiveQuestionStudentExam aaq " +
                "where aaq.question.id = ? and aaq.answerStudentExam.id = ?",
                new Object[]{questionId, anwerStudentExam}).get(0));
    }

    public ExternalAnswerStudentExam getAnswerExternalQuestionStudentExam(Long anwerStudentExam, Long questionId) {
        return (ExternalAnswerStudentExam) (daoAnswerSubjective.find("from AnswerExternalQuestionStudentExam aqs " +
                "where aqs.question.id = ? and aqs.answerStudentExam.id = ?",
                new Object[]{questionId, anwerStudentExam}).get(0));
    }

    public Long addAnswerStudentExam(Long studentExamId, int type) {
        AnswerStudentExam answerStudentExam = new AnswerStudentExam();
        answerStudentExam.setType(type);
        answerStudentExam.setStudentExam(daoStudentExam.get(studentExamId));
        return (Long) daoAnswerStudentExam.save(answerStudentExam);

    }

    public Long addAnswerStudentQuestionExam(Long questionId, Long answerStudentExamId, String answer) {

        AnswerSubjectiveQuestionStudentExam answerSubjectiveQuestionStudentExam = new AnswerSubjectiveQuestionStudentExam();
        answerSubjectiveQuestionStudentExam.setAnswer(answer);

        AnswerStudentExam answerStudentExam = getAnswerStudentExam(answerStudentExamId);
        answerSubjectiveQuestionStudentExam.setAnswerStudentExam(answerStudentExam);

        Question question = daoQuestion.get(questionId);
        answerSubjectiveQuestionStudentExam.setQuestion(question);

        return (Long) daoAnswerSubjective.save(answerSubjectiveQuestionStudentExam);

    }

    public Long addAnswerStudentQuestionExam(Long answerStudentExamId, Long objectiveAnswerId) {

        ObjectiveAnswerStudentExam objectiveAnswerStudentExam = new ObjectiveAnswerStudentExam();

        AnswerStudentExam answerStudentExam = getAnswerStudentExam(answerStudentExamId);
        ObjectiveAnswer objectiveAnswer = daoObjectiveAnswer.get(objectiveAnswerId);

        objectiveAnswerStudentExam.setAnswerStudentExam(answerStudentExam);
        objectiveAnswerStudentExam.setObjectiveAnswer(objectiveAnswer);

        return (Long) daoAnswerObjective.save(objectiveAnswerStudentExam);
    }    // Add business logic below. (Right-click in editor and choose

    public Long addAnswerStudentQuestionExam(Long questionId, Long answerStudentExamId, BigDecimal score) {
        AnswerAuditiveQuestionStudentExam answerAuditiveQuestionStudentExam = new AnswerAuditiveQuestionStudentExam();

        Question question = daoQuestion.get(questionId);
        answerAuditiveQuestionStudentExam.setQuestion(question);

        AnswerStudentExam answerStudentExam = getAnswerStudentExam(answerStudentExamId);
        answerAuditiveQuestionStudentExam.setAnswerStudentExam(answerStudentExam);


        answerAuditiveQuestionStudentExam.setScore(score);
        return (Long) daoAnswerAuditive.save(answerAuditiveQuestionStudentExam);
    }

    public Long addAnswerStudentQuestionExam(Long questionId, Long answerStudentExamId, Boolean aproved, String resultValue) {
        ExternalAnswerStudentExam answerExternalQuestionStudentExam = new ExternalAnswerStudentExam();

        Question question = daoQuestion.get(questionId);
        AnswerStudentExam answerStudentExam = getAnswerStudentExam(answerStudentExamId);

        answerExternalQuestionStudentExam.setQuestion(question);
        answerExternalQuestionStudentExam.setAnswerStudentExam(answerStudentExam);

        answerExternalQuestionStudentExam.setAproved(aproved);
        answerExternalQuestionStudentExam.setResultValue(resultValue);

        return (Long) daoAnswerExternal.save(answerExternalQuestionStudentExam);
    }

    public boolean updateAnswerSubjectiveQuestionStudentExam(AnswerSubjectiveQuestionStudentExam ans) {

        return daoAnswerSubjective.update(ans);
    }

    public List<AnswerSubjectiveQuestionStudentExam> getListAnswerSubjectiveQuestionStudentExam(Long idAnwerStudentExam) {
        return (List<AnswerSubjectiveQuestionStudentExam>) daoAnswerSubjective.find("from AnswerSubjectiveQuestionStudentExam asq " +
                "where asq.answerStudentExam.id = ? order by asq.id", new Object[]{idAnwerStudentExam});
    }

    public List<AnswerStudentExam> getListAnswerStudentExamTypeSubjective(Long idStudentExam) {
        return (List<AnswerStudentExam>) daoAnswerStudentExam.find("from AnswerStudentExam ase " +
                "where ase.studentExam.id = ? and type = ?", new Object[]{idStudentExam, 1});
    }
    
    /**
     * Score card
     * @param studentId
     * @param unitContentId
     */
    public List averageMark(Long studentId, Long examId, Long gradeId,boolean isSubjectiveCorrection){
        
        
        double totalAvg = -1.0;
        double markAllSE = 0.0;
        int correctedSubjectives = 0;
        int subjectiveTotal = 0;
        double manualScore = 0.0;
        boolean hasSubjective = false;
        /*
        if(!this.hasNegativeScore(examId, studentId) && !isSubjectiveCorrection){
                //retorna o que já tá em score, calculando todas as médias que já foram salvas anteriormente
                List<StudentExam> ses = this.getStudentExam(studentId, examId);
                
                if(ses!=null && ses.size()>0){
                     
                    double totSc = 0.0;
                     
                    for(StudentExam se: ses){
                        //tem score manual, releva o calculo automatico
                        if(se.getManualScore().doubleValue()!=-1){
                            List res = new ArrayList();
                            res.add(new Double(-1.0));
                            res.add(new Boolean(hasSubjective));
                            res.add(se.getManualScore());
                            return res;
                        }
                        //caso nao, vai calculando oq já tenho
                        totSc += se.getScore().doubleValue();
                        System.out.println(se.getScore().doubleValue());
                    }
                    //System.out.println("totSc - " + totSc);
                    //System.out.println("ses.size() - " + ses.size());
                    //System.out.println("ses.size() - " + totSc/ses.size());
                    totalAvg = totSc/ses.size();
                    
                    List res = new ArrayList();
                    res.add(new Double(totalAvg));
                    res.add(new Boolean(hasSubjective));
                    res.add(new Double(-1));
                     
                
                    return res;
                }else{
                    //nao existem entradas em Student exercise. Valores default
                    //System.out.println("3");
                    List res = new ArrayList();
                    res.add(new Double(totalAvg));
                    res.add(new Boolean(hasSubjective));
                    res.add(new Double(-1));
                    return res;
                }
            }
        */
        
        //tudo o que o aluno respondeu
        String sql = "from StudentExam se " +
                     "where se.student.id=? " +
                     " and se.exam.id=?" +
                     "and se.grade.id=?";
        Object params[] = new Object[]{studentId, examId,gradeId};
        List<StudentExam> studentExamList = daoStudentExam.find(sql, params);
        
        if(studentExamList!=null && studentExamList.size()!=0){
            
            for(StudentExam se : studentExamList){
                
                manualScore  = se.getManualScore().doubleValue();
                
                String sqlForAnswerStudentExam = "from AnswerStudentExam ase where ase.studentExam.id=?";
                Object paramsForAnswerStudentExam[] = new Object[]{se.getId()}; 
                List<AnswerStudentExam> answerStudentExamList = daoAnswerStudentExam.find(sqlForAnswerStudentExam, 
                                                                                                   paramsForAnswerStudentExam);
                
                if(answerStudentExamList!=null){
                    int total = 0;
                    int correctOnes = 0;
                    for(AnswerStudentExam ase: answerStudentExamList){
                        
                        //objective
                        if(ase.getType()==Constants.QUESTION_OBJECTIVE)
                        {
                            List res = isCorrectObjective(ase.getId(), examId);
                            boolean isCorrectFromRes = ((Boolean)res.get(0)).booleanValue();
                            int weightFromRes = ((Integer)res.get(1)).intValue();
                            //System.out.println("A: " + isCorrectFromRes);
                            //System.out.println("B: " + weightFromRes);
                            int nota = 0;
                            if(isCorrectFromRes){
                                nota = 1*weightFromRes;
                                
                            }
                            
                            correctOnes+=nota;
                            total+=weightFromRes;
                        }
                        //subjective
                        
                        else if(ase.getType()==Constants.QUESTION_SUBJECTIVE){
                            hasSubjective = true;
                            
                            List res = isCorrectSubjective(ase.getId(),examId);
                            
                            Integer cor = (Integer)res.get(0);
                            Integer totRes = (Integer)res.get(1);
                            
                            correctedSubjectives+=cor.intValue();
                            subjectiveTotal+= totRes.intValue();
                        }
                       
                        
                    }//check correct ones
                    
                    total += subjectiveTotal;
                    double markSE = 0;
                    if(total!=0)
                        markSE = ((double)(correctOnes+correctedSubjectives)/(double)total)*10;
                    //System.out.println("markSE: "+markSE);
                    
                    this.setGradeForStudentExamId(se.getId(), markSE);
                    
                    markAllSE+=markSE;
                    
                }
                
            }
           
             
            
            totalAvg = markAllSE/studentExamList.size();
             
        }
        
        List res = new ArrayList();
        res.add(new Double(totalAvg));
        res.add(new Boolean(hasSubjective));
        res.add(new Double(manualScore));
        return res;
    }
    
    private List isCorrectObjective(Long answerStudentExamId, Long examId){
        String sql = "from ObjectiveAnswerStudentExam oase where oase.answerStudentExam.id = ?";
        Object[] params = new Object[]{answerStudentExamId};
        List<ObjectiveAnswerStudentExam> listObjectiveAnswerStudentExam = daoAnswerObjective.find(sql, params);
        if(listObjectiveAnswerStudentExam!=null){
            
            for(ObjectiveAnswerStudentExam oase: listObjectiveAnswerStudentExam){
               Integer weight = null; 
               ObjectiveAnswer objectiveAnswer = oase.getObjectiveAnswer();
                
               //System.out.println("objectiveAnswer.getId(): "+objectiveAnswer.getId());
               
               Question question = objectiveAnswer.getQuestion();
                weight = this.getWeight(examId, question.getId());
                
               //System.out.println("question.id: "+question.getId());
               List<ObjectiveQuestion> listObjectiveQuestion = daoObjectiveQuestion.find("from ObjectiveQuestion where question.id=?", new Object[]{question.getId()}); 
               if(listObjectiveQuestion!=null){
                   
                   ObjectiveQuestion oQuestion = listObjectiveQuestion.get(0);
                   //System.out.println("oQuestion.getCorrectAnswer().getId(): "+oQuestion.getCorrectAnswer().getId().longValue());
                   if(oQuestion.getCorrectAnswer().getId().longValue() == objectiveAnswer.getId().longValue()){
                       ArrayList res = new ArrayList();
                       res.add(new Boolean(true));
                       res.add(weight);
                       return res; 
                         
                   }else{
                       ArrayList res = new ArrayList();
                       res.add(new Boolean(true));
                       res.add(weight);
                       return res; 
                   }
                        
               }
               
               
            }
        }
        ArrayList res = new ArrayList();
        res.add(new Boolean(false));
        res.add(1);
        return res;
    }
    
     public boolean hasSubjectiveQuestion(Long examId){
       
        String sql = "Select qe.question.id from QuestionExam qe where qe.exam.id=? and question.type=?";
        Object[] params = new Object[]{examId,new Integer(Constants.QUESTION_SUBJECTIVE)};
        List<Long> questionExams = daoQuestionExam.find(sql, params);
        if(questionExams!=null && questionExams.size()>0)
            return true;
        return false;
       
    }
    
    private List isCorrectSubjective(Long answerStudentExamId, Long examId){
       // System.out.println("--->"+answerStudentExamId.longValue());
        int total = 0;
        int nota = 0; 
         
        String sql = "from AnswerSubjectiveQuestionStudentExam asqse " +
                     "where asqse.answerStudentExam.id = ?";
        Object[] params = new Object[]{answerStudentExamId};
        List<AnswerSubjectiveQuestionStudentExam> list = daoAnswerSubjective.find(sql, params);
        
        if(list!=null){
            AnswerSubjectiveQuestionStudentExam asqse = list.get(0);
            if(asqse.getScore()!=null){
                 int weight = this.getWeight(examId,asqse.getQuestion().getId());
                total+=weight;
                 
                // System.out.println("--->"+asqse.getScore());
                if(asqse.getScore().longValue()==10){
                     
                    nota = 1*weight;
                }else if(asqse.getScore().longValue()==0){
                     
                    nota = 0*weight;
                }
            }
            
        }
        List res = new ArrayList<Object>();
        res.add(0, new Integer(nota));
        res.add(1, new Integer(total)); 
        
         
        return res;
        
    }
    
    private void setGradeForStudentExamId(Long studentExamId, double grade){
        //System.out.println("updating " + studentExamId + " grade " + grade);
        StudentExam se = daoStudentExam.get(studentExamId);
        se.setScore(new Double(grade));
        daoStudentExam.update(se);
    }
    
     public boolean hasNegativeScore(Long exerciseId, Long userId){
        String sql = "select se.score from StudentExam se " +
                    " where se.exam.id=? and se.student.id=?";
        Object[] params = new Object[]{exerciseId,userId};
        List<Double> scores = daoStudentExam.find(sql, params);
        if(scores!=null){
            for(Double score:scores){
                if(score.doubleValue()==-100)
                    return true;
            }
        }
        
        return false;
    }
     
//     public Double averageExamByCourse(Long courseId,Long studentId ){
//        Double averageExam = 0.0;
//        int numberExam =0;
//        boolean hasExam = false;
//        List<StudentExam> studentExam;
//        Grade grade = gradeBean.getActiveByStudentByCourse(studentId, courseId);
//        List<Discipline> disCourse = disciplineBean.getByCourse(courseId);
//        for (Discipline discipline : disCourse) {
//            List<Unit> unitCourse = unitBean.getByDiscipline(discipline.getId());
//            for (Unit unit : unitCourse) {
//                List<UnitContent> unitContentCourse = unitContentBean.getByUnit(unit.getId());
//                for (UnitContent unitContent : unitContentCourse) { 
//                    List<Exam> examCourse = examBean.getListExamByUnitContent(unitContent.getId());
//                    if(examCourse!=null && examCourse.size()>0){
//                        hasExam = true;
//                        for (Exam exam : examCourse) {
//                               studentExam = this.getStudentExam(studentId, exam.getId(),grade.getId());
//                               if(studentExam!=null && studentExam.size()>0){
//                                   if(studentExam.get(0).getManualScore()>0){
//                                        averageExam += (studentExam.get(0).getExam().getWeight() * studentExam.get(0).getManualScore());
//                                        numberExam += studentExam.get(0).getExam().getWeight();
//                                   }
//                                   else if(studentExam.get(0).getScore()>0){
//                                        averageExam += (studentExam.get(0).getExam().getWeight() * studentExam.get(0).getScore());
//                                        numberExam += studentExam.get(0).getExam().getWeight() ;
//                                   }
//                               }
//                        }
//        
//                    
//                   }
//                }
//            }
//        }
//         if (numberExam==0){ 
//             if (hasExam) averageExam = 0.0;
//             else averageExam = -1.0;
//         }
//         else averageExam = averageExam/numberExam;
//        
//        return averageExam;
//    }
     
     public Double averageExamByGrade(Long gradeId,Long studentId ){
        Double averageExam = 0.0;
        int numberExam =0;
        boolean hasExam = false;
        List<StudentExam> studentExam;
        Grade grade = gradeBean.get(gradeId);
        List<Discipline> disCourse = disciplineBean.getByCourse(grade.getCourseId());
        for (Discipline discipline : disCourse) {
            List<Unit> unitCourse = unitBean.getByDiscipline(discipline.getId());
            for (Unit unit : unitCourse) {
                List<UnitContent> unitContentCourse = unitContentBean.getByUnit(unit.getId());
                for (UnitContent unitContent : unitContentCourse) { 
                    List<Exam> examCourse = examBean.getListExamByUnitContent(unitContent.getId());
                    if(examCourse!=null && examCourse.size()>0){
                        hasExam = true;
                        for (Exam exam : examCourse) {
                               studentExam = this.getStudentExam(studentId, exam.getId(),grade.getId());
                               if(studentExam!=null && studentExam.size()>0){
                                   if(studentExam.get(0).getManualScore()>0){
                                        averageExam += (studentExam.get(0).getExam().getWeight() * studentExam.get(0).getManualScore());
                                        numberExam += studentExam.get(0).getExam().getWeight();
                                   }
                                   else if(studentExam.get(0).getScore()>0){
                                        averageExam += (studentExam.get(0).getExam().getWeight() * studentExam.get(0).getScore());
                                        numberExam += studentExam.get(0).getExam().getWeight() ;
                                   }
                               }
                        }
        
                    
                   }
                }
            }
        }
         if (numberExam==0){ 
             if (hasExam) averageExam = 0.0;
             else averageExam = -1.0;
         }
         else averageExam = averageExam/numberExam;
        
        return averageExam;
    }
 
        public boolean updateAllStudentExam(StudentExam studentExam){
        List<StudentExam> list= daoStudentExam.find("from StudentExam se where se.exam.id = ? and se.student.id = ? and se.grade.id =? ",new Object[]{studentExam.getExam().getId(),studentExam.getStudent().getId(),studentExam.getGrade().getId()});
        boolean result = true;
        for (StudentExam studentEx : list) {
            studentEx.setManualScore(studentExam.getManualScore());
            result = daoStudentExam.update(studentEx) && result;
        }
        return result;
    } 
     
    public int getWeight(Long examId, Long questionId){
        
        String sql = "select qe.weight from QuestionExam qe " +
                      "where qe.exam.id=? and qe.question.id=?";
        Object params[] = new Object[]{examId,questionId};
        
        List<Integer> weights = daoQuestionExam.find(sql, params);
        if(weights!=null && weights.size()>0)
            return weights.get(0).intValue();
        return 1;
    }

}
