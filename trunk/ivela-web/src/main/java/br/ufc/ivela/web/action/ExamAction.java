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
import br.ufc.ivela.commons.model.Exam;
import br.ufc.ivela.commons.model.FinishedExam;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.ObjectiveAnswer;
import br.ufc.ivela.commons.model.ObjectiveQuestion;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.QuestionExam;
import br.ufc.ivela.commons.model.QuestionText;
import br.ufc.ivela.commons.model.SentenceAuditiveQuestion;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.ExamRemote;
import br.ufc.ivela.ejb.interfaces.QuestionRemote;
import br.ufc.ivela.ejb.interfaces.QuestionExamRemote;
import br.ufc.ivela.commons.util.Validators;
import br.ufc.ivela.ejb.interfaces.AnswerStudentExamRemote;
import br.ufc.ivela.ejb.interfaces.AuditiveQuestionRemote;
import br.ufc.ivela.ejb.interfaces.DisciplineRemote;
import br.ufc.ivela.ejb.interfaces.ExternalQuestionRemote;
import br.ufc.ivela.ejb.interfaces.FinishedExamRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.interfaces.ObjectiveAnswerRemote;
import br.ufc.ivela.ejb.interfaces.ObjectiveQuestionRemote;
import br.ufc.ivela.ejb.interfaces.UnitContentRemote;
import br.ufc.ivela.ejb.interfaces.UnitRemote;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
 * @author Emanuelle Vieira/Maristella Myrian
 */
public class ExamAction extends GenericAction {

    //remote
    private ExamRemote examRemote;
    private QuestionRemote questionRemote;
    private QuestionExamRemote questionExamRemote;
    private ObjectiveQuestionRemote objectiveQuestionRemote;
    private ObjectiveAnswerRemote objectiveAnswerRemote;
    private AuditiveQuestionRemote auditiveQuestionRemote;
    private GradeRemote gradeRemote;
    private ExternalQuestionRemote externalQuestionRemote;
    private DisciplineRemote disciplineRemote;
    private UnitRemote unitRemote;
    private Exam exam;
    private Question question;
    private Unit unit;
    private UnitContent unitContent;
    private QuestionExam questionExam;
    private QuestionText questionText;
    private Course course; 
    private List<Exam> examList;
    private List<Exam> examListFromUnitContent;
    private List<Question> questionList;
    private List<QuestionExam> questionExamList;
    private List<CheckItem> checkItens;
    private UnitContentRemote unitContentRemote;
    private AnswerStudentExamRemote answerStudentExamRemote;
    private boolean[] examsCompleted;    //subjective 
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
    private int count;                  //question type
    private int questionObjective;
    private int questionSubjective;
    private int questionAuditive;
    private int questionExternal;
    private Long answerStudentExamId;
    private Vector<String> result;
    private java.io.InputStream inputStream;
    private boolean cleanSubjective;
    private FinishedExamRemote finishedExamRemote;

    public FinishedExamRemote getFinishedExamRemote() {
        return finishedExamRemote;
    }

    public void setFinishedExamRemote(FinishedExamRemote finishedExamRemote) {
        this.finishedExamRemote = finishedExamRemote;
    }
    
    /**
     * Add a new QuestionExam
     * @return a list of the exams
     */
    public String add() {
        // validates the add
        performValidationAdd();
        if (!hasActionErrors()) {
            Long idExam = examRemote.add(exam);
            setExam(examRemote.get(idExam));
            if (getCheckItensMap() != null) {
                for (String key : getCheckItensMap().keySet()) {
                    CheckItem c = (CheckItem) getCheckItensMap().get(key);
                    if (c.isValue()) {
                        QuestionExam qe = new QuestionExam();
                        qe.setQuestion(questionRemote.get(new Long(key)));
                        qe.setExam(exam);
                        questionExamRemote.add(qe);
                    }
                }
            }

            return list();
        }
        addActionError("Unable to add a question in the exam");
        return list();
    }

    /**
     * Remove a Exam by Id
     * @return a list of the exams
     */
    public String remove() {
        // validates the remove
        performValidationRemove();
        if (hasActionErrors()) {
            examRemote.remove(exam.getId());
            return list();
        }
        return ERROR;
    }

    public String getExamUnitContentJson() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        String json = xStream.toXML(examRemote.getListExamByUnitContent(unitContent.getId()).size());
        json = json.replaceAll("int", "count");

        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String getExamQuestionNumberJson(){
         
        int size = examRemote.getQuestionNumber(exam.getId());
        
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("questionNumber", String.class);
        String questionN = ""+size;
        setInputStream(new ByteArrayInputStream( xStream.toXML(questionN).getBytes()));
        return "json";
     }
    
     /**
     * Retrieves a requisites of one json structure
     * @return
     */
    public String getExamRequisitsFromJson(){
         
        Exam exeToJson = examRemote.get(exam.getId());
        Set<Exam> requisitesSet = exeToJson.getRequisites();
        List<String> requisites = new Vector();
        for(Exam exe: requisitesSet){
            if(!examRemote.isExamFinishedForStudent(exe.getId(),getAuthenticatedUser().getId())){
                requisites.add(exe.getTitle()+"#");
            }
                              
        }
        
        
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("exams", String.class);
         
        setInputStream(new ByteArrayInputStream( xStream.toXML(requisites).getBytes()));
        return "json";
    }
    
    /**
     * Remove a QuestionExam bye Id
     * @return edit
     */
    public String removeQuestionExam() {
        questionExamRemote.remove(questionExam.getId());
        return edit();
    }

    /**
     * Edit a exam
     * @return edit
     */
    public String edit() {
        setExam(examRemote.get(exam.getId()));
        setQuestionExamList(questionExamRemote.getByExam(exam.getId()));
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
            this.checkItensMap.put(c.getId(), c);
        }

        return "edit";
    }

    /**
     * Update a exam
     * @return a string
     */
    public String update() {
        // validates the update
        performValidationUpdate();
        if (!hasActionErrors()) {
            examRemote.update(exam);
            setExam(examRemote.get(exam.getId()));
            if (getCheckItensMap() != null) {
                for (String key : getCheckItensMap().keySet()) {
                    CheckItem c = (CheckItem) getCheckItensMap().get(key);
                    if (c.isValue()) {
                        QuestionExam qe = new QuestionExam();
                        qe.setQuestion(questionRemote.get(new Long(key)));
                        qe.setExam(exam);
                        questionExamRemote.add(qe);
                    }
                }

            }
            return list();
        }
        return ERROR;
    }

    /**
     * Lists the variables to be used on the input exam
     * @return input
     */
    @Override
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
     * Show the Exams and the questions
     * @return show a list of exams
     */
    public String show() {
        setExam(examRemote.get(exam.getId()));
        setQuestionExamList(questionExamRemote.getByExam(exam.getId()));
        return "show";
    }

    /**
     * Show exam for unitContent
     * @return
     */
    public String listExamByUnitContent() {
        this.getSession().remove("EXAM_RESULT");
        this.examListFromUnitContent = examRemote.getListExamByUnitContent(unitContent.getId());
        this.examsCompleted = new boolean[this.examListFromUnitContent.size()];

        this.setUnitContent(this.unitContentRemote.get(unitContent.getId()));
        int i = 0;
        for (Exam e : this.examListFromUnitContent) {

            //this.examsCompleted[i] = answerStudentExamRemote.getNumberStudentExam(getAuthenticatedUser().getId(), e.getId());
            
            
            this.examsCompleted[i] = this.examRemote.isExamFinishedForStudent(e.getId(),getAuthenticatedUser().getId(),course.getId());
            i++;

        }

        this.getSession().put("COMPLETED", this.examsCompleted);
        return "list";
    }

    /**
     * Show question by page
     * @return
     */
    public String showPaging() {
        
        //System.out.println("ucId: " + unitContent.getId());
        //System.out.println("userId: " + getAuthenticatedUser().getId());
        //UnitContent ucTemp = unitContentRemote.get(unitContent.getId());
        //System.out.println(ucTemp);
        
        //Unit unitTemp = unitRemote.get(ucTemp.getUnitId());
        //System.out.println("unitId: " + unitTemp.getId());
        
        //Discipline discipline = disciplineRemote.get(unitTemp.getDisciplineId());
        
        //System.out.println("disciplineId: " + discipline.getId());
        //System.out.println("courseId: " + discipline.getCourseId());
        //Grade grade = gradeRemote.getActiveByStudentByCourse(getAuthenticatedUser().getId(),discipline.getCourseId());
        //System.out.println("gradeId: " +grade.getId());
        //System.out.println("course: " + course.getId());
        
        //Grade grade = gradeRemote.getActiveByStudentByCourse( getAuthenticatedUser().getId(), course.getId());
        
        //System.out.println("Grade: " + grade.getId());
        
        int pageSessionCount = -1;
        if (page < 1) {
            page = 1;
        }

        //really necessary???
        setExam(examRemote.get(exam.getId()));

        if (this.getSession().get("PAGE_COUNT") != null) {
            pageSessionCount = ((Integer) this.getSession().get("PAGE_COUNT")).intValue();
        }
        
       
        
        //mostrando os resultados
        if (page == (pageSessionCount + 1)) {
            
            if(this.getSession().get("EXAM_RESULT")!=null){
                return showResultExamFromSession();
            }

        } else {



            Page p = examRemote.getQuestionPageByExam(exam.getId(), page, 1);

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
            HashMap<Long, TypeAnswer> resultsFromSession = (HashMap<Long, TypeAnswer>) this.getSession().get("EXAM_RESULT");
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
    
    /**
     * This method gets the result from session. 
     * show and save
     * @return
     */
    private String showResultExamFromSession(){
        //logger.log("%%FROM SESSION");
        setExam(examRemote.get(exam.getId()));
                    
        //fetch from session
        this.result = new Vector<String>();
        List<Question> list = examRemote.getQuestions(exam.getId());
        HashMap<Long, TypeAnswer> resultsFromSession = (HashMap<Long, TypeAnswer>) this.getSession().get("EXAM_RESULT");
        
        //SAVING student exam 
        //
        Long studentExamId = this.answerStudentExamRemote.addStudentExam(exam.getId(), getAuthenticatedUser().getId(),course.getId());
         
         
        
        //saving questions
        for (Question q : list) {
            //logger.log("%%FROM SESSION IN FOR: " + this.getAnswerStudentExerciseId());
            
            if (q.getType() == Constants.QUESTION_OBJECTIVE) {
               
                //fetching the results
                TypeAnswer typeAnswer = resultsFromSession.get(q.getId());
                ObjectiveAnswer sessionObjectiveAnswer = (ObjectiveAnswer)typeAnswer.getAnswer();
                Long correctAnswerId = objectiveQuestionRemote.getByQuestion(q.getId()).getCorrectAnswer().getId();
               
                
                //saving in table answer student exercise
                Long aseLong = this.answerStudentExamRemote.addAnswerStudentExam(studentExamId,q.getType());
                
                //saving in data base, table ObjectiveAnswerExercise (To later acess),
                // binding with aselong
                this.answerStudentExamRemote.addAnswerStudentQuestionExam(aseLong,
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
                
                Long aseLong = this.answerStudentExamRemote.addAnswerStudentExam(studentExamId,q.getType());
                this.answerStudentExamRemote.addAnswerStudentQuestionExam(q.getId(),aseLong,answerSub);
                

            } else if (q.getType() == Constants.QUESTION_AUDITIVE) {
                TypeAnswer typeAnswer = resultsFromSession.get(q.getId());
                BigDecimal sessionAuditiveAnswer = (BigDecimal)typeAnswer.getAnswer();
                result.add(sessionAuditiveAnswer.toString());
                
                /*
                 //saving in table answer student exercise
                Long aseLong = this.answerStudentExerciseRemote.addAnswerStudentExercise(studentExerciseId);
                
                //saving the answer for that student biding with aselong
                this.answerStudentExerciseRemote.addAnswerStudentQuestionExercise(q.getId(),
                    aseLong,
                    sessionAuditiveAnswer, 5);
                 */
            
            } else if (q.getType() == Constants.QUESTION_EXTERNAL) {
                TypeAnswer typeAnswer = resultsFromSession.get(q.getId());
                String sessionExternalAnswer = (String)typeAnswer.getAnswer();
                result.add(sessionExternalAnswer);
            }
            
            


        }
        UnitContent uc = unitContentRemote.get(exam.getUnitContentId());
        //this.addHistory("history.finishexam.title", "history.finishexam.description", exam.getTitle(), uc.getTitle());
        List<FinishedExam> listExam = finishedExamRemote.getByExamAndSystemUser(exam.getId(), getAuthenticatedUser().getId());
        if (listExam.size() == 0) {
            FinishedExam finishedExam = new FinishedExam();
            finishedExam.setSystemUser(getAuthenticatedUser().getId());
            finishedExam.setUnitContent(exam.getUnitContentId());
            finishedExam.setExam(exam.getId());
            finishedExamRemote.add(finishedExam);
        }
        Grade g = gradeRemote.getActiveByStudentByCourse(getAuthenticatedUser().getId(), course.getId());
        answerStudentExamRemote.averageMark(getAuthenticatedUser().getId(),exam.getId(),g.getId(),false);
        
        //resultsFromSession.clear();
        return "show";
        
         
    }
    
    /**
     * Show result exam (maybe useless)
     * @return
     */
    private String showResultExam() {
        List<Question> list = examRemote.getQuestions(exam.getId());
        this.result = new Vector<String>();

        for (Question q : list) {
            if (q.getType() == Constants.QUESTION_OBJECTIVE) {
                Long answerStudentId = answerStudentExamRemote.getObjectiveAnswerStudentExamByAnswerStudentExam(answerStudentExamId).getObjectiveAnswer().getId();
                Long correctAnswerId = objectiveQuestionRemote.getByQuestion(q.getId()).getCorrectAnswer().getId();
                logger.log("answerStudentId->>>>>>>>>>>>>>>>>>>>>>>" + answerStudentId);
                logger.log("correctAnswerId->>>>>>>>>>>>>>>>>>>>>>>" + correctAnswerId);

                if (answerStudentId.longValue() == correctAnswerId.longValue()) {
                    result.add("Correct");
                //result[i] = "10";
                } else {
                    result.add("Wrong");
                //result[i] = "0";
                }
            } else if (q.getType() == Constants.QUESTION_SUBJECTIVE) {

                BigDecimal score = answerStudentExamRemote.getAnswerSubjectiveQuestionStudentExam(answerStudentExamId, q.getId()).getScore();
                if (score != null) {
                    //result[i] = res;
                    result.add(score.toString());
                } else {
                    //result[i] = "Enviada para professor avaliar";
                    result.add("Enviada para professor avaliar");
                }

            } else if (q.getType() == Constants.QUESTION_AUDITIVE) {

                //result[i] = answerStudentExamRemote.getAnswerAuditiveQuestionStudentExam(answerStudentExamId, question.getId()).getScore().toString();
                result.add(answerStudentExamRemote.getAnswerAuditiveQuestionStudentExam(answerStudentExamId, q.getId()).getScore().toString());
            } else if (q.getType() == Constants.QUESTION_EXTERNAL) {
                //result[i] = answerStudentExamRemote.getAnswerExternalQuestionStudentExam(answerStudentExamId,question.getId()).getResultValue();
                result.add(answerStudentExamRemote.getAnswerExternalQuestionStudentExam(answerStudentExamId, q.getId()).getResultValue());
            }


        }

        return "show";
    }

    /**
     * show question 
     */
    private void chooseQuestion() {


        this.setQuestionSubjective(Constants.QUESTION_SUBJECTIVE);
        this.setQuestionObjective(Constants.QUESTION_OBJECTIVE);
        this.setQuestionAuditive(Constants.QUESTION_AUDITIVE);
        this.setQuestionExternal(Constants.QUESTION_EXTERNAL);

        if (question.getType() == Constants.QUESTION_OBJECTIVE) {

            ObjectiveAnswer res = (ObjectiveAnswer) this.getSession().get(question.getId());
            setListObjectiveAnswer(objectiveAnswerRemote.getByQuestion(question.getId()));
            setObjectiveQuestion(objectiveQuestionRemote.getByQuestion(question.getId()));

            setQuestionText(questionRemote.getQuestionTextByQuestion(question.getId()));


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
     * Save result for a question (always in session);
     * @return
     */
    public String saveResultQuestion() {
        
        logger.log("%%SAVING IN SESSION");
        //before paging
        this.saveInSession();

        //update question
        String returnPage = this.showPaging() ;

        //after paging. If the questions exists in Session, the Front-End will show it.
        this.getFromSession();

        return returnPage;
    }

    /**
     * Saving in session.
     */
    private void saveInSession() {
        
        HashMap<Long, TypeAnswer> results = (HashMap<Long, TypeAnswer>) this.getSession().get("EXAM_RESULT");
        if (results == null) {
            
            results = new HashMap<Long, TypeAnswer>();
            this.getSession().put("EXAM_RESULT", results);
        }

        
        Long currentQuestionId = this.question.getId();
        //logger.log("%%Current save: " + currentQuestionId.longValue());
        
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
    
    /**
     * returns each type of question from session.
     */
    private void getFromSession(){
        HashMap<Long, TypeAnswer> results = (HashMap<Long, TypeAnswer>) this.getSession().get("EXAM_RESULT");
         
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
     *  Lists all exams
     * @return a list of exam
     */
    public String list() {
        setExamList(examRemote.getAll());
        return "list";
    }

    /**
     *  List exams by unit
     * @return a list of exams
     */
    public String listByUnit() {

        setExamList(examRemote.listByUnit(unit.getId()));
        return "list";
    }

    /**
     * Perform a validation to add an exam
     */
    private void performValidationAdd() {
        // verifies if the video is null
        if (exam == null) {
            addActionError(getText("exam.input.validation.requiredExam"));
        }
        // verifies if the title is empty
        if (!StringUtils.hasText(exam.getTitle())) {
            addActionError(getText("exam.input.validation.requiredTitle"));
        }

    }

    /**
     * Performs a validator to remove an exam
     */
    private void performValidationRemove() {
        // verifies if there is an id
        if (exam.getId() == null) {
            addActionError(getText("exam.input.validation.requiredId"));
        } else {
            // verifies if this id is valid
            if (examRemote.get(exam.getId()) == null) {
                addActionError(getText("exam.input.validation.invalidId"));
            }
        }
    }

    /**
     * Perform a validation to update an exam
     */
    private void performValidationUpdate() {
        // verifies if the exam is null
        if (exam == null || Validators.isPositive(exam.getId())) {
            addActionError(getText("exam.edit.validation.required"));
        } else {
            // verifies if this id is valid
            if (examRemote.get(exam.getId()) == null) {
                addActionError(getText("exam.edit.validation.invalidId"));
            }
        }
    }

    /**
     * Sets the exam id, of the answer the student
     * @param answerStudentExamId
     */
    public void setAnswerStudentExamId(Long answerStudentExamId) {
        this.answerStudentExamId = answerStudentExamId;
    }

    /**
     * Retrieves the exam id, of the answer the student
     * @return answerStudentExamId
     */
    public Long getAnswerStudentExamId() {
        return answerStudentExamId;
    }

    /**
     * Retrievesd the audio of the auditive question
     * @return audioAuditiveQuestion
     */
    public String getAudioAuditiveQuestion() {
        return audioAuditiveQuestion;
    }

    /**
     * Sets the audio of the auditive question
     * @param audioAuditiveQuestion
     */
    public void setAudioAuditiveQuestion(String audioAuditiveQuestion) {
        this.audioAuditiveQuestion = audioAuditiveQuestion;
    }

    /**
     * Retrieves the adutive question
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

    public GradeRemote getGradeRemote() {
        return gradeRemote;
    }

    public void setGradeRemote(GradeRemote gradeRemote) {
        this.gradeRemote = gradeRemote;
    }

    
    /**
     * Retrieves the Input stream
     * @return inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Sets a input stream
     * @param inputStream
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
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
     * Retrieves the remote external question
     * @return externalQuestionRemote
     */
    public ExternalQuestionRemote getExternalQuestionRemote() {
        return externalQuestionRemote;
    }

    /**
     * Sets the remote external question
     * @param externalQuestionRemote
     */
    public void setExternalQuestionRemote(ExternalQuestionRemote externalQuestionRemote) {
        this.externalQuestionRemote = externalQuestionRemote;
    }

    /**
     * Retrieves the file conf
     * @return fileConf
     */
    public String getFileConf() {
        return fileConf;
    }

    /**
     * Sets the file conf
     * @param fileConf
     */
    public void setFileConf(String fileConf) {
        this.fileConf = fileConf;
    }

    /**
     * Retrieves the list of auditive question
     * @return listAuditiveQuestion
     */
    public List<SentenceAuditiveQuestion> getListAuditiveQuestion() {
        return listAuditiveQuestion;
    }

    /**
     * Sets the list of auditive question
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
     * Sets the list of objective answer
     * @param listObjectiveAnswer
     */
    public void setListObjectiveAnswer(List<ObjectiveAnswer> listObjectiveAnswer) {
        this.listObjectiveAnswer = listObjectiveAnswer;
    }

    /**
     * Retrieves the objetive answer
     * @return objectiveAnswer
     */
    public ObjectiveAnswer getObjectiveAnswer() {
        return objectiveAnswer;
    }

    /**
     * Sets the objetive answer
     * @param objectiveAnswer
     */
    public void setObjectiveAnswer(ObjectiveAnswer objectiveAnswer) {
        this.objectiveAnswer = objectiveAnswer;
    }

    /**
     * Retrieves the remote objevtive answer
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
     * Retrieves the objective question
     * @return objectiveQuestion
     */
    public ObjectiveQuestion getObjectiveQuestion() {
        return objectiveQuestion;
    }

    /**
     * Sets the objetive question
     * @param objectiveQuestion
     */
    public void setObjectiveQuestion(ObjectiveQuestion objectiveQuestion) {
        this.objectiveQuestion = objectiveQuestion;
    }

    /**
     * Retrieves the remote objective question
     * @return objectiveQuestionRemote
     */
    public ObjectiveQuestionRemote getObjectiveQuestionRemote() {
        return objectiveQuestionRemote;
    }

    /**
     * Sets the remote objective question
     * @param objectiveQuestionRemote
     */
    public void setObjectiveQuestionRemote(ObjectiveQuestionRemote objectiveQuestionRemote) {
        this.objectiveQuestionRemote = objectiveQuestionRemote;
    }

    /**
     * Retrieves the number of pages
     * @return pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the number of pages
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Retrieves a vector whith the result
     * @return result
     */
    public Vector<String> getResult() {
        return result;
    }

    /**
     * Sets a vector with the results
     * @param result
     */
    public void setResult(Vector<String> result) {
        this.result = result;
    }

    /**
     * Retrieves a sentence of the auditive question
     * @return sentenceAuditiveQuestion
     */
    public String getSentenceAuditiveQuestion() {
        return sentenceAuditiveQuestion;
    }

    /**
     * Sets a sentence with the auditive question
     * @param sentenceAuditiveQuestion
     */
    public void setSentenceAuditiveQuestion(String sentenceAuditiveQuestion) {
        this.sentenceAuditiveQuestion = sentenceAuditiveQuestion;
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
     * Retrieves the External URL
     * @return urlExternal
     */
    public String getUrlExternal() {
        return urlExternal;
    }

    /**
     * Sets a exernal URL
     * @param urlExternal
     */
    public void setUrlExternal(String urlExternal) {
        this.urlExternal = urlExternal;
    }

    /**
     * Retrieves a question auditive
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
     * Retrieves the external question
     * @return questionExternal
     */
    public int getQuestionExternal() {
        return questionExternal;
    }

    /**
     * Sets the external question
     * @param questionExternal
     */
    public void setQuestionExternal(int questionExternal) {
        this.questionExternal = questionExternal;
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
     * Lists the value of exam variable
     * @param exam
     */
    public void setExam(Exam exam) {
        this.exam = exam;
    }

    /**
     * Retrieves the value of exam variable
     * @return exam
     */
    public Exam getExam() {
        return exam;
    }

    /**
     * Retrieves the text of the question
     * @return questionText
     */
    public QuestionText getQuestionText() {
        return questionText;
    }

    /**
     * Sets the text of the question variable
     * @param questionText
     */
    public void setQuestionText(QuestionText questionText) {
        this.questionText = questionText;
    }

    /**
     * Retrieves the value of examRemote variable
     * @return examRemote
     */
    public ExamRemote getExamRemote() {
        return examRemote;
    }

    /**
     * Lists the value of examRemote variable
     * @param examRemote
     */
    public void setExamRemote(ExamRemote examRemote) {
        this.examRemote = examRemote;
    }

    /**
     * Retrieves the remote exam of the student answer
     * @return answerStudentExamRemote
     */
    public AnswerStudentExamRemote getAnswerStudentExamRemote() {
        return answerStudentExamRemote;
    }

    /**
     * Sets the remote exam of the student answer
     * @param answerStudentExamRemote
     */
    public void setAnswerStudentExamRemote(AnswerStudentExamRemote answerStudentExamRemote) {
        this.answerStudentExamRemote = answerStudentExamRemote;
    }

    /**
     * Retrieves a vector boolean of the exams completed
     * @return a vector of true or false of examsCompleted
     */
    public boolean[] getExamsCompleted() {
        return examsCompleted;
    }

    /**
     * Sets a vector boolean of the completed exams
     * @param examsCompleted
     */
    public void setExamsCompleted(boolean[] examsCompleted) {
        this.examsCompleted = examsCompleted;
    }

    /**
     * Retrieves a remote unit content
     * @return unitContentRemote
     */
    public UnitContentRemote getUnitContentRemote() {
        return unitContentRemote;
    }

    /**
     * Sets a remote unit content
     * @param unitContentRemote
     */
    public void setUnitContentRemote(UnitContentRemote unitContentRemote) {
        this.unitContentRemote = unitContentRemote;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    
    
    /**
     * Retrieves a Lis of examList
     * @return examList
     */
    public List<Exam> getExamList() {
        return examList;
    }

    /**
     * Lists the value of examList variable
     * @param examList
     */
    public void setExamList(List<Exam> examList) {
        this.examList = examList;
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
     * Retrieves a List of questionExamList
     * @return questionExamList
     */
    public List<QuestionExam> getQuestionExamList() {
        return questionExamList;
    }

    /**
     * Set a list of QuestionExamList
     * @param questionExamList
     */
    public void setQuestionExamList(List<QuestionExam> questionExamList) {
        this.questionExamList = questionExamList;
    }

    /**
     * Retrieves the value of  questionExamRemote
     * @return questionExamRemote
     */
    public QuestionExamRemote getQuestionExamRemote() {
        return questionExamRemote;
    }

    /**
     * Set a QuestionExamRemote
     * @param questionExamRemote
     */
    public void setQuestionExamRemote(QuestionExamRemote questionExamRemote) {
        this.questionExamRemote = questionExamRemote;
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
     * Retrieves the value of questionExam
     * @return questionExam
     */
    public QuestionExam getQuestionExam() {
        return questionExam;
    }

    /**
     * Set a questionExam
     * @param questionExam
     */
    public void setQuestionExam(QuestionExam questionExam) {
        this.questionExam = questionExam;
    }

    /**
     * Retrieves a map of CheckItensMap
     * @return checkItensMap
     */
    public Map<String, CheckItem> getCheckItensMap() {
        return checkItensMap;
    }

    /**
     * Set the CheckItensMap
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
     * Set the CheckItens
     * @param checkItens
     */
    public void setCheckItens(List<CheckItem> checkItens) {
        this.checkItens = checkItens;
    }

    /**
     * Sets the unit
     * @param unit
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Retrieves a unit
     * @return unit
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Retrieves a count
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets a count
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Retrieves the number of the page
     * @return page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the number of the page
     * @param page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Retrieves the page count
     * @return pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * Sets the page count
     * @param pageCount
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * Retrieves a list of exam from one unit content
     * @return examListFromUnitContent
     */
    public List<Exam> getExamListFromUnitContent() {
        return examListFromUnitContent;
    }

    /**
     * Sets a list of exam from one unit content
     * @param examListFromUnitContent
     */
    public void setExamListFromUnitContent(List<Exam> examListFromUnitContent) {
        this.examListFromUnitContent = examListFromUnitContent;
    }

    /**
     * Retrieves the content of the unit
     * @return unitContent
     */
    public UnitContent getUnitContent() {
        return unitContent;
    }

    /**
     * Sets the content of the unit
     * @param unitContent
     */
    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }

    public boolean isCleanSubjective() {
        return cleanSubjective;
    }

    public void setCleanSubjective(boolean cleanSubjective) {
        this.cleanSubjective = cleanSubjective;
    }

    public DisciplineRemote getDisciplineRemote() {
        return disciplineRemote;
    }

    public void setDisciplineRemote(DisciplineRemote disciplineRemote) {
        this.disciplineRemote = disciplineRemote;
    }

    public UnitRemote getUnitRemote() {
        return unitRemote;
    }

    public void setUnitRemote(UnitRemote unitRemote) {
        this.unitRemote = unitRemote;
    }
    
    
}
