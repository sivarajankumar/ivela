/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.ExternalParams;
import br.ufc.ivela.commons.model.ExternalQuestion;
import br.ufc.ivela.ejb.interfaces.ExternalQuestionRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author jefferson
 */
@Stateless(mappedName="ExternalQuestionBean")
public class ExternalQuestionBean implements ExternalQuestionRemote{

    private GenericDao<ExternalQuestion> daoExternalQuestion = DaoFactory.getInstance(ExternalQuestion.class);
    private GenericDao<ExternalParams> daoExternalParam = DaoFactory.getInstance(ExternalParams.class);
    
    public ExternalQuestion getExternalQuestionByQuestion(Long id) {
        
         List list = daoExternalQuestion.getByFK("question.id", id);
         if(list!=null && list.size()!=0)
            return (ExternalQuestion)list.get(0);
         return null;
    }

    public Long add(ExternalQuestion externalQuestion) {
        
       return (Long)daoExternalQuestion.save(externalQuestion);
    }

    public Long addExternalParam(ExternalParams externalParam) {
        return (Long)daoExternalParam.save(externalParam);
    }

}
