/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action.admin;

import br.ufc.ivela.web.action.*;
import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.ReplaceModelString;
import br.ufc.ivela.commons.model.AuditiveQuestion;
import br.ufc.ivela.commons.model.Exam;
import br.ufc.ivela.commons.model.Exercise;
import br.ufc.ivela.commons.model.ObjectiveAnswer;
import br.ufc.ivela.commons.model.ObjectiveQuestion;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.QuestionExam;
import br.ufc.ivela.commons.model.QuestionExercise;
import br.ufc.ivela.commons.model.QuestionText;
import br.ufc.ivela.commons.model.SentenceAuditiveQuestion;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.QuestionRemote;
import br.ufc.ivela.ejb.interfaces.ObjectiveAnswerRemote;
import br.ufc.ivela.ejb.interfaces.ObjectiveQuestionRemote;
import br.ufc.ivela.commons.util.Validators;
import br.ufc.ivela.ejb.interfaces.AuditiveQuestionRemote;
import br.ufc.ivela.ejb.interfaces.ExamRemote;
import br.ufc.ivela.ejb.interfaces.ExerciseRemote;
import br.ufc.ivela.ejb.interfaces.ExternalQuestionRemote;
import br.ufc.ivela.ejb.interfaces.QuestionExamRemote;
import br.ufc.ivela.ejb.interfaces.QuestionExerciseRemote;
import java.util.HashSet;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import javax.activation.MimetypesFileTypeMap;

public class QuestionAction extends GenericAction {

    private QuestionRemote questionRemote;
    private ObjectiveAnswerRemote objectiveAnswerRemote;
    private ObjectiveQuestionRemote objectiveQuestionRemote;
    private QuestionExamRemote questionExamRemote;
    private Question question;
    private ObjectiveAnswer objectiveAnswer;
    private ObjectiveQuestion objectiveQuestion;
    private AuditiveQuestion auditiveQuestion;
    private QuestionText questionText;
    private AuditiveQuestionRemote auditiveQuestionRemote;
    private List<Question> questionList;
    private List<ObjectiveAnswer> listObjectiveAnswer;
    private List<SentenceAuditiveQuestion> listAuditiveQuestion;
    private int correctAnswer;
    private int questionObjective;
    private int questionSubjective;
    private int questionAuditive;
    private int questionReading;
    private String[] answerOption;
    private String[] sentence;
    private String uploadFileQuestion;
    private java.io.File fileQuestion;
    private String fileQuestionContentType;
    private String fileQuestionFileName;
    private String[] upload;
    private File[] uploadFile;
    private String[] uploadFileName;
    private String sentenceAuditiveQuestion;
    private String audioAuditiveQuestion;
    private int radio;
    private int radioTense;
    private int questionWeight;
    private boolean questionRequired;
    private String atividade;
    private String fileConf;
    private InputStream inputStream;
    private XStream xStream = new XStream(new JettisonMappedXmlDriver());
    private ExerciseRemote exerciseRemote;
    private Exercise exercise;
    private Exam exam;
    private Unit unit;
    private UnitContent unitContent;
    private QuestionExam questionExam;
    private QuestionExercise questionExercise;
    private ExamRemote examRemote;
    private ExternalQuestionRemote externalQuestionRemote;
    private QuestionExerciseRemote questionExerciseRemote;
    //external
    private String urlBinary;
    private String urlResult;
    private int binType;
    private String[] paramValue;
    private String[] paramKey;

    /**
     * Add a new question
     * @return
     */
    public String add() {
        // performValidates the add
        //performValidateAdd();
        String typeFile = "";
        boolean typeOgg = true;
        boolean maxlength = true;
         
        if(fileQuestion!=null){
          typeFile = fileQuestionContentType.substring(fileQuestionContentType.indexOf("/")+1);
          if(!typeFile.equalsIgnoreCase("ogg")) typeOgg = false;
          if(fileQuestion.length()>Constants.MAX_LENGTH_FILE) maxlength = false;
          
        }
        
        
        
        if (typeOgg && maxlength) {
            Long idQuestion;
            ReplaceModelString.replace(question);
            //add question
            question.setCreatedBy(getAuthenticatedUser());
            idQuestion = questionRemote.add(question);
            question = questionRemote.get(idQuestion);

            //add question objective
            if (question.getType() == Constants.QUESTION_OBJECTIVE) {
                ObjectiveAnswer oa;
                for (int i = 0; i < answerOption.length; i++) {
                    oa = new ObjectiveAnswer();
                    oa.setAnswer(answerOption[i]);
                    oa.setQuestion(question);
                    Long idAnswer = objectiveAnswerRemote.add(oa);
                    if (i == radio) {
                        ObjectiveQuestion oq = new ObjectiveQuestion();
                        oq.setCorrectAnswer(objectiveAnswerRemote.get(idAnswer));
                        oq.setQuestion(question);
                        objectiveQuestionRemote.add(oq);
                    }

                }
            }
//            else
//            //add question auditive
//            if (question.getType() == Constants.QUESTION_AUDITIVE) {
//                logger.log("\n\n\n\n\n\n\nquestionauditive");
//                auditiveQuestion.setQuestion(questionObj);
//                auditiveQuestion.setConfFile("");
//                Long idQA = auditiveQuestionRemote.add(auditiveQuestion);
//                auditiveQuestion = auditiveQuestionRemote.get(idQA);
//                SentenceAuditiveQuestion saq;
//                uploadFile = new File[upload.length];
//                for (int i = 0; i < upload.length; i++) {
//                    uploadFile[i] = new File(upload[i]);
//                }
//                for (int i = 0; i < sentence.length; i++) {
//                    java.io.File fileIo = uploadFile[i];
//                    saq = new SentenceAuditiveQuestion();
//                    saq.setSentence(sentence[i]);
//                    saq.setFile(path + fileIo.getName());
//                    logger.log("file" + saq.getFile());
//                    saq.setSequence(i);
//                    saq.setQuestion(auditiveQuestion);
//                    auditiveQuestionRemote.addSentence(saq, fileIo);
//                }
//                logger.log("fim");
//            }
//            else
//            if (question.getType() == Constants.QUESTION_EXTERNAL) {
//                //logger.log("%%%%%%%%INSERINDO EXTERNO");
//                //logger.log("%%%%%%%%INSERINDO EXTERNO " + this.urlBinary);
//                //logger.log("%%%%%%%%INSERINDO EXTERNO" + this.urlResult);
//                //logger.log("%%%%%%%%INSERINDO EXTERNO" + this.binType);
//                ExternalQuestion eq = new ExternalQuestion();
//                eq.setUrlBinary(urlBinary);
//                eq.setUrlResult(urlResult);
//                eq.setBinType(binType);
//                eq.setQuestion(questionObj);
//                
//                
//                Set<ExternalParams> externalParameters = new HashSet<ExternalParams>();
//                if(paramKey!=null && paramValue!=null)
//                    for (int i = 0; i < paramKey.length; i++) {
//                        String tmpParamKey = paramKey[i];
//                        String tmpParamValue = paramValue[i];
//                        System.out.println(paramKey[i]+"---"+paramValue[i]);
//                        ExternalParams ep = new ExternalParams();
//                        ep.setKey(tmpParamKey);
//                        ep.setValue(tmpParamValue);
//
//                        Long id = externalQuestionRemote.addExternalParam(ep);
//                        ep.setId(id);
//                        externalParameters.add(ep);
//
//                    }
//                
//                eq.setExternalParams(externalParameters);
//                externalQuestionRemote.add(eq);
//            }
            
            
            //add question text
            addQuestionText();
              
            
             //add question exam or question exercise
             if(exercise!=null && exercise.getId()!=null ){ 
                  logger.log(addQuestionExercise().toString());
              }
              else if(exam!=null && exam.getId()!=null ) {
                   logger.log(addQuestionExam().toString());
              }
             
              return "show";
        }
        else {
            return "edit";
        }
        
    }
    
    
    public Boolean addQuestionText(){
        boolean insert = false;
        String path = "";
        if(questionText.getText()!=null && (!questionText.getText().equals(""))){
               insert= true;
                ReplaceModelString.replace(questionText);
        }
        if(fileQuestion!=null){
              insert = true;
              path += unit.getId()+"/"+unitContent.getId()+"/"+atividade+"/";
              if(exercise!=null && exercise.getId()!=null ){ 
                  path += exercise.getId()+"/";
              }
              else if(exam!=null && exam.getId()!=null ) {
                  path += exam.getId()+"/";
              }
              questionText.setAudio(path + fileQuestionFileName);
        }
        
        
        if(insert){
             //add question text
            Long idQuestionText = questionRemote.addQuestionText(questionText,fileQuestion,path);
            HashSet hashset = new HashSet();
            hashset.add(questionRemote.getQuestionText(idQuestionText));
            question.setQuestionText(hashset);
            return questionRemote.update(question);
        }
        return false;
    }
    
        //add questionExam
    public Long addQuestionExam() {
        questionExam = new QuestionExam();
        questionExam.setExam(exam);
        questionExam.setQuestion(question);
        questionExam.setRequired(questionRequired);
        questionExam.setWeight(questionWeight);
        logger.log("fsdfexam" + questionExam.getExam().getId());
        questionExam.setActive(true);
        return examRemote.addQuestion(questionExam);
    }
    
    public Long addQuestionExercise() {
        questionExercise = new QuestionExercise();
        questionExercise.setExercise(exercise);
        questionExercise.setQuestion(question);
        questionExercise.setRequired(questionRequired);
        questionExercise.setWeight(questionWeight);
        logger.log("fsdfexe" + questionExercise.getExercise().getId());
        questionExercise.setActive(true);
        return exerciseRemote.addQuestion(questionExercise);
        
    }


    /**
     * Edit a question
     * @return
     */
    public String edit() {
        setQuestion(questionRemote.get(question.getId()));
        if (question.getType() == Constants.QUESTION_OBJECTIVE) {
            setListObjectiveAnswer(objectiveAnswerRemote.getByQuestion(question.getId()));
            setObjectiveQuestion(objectiveQuestionRemote.getByQuestion(question.getId()));
        }
        return "edit";
    }

    /**
     * Update a question
     * @return
     */
    public String update() {
        // performValidates the update
        performValidateUpdate();

        if (!hasActionErrors()) {
            questionRemote.edit(question);
            if (question.getType() == Constants.QUESTION_OBJECTIVE) {
                for (int i = 0; i < listObjectiveAnswer.size(); i++) {
                    objectiveAnswerRemote.edit(listObjectiveAnswer.get(i));
                }
                objectiveQuestion.setCorrectAnswer(listObjectiveAnswer.get(radio));
                objectiveQuestionRemote.edit(objectiveQuestion);
            }
            return list();
        }
        addActionError("Unable to update a question");
        return list();
    }
    
    public String updateQuestion(){
    
        boolean result = false;
        if(exercise!=null){
            setQuestionExercise(questionExerciseRemote.getByQuestionByExercise(question.getId(), exercise.getId()));
            questionExercise.setActive(false);
            result = questionExerciseRemote.update(questionExercise);
        }
        else if(exam!=null){
            setQuestionExam(questionExamRemote.getByQuestionByExam(question.getId(), exam.getId()));
            questionExam.setActive(false);
            result = questionExamRemote.update(questionExam);
        }

        xStream.alias("update", boolean.class);
        
        String json = xStream.toXML(result);  
        
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        
        return "json";
    }


    
    /**
     * Show the questions
     * @return
     */
    public String show() {
        
        Integer weight = null;
        String required = null;
        Integer typeUnitContent = null;    // 0-Exercise ou 1-Exam
        String text = "";

        //type of questions
        setQuestion(questionRemote.get(question.getId()));
        setQuestionText(questionRemote.getQuestionTextByQuestion(question.getId()));
        if(exercise!=null){
            setQuestionExercise(questionExerciseRemote.getByQuestionByExercise(question.getId(), exercise.getId()));
            weight = questionExercise.getWeight();
            required = questionExercise.getRequired().toString();
            typeUnitContent = 0;
            logger.log("weiexer"+weight+"required"+required);
        }
        else if(exam!=null){
            setQuestionExam(questionExamRemote.getByQuestionByExam(question.getId(), exam.getId()));
            weight = questionExam.getWeight();
            required = questionExam.getRequired().toString();
            typeUnitContent = 1;
            
            logger.log("weiexam"+weight+"required"+required);
        }
        
        if(questionText!=null && questionText.getText() !=null){
            text = questionText.getText().trim().replaceAll("\n", "IVELA");
        }

        //mount question json
        StringBuilder json = new StringBuilder();


        
        json.append("{");
            json.append("\"question\":{");
            json.append("\"id\":\"" + question.getId() + "\",");
            json.append("\"description\":\"" + question.getQuestion().replaceAll("\n", "IVELA") + "\",");
            json.append("\"type\":\"" + question.getType() + "\",");
            json.append("\"createdBy\":\"" + question.getCreatedBy().getUsername() + "\",");
            json.append("\"createdAt\":\"" + question.getCreatedAt().toString() + "\",");
            json.append("\"questionText\":\"" + text + "\",");
            json.append("\"weight\":\"" + weight + "\",");
            json.append("\"required\":\"" + required + "\",");
            json.append("\"typeUnitContent\":\"" + typeUnitContent + "\"");

            if (question.getType() == Constants.QUESTION_OBJECTIVE) {
                json.append(",\"objectiveAnswerArray\":{\"objectiveanswer\":[");
                setListObjectiveAnswer(objectiveAnswerRemote.getByQuestion(question.getId()));
                setObjectiveQuestion(objectiveQuestionRemote.getByQuestion(question.getId()));
                    for (ObjectiveAnswer oa : listObjectiveAnswer) {
                        json.append("{");
                        json.append("\"id\":\"" + oa.getId() + "\",");
                        json.append("\"answer\":\"" + oa.getAnswer() + "\",");
                        if (oa.getId() == objectiveQuestion.getCorrectAnswer().getId()) {
                            json.append("\"correct\":\"true\"");
                        } else {
                            json.append("\"correct\":\"false\"");
                        }
                        json.append("},");
                    }
                 json = new StringBuilder(json.substring(0, json.length()-1));   
                 json.append("]}");

            }
            
            if (question.getType() == Constants.QUESTION_AUDITIVE) {
                //applet for voice is called
                setListAuditiveQuestion(auditiveQuestionRemote.getSentenceByQuestion(question.getId()));
                setAuditiveQuestion(auditiveQuestionRemote.getByQuestion(question.getId()));
                json.append(",\"auditiveQuestion\":{");
                    json.append("\"chanceSentence\":\""+auditiveQuestion.getChanceSentence()+ "\",");
                    json.append("\"sentenceAuditiveArray\":{\"auditive\":[");
                for (SentenceAuditiveQuestion saq : listAuditiveQuestion) {
                    json.append("{");
                    json.append("\"id\":\"" + saq.getId() + "\",");
                    json.append("\"sentence\":\"" + saq.getSentence() + "\",");
                    json.append("\"nameFile\":\"" + saq.getFile() + "\"");
                    json.append("},");
                }
                json = new StringBuilder(json.substring(0, json.length()-1));
                json.append("]}");
                json.append("}");
            
            } 
        
            json.append("}");
        json.append("}");



        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";


    }
    


    /**
     * Remove a question
     * @return
     */
    public String remove() {
        //performValidates the remove
        performValidateRemove();
        if (!hasActionErrors()) {
            questionRemote.remove(getQuestion().getId());
            return list();
        }
        addActionError("Unable to remove a question");
        return list();
    }

    /**
     * Sets the variables to be used on the input form
     * @return
     */
    @Override
    public String input() {

        return "input";
    }

    /**
     * Perform a validate in to the add method
     */
    private void performValidateAdd() {
        // verifies if the question is null
        if (question == null) {
            addActionError(getText("question.input.validation.required"));
        }
    }

    
    

    /**
     * Perform a validate in to the update method
     */
    private void performValidateUpdate() {
        // verifies if the category is null
        if (question == null || !Validators.isPositive(question.getId())) {
            addActionError(getText("question.edit.validation.requiredId"));
        } else {
            // verifies if this id is valid
            if (questionRemote.get(question.getId()) == null) {
                addActionError(getText("question.edit.validation.invalidId"));
            }
        }
    }

    /**
     * List the questions
     * @return
     */
    public String list() {
        List<Question> l = questionRemote.getAll();
        setQuestionList(l);
        return "list";
    }

    public String questionsByExercises() {
        xStream.alias("questionArray", Question.class);
        xStream.omitField(Question.class, "createdBy");
        xStream.omitField(Question.class, "questionText");
        String json = xStream.toXML(exerciseRemote.getQuestions(exercise.getId()));

        setInputStream(new ByteArrayInputStream(json.getBytes()));

        return "json";
    }

    public String questionsByExam() {
        xStream.alias("questionArray", Question.class);
        xStream.omitField(Question.class, "createdBy");
        xStream.omitField(Question.class, "questionText");
        String json = xStream.toXML(examRemote.getQuestions(exam.getId()));

        setInputStream(new ByteArrayInputStream(json.getBytes()));

        return "json";
    }

    /**
     * Retrieves the remote questions
     * @return questionRemote
     */
    public QuestionRemote getQuestionRemote() {
        return questionRemote;
    }

    /**
     * Sets the remote questions
     * @param questionRemote
     */
    public void setQuestionRemote(QuestionRemote questionRemote) {
        this.questionRemote = questionRemote;
    }
    
        /**
     * Perform a validate in to the remove method
     */
    private void performValidateRemove() {
    }

    public QuestionExerciseRemote getQuestionExerciseRemote() {
        return questionExerciseRemote;
    }

    public void setQuestionExerciseRemote(QuestionExerciseRemote questionExerciseRemote) {
        this.questionExerciseRemote = questionExerciseRemote;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }


    
    public ExamRemote getExamRemote() {
        return examRemote;
    }

    public void setExamRemote(ExamRemote examRemote) {
        this.examRemote = examRemote;
    }

    public QuestionExamRemote getQuestionExamRemote() {
        return questionExamRemote;
    }

    public void setQuestionExamRemote(QuestionExamRemote questionExamRemote) {
        this.questionExamRemote = questionExamRemote;
    }
    
    
    public QuestionExam getQuestionExam() {
        return questionExam;
    }

    public void setQuestionExam(QuestionExam questionExam) {
        this.questionExam = questionExam;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public UnitContent getUnitContent() {
        return unitContent;
    }

    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }
    

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
    
    

    public File[] getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(File[] uploadFile) {
        this.uploadFile = uploadFile;
    }

    /**
     * Retrieves a question
     * @return question
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Sets a question
     * @param question
     */
    public void setQuestion(Question question) {
        this.question = question;
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

    public QuestionText getQuestionText() {
        return questionText;
    }

    public void setQuestionText(QuestionText questionText) {
        this.questionText = questionText;
    }

    /**
     * Retrieves a upload file name
     * @return uploadFileName
     */
    public String[] getUploadFileName() {
        return uploadFileName;
    }

    /**
     *  Sets a a upload file name
     * @param uploadFileName
     */
    public void setUploadFileName(String[] uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    /**
     * Retrieves a audito auditive question
     * @return audioAuditiveQuestion
     */
    public String getAudioAuditiveQuestion() {
        return audioAuditiveQuestion;
    }

    /**
     * Sets a audito auditive question
     * @param audioAuditiveQuestion
     */
    public void setAudioAuditiveQuestion(String audioAuditiveQuestion) {
        this.audioAuditiveQuestion = audioAuditiveQuestion;
    }

    /**
     * Retrieves a list of question
     * @return questionList
     */
    public List<Question> getQuestionList() {
        return questionList;
    }

    /**
     * Sets a list of question
     * @param questionList
     */
    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    /**
     * Retrieves a remote auditive question
     * @return auditiveQuestionRemote
     */
    public AuditiveQuestionRemote getAuditiveQuestionRemote() {
        return auditiveQuestionRemote;
    }

    /**
     * Sets a remote auditive question
     * @param auditiveQuestionRemote
     */
    public void setAuditiveQuestionRemote(AuditiveQuestionRemote auditiveQuestionRemote) {
        this.auditiveQuestionRemote = auditiveQuestionRemote;
    }

    /**
     * Retrieves a list of auditive question
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
     * Retrieves a auditive question
     * @return auditiveQuestion
     */
    public AuditiveQuestion getAuditiveQuestion() {
        return auditiveQuestion;
    }

    /**
     * Sets a auditive question
     * @param auditiveQuestion
     */
    public void setAuditiveQuestion(AuditiveQuestion auditiveQuestion) {
        this.auditiveQuestion = auditiveQuestion;
    }

    public QuestionExercise getQuestionExercise() {
        return questionExercise;
    }

    public void setQuestionExercise(QuestionExercise questionExercise) {
        this.questionExercise = questionExercise;
    }
    

    /**
     * Retrieves a remote objective answer
     * @return objectiveAnswerRemote
     */
    public ObjectiveAnswerRemote getObjectiveAnswerRemote() {
        return objectiveAnswerRemote;
    }

    /**
     * Sets a remote objective answer
     * @param objectiveAnswerRemote
     */
    public void setObjectiveAnswerRemote(ObjectiveAnswerRemote objectiveAnswerRemote) {
        this.objectiveAnswerRemote = objectiveAnswerRemote;
    }

    /**
     * Retrieves a remote objective question
     * @return objectiveQuestionRemote
     */
    public ObjectiveQuestionRemote getObjectiveQuestionRemote() {
        return objectiveQuestionRemote;
    }

    /**
     * Sets a remote objective question
     * @param objectiveQuestionRemote
     */
    public void setObjectiveQuestionRemote(ObjectiveQuestionRemote objectiveQuestionRemote) {
        this.objectiveQuestionRemote = objectiveQuestionRemote;
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
     * Retrieves a objective question
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
     * Retrieves a setence of auditive question
     * @return sentenceAuditiveQuestion
     */
    public String getSentenceAuditiveQuestion() {
        return sentenceAuditiveQuestion;
    }

    /**
     * Sets a setence of auditive question
     * @param sentenceAuditiveQuestion
     */
    public void setSentenceAuditiveQuestion(String sentenceAuditiveQuestion) {
        this.sentenceAuditiveQuestion = sentenceAuditiveQuestion;
    }

    /**
     * Retrieves a radio
     * @return radio
     */
    public int getRadio() {
        return radio;
    }

    /**
     * Sets a radio
     * @param radio
     */
    public void setRadio(int radio) {
        this.radio = radio;
    }

    /**
     * Retrieves a correct answer
     * @return correctAnswer
     */
    public int getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Sets a correct answer
     * @param correctAnswer
     */
    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Retrieves a question auditive
     * @return questionAuditive
     */
    public int getQuestionAuditive() {
        return questionAuditive;
    }

    /**
     * Sets a question auditive
     * @param questionAuditive
     */
    public void setQuestionAuditive(int questionAuditive) {
        this.questionAuditive = questionAuditive;
    }

    /**
     * Retrieves a obective question
     * @return questionObjective
     */
    public int getQuestionObjective() {
        return questionObjective;
    }

    /**
     * Sets a obective question
     * @param questionObjective
     */
    public void setQuestionObjective(int questionObjective) {
        this.questionObjective = questionObjective;
    }

    /**
     * Retrieves a subjetive question
     * @return questionSubjective
     */
    public int getQuestionSubjective() {
        return questionSubjective;
    }

    /**
     * Sets a subjetive question
     * @param questionSubjective
     */
    public void setQuestionSubjective(int questionSubjective) {
        this.questionSubjective = questionSubjective;
    }

    public File getFileQuestion() {
        return fileQuestion;
    }

    public void setFileQuestion(File fileQuestion) {
        this.fileQuestion = fileQuestion;
    }

    public String getFileQuestionContentType() {
        return fileQuestionContentType;
    }

    public void setFileQuestionContentType(String fileQuestionContentType) {
        this.fileQuestionContentType = fileQuestionContentType;
    }

    
    public String getUploadFileQuestion() {
        return uploadFileQuestion;
    }

    public void setUploadFileQuestion(String uploadFileQuestion) {
        this.uploadFileQuestion = uploadFileQuestion;
    }
    
    

    /**
     * Retrieves a option answer
     * @return answerOption
     */
    public String[] getAnswerOption() {
        return answerOption;
    }

    /**
     * Sets a option answer
     * @param answerOption
     */
    public void setAnswerOption(String[] answerOption) {
        this.answerOption = answerOption;
    }

    /**
     * Retrieves a setence
     * @return sentence
     */
    public String[] getSentence() {
        return sentence;
    }

    /**
     * Sets a setence
     * @param sentece
     */
    public void setSentence(String[] sentece) {
        this.sentence = sentece;
    }

    /**
     * Retrieves a upload
     * @return upload
     */
    public String[] getUpload() {
        return upload;
    }

    /**
     * Sets a upload
     * @param upload
     */
    public void setUpload(String[] upload) {
        this.upload = upload;
    }

    /**
     * Retrieves a radio tense
     * @return radioTense
     */
    public int getRadioTense() {
        return radioTense;
    }

    /**
     * Sets a radio tense
     * @param radioTense
     */
    public void setRadioTense(int radioTense) {
        this.radioTense = radioTense;
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
     * Retrieves a question reading
     * @return questionReading
     */
    public int getQuestionReading() {
        return questionReading;
    }

    /**
     * Sets a question reading
     * @param questionReading
     */
    public void setQuestionReading(int questionReading) {
        this.questionReading = questionReading;
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

    public String getUrlBinary() {
        return urlBinary;
    }

    public void setUrlBinary(String urlBinary) {
        this.urlBinary = urlBinary;
    }

    public String getUrlResult() {
        return urlResult;
    }

    public void setUrlResult(String urlResult) {
        this.urlResult = urlResult;
    }

    public int getBinType() {
        return binType;
    }

    public void setBinType(int binType) {
        this.binType = binType;
    }

    public ExternalQuestionRemote getExternalQuestionRemote() {
        return externalQuestionRemote;
    }

    public void setExternalQuestionRemote(ExternalQuestionRemote externalQuestionRemote) {
        this.externalQuestionRemote = externalQuestionRemote;
    }

    public String[] getParamKey() {
        return paramKey;
    }

    public void setParamKey(String[] paramKey) {
        this.paramKey = paramKey;
    }

    public String[] getParamValue() {
        return paramValue;
    }

    public void setParamValue(String[] paramValue) {
        this.paramValue = paramValue;
    }

    public String getFileQuestionFileName() {
        return fileQuestionFileName;
    }

    public void setFileQuestionFileName(String fileQuestionFileName) {
        this.fileQuestionFileName = fileQuestionFileName;
    }

    public boolean isQuestionRequired() {
        return questionRequired;
    }

    public void setQuestionRequired(boolean questionRequired) {
        this.questionRequired = questionRequired;
    }

    public int getQuestionWeight() {
        return questionWeight;
    }

    public void setQuestionWeight(int questionWeight) {
        this.questionWeight = questionWeight;
    }
    
    
}



