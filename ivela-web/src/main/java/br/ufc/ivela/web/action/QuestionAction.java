/*    
#############################################################################################
# Copyright(c) 2009 by IBM Brasil Ltda and others                                           #
# This file is part of ivela project, an open-source                                        #
# Program URL   : http://code.google.com/p/ivela/                                           #  
#                                                                                           #
# This program is free software; you can redistribute it and/or modify it under the terms   #
# of the GNU General Public License as published by the Free Software Foundation; either    #
# version 3 of the License, or (at your option) any later version.                          #
#                                                                                           #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. #
# See the GNU General Public License for more details.                                      #  
#                                                                                           #
#############################################################################################
# File: QuestionAction.java                                                                 #
# Document: Action for the Questions                                                        # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - ??????                            - XXXXXX - Initial Version                #
# 24-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Fixes                  #
#############################################################################################
*/
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.model.AuditiveQuestion;
import br.ufc.ivela.commons.model.ObjectiveAnswer;
import br.ufc.ivela.commons.model.ObjectiveQuestion;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.QuestionText;
import br.ufc.ivela.commons.model.SentenceAuditiveQuestion;
import br.ufc.ivela.ejb.interfaces.QuestionRemote;
import br.ufc.ivela.ejb.interfaces.ObjectiveAnswerRemote;
import br.ufc.ivela.ejb.interfaces.ObjectiveQuestionRemote;
import br.ufc.ivela.commons.util.Validators;
import br.ufc.ivela.ejb.interfaces.AuditiveQuestionRemote;
import java.io.File;
import java.util.HashSet;
import java.util.List;

public class QuestionAction extends GenericAction {

    private QuestionRemote questionRemote;
    private ObjectiveAnswerRemote objectiveAnswerRemote;
    private ObjectiveQuestionRemote objectiveQuestionRemote;
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
    private java.io.File[] upload;
    private String[] uploadFileName;
    private String sentenceAuditiveQuestion;
    private String audioAuditiveQuestion;
    private int radio;
    private int radioTense;
    private String fileConf;
    private String path = Constants.FILE_UPLOAD_PATH;
  

    /**
     * Show the questions
     * @return
     */
    public String show() {
        //type of questions
        setQuestion(questionRemote.get(question.getId()));
        setQuestionSubjective(Constants.QUESTION_SUBJECTIVE);
        setQuestionObjective(Constants.QUESTION_OBJECTIVE);
        setQuestionAuditive(Constants.QUESTION_AUDITIVE);
        setQuestionReading(Constants.QUESTION_READING);

        //loading right one
        if (question.getType() == Constants.QUESTION_OBJECTIVE) {
            setListObjectiveAnswer(objectiveAnswerRemote.getByQuestion(question.getId()));
            setObjectiveQuestion(objectiveQuestionRemote.getByQuestion(question.getId()));
        } else if (question.getType() == Constants.QUESTION_AUDITIVE) {
            //applet for voice is called
            setListAuditiveQuestion(auditiveQuestionRemote.getSentenceByQuestion(question.getId()));
            setAuditiveQuestion(auditiveQuestionRemote.getByQuestion(question.getId()));
            setSentenceAuditiveQuestion("");
            setAudioAuditiveQuestion("");
            for (SentenceAuditiveQuestion s : listAuditiveQuestion) {
                setSentenceAuditiveQuestion(sentenceAuditiveQuestion + s.getSentence() + ".");
                setAudioAuditiveQuestion(audioAuditiveQuestion + s.getFile() + "#");
            }
            setFileConf(auditiveQuestion.getConfFile() + ".jconf");

        } 
        return "show";

    }

    /**
     * Show auditive questions
     * @return
     */
    public String showAuditive() {


        return "auditive";
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

    public QuestionText getQuestionText() {
        return questionText;
    }

    public void setQuestionText(QuestionText questionText) {
        this.questionText = questionText;
    }

    /**
     * Retrieves a path
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     *Sets a path
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
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
    public File[] getUpload() {
        return upload;
    }

    /**
     * Sets a upload
     * @param upload
     */
    public void setUpload(File[] upload) {
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
}

