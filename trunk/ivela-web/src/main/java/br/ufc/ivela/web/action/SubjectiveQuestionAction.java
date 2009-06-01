/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.ejb.interfaces.QuestionRemote;
import java.util.List;

public class SubjectiveQuestionAction extends GenericAction {

    private QuestionRemote questionRemote;
    private Question question;
    private List<Question> questionList;

    /**
     * Validate a question, if hasn't erros, add a new subjective question
     * if has retur error
     * @return new question or the error
     */
    public String add() {
        // validates the add
        performvalidateAdd();
        if (!hasActionErrors()) {
            getQuestionRemote().add(getQuestion());
            return list();
        }
        addActionError("Unable to add a question");
        return list();
    }

    /**
     * Edit a question
     * @return list of questions
     */
    public String edit() {
        getQuestionRemote().edit(getQuestion());
        return list();
    }

    /**
     * Validate a question, if hasn't erros, remove a question, 
     * if has return error
     * @return list of questions or error
     */
    public String remove() {
        // validates the remove
        performvalidateRemove();
        if (!hasActionErrors()) {
            getQuestionRemote().remove(getQuestion().getId());
            return list();
        }
        addActionError("Unable to remove a question");
        return list();
    }

    /**
     * Sets the variables to be used on the input form
     * @return
     */
    public String input() {
        return INPUT;
    }

    /**
     * Lists the questions
     * @return list
     */
    public String list() {
        List<Question> list = getQuestionRemote().getAll();
        setQuestionList(list);
        return "list";
    }

    /**
     * Perform a validation in adicional method
     */
    private void performvalidateAdd() {
        // verifies if the video is null
        if (question == null) {
            addActionError(getText("question.input.validation.requiredQuestion"));
        }
    }

    /**
     * Perform a validation in the method remove
     */
    private void performvalidateRemove() {
        // verifies if there is an id
        if (question.getId() == null) {
            addActionError(getText("question.input.validation.requiredId"));
        } else {
            // verifies if this id is valid
            if (questionRemote.get(question.getId()) == null) {
                addActionError(getText("question.input.validation.invalidId"));
            }
        }
    }

    /**
     * Retrieves a remote question
     * @return questionRemote
     */
    public QuestionRemote getQuestionRemote() {
        return questionRemote;
    }

    /**
     * Sets a remote question
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

    /**
     * Retrieves the list of questions
     * @return questionList
     */
    public List<Question> getQuestionList() {
        return questionList;
    }

    /**
     * Sets the list of questions
     * @param questionList
     */
    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}

