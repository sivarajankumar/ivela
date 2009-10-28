/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.AuditiveQuestion;
import br.ufc.ivela.commons.model.SentenceAuditiveQuestion;
import java.io.File;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ema`nuelle
 */
@Remote
public interface AuditiveQuestionRemote {
    public List<SentenceAuditiveQuestion> getSentenceByQuestion(Long questionId);
    public Long add(AuditiveQuestion auditiveQuestion);
    public AuditiveQuestion getByQuestion(Long idQuestion);
    public AuditiveQuestion get(Long idAuditiveQuestion);
    public Long addSentence(SentenceAuditiveQuestion sentenceAuditiveQuestion,File file);
}
