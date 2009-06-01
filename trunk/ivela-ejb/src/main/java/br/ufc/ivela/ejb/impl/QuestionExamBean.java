/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.QuestionExam;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.ejb.interfaces.QuestionExamRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Maristella Myrian/ Emanuelle Vieira
 * 
 * Class of ejb which implements the interface QuestionExamLocal
 */
@Stateless(mappedName="QuestionExamBean")
public class QuestionExamBean implements QuestionExamRemote {

    private GenericDao<QuestionExam> daoQuestionExam = DaoFactory.getInstance(QuestionExam.class);
    
    public QuestionExam get(Long id) {
        if (id == null) {
            return null;
        }
        return daoQuestionExam.get(id);
    }

    public List<QuestionExam> getByExam(Long exam) {
        return (List<QuestionExam>)daoQuestionExam.getByFK("exam.id", exam);
    }

    public Long add(QuestionExam questionExam) {
        return  (Long) daoQuestionExam.save(questionExam);
    }

    public boolean remove(Long id) {
        return daoQuestionExam.remove(id);
    }
    
    public QuestionExam getByQuestionByExam(Long questionId, Long examId){
        return (QuestionExam)daoQuestionExam.find("from QuestionExam qe where qe.question.id = ? and qe.exam.id = ?", new Object[]{questionId,examId}).get(0);
    }

    public boolean update(QuestionExam questionExam) {
        return daoQuestionExam.update(questionExam);
    }
}