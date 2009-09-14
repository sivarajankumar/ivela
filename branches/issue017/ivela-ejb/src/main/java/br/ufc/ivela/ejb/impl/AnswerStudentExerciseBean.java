/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.AnswerStudentExercise;
import br.ufc.ivela.commons.model.ObjectiveAnswer;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.AnswerAuditiveQuestionStudentExercise;
import br.ufc.ivela.commons.model.AnswerExternalQuestionStudentExercise;
import br.ufc.ivela.commons.model.AnswerSubjectiveQuestionStudentExercise;
import br.ufc.ivela.commons.model.ChallengerResult;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Exercise;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.ObjectiveAnswerStudentExercise;
import br.ufc.ivela.commons.model.ObjectiveQuestion;
import br.ufc.ivela.commons.model.QuestionExercise;
import br.ufc.ivela.commons.model.StudentExercise;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.AnswerStudentExerciseRemote;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author emanuelle
 */
@Stateless(mappedName="AnswerStudentExerciseBean")
public class AnswerStudentExerciseBean implements AnswerStudentExerciseRemote {

    private GenericDao<StudentExercise> daoStudentExercise = DaoFactory.getInstance(StudentExercise.class);
    private GenericDao<AnswerStudentExercise> daoAnswerStudentExercise = DaoFactory.getInstance(AnswerStudentExercise.class);
    private GenericDao<AnswerAuditiveQuestionStudentExercise> daoAnswerAuditive = DaoFactory.getInstance(AnswerAuditiveQuestionStudentExercise.class);
    private GenericDao<AnswerSubjectiveQuestionStudentExercise> daoAnswerSubjective = DaoFactory.getInstance(AnswerSubjectiveQuestionStudentExercise.class);
    private GenericDao<AnswerExternalQuestionStudentExercise> daoAnswerExternal = DaoFactory.getInstance(AnswerExternalQuestionStudentExercise.class);
    private GenericDao<ObjectiveAnswerStudentExercise> daoAnswerObjective = DaoFactory.getInstance(ObjectiveAnswerStudentExercise.class);
    private GenericDao<Exercise> daoExercise = DaoFactory.getInstance(Exercise.class);
    private GenericDao<SystemUser> daoSystemUser = DaoFactory.getInstance(SystemUser.class);
    private GenericDao<Question> daoQuestion = DaoFactory.getInstance(Question.class);
    private GenericDao<ObjectiveAnswer> daoObjectiveAnswer = DaoFactory.getInstance(ObjectiveAnswer.class);
    private GenericDao<ObjectiveQuestion> daoObjectiveQuestion = DaoFactory.getInstance(ObjectiveQuestion.class);
    private GenericDao<QuestionExercise> daoQuestionExercise = DaoFactory.getInstance(QuestionExercise.class);
    
    private DisciplineBean disciplineBean;
    private UnitBean unitBean;
    private UnitContentBean unitContentBean;
    private ExerciseBean exerciseBean ;
    private GradeBean gradeBean;
    private ChallengeItemsBean challengeItemsBean;
    
//
//    public Long addStudentExercise(Long exerciseId, Long userId) {
//        StudentExercise studentExercise = new StudentExercise();
//        Exercise exercise = daoExercise.get(exerciseId);        
//        studentExercise.setExercise(exercise);
//        studentExercise.setStudent(daoSystemUser.get(userId));
//        studentExercise.setChances(-1);
//        studentExercise.setScore(new Double(-100));
//        studentExercise.setManualScore(new Double(-1));
//        studentExercise.setStatus(Constants.STUDENT_EXERCISE_FINISHED);
//        
//        //decreasing chances
//        /*Exercise exe = daoExercise.get(exerciseId);
//        if(exe.getChances()!=0){
//            exe.setChances(exe.getChances()-1);
//        }
//        daoExercise.update(exe);*/
//        
//        //System.out.println("TESTE: " + studentExercise);
//        return (Long) daoStudentExercise.save(studentExercise);
//    }
//    
        public Long addStudentExercise(Long exerciseId, Long userId, Long courseId) {
        StudentExercise studentExercise = new StudentExercise();
        gradeBean =  new GradeBean();
        Grade grade = gradeBean.getActiveByStudentByCourse(userId, courseId);
        Exercise exercise = daoExercise.get(exerciseId);        
        studentExercise.setExercise(exercise);
        studentExercise.setGrade(grade);
        studentExercise.setStudent(daoSystemUser.get(userId));
        studentExercise.setChances(-1);
        studentExercise.setScore(new Double(-100));
        studentExercise.setManualScore(new Double(-1));
        studentExercise.setStatus(Constants.STUDENT_EXERCISE_FINISHED);
        
        //decreasing chances
        /*Exercise exe = daoExercise.get(exerciseId);
        if(exe.getChances()!=0){
            exe.setChances(exe.getChances()-1);
        }
        daoExercise.update(exe);*/
        
        //System.out.println("TESTE: " + studentExercise);
        return (Long) daoStudentExercise.save(studentExercise);
    }

//    public List<StudentExercise> getStudentExercise(Long userId, Long exerciseId) {
//        return daoStudentExercise.find("from StudentExercise where student.id = ? and exercise.id = ?", new Object[]{userId, exerciseId});
//    }
    
    public List<StudentExercise> getStudentExercise(Long userId, Long exerciseId, Long gradeId) {
        return daoStudentExercise.find("from StudentExercise where student.id = ? and exercise.id = ? and grade.id = ?", new Object[]{userId, exerciseId, gradeId});
    }

    public AnswerStudentExercise getAnswerStudentExercise(Long id) {
        return daoAnswerStudentExercise.get(id);
    }

    public ObjectiveAnswerStudentExercise getObjectiveAnswerStudentExercise(Long id) {
        return daoAnswerObjective.get(id);
    }

    public boolean getNumberStudentExercise(Long userId, Long exerciseId) {
        List list = daoStudentExercise.find("select count(*) from StudentExercise where " +
                                            "student.id = ? and exercise.id = ?", 
                                            new Object[]{userId, exerciseId});
        //System.out.println("kkkkkkkkkkkkkkkkk:" + list.get(0).toString());
        if (list.get(0).toString().equals("0")) {
            return false;
        } else {
            return true;
        }

    }

    public ObjectiveAnswerStudentExercise getObjectiveAnswerStudentExerciseByAnswerStudentExercise(Long answerStudentExerciseId) {
        return (ObjectiveAnswerStudentExercise) (daoAnswerObjective.find("from ObjectiveAnswerStudentExercise oa " +
                "where oa.answerStudentExercise.id = ?",
                new Object[]{answerStudentExerciseId}).get(0));

    }

    public AnswerSubjectiveQuestionStudentExercise getAnswerSubjectiveQuestionStudentExercise(Long anwerStudentExercise, Long questionId) {
        return (AnswerSubjectiveQuestionStudentExercise) (daoAnswerSubjective.find("from AnswerSubjectiveQuestionStudentExercise asq " +
                "where asq.question.id = ? and asq.answerStudentExercise.id = ?", new Object[]{questionId, anwerStudentExercise}).get(0));
    }
    
    public AnswerSubjectiveQuestionStudentExercise getAnswerSubjectiveQuestionStudentExerciseById(Long id) {
        return (AnswerSubjectiveQuestionStudentExercise) (daoAnswerSubjective.find("from AnswerSubjectiveQuestionStudentExercise asq " +
                "where asq.id = ?", new Object[]{id}).get(0));
    }

    public AnswerAuditiveQuestionStudentExercise getAnswerAuditiveQuestionStudentExercise(Long anwerStudentExercise, Long questionId) {
        return (AnswerAuditiveQuestionStudentExercise) (daoAnswerSubjective.find("from AnswerAuditiveQuestionStudentExercise aaq " +
                "where aaq.question.id = ? and aaq.answerStudentExercise.id = ?",
                new Object[]{questionId, anwerStudentExercise}).get(0));
    }

    public AnswerExternalQuestionStudentExercise getAnswerExternalQuestionStudentExercise(Long anwerStudentExercise, Long questionId) {
        return (AnswerExternalQuestionStudentExercise) (daoAnswerSubjective.find("from AnswerExternalQuestionStudentExercise aqs " +
                "where aqs.question.id = ? and aqs.answerStudentExercise.id = ?",
                new Object[]{questionId, anwerStudentExercise}).get(0));
    }

    public Long addAnswerStudentExercise(Long studentExerciseId, int questionType) {
        AnswerStudentExercise answerStudentExercise = new AnswerStudentExercise();
        answerStudentExercise.setStudentExercise(daoStudentExercise.get(studentExerciseId));
        answerStudentExercise.setType(questionType);
        return (Long) daoAnswerStudentExercise.save(answerStudentExercise);

    }
    
    //subjective
    public Long addAnswerStudentQuestionExercise(Long questionId, Long answerStudentExerciseId, String answer, Long studentId, Long exerciseId,Long courseId) {
        Grade g = gradeBean.getActiveByStudentByCourse(studentId, courseId);
        List<StudentExercise> listStudentExercises = this.getStudentExercise(studentId, exerciseId,g.getId());
        if(listStudentExercises!=null && listStudentExercises.size()!=0){
            for(StudentExercise se : listStudentExercises){
                
                List<AnswerStudentExercise> listAnswerStudentExercises = this.getListAnswerStudentExerciseTypeSubjective(se.getId());
                for(AnswerStudentExercise ase : listAnswerStudentExercises){
                  
                    List<AnswerSubjectiveQuestionStudentExercise> listAnswerSubjectiveQuestionStudentExercises = this.getListAnswerSubjectiveQuestionStudentExercise(ase.getId());
                    if(listAnswerSubjectiveQuestionStudentExercises!=null && listAnswerStudentExercises.size()!=0){
                        for(AnswerSubjectiveQuestionStudentExercise asqse: listAnswerSubjectiveQuestionStudentExercises){
                            if(asqse.getQuestion().getId().longValue()==questionId.longValue()){
                                asqse.setAnswer(answer);
                                daoAnswerSubjective.update(asqse);
                                daoAnswerStudentExercise.remove(answerStudentExerciseId);
                                return asqse.getId();
                            }
                        }
                    }
                    
                }
            }
        }
        
        
        AnswerSubjectiveQuestionStudentExercise answerSubjectiveQuestionStudentExercise = new AnswerSubjectiveQuestionStudentExercise();
        answerSubjectiveQuestionStudentExercise.setAnswer(answer);

        AnswerStudentExercise answerStudentExercise = getAnswerStudentExercise(answerStudentExerciseId);
        answerSubjectiveQuestionStudentExercise.setAnswerStudentExercise(answerStudentExercise);

        Question question = daoQuestion.get(questionId);
        answerSubjectiveQuestionStudentExercise.setQuestion(question);

        return (Long) daoAnswerSubjective.save(answerSubjectiveQuestionStudentExercise);

    }

    //objective
    public Long addAnswerStudentQuestionExercise(Long answerStudentExerciseId, Long objectiveAnswerId) {

        ObjectiveAnswerStudentExercise objectiveAnswerStudentExercise = new ObjectiveAnswerStudentExercise();

        AnswerStudentExercise answerStudentExercise = getAnswerStudentExercise(answerStudentExerciseId);
        ObjectiveAnswer objectiveAnswer = daoObjectiveAnswer.get(objectiveAnswerId);

        objectiveAnswerStudentExercise.setAnswerStudentExercise(answerStudentExercise);
        objectiveAnswerStudentExercise.setObjectiveAnswer(objectiveAnswer);

        return (Long) daoAnswerObjective.save(objectiveAnswerStudentExercise);
    }    // Add business logic below. (Right-click in editor and choose

    //auditive
    public Long addAnswerStudentQuestionExercise(Long questionId, Long answerStudentExerciseId, BigDecimal score, int times) {
        AnswerAuditiveQuestionStudentExercise answerAuditiveQuestionStudentExercise = new AnswerAuditiveQuestionStudentExercise();

        Question question = daoQuestion.get(questionId);
        answerAuditiveQuestionStudentExercise.setQuestion(question);

        AnswerStudentExercise answerStudentExercise = getAnswerStudentExercise(answerStudentExerciseId);
        answerAuditiveQuestionStudentExercise.setAnswerStudentExercise(answerStudentExercise);


        answerAuditiveQuestionStudentExercise.setTimes(times);
        
        answerAuditiveQuestionStudentExercise.setScore(score);
        return (Long) daoAnswerAuditive.save(answerAuditiveQuestionStudentExercise);
    }

    //external
    public Long addAnswerStudentQuestionExercise(Long questionId, Long answerStudentExerciseId, Boolean aproved, String resultValue) {
        AnswerExternalQuestionStudentExercise answerExternalQuestionStudentExercise = new AnswerExternalQuestionStudentExercise();

        Question question = daoQuestion.get(questionId);
        AnswerStudentExercise answerStudentExercise = getAnswerStudentExercise(answerStudentExerciseId);

        answerExternalQuestionStudentExercise.setQuestion(question);
        answerExternalQuestionStudentExercise.setAnswerStudentExercise(answerStudentExercise);

        answerExternalQuestionStudentExercise.setAproved(aproved);
        answerExternalQuestionStudentExercise.setResultValue(resultValue);

        return (Long) daoAnswerExternal.save(answerExternalQuestionStudentExercise);
    }
    
    /**
     * Score card
     * @param studentId
     * @param unitContentId
     */
    public List averageMark(Long studentId, Long exerciseId,Long gradeId, boolean isSubjectiveCorrection){
        
        
        double totalAvg = -1.0;
        double markAllSE = 0.0;
        double manualScore = 0.0;
        int correctedSubjectives = 0;
        int subjectiveTotal = 0;
        boolean hasSubjective = hasSubjectiveQuestion(exerciseId);
        
        /*
            if(!this.hasNegativeScore(exerciseId, studentId) && !isSubjectiveCorrection){
                //retorna o que já tá em score, calculando todas as médias que já foram salvas anteriormente
                List<StudentExercise> ses = this.getStudentExercise(studentId, exerciseId);
                
                if(ses!=null && ses.size()>0){
                     
                    double totSc = 0.0;
                     
                    for(StudentExercise se: ses){
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
                        //System.out.println(se.getScore().doubleValue());
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
        //CALCULO AUTOMATICO DE NOTA! SO VAI SER REALIZADO CASO SE ESTEJA COM SCORE -1
        //tudo o que o aluno respondeu
        String sql = "from StudentExercise se " +
                     "where se.student.id=? " +
                     " and se.exercise.id=?"+
                     " and se.grade.id = ?";
        Object params[] = new Object[]{studentId, exerciseId, gradeId};
        List<StudentExercise> studentExerciseList = daoStudentExercise.find(sql, params);
        
        if(studentExerciseList!=null && studentExerciseList.size()!=0){
            
            for(StudentExercise se : studentExerciseList){
                 //System.out.println("----");
                 manualScore  = se.getManualScore().doubleValue();
                
                String sqlForAnswerStudentExercise = "from AnswerStudentExercise ase where ase.studentExercise.id=?";
                Object paramsForAnswerStudentExercise[] = new Object[]{se.getId()}; 
                List<AnswerStudentExercise> answerStudentExerciseList = daoAnswerStudentExercise.find(sqlForAnswerStudentExercise, 
                                                                                                   paramsForAnswerStudentExercise);
                
                if(answerStudentExerciseList!=null){
                    int total = 0;
                    int correctOnes = 0;
                    for(AnswerStudentExercise ase: answerStudentExerciseList){
                         
                        //objective
                        if(ase.getType()==Constants.QUESTION_OBJECTIVE)
                        {
                            
                            List res = isCorrectObjective(ase.getId(), exerciseId);
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
                            
                            List res = isCorrectSubjective(ase.getId(),exerciseId);
                            
                            Integer cor = (Integer)res.get(0);
                            Integer totRes = (Integer)res.get(1);
                            
                            correctedSubjectives+=cor.intValue();
                            subjectiveTotal+= totRes.intValue();
                            /*if(cor.booleanValue()){
                                correctedSubjectives++;
                               
                                 
                            }*/
                        }
                       
                        
                    }//check correct ones
                    
                    total += subjectiveTotal;
                    double markSE = 0;
                    if(total!=0)
                      markSE = ((double)(correctOnes+correctedSubjectives)/(double)total)*10;
                    
                    //System.out.println("=="+markSE);
                    
                    this.setMarkForStudentExerciseId(se.getId(), markSE);
                    
                    markAllSE+=markSE;
                    
                }
                
            }
           
             
            
            totalAvg = markAllSE/studentExerciseList.size();
             
        }
        List res = new ArrayList();
        res.add(new Double(totalAvg));
        res.add(new Boolean(hasSubjective));
        res.add(new Double(manualScore));
        return res;
    }
    
    private List isCorrectObjective(Long answerStudentExerciseId, Long exerciseId){
        String sql = "from ObjectiveAnswerStudentExercise oase where oase.answerStudentExercise.id = ?";
        Object[] params = new Object[]{answerStudentExerciseId};
        List<ObjectiveAnswerStudentExercise> listObjectiveAnswerStudentExercise = daoAnswerObjective.find(sql, params);
        if(listObjectiveAnswerStudentExercise!=null){
            
            for(ObjectiveAnswerStudentExercise oase: listObjectiveAnswerStudentExercise){
                
               Integer weight = null; 
               ObjectiveAnswer objectiveAnswer = oase.getObjectiveAnswer();
               //System.out.println("objectiveAnswer.getId(): "+objectiveAnswer.getId());
                
               
               Question question = objectiveAnswer.getQuestion();
               
               weight = this.getWeight(exerciseId, question.getId());
               //System.out.println(exerciseId+"--"+question.getId()+"--"+weight);
               
               //System.out.println("question.id: "+question.getId());
               List<ObjectiveQuestion> listObjectiveQuestion = daoObjectiveQuestion.find("from ObjectiveQuestion where question.id=?", new Object[]{question.getId()}); 
               if(listObjectiveQuestion!=null){
                   
                   ObjectiveQuestion oQuestion = listObjectiveQuestion.get(0);
                   //System.out.println("oQuestion.getCorrectAnswer().getId(): "+oQuestion.getCorrectAnswer().getId().longValue());
                   if(oQuestion.getCorrectAnswer().getId().longValue() == objectiveAnswer.getId().longValue()){
                      // System.out.println("A-T");
                       ArrayList res = new ArrayList();
                       res.add(new Boolean(true));
                       res.add(weight);
                       return res;    
                   }else{
                       //System.out.println("A-F");
                       ArrayList res = new ArrayList();
                       res.add(new Boolean(false));
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
    
    private List isCorrectSubjective(Long answerStudentExerciseId, Long exerciseId){
        int total = 0;
        int nota = 0; 
        String sql = "from AnswerSubjectiveQuestionStudentExercise asqse " +
                     "where asqse.answerStudentExercise.id = ?";
        Object[] params = new Object[]{answerStudentExerciseId};
        List<AnswerSubjectiveQuestionStudentExercise> list = daoAnswerSubjective.find(sql, params);
        
        if(list!=null){
            AnswerSubjectiveQuestionStudentExercise asqse = list.get(0);
            
            if(asqse.getScore()!=null){
                int weight = this.getWeight(exerciseId,asqse.getQuestion().getId());
                total+=weight;
                
                if(asqse.getScore().longValue()==10){
                    //System.out.println("B-T");
                    nota = 1*weight;
                    //correct = true;
                }else if(asqse.getScore().longValue()==0){
                    //System.out.println("B-F");
                    nota = 0*weight;
                    //correct = false;
                }
                
                
            }
            
        }
        
        
         List res = new ArrayList<Object>();
         res.add(0, new Integer(nota));
         res.add(1, new Integer(total));
         return res;
        
    }
    
    private void setMarkForStudentExerciseId(Long studentExerciseId, double mark){
        StudentExercise se = daoStudentExercise.get(studentExerciseId);
        se.setScore(new Double(mark));
        daoStudentExercise.update(se);
    }
    
    public boolean updateAnswerSubjectiveQuestionStudentExercise(AnswerSubjectiveQuestionStudentExercise ans) {

        return daoAnswerSubjective.update(ans);
    }

    public List<AnswerSubjectiveQuestionStudentExercise> getListAnswerSubjectiveQuestionStudentExercise(Long idAnwerStudentExercise) {
        return (List<AnswerSubjectiveQuestionStudentExercise>) daoAnswerSubjective.find("from AnswerSubjectiveQuestionStudentExercise asq " +
                "where asq.answerStudentExercise.id = ? order by asq.id", new Object[]{idAnwerStudentExercise});
    }

    public List<AnswerStudentExercise> getListAnswerStudentExerciseTypeSubjective(Long idStudentExercise) {
        return (List<AnswerStudentExercise>) daoAnswerStudentExercise.find("from AnswerStudentExercise ase " +
                "where ase.studentExercise.id = ? and type = ?", new Object[]{idStudentExercise, 1});
    }
    
       
        
        
//      public Double averageExercisesByCourse(Long courseId,Long studentId ){
//        Double averageExe = 0.0;
//        int numberExe =0;
//        boolean hasExercise = false;
//        List<StudentExercise> studentExercise;
//        disciplineBean = new DisciplineBean();
//        unitBean = new UnitBean();
//        unitContentBean  = new UnitContentBean();
//        exerciseBean = new ExerciseBean();
//        Grade grade = gradeBean.getActiveByStudentByCourse(studentId, courseId);
//        List<Discipline> disCourse = disciplineBean.getByCourse(courseId);
//        for (Discipline discipline : disCourse) {
//            List<Unit> unitCourse = unitBean.getByDiscipline(discipline.getId());
//            for (Unit unit : unitCourse) {
//                List<UnitContent> unitContentCourse = unitContentBean.getByUnit(unit.getId());
//                for (UnitContent unitContent : unitContentCourse) {                    
//                    List<Exercise> exerciseCourse = exerciseBean.getListExerciseByUnitContent(unitContent.getId());
//                    if(exerciseCourse!=null &&  exerciseCourse.size()>0){
//                        hasExercise = true;
//                        for (Exercise exercise : exerciseCourse) {
//                               studentExercise = this.getStudentExercise(studentId, exercise.getId(),grade.getId());
//                               if(studentExercise!=null && studentExercise.size()>0){
//                                   if(studentExercise.get(0).getManualScore()>0){
//                                        averageExe += (studentExercise.get(0).getExercise().getWeight() * studentExercise.get(0).getManualScore());
//                                        numberExe += studentExercise.get(0).getExercise().getWeight();
//                                   }
//                                   else if(studentExercise.get(0).getScore()>0){
//                                        averageExe += (studentExercise.get(0).getExercise().getWeight() * studentExercise.get(0).getScore());
//                                        numberExe += studentExercise.get(0).getExercise().getWeight();
//                                   }
//                               }
//                        }
//        
//                    
//                   }
//                        
//                }
//            }
//        }
//          if (numberExe==0){
//            if(hasExercise) averageExe = 0.0;
//              else averageExe = -1.0;
//          }          
//          else averageExe = averageExe/numberExe;
//        
//        return averageExe;
//    }
//      
    public Double averageExercisesByGrade(Long gradeId,Long studentId ){
        Double averageExe = 0.0;
        int numberExe =0;
        boolean hasExercise = false;
        gradeBean = new GradeBean();
        disciplineBean  = new DisciplineBean();
        unitBean = new UnitBean();
        unitContentBean  = new UnitContentBean();
        Grade grade = gradeBean.get(gradeId);
        exerciseBean = new ExerciseBean();
        unitContentBean = new UnitContentBean();
        challengeItemsBean = new ChallengeItemsBean();
        List<StudentExercise> studentExercise;
        List<Discipline> disCourse = disciplineBean.getByCourse(grade.getCourseId());
        for (Discipline discipline : disCourse) {
            List<Unit> unitCourse = unitBean.getByDiscipline(discipline.getId());
            for (Unit unit : unitCourse) {
                List<UnitContent> unitContentCourse = unitContentBean.getByUnit(unit.getId());
                for (UnitContent unitContent : unitContentCourse) {
                    List<ChallengerResult> challengerResultList = challengeItemsBean.getChallengerResultByUnitContent(unitContent.getId(), studentId,grade.getId());
                    if(challengerResultList!=null && challengerResultList.size()>0){
                        for (ChallengerResult challengerResult : challengerResultList) {
                            averageExe += challengerResult.getMark();
                            numberExe++;
                        }
                    }
                    
                    List<Exercise> exerciseCourse = exerciseBean.getListExerciseByUnitContent(unitContent.getId());
                    if(exerciseCourse!=null &&  exerciseCourse.size()>0){
                        hasExercise = true;
                        for (Exercise exercise : exerciseCourse) {
                               studentExercise = this.getStudentExercise(studentId, exercise.getId(),grade.getId());
                               if(studentExercise!=null && studentExercise.size()>0){
                                   if(studentExercise.get(0).getManualScore()>0){
                                        averageExe += (studentExercise.get(0).getExercise().getWeight() * studentExercise.get(0).getManualScore());
                                        numberExe += studentExercise.get(0).getExercise().getWeight();
                                   }
                                   else if(studentExercise.get(0).getScore()>0){
                                        averageExe += (studentExercise.get(0).getExercise().getWeight() * studentExercise.get(0).getScore());
                                        numberExe += studentExercise.get(0).getExercise().getWeight();
                                   }
                               }
                        }
        
                    
                   }
                        
                }
            }
        }
          if (numberExe==0){
            if(hasExercise) averageExe = 0.0;
              else averageExe = -1.0;
          }          
          else averageExe = averageExe/numberExe;
        
        return averageExe;
    }      
    
    public boolean updateAllStudentExercise(StudentExercise studentExercise){
        List<StudentExercise> list= daoStudentExercise.find("from StudentExercise se where se.exercise.id = ? and se.student.id = ? and se.grade.id = ?",new Object[]{studentExercise.getExercise().getId(),studentExercise.getStudent().getId(),studentExercise.getGrade().getId()});
        boolean result = true;
        for (StudentExercise studentExe : list) {
            studentExe.setManualScore(studentExercise.getManualScore());
            result = daoStudentExercise.update(studentExe) && result;
        }
        return result;
    }
    
    public boolean hasSubjectiveQuestion(Long exerciseId){
       
        String sql = "Select qe.question.id from QuestionExercise qe where qe.exercise.id=? and question.type=?";
        Object[] params = new Object[]{exerciseId,new Integer(Constants.QUESTION_SUBJECTIVE)};
        List<Long> questionExercises = daoQuestionExercise.find(sql, params);
        if(questionExercises!=null && questionExercises.size()>0)
            return true;
        return false;
       
    }
    
    public boolean hasNegativeScore(Long exerciseId, Long userId , Long gradeId){
        String sql = "select se.score from StudentExercise se " +
                    " where se.exercise.id=? and se.student.id=? and se.grade.id =?";
        Object[] params = new Object[]{exerciseId,userId,gradeId};
        List<Double> scores = daoStudentExercise.find(sql, params);
        if(scores!=null){
            for(Double score:scores){
                if(score.doubleValue()==-100)
                    return true;
            }
        }
        
        return false;
    }
    
    public int getWeight(Long exerciseId, Long questionId){
        
        String sql = "select qe.weight from QuestionExercise qe " +
                      "where qe.exercise.id=? and qe.question.id=?";
        Object params[] = new Object[]{exerciseId,questionId};
        
        List<Integer> weights = daoQuestionExercise.find(sql, params);
        if(weights!=null && weights.size()>0)
            return weights.get(0).intValue();
        return 1;
    } 
    
}
