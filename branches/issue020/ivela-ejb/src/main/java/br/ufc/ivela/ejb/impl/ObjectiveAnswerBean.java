/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.ejb.interfaces.ObjectiveAnswerRemote;
import br.ufc.ivela.ejb.*;
import java.util.List;
import javax.ejb.Stateless;
import br.ufc.ivela.commons.model.ObjectiveAnswer;

/**
 *
 * @author emanuelle
 * 
 * Class of ejb which implements the interface ObjectiveAnswerLocal
 */
@Stateless(mappedName="ObjectiveAnswerBean")
public class ObjectiveAnswerBean implements ObjectiveAnswerRemote {

    private GenericDao<ObjectiveAnswer> daoObjectiveAnswer = DaoFactory.getInstance(ObjectiveAnswer.class);
    
    public Long add(ObjectiveAnswer objectiveAnswer) {
        return (Long)daoObjectiveAnswer.save(objectiveAnswer);
    }

    public ObjectiveAnswer get(Long id) {
        if (id == null) {
            return null;
        }
        return daoObjectiveAnswer.get(id);
    }

    public boolean remove(Long id) {
        return daoObjectiveAnswer.remove(id);
    }

    public boolean edit(ObjectiveAnswer objectiveAnswer) {
        ObjectiveAnswer oa = daoObjectiveAnswer.get(objectiveAnswer.getId());
        oa.setAnswer(objectiveAnswer.getAnswer());
        return daoObjectiveAnswer.update(oa);
    }

    public List<ObjectiveAnswer> getByQuestion(Long question) {
        return (List<ObjectiveAnswer>)daoObjectiveAnswer.getByFK("question.id", question);
    }

    
}
