/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.AuditiveQuestion;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.SentenceAuditiveQuestion;
import br.ufc.ivela.ejb.interfaces.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author emanuelle
 */
@Stateless(mappedName="AuditiveQuestionBean")
public class AuditiveQuestionBean implements AuditiveQuestionRemote {

    private GenericDao<SentenceAuditiveQuestion> daoSentenceAQ = DaoFactory.getInstance(SentenceAuditiveQuestion.class);
    private GenericDao<Question> daoQuestion = DaoFactory.getInstance(Question.class);
    private GenericDao<AuditiveQuestion> daoAuditiveQuestion = DaoFactory.getInstance(AuditiveQuestion.class);

    /**
     * Get all sentences of a question auditive
     * @param questionId
     * @return SentenceAuditiveQuestion  List
     */
    public List<SentenceAuditiveQuestion> getSentenceByQuestion(Long questionId) {
        AuditiveQuestion aq = (AuditiveQuestion) daoAuditiveQuestion.getByFK("question.id", questionId).get(0);
        Object[] params = new Object[]{aq.getId()};
        return (List<SentenceAuditiveQuestion>) daoSentenceAQ.find("from SentenceAuditiveQuestion where question.id = ? order by sequence", params);

    }

    /**
     * Add a question auditive
     * @param Auditive Question
     * @return Long  
     */
    public Long add(AuditiveQuestion auditiveQuestion) {
        return (Long) daoAuditiveQuestion.save(auditiveQuestion);

    }

    /**
     * Add a sentence of question auditive
     * @param sentence of Auditive Question
     * @return Long  
     */
    public Long addSentence(SentenceAuditiveQuestion sentenceAuditiveQuestion, File file) {
        InputStream data = null;
        try {
            java.io.File f = new java.io.File(sentenceAuditiveQuestion.getFile());
            data = new FileInputStream(file);
            OutputStream out = new FileOutputStream(f);
            byte[] buf = new byte[1024];
            int len;
            while ((len = data.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            data.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (Long) daoSentenceAQ.save(sentenceAuditiveQuestion);

    }

    /**
     * Get auditive question by question
     * @param idQuestion
     * @return AuditiveQuestion
     */
    public AuditiveQuestion getByQuestion(Long idQuestion) {
        return (AuditiveQuestion) daoAuditiveQuestion.getByFK("question.id", idQuestion).get(0);
    }

    /**
     * Get a question auditive
     * @param id auditive question
     * @return AuditiveQuestion
     */
    public AuditiveQuestion get(Long idAuditiveQuestion) {
        return daoAuditiveQuestion.get(idAuditiveQuestion);
    }    // Add business logic below. (Right-click in editor and choose
    // "EJB Methods > Add Business Method" or "Web Service > Add Operation")
}
