/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.ObjectiveAnswer;
import br.ufc.ivela.ejb.interfaces.ObjectiveQuestionRemote;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.ObjectiveQuestion;
import javax.ejb.Stateless;

/**
 *
 * @author emanuelle
 * Class of ejb which implements the interface ObjectiveQuestionLocal
 */
@Stateless(mappedName="ObjectiveQuestionBean")
public class ObjectiveQuestionBean implements ObjectiveQuestionRemote {

    private GenericDao<ObjectiveQuestion> daoObjectiveQuestion = DaoFactory.getInstance(ObjectiveQuestion.class);
    private GenericDao<ObjectiveAnswer> daoObjectiveAnswer = DaoFactory.getInstance(ObjectiveAnswer.class);
    
    public Long add(ObjectiveQuestion objectiveQuestion) {
        return (Long)daoObjectiveQuestion.save(objectiveQuestion);
    }

    public ObjectiveQuestion get(Long id) {
        return daoObjectiveQuestion.get(id);
    }

    public boolean edit(ObjectiveQuestion objectiveQuestion) {
        ObjectiveQuestion oq = daoObjectiveQuestion.get(objectiveQuestion.getId());
        ObjectiveAnswer oa = daoObjectiveAnswer.get(objectiveQuestion.getCorrectAnswer().getId());
        oq.setCorrectAnswer(oa);
        return daoObjectiveQuestion.update(oq);
    }

    public ObjectiveQuestion getByQuestion(Long question) {
        return (ObjectiveQuestion)daoObjectiveQuestion.getByFK("question.id",question ).get(0);
    }
}
