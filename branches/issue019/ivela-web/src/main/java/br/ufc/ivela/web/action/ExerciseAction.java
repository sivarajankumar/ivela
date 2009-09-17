/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.CheckItem;
import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.TypeAnswer;
import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.AuditiveQuestion;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Exercise;
import br.ufc.ivela.commons.model.FinishedExercise;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.ObjectiveAnswer;
import br.ufc.ivela.commons.model.ObjectiveQuestion;
import br.ufc.ivela.ejb.interfaces.ExerciseRemote;
import br.ufc.ivela.ejb.interfaces.QuestionRemote;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.QuestionExercise;
import br.ufc.ivela.commons.model.QuestionText;
import br.ufc.ivela.commons.model.SentenceAuditiveQuestion;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.QuestionExerciseRemote;
import br.ufc.ivela.commons.util.Validators;
import br.ufc.ivela.ejb.interfaces.AnswerStudentExerciseRemote;
import br.ufc.ivela.ejb.interfaces.AuditiveQuestionRemote;
import br.ufc.ivela.ejb.interfaces.DisciplineRemote;
import br.ufc.ivela.ejb.interfaces.ExternalQuestionRemote;
import br.ufc.ivela.ejb.interfaces.FinishedExerciseRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.interfaces.ObjectiveAnswerRemote;
import br.ufc.ivela.ejb.interfaces.ObjectiveQuestionRemote;
import br.ufc.ivela.ejb.interfaces.UnitContentRemote;
import br.ufc.ivela.ejb.interfaces.UnitRemote;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Maristella Myrian
 */
public class ExerciseAction extends GenericAction {

    //remotes
    private ExerciseRemote exerciseRemote;
    private QuestionRemote questionRemote;
    private UnitContentRemote unitContentRemote;
    private QuestionExerciseRemote questionExerciseRemote;
    private ObjectiveQuestionRemote objectiveQuestionRemote;
    private ObjectiveAnswerRemote objectiveAnswerRemote;
    private AuditiveQuestionRemote auditiveQuestionRemote;
    private ExternalQuestionRemote externalQuestionRemote;
    private UnitRemote unitRemote;
    private DisciplineRemote disciplineRemote;
    private GradeRemote gradeRemote; 
    private Course course;
    private AnswerStudentExerciseRemote answerStudentExerciseRemote;    //session objects
    private Question question;
    private QuestionExercise questionExercise;
    private UnitContent unitContent;
    private QuestionText questionText;
    private Exercise exercise;
    private List<Exercise> exerciseListFromUnitContent;
    private List<Exercise> exerciseList;
    private List<Question> questionList;
    private List<QuestionExercise> questionExerciseList;
    private List<CheckItem> checkItens;
    private boolean[] exercisesCompleted;    //subjective
    private int[] chancesNumber;
    private String subjectiveAnswer;     //external
    private String urlExternal;    //objective
    private List<ObjectiveAnswer> listObjectiveAnswer;
    private ObjectiveAnswer objectiveAnswer;
    private ObjectiveQuestion objectiveQuestion;    //auditive
    private List<SentenceAuditiveQuestion> listAuditiveQuestion;
    private AuditiveQuestion auditiveQuestion;
    private String fileConf;
    private String sentenceAuditiveQuestion;
    private String audioAuditiveQuestion;
    private Map<String, CheckItem> checkItensMap;    //paginator
    private int pageCount;
    private int page;
    private int pageSize = 1;
    private int count;    //question type
    private int questionObjective;
    private int questionSubjective;
    private int questionAuditive;
    private int questionExternal;
    private int chancesToExercise;
    //
    private Long answerStudentExerciseId;
    private Vector<String> result;
    private java.io.InputStream inputStream;
    private String back;
    private String requisitesString;
    private boolean cleanSubjective;
    private FinishedExerciseRemote finishedExerciseRemote;

    public FinishedExerciseRemote getFinishedExerciseRemote() {
        return finishedExerciseRemote;
    }

    public void setFinishedExerciseRemote(FinishedExerciseRemote finishedExerciseRemote) {
        this.finishedExerciseRemote = finishedExerciseRemote;
    }
     
    

    /**
     * Add a new Exercise
     *  @return a list of the exercise
     */
    public String add() {
        // performValidates the add
        performValidateAdd();
        if (!hasActionErrors()) {
            Long idExercise = exerciseRemote.add(exercise);
            setExercise(exerciseRemote.get(idExercise));
            if (getCheckItensMap() != null) {
                for (String key : getCheckItensMap().keySet()) {
                    CheckItem c = (CheckItem) getCheckItensMap().get(key);
                    if (c.isValue()) {
                        QuestionExercise qe = new QuestionExercise();
                        qe.setQuestion(questionRemote.get(new Long(key)));
                        qe.setExercise(exercise);
                        questionExerciseRemote.add(qe);
                    }
                }
            }
            return list();
        }
        return ERROR;
    }
    
    /**
     * Remove a Exercise by Id
     * @return a list of the exercise
     */
    public String remove() {
        // performValidates the remove
        performValidateRemove();

        if (!hasActionErrors()) {
            exerciseRemote.remove(exercise.getId());
            return list();
        }
        return ERROR;
    }

    /**
     * Remove a QuestionExercise bye Id
     * @return edit
     */
    public String removeQuestionExercise() {
        questionExerciseRemote.remove(questionExercise.getId());
        return edit();
    }

    /**
     * Edit a exercise
     * @return edit
     */
    public String edit() {
        setExercise(exerciseRemote.get(exercise.getId()));
        setQuestionExerciseList(questionExerciseRemote.getByExercise(exercise.getId()));
        checkItens = new ArrayList<CheckItem>();
        setQuestionList(questionRemote.getAll());

        for (Question questionRef : getQuestionList()) {
            CheckItem chkItem = new CheckItem();
            chkItem.setId(String.valueOf(questionRef.getId()));
            chkItem.setObject(questionRef);
            chkItem.setValue(false);
            checkItens.add(chkItem);
        }
        this.checkItensMap = new HashMap();
        for (CheckItem c : this.getCheckItens()) {
            this.checkItensMap.put(c.getId(), c);
        }
        return "edit";
    }

    public String getExerciseUnitContentJson() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        String json = xStream.toXML(exerciseRemote.getListExerciseByUnitContent(unitContent.getId()).size());
        json = json.replaceAll("int", "count");

        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    /**
     * Update a exercise
     * @return a string
     */
    public String update() {
        // performValidates the update
        performValidateUpdate();

        if (!hasActionErrors()) {
            exerciseRemote.update(exercise);
            setExercise(exerciseRemote.get(exercise.getId()));
            if (getCheckItensMap() != null) {
                for (String key : getCheckItensMap().keySet()) {
                    CheckItem c = (CheckItem) getCheckItensMap().get(key);
                    if (c.isValue()) {
                        QuestionExercise qe = new QuestionExercise();
                        qe.setQuestion(questionRemote.get(new Long(key)));
                        qe.setExercise(exercise);
                        questionExerciseRemote.add(qe);
                    }
                }

            }
            return list();
        }
        return ERROR;
    }

    /**
     * Sets the variables to be used on the input exercise
     */
    public String input() {
        checkItens = new ArrayList<CheckItem>();
        setQuestionList(questionRemote.getAll());

        for (Question question : getQuestionList()) {
            CheckItem chkItem = new CheckItem();
            chkItem.setId(String.valueOf(question.getId()));
            chkItem.setObject(question);
            chkItem.setValue(false);
            checkItens.add(chkItem);
        }

        this.checkItensMap = new HashMap();
        for (CheckItem c : this.getCheckItens()) {
            logger.log(">> " + c.getId());
            this.checkItensMap.put(c.getId(), c);
        }

        return "input";
    }

    /**
     * Saving result in session, after each paging.
     * ALL question uses this method.
     * The whole set of results is submited to server after.
     * @return
     */
    public String saveResultQuestion() {

         
        
           //before paging
            this.saveInSession();

            //update question
            String returnPage = this.showPaging() ;

            //after paging. If the questions exists in Session, the Front-End will show it.
            this.getFromSession();

            return returnPage;
        
    }

    /**
     * returns each type of question from session.
     */
    private void getFromSession(){
        HashMap<Long, TypeAnswer> results = (HashMap<Long, TypeAnswer>) this.getSession().get("EXERCISE_RESULT");
         
        //logger.log("%%Current get: " + currentQuestionId.longValue());
        TypeAnswer sessionTypeAnswer = results.get(this.question.getId());
        
        switch (question.getType()) {
            
            case Constants.QUESTION_OBJECTIVE:
                
                
                
                if(sessionTypeAnswer!=null){
                    ObjectiveAnswer sessionObjectiveAnswer = (ObjectiveAnswer)sessionTypeAnswer.getAnswer();
                    this.setObjectiveAnswer(sessionObjectiveAnswer);
                }
                    
                
                break;
            case Constants.QUESTION_AUDITIVE:
                
                
                if(sessionTypeAnswer!=null){
                    BigDecimal sessionAuditiveAnswer = (BigDecimal)sessionTypeAnswer.getAnswer();
                }
                
                break;
            case Constants.QUESTION_SUBJECTIVE:
                
                
                if(sessionTypeAnswer!=null){
                    String sessionSubjectiveAnswer = (String)sessionTypeAnswer.getAnswer();
                    
                    logger.log("%%%sessionSubjectiveAnswer"+sessionSubjectiveAnswer);
                    this.setSubjectiveAnswer(sessionSubjectiveAnswer);
                }
                    
                
                 
                break;
            case Constants.QUESTION_EXTERNAL:
                
               
                if(sessionTypeAnswer!=null){
                     String sessionExternalAnswer = (String)sessionTypeAnswer.getAnswer();
                }  
                
                break;
        }
    }
    
    /**
     * Saving in session.
     */
    private void saveInSession() {
        
        HashMap<Long, TypeAnswer> results = (HashMap<Long, TypeAnswer>) this.getSession().get("EXERCISE_RESULT");
        if (results == null) {
            
            results = new HashMap<Long, TypeAnswer>();
            this.getSession().put("EXERCISE_RESULT", results);
        }

        
        Long currentQuestionId = this.question.getId();
        logger.log("%%Current save: " + currentQuestionId.longValue());
        logger.log("%%Current subjectiveAnswer: " + this.subjectiveAnswer);
        
        switch (question.getType()) {
            
            case Constants.QUESTION_OBJECTIVE:
                
                results.put(currentQuestionId, 
                            new TypeAnswer( Constants.QUESTION_OBJECTIVE, 
                                            objectiveAnswer));
                
                break;
            case Constants.QUESTION_AUDITIVE:
                
                results.put(currentQuestionId,
                            new TypeAnswer(Constants.QUESTION_AUDITIVE,
                                    new BigDecimal(10))
                            );
                
                break;
            case Constants.QUESTION_SUBJECTIVE:
                logger.log("%%%Dentro do case:"+this.subjectiveAnswer);
                results.put(currentQuestionId, 
                        new TypeAnswer(Constants.QUESTION_SUBJECTIVE,
                                       this.subjectiveAnswer)
                        );
                
                 
                break;
            case Constants.QUESTION_EXTERNAL:
                
                
                results.put(currentQuestionId, new TypeAnswer(Constants.QUESTION_EXTERNAL,
                                                              "100%"));
                  
                
                break;
        }
       

    }
    
     public String getExerciseQuestionNumberJson(){
         
        List<Question> questions = exerciseRemote.getQuestions(exercise.getId());
        
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("questionNumber", String.class);
        String questionN = ""+questions.size();
        setInputStream(new ByteArrayInputStream( xStream.toXML(questionN).getBytes()));
        return "json";
     }
    
   /**
     * Retrieves a requisites of one json structure
     * @return
     */
    public String getExerciseRequistedJson(){
         
        Exercise exeToJson = exerciseRemote.get(exercise.getId());
        Set<Exercise> requisitesSet = exeToJson.getRequisites();
        List<String> requisites = new Vector();
        for(Exercise exe: requisitesSet){
            if(!exerciseRemote.isExerciseFinishedForStudent(exe.getId(), 
                                                           getAuthenticatedUser().getId())){
                requisites.add(exe.getTitle()+"#");
            }
                              
        }
        
        
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("exercises", String.class);
         
        setInputStream(new ByteArrayInputStream( xStream.toXML(requisites).getBytes()));
        return "json";
    }

   /**
    * This method paginates the questions of an exercise
    * @return
    */
    public String showPaging() {
        
         
        
        int pageSessionCount = -1;
        if (page < 1) {
            page = 1;
        }

        //really necessary???
        setExercise(exerciseRemote.get(exercise.getId()));

        if (this.getSession().get("PAGE_COUNT") != null) {
            pageSessionCount = ((Integer) this.getSession().get("PAGE_COUNT")).intValue();
        }
        
       
        
        //mostrando os resultados
        if (page == (pageSessionCount + 1)) {
            
            if(this.getSession().get("EXERCISE_RESULT")!=null){
                return showResultExerciseFromSession();
            }

        } else {



            Page p = exerciseRemote.getQuestionPageByExercise(exercise.getId(), page, 1);

            this.question = (Question) p.getList().get(0);
            
            //clean subjective
            clearSubjective();
            
            //choosing the current type question 
            this.chooseQuestion();

            this.getSession().put("PAGE_COUNT", new Integer(p.getPageCount()));

            setCount(p.getCount());
            setPageCount(p.getPageCount());
        }
        return "show";
    }

    /**
     * Clean subjective question textarea
     */
    private void clearSubjective(){
        if(this.question.getType()==Constants.QUESTION_SUBJECTIVE){
            HashMap<Long, TypeAnswer> resultsFromSession = (HashMap<Long, TypeAnswer>) this.getSession().get("EXERCISE_RESULT");
            if(resultsFromSession!=null){
                TypeAnswer current = resultsFromSession.get(question.getId());
                if(current==null){
                    this.cleanSubjective = true;
                }else
                    this.cleanSubjective = false;
            }else
                this.cleanSubjective = true;
            
        }
    }
    
    //
    /*
    private void saveInDataBase() {
         
        //saving
        if (this.answerStudentExerciseId == null) {

            Long studentExerciseId = this.answerStudentExerciseRemote.addStudentExercise(exercise.getId(),
                    getAuthenticatedUser().getId());

            this.setAnswerStudentExerciseId(this.answerStudentExerciseRemote.addAnswerStudentExercise(studentExerciseId));

        }

        if (question.getType() == Constants.QUESTION_OBJECTIVE) {


            this.answerStudentExerciseRemote.addAnswerStudentQuestionExercise(answerStudentExerciseId,
                    objectiveAnswer.getId());


        } else if (question.getType() == Constants.QUESTION_AUDITIVE) {

            this.answerStudentExerciseRemote.addAnswerStudentQuestionExercise(question.getId(),
                    answerStudentExerciseId,
                    new BigDecimal(10), 5);


        } else if (question.getType() == Constants.QUESTION_SUBJECTIVE) {

            this.answerStudentExerciseRemote.addAnswerStudentQuestionExercise(question.getId(),
                    this.getAnswerStudentExerciseId(),
                    this.subjectiveAnswer);

        } else if (question.getType() == Constants.QUESTION_EXTERNAL) {

            this.answerStudentExerciseRemote.addAnswerStudentQuestionExercise(question.getId(),
                    this.getAnswerStudentExerciseId(),
                    true, "100%");
        }
    }*/
    
    /**
     * This method gets the result from session. 
     * show and save
     * @return
     */
    private String showResultExerciseFromSession(){
        //logger.log("%%FROM SESSION");
        
        setExercise(exerciseRemote.get(exercise.getId()));
        
        //fetch from session
        this.result = new Vector<String>();
        List<Question> list = exerciseRemote.getQuestions(exercise.getId());
        HashMap<Long, TypeAnswer> resultsFromSession = (HashMap<Long, TypeAnswer>) this.getSession().get("EXERCISE_RESULT");
        
        
        
        
        //SAVING student exercise 
        //
        Long studentExerciseId = this.answerStudentExerciseRemote.addStudentExercise(exercise.getId(),
                    getAuthenticatedUser().getId(),course.getId());
        
        
         
        //
        this.exerciseRemote.updateStudentExerciseChances(getAuthenticatedUser().getId(), exercise.getId());
        
        //saving questions
        for (Question q : list) {
            //logger.log("%%FROM SESSION IN FOR: " + this.getAnswerStudentExerciseId());
            
            if (q.getType() == Constants.QUESTION_OBJECTIVE) {
               
                //fetching the results
                TypeAnswer typeAnswer = resultsFromSession.get(q.getId());
                ObjectiveAnswer sessionObjectiveAnswer = (ObjectiveAnswer)typeAnswer.getAnswer();
                Long correctAnswerId = objectiveQuestionRemote.getByQuestion(q.getId()).getCorrectAnswer().getId();
               
                //saving in table answer student exercise
                Long aseLong = this.answerStudentExerciseRemote.addAnswerStudentExercise(studentExerciseId,q.getType());
                
                //saving in data base, table ObjectiveAnswerExercise (To later acess),
                // binding with aselong
                this.answerStudentExerciseRemote.addAnswerStudentQuestionExercise(aseLong,
                                                                                  sessionObjectiveAnswer.getId());
               
                
                //results
                if (sessionObjectiveAnswer.getId().longValue() == correctAnswerId.longValue()) {
                    result.add("Correct");
                //result[i] = "10";
                } else {
                    result.add("Wrong");
                //result[i] = "0";
                }
            } else if (q.getType() == Constants.QUESTION_SUBJECTIVE) {

                TypeAnswer typeAnswer = resultsFromSession.get(q.getId());
                String  answerSub = (String)typeAnswer.getAnswer();
                
                BigDecimal score = null;
                if (score != null) {
                    //result[i] = res;
                    result.add(score.toString());
                } else {
                    //result[i] = "Enviada para professor avaliar";
                    result.add("Enviada para professor avaliar");
                }
                
                Long aseLong = this.answerStudentExerciseRemote.addAnswerStudentExercise(studentExerciseId,q.getType());
                
                
                
                this.answerStudentExerciseRemote.addAnswerStudentQuestionExercise(q.getId(),aseLong,answerSub,getAuthenticatedUser().getId(),exercise.getId(),course.getId());
                

            } else if (q.getType() == Constants.QUESTION_AUDITIVE) {
                TypeAnswer typeAnswer = resultsFromSession.get(q.getId());
                BigDecimal sessionAuditiveAnswer = (BigDecimal)typeAnswer.getAnswer();
                result.add(sessionAuditiveAnswer.toString());
                
                //
                Long aseLong = this.answerStudentExerciseRemote.addAnswerStudentExercise(studentExerciseId,q.getType());
                
                //saving the answer for that student biding with aselong
                this.answerStudentExerciseRemote.addAnswerStudentQuestionExercise(q.getId(),
                    aseLong,
                    sessionAuditiveAnswer, 5);
            
            } else if (q.getType() == Constants.QUESTION_EXTERNAL) {
                TypeAnswer typeAnswer = resultsFromSession.get(q.getId());
                String sessionExternalAnswer = (String)typeAnswer.getAnswer();
                result.add(sessionExternalAnswer);
            }


        }

        //
        //this.addHistory("Realizou Exercicio", "O aluno realizou o exercicio "+ exercise.getTitle(), getAuthenticatedUser());
        UnitContent uc = unitContentRemote.get(exercise.getUnitContentId());
        //addHistory("history.finishexercise.title", "history.finishexercise.description", exercise.getTitle(), uc.getTitle());
        
        List<FinishedExercise> listExercise = finishedExerciseRemote.getByExerciseAndSystemUser(exercise.getId(), getAuthenticatedUser().getId());
        if (listExercise.size() == 0) {
            FinishedExercise finishedExercise = new FinishedExercise();
            finishedExercise.setExercise(exercise.getId());
            finishedExercise.setUnitContent(exercise.getUnitContentId());
            finishedExercise.setSystemUser(getAuthenticatedUser().getId());
            finishedExerciseRemote.add(finishedExercise);
        }
        Grade g = gradeRemote.getActiveByStudentByCourse(getAuthenticatedUser().getId(), course.getId());
        answerStudentExerciseRemote.averageMark(getAuthenticatedUser().getId(),exercise.getId(),g.getId(),false);
        //resultsFromSession.clear();
        return "show";
        
         
    }
    
     
    
    //maybe useless
    /*private String showResultExerciseFromDataBase() {
        List<Question> list = exerciseRemote.getQuestions(exercise.getId());
        this.result = new Vector<String>();

        for (Question q : list) {
            if (q.getType() == Constants.QUESTION_OBJECTIVE) {
                Long answerStudentId = answerStudentExerciseRemote.getObjectiveAnswerStudentExerciseByAnswerStudentExercise(answerStudentExerciseId).getObjectiveAnswer().getId();
                Long correctAnswerId = objectiveQuestionRemote.getByQuestion(q.getId()).getCorrectAnswer().getId();
                
                
                if (answerStudentId.longValue() == correctAnswerId.longValue()) {
                    result.add("Correct");
                //result[i] = "10";
                } else {
                    result.add("Wrong");
                //result[i] = "0";
                }
            } else if (q.getType() == Constants.QUESTION_SUBJECTIVE) {

                BigDecimal score = answerStudentExerciseRemote.getAnswerSubjectiveQuestionStudentExercise(answerStudentExerciseId, q.getId()).getScore();
                if (score != null) {
                    //result[i] = res;
                    result.add(score.toString());
                } else {
                    //result[i] = "Enviada para professor avaliar";
                    result.add("Enviada para professor avaliar");
                }

            } else if (q.getType() == Constants.QUESTION_AUDITIVE) {

                //result[i] = answerStudentExerciseRemote.getAnswerAuditiveQuestionStudentExercise(answerStudentExerciseId, question.getId()).getScore().toString();
                result.add(answerStudentExerciseRemote.getAnswerAuditiveQuestionStudentExercise(answerStudentExerciseId, q.getId()).getScore().toString());
            } else if (q.getType() == Constants.QUESTION_EXTERNAL) {
                //result[i] = answerStudentExerciseRemote.getAnswerExternalQuestionStudentExercise(answerStudentExerciseId,question.getId()).getResultValue();
                result.add(answerStudentExerciseRemote.getAnswerExternalQuestionStudentExercise(answerStudentExerciseId, q.getId()).getResultValue());
            }


        }

        return "show";
    }
*/
    private void chooseQuestion() {


        this.setQuestionSubjective(Constants.QUESTION_SUBJECTIVE);
        this.setQuestionObjective(Constants.QUESTION_OBJECTIVE);
        this.setQuestionAuditive(Constants.QUESTION_AUDITIVE);
        this.setQuestionExternal(Constants.QUESTION_EXTERNAL);

        if (question.getType() == Constants.QUESTION_OBJECTIVE) {

            ObjectiveAnswer res = (ObjectiveAnswer) this.getSession().get(question.getId());
            setQuestionText(questionRemote.getQuestionTextByQuestion(question.getId()));

            setListObjectiveAnswer(objectiveAnswerRemote.getByQuestion(question.getId()));
            setObjectiveQuestion(objectiveQuestionRemote.getByQuestion(question.getId()));
        } else if (question.getType() == Constants.QUESTION_AUDITIVE) {

            //applet for voice is called
            setListAuditiveQuestion(auditiveQuestionRemote.getSentenceByQuestion(question.getId()));
            setAuditiveQuestion(auditiveQuestionRemote.getByQuestion(question.getId()));

            setSentenceAuditiveQuestion("");
            setAudioAuditiveQuestion("");

            for (SentenceAuditiveQuestion s : listAuditiveQuestion) {
                setSentenceAuditiveQuestion(sentenceAuditiveQuestion + s.getSentence() + "#");
                setAudioAuditiveQuestion(audioAuditiveQuestion + s.getFile() + "#");
            }

            setFileConf(auditiveQuestion.getConfFile());
        } else if (question.getType() == Constants.QUESTION_SUBJECTIVE) {


            setQuestionText(questionRemote.getQuestionTextByQuestion(question.getId()));


        } else if (question.getType() == Constants.QUESTION_EXTERNAL) {

            this.setUrlExternal(externalQuestionRemote.getExternalQuestionByQuestion(question.getId()).getUrlBinary());
        }

    }

    /**
     * Show the Exercises and the questions
     * @return show a list of exercises
     */
    public String show() {



        setExercise(exerciseRemote.get(exercise.getId()));
        setQuestionExerciseList(questionExerciseRemote.getByExercise(exercise.getId()));


        return "show";
    }

    /**
     * Show the question by page
     * @return question 
     */
    public String showQuestionByPage() {


        setExercise(exerciseRemote.get(exercise.getId()));

        return "show";
    }

    /**
     * Retrieves a list of exercise by unit content
     * @return list of exercise
     */
    public String listExerciseByUnitContent() {
        this.getSession().remove("EXERCISE_RESULT");
        this.exerciseListFromUnitContent = exerciseRemote.getListExerciseByUnitContent(unitContent.getId());

        if (this.exerciseListFromUnitContent != null && this.exerciseListFromUnitContent.size() > 0) {
            
            //this.exercisesCompleted = new boolean[this.exerciseListFromUnitContent.size()];
            this.chancesNumber = new int[exerciseListFromUnitContent.size()];
            this.setUnitContent(this.unitContentRemote.get(unitContent.getId()));
            
            int i = 0;
           
            for (Exercise e : this.exerciseListFromUnitContent) {
                
                //boolean finished = this.exerciseRemote.isExerciseFinishedForStudent(e.getId(),getAuthenticatedUser().getId());
                
                int chances = this.exerciseRemote.getChancesStudentExercise(getAuthenticatedUser().getId(),e.getId(),course.getId());
                if(chances < 0){
                    this.chancesNumber[i] = e.getChances();
                }else
                    this.chancesNumber[i] = chances;
                    
                /*if(chances == 0)
                    this.exercisesCompleted[i] = true;
                else
                    this.exercisesCompleted[i] = false;*/
               
                i++;
            }

            this.getSession().put("COMPLETED", this.chancesNumber);
        }



        return "list";
    }

    /*private void generateCheckList(List<Exercise> exerciseList){
        this.checkItens = new ArrayList<CheckItem>();
        for(Exercise exe: exerciseList){
            CheckItem checkItem = new CheckItem();
            checkItem.setId(""+exe.getId());
            checkItem.setObject(exe);
            checkItem.setValue(false);
            this.checkItens.add(checkItem);
        }
        
    }*/
        
    
    /**
     * List all exercise
     * @return a list of exercises
     */
    public String list() {

        setExerciseList(exerciseRemote.getAll());
        return "list";
    }

    public String addRequisitesWithJason(){
        if(this.requisitesString!=null && !this.requisitesString.equals("")){
            requisitesString = requisitesString.trim();
            String[] major = requisitesString.split("a");
            
            Long exeId = Long.parseLong(major[0]);
            
            if(major.length == 2){
                String[] checkedExe = major[1].trim().split("b");
                 
                for(int i=0; i<checkedExe.length; i++){
                    
                    if(checkedExe[i]!=null && !checkedExe[i].equals("") && !checkedExe[i].equals(" ")){
                        Long reqId = Long.parseLong(checkedExe[i]);
                        exerciseRemote.addRequisite(exeId, reqId);
                        
                    }
                        
                }
            }
        
        }
        return "list";
    }
    
    /**
     * 
     */
    private void performValidateAdd() {
        // verifies if the exercise is null
        if (exercise == null) {
            addActionError(getText("exercise.input.validation.requiredExercise"));
        }
        // verifies if the title is empty
        if (!StringUtils.hasText(exercise.getTitle())) {
            addActionError(getText("exercise.input.validation.requiredTitle"));
        }

    }

    private void performValidateRemove() {
        // verifies if there is an id
        if (exercise.getId() == null) {
            addActionError(getText("exercise.input.validation.requiredId"));
        } else {
            // verifies if this id is valid
            if (exerciseRemote.get(exercise.getId()) == null) {
                addActionError(getText("exercise.input.validation.invalidId"));
            }
        }
    }

    private void performValidateUpdate() {
        // verifies if the category is null
        if (exercise == null || Validators.isPositive(exercise.getId())) {
            addActionError(getText("exercise.edit.validation.requiredExercise"));
        } else {
            // verifies if this id is valid
            if (exerciseRemote.get(exercise.getId()) == null) {
                addActionError(getText("exercise.edit.validation.invalidId"));
            }
        }
    }

    
    
    /**
     * Sets the value of exercise variable
     * @param exercise
     */
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public GradeRemote getGradeRemote() {
        return gradeRemote;
    }

    public void setGradeRemote(GradeRemote gradeRemote) {
        this.gradeRemote = gradeRemote;
    }

    /**
     * Retrieves the value of exercise variable
     * @return exercise
     */
    public Exercise getExercise() {
        return exercise;
    }

    /**
     * Retrieves the input Stream
     * @return inputStream
     */
    public java.io.InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Sets the input stream
     * @param inputStream
     */
    public void setInputStream(java.io.InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Retrieves the value of exerciseRemote variable
     * @return exerciseRemote
     */
    public ExerciseRemote getExerciseRemote() {
        return exerciseRemote;
    }

    /**
     * Sets the value of exerciseRemote variable
     * @param exerciseRemote
     */
    public void setExerciseRemote(ExerciseRemote exerciseRemote) {
        this.exerciseRemote = exerciseRemote;
    }

    /**
     * Retrieves the value of exerciseList variable
     * @return exerciseList
     */
    public List<Exercise> getExerciseListFromUnitContent() {
        return exerciseListFromUnitContent;
    }

    /**
     * Sets the value of exerciseList variable
     * @param exerciseListFromUnitContent
     */
    public void setExerciseListFromUnitContent(List<Exercise> exerciseListFromUnitContent) {
        this.exerciseListFromUnitContent = exerciseListFromUnitContent;
    }

    /**
     * Retrieves the value of questionRemote
     * @return questionRemote
     */
    public QuestionRemote getQuestionRemote() {
        return questionRemote;
    }

    /**
     * Set the value of questionRemote
     * @param questionRemote
     */
    public void setQuestionRemote(QuestionRemote questionRemote) {
        this.questionRemote = questionRemote;
    }

    /**
     * Retrieves a List of questionList
     * @return questionList
     */
    public List<Question> getQuestionList() {
        return questionList;
    }

    /**
     * Set the QuestionList
     * @param questionList
     */
    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    /**
     * Retrieves a List of questionExerciseList
     * @return questionExerciseList
     */
    public List<QuestionExercise> getQuestionExerciseList() {
        return questionExerciseList;
    }

    /**
     * Set a list of QuestionExerciseList
     * @param questionExerciseList
     */
    public void setQuestionExerciseList(List<QuestionExercise> questionExerciseList) {
        this.questionExerciseList = questionExerciseList;
    }

    /**
     * Retrieves the value of  questionExerciseRemote
     * @return questionExerciseRemote
     */
    public QuestionExerciseRemote getQuestionExerciseRemote() {
        return questionExerciseRemote;
    }

    /**
     * Set a QuestionExerciseRemote
     * @param questionExerciseRemote
     */
    public void setQuestionExerciseRemote(QuestionExerciseRemote questionExerciseRemote) {
        this.questionExerciseRemote = questionExerciseRemote;
    }

    /**
     * Retrieves the value of  question
     * @return question
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Set the question
     * @param question
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

    /**
     * Retrieves the value of questionExercise
     * @return questionExercise
     */
    public QuestionExercise getQuestionExercise() {
        return questionExercise;
    }

    /**
     * Set a questionExercise
     * @param questionExercise
     */
    public void setQuestionExercise(QuestionExercise questionExercise) {
        this.questionExercise = questionExercise;
    }

    /**
     * Retrieves a map of CheckItensMap
     * @return checkItensMap
     */
    public Map<String, CheckItem> getCheckItensMap() {
        return checkItensMap;
    }

    /**
     * Set the Check Itens Map
     * @param checkItensMap
     */
    public void setCheckItensMap(Map<String, CheckItem> checkItensMap) {
        this.checkItensMap = checkItensMap;
    }

    /**
     * Retrieves a List of Check Itens
     * @return checkItens
     */
    public List<CheckItem> getCheckItens() {
        return checkItens;
    }

    /**
     *  Retrieves the unit content
     * @return unitContent
     */
    public UnitContent getUnitContent() {
        return unitContent;
    }

    /**
     * Sets the unit content
     * @param unitContent
     */
    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }

    /**
     * Set the CheckItens
     * @param checkItens
     */
    public void setCheckItens(List<CheckItem> checkItens) {
        this.checkItens = checkItens;
    }

    /**
     * Retrieves the list of exercise
     * @return exerciseList
     */
    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    /**
     * Sets the list of exercise
     * @param exerciseList
     */
    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    /**
     *  Retrieves the remote unit content
     * @return unitContentRemote
     */
    public UnitContentRemote getUnitContentRemote() {
        return unitContentRemote;
    }

    /**
     * Sets the remote unit content
     * @param unitContentRemote
     */
    public void setUnitContentRemote(UnitContentRemote unitContentRemote) {
        this.unitContentRemote = unitContentRemote;
    }

    /**
     * Retrieves the count
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the count
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Retrievest the current page
     * @return page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the page number
     * @param page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Retrieves the count of the page
     * @return pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * Sets the count page
     * @param pageCount
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     *  Retrieves the page size
     * @return pagesize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the page size
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Retrieves the remote auditive question
     * @return auditiveQuestionRemote
     */
    public AuditiveQuestionRemote getAuditiveQuestionRemote() {
        return auditiveQuestionRemote;
    }

    /**
     * Sets the remote auditive question
     * @param auditiveQuestionRemote
     */
    public void setAuditiveQuestionRemote(AuditiveQuestionRemote auditiveQuestionRemote) {
        this.auditiveQuestionRemote = auditiveQuestionRemote;
    }

    /**
     * Retrieves the remote objective answer
     * @return objectiveAnswerRemote
     */
    public ObjectiveAnswerRemote getObjectiveAnswerRemote() {
        return objectiveAnswerRemote;
    }

    /**
     * Sets the remote objective answer
     * @param objectiveAnswerRemote
     */
    public void setObjectiveAnswerRemote(ObjectiveAnswerRemote objectiveAnswerRemote) {
        this.objectiveAnswerRemote = objectiveAnswerRemote;
    }

    /**
     * Retrieves the remote objective question
     * @return
     */
    public ObjectiveQuestionRemote getObjectiveQuestionRemote() {
        return objectiveQuestionRemote;
    }

    /**
     * Sets the remote Objective question
     * @param objectiveQuestionRemote
     */
    public void setObjectiveQuestionRemote(ObjectiveQuestionRemote objectiveQuestionRemote) {
        this.objectiveQuestionRemote = objectiveQuestionRemote;
    }

    /**
     * Retrieves the auditive question
     * @return questionAuditive
     */
    public int getQuestionAuditive() {
        return questionAuditive;
    }

    /**
     * Sets the auditive question
     * @param questionAuditive
     */
    public void setQuestionAuditive(int questionAuditive) {
        this.questionAuditive = questionAuditive;
    }

    /**
     * Retrieves the objective question
     * @return questionObjective
     */
    public int getQuestionObjective() {
        return questionObjective;
    }

    /**
     * Sets the objective question
     * @param questionObjective
     */
    public void setQuestionObjective(int questionObjective) {
        this.questionObjective = questionObjective;
    }

    /**
     * Retrieves the subjective question
     * @return questionSubjective
     */
    public int getQuestionSubjective() {
        return questionSubjective;
    }

    /**
     * Sets the subjective question
     * @param questionSubjective
     */
    public void setQuestionSubjective(int questionSubjective) {
        this.questionSubjective = questionSubjective;
    }

    /**
     * Retrieves the auditive question
     * @return auditiveQuestion
     */
    public AuditiveQuestion getAuditiveQuestion() {
        return auditiveQuestion;
    }

    /**
     * Sets the auditive question
     * @param auditiveQuestion
     */
    public void setAuditiveQuestion(AuditiveQuestion auditiveQuestion) {
        this.auditiveQuestion = auditiveQuestion;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
    

    /**
     * Retrieves a list of sentence auditive questions
     * @return listAuditiveQuestion
     */
    public List<SentenceAuditiveQuestion> getListAuditiveQuestion() {
        return listAuditiveQuestion;
    }

    /**
     * Sets a list of auditive question
     * @param listAuditiveQuestion
     */
    public void setListAuditiveQuestion(List<SentenceAuditiveQuestion> listAuditiveQuestion) {
        this.listAuditiveQuestion = listAuditiveQuestion;
    }

    /**
     * Retrieves a list of objective answer
     * @return listObjectiveAnswer
     */
    public List<ObjectiveAnswer> getListObjectiveAnswer() {
        return listObjectiveAnswer;
    }

    /**
     * Sets a list of objective answer
     * @param listObjectiveAnswer
     */
    public void setListObjectiveAnswer(List<ObjectiveAnswer> listObjectiveAnswer) {
        this.listObjectiveAnswer = listObjectiveAnswer;
    }

    /**
     * Retrieves a objective answer
     * @return objectiveAnswer
     */
    public ObjectiveAnswer getObjectiveAnswer() {
        return objectiveAnswer;
    }

    /**
     * Sets a objective answer
     * @param objectiveAnswer
     */
    public void setObjectiveAnswer(ObjectiveAnswer objectiveAnswer) {
        this.objectiveAnswer = objectiveAnswer;
    }

    /**
     *Retrieves a Objective question
     * @return objectiveQuestion
     */
    public ObjectiveQuestion getObjectiveQuestion() {
        return objectiveQuestion;
    }

    /**
     * Sets a objective question
     * @param objectiveQuestion
     */
    public void setObjectiveQuestion(ObjectiveQuestion objectiveQuestion) {
        this.objectiveQuestion = objectiveQuestion;
    }

    /**
     * Retrieves an audio auditive question
     * @return audioAuditiveQuestion
     */
    public String getAudioAuditiveQuestion() {
        return audioAuditiveQuestion;
    }

    /**
     * Sets an audio auditive question
     * @param audioAuditiveQuestion
     */
    public void setAudioAuditiveQuestion(String audioAuditiveQuestion) {
        this.audioAuditiveQuestion = audioAuditiveQuestion;
    }

    /**
     * Retrieves a file conf
     * @return fileConf
     */
    public String getFileConf() {
        return fileConf;
    }

    /**
     * Sets a file conf 
     * @param fileConf
     */
    public void setFileConf(String fileConf) {
        this.fileConf = fileConf;
    }

    /**
     * Retrieves a sentence auditive question
     * @return sentenceAuditiveQuestion
     */
    public String getSentenceAuditiveQuestion() {
        return sentenceAuditiveQuestion;
    }

    /**
     * Sets a sentence auditive question
     * @param sentenceAuditiveQuestion
     */
    public void setSentenceAuditiveQuestion(String sentenceAuditiveQuestion) {
        this.sentenceAuditiveQuestion = sentenceAuditiveQuestion;
    }

    /**
     * Retrieves a text of one question
     * @return questionText
     */
    public QuestionText getQuestionText() {
        return questionText;
    }

    /**
     * Sets a text of one question
     * @param questionText
     */
    public void setQuestionText(QuestionText questionText) {
        this.questionText = questionText;
    }

    /**
     * Retrieves a external question
     * @return questionExternal
     */
    public int getQuestionExternal() {
        return questionExternal;
    }

    /**
     * Sets a external question
     * @param questionExternal
     */
    public void setQuestionExternal(int questionExternal) {
        this.questionExternal = questionExternal;
    }

    /**
     * Retrieves a external url
     * @return urlExternal
     */
    public String getUrlExternal() {
        return urlExternal;
    }

    /**
     * Sets a external url
     * @param urlExternal
     */
    public void setUrlExternal(String urlExternal) {
        this.urlExternal = urlExternal;
    }

    /**
     * Retrieves a remote external question
     * @return externalQuestionRemote
     */
    public ExternalQuestionRemote getExternalQuestionRemote() {
        return externalQuestionRemote;
    }

    /**
     * Sets a remote external question
     * @param externalQuestionRemote
     */
    public void setExternalQuestionRemote(ExternalQuestionRemote externalQuestionRemote) {
        this.externalQuestionRemote = externalQuestionRemote;
    }

    /**
     * Retrieves a subjective answer
     * @return subjectiveAnswer
     */
    public String getSubjectiveAnswer() {
        return subjectiveAnswer;
    }

    /**
     * Sets a subjective answer
     * @param subjectiveAnswer
     */
    public void setSubjectiveAnswer(String subjectiveAnswer) {
        this.subjectiveAnswer = subjectiveAnswer;
    }

    /**
     * Retrieves a remote answer student exercise
     * @return answerStudentExerciseRemote
     */
    public AnswerStudentExerciseRemote getAnswerStudentExerciseRemote() {
        return answerStudentExerciseRemote;
    }

    /**
     * Sets a remote answer student exercise
     * @param answerStudentExerciseRemote
     */
    public void setAnswerStudentExerciseRemote(AnswerStudentExerciseRemote answerStudentExerciseRemote) {
        this.answerStudentExerciseRemote = answerStudentExerciseRemote;
    }

    /**
     * Retrieves an id of the answer student exercise
     * @return answerStudentExerciseId
     */
    public Long getAnswerStudentExerciseId() {
        return answerStudentExerciseId;
    }

    /**
     * Sets an id of the answer student exercise
     * @param answerStudentExerciseId
     */
    public void setAnswerStudentExerciseId(Long answerStudentExerciseId) {
        this.answerStudentExerciseId = answerStudentExerciseId;
    }

    /**
     * Retrieves a variable result
     * @return result
     */
    public Vector<String> getResult() {
        return result;
    }

    /**
     * Sets a variable result
     * @param result
     */
    public void setResult(Vector<String> result) {
        this.result = result;
    }

    /**
     * Retrieves a booleand of the completed exercise
     * @return exercisesCompleted
     */
    public boolean[] getExercisesCompleted() {
        return exercisesCompleted;
    }

    /**
     * Sets a booleand of the completed exercise
     * @param exercisesCompleted
     */
    public void setExercisesCompleted(boolean[] exercisesCompleted) {
        this.exercisesCompleted = exercisesCompleted;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getRequisitesString() {
        return requisitesString;
    }

    public void setRequisitesString(String requisitesString) {
        this.requisitesString = requisitesString;
    }

    public boolean isCleanSubjective() {
        return cleanSubjective;
    }

    public void setCleanSubjective(boolean cleanSubjective) {
        this.cleanSubjective = cleanSubjective;
    }

    public int getChancesToExercise() {
        return chancesToExercise;
    }

    public void setChancesToExercise(int chancesToExercise) {
        this.chancesToExercise = chancesToExercise;
    }

    public int[] getChancesNumber() {
        return chancesNumber;
    }

    public void setChancesNumber(int[] chancesNumber) {
        this.chancesNumber = chancesNumber;
    }

    public UnitRemote getUnitRemote() {
        return unitRemote;
    }

    public void setUnitRemote(UnitRemote unitRemote) {
        this.unitRemote = unitRemote;
    }

    public DisciplineRemote getDisciplineRemote() {
        return disciplineRemote;
    }

    public void setDisciplineRemote(DisciplineRemote disciplineRemote) {
        this.disciplineRemote = disciplineRemote;
    }

    
    
    
}
