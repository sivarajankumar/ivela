/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.ExternalParams;
import br.ufc.ivela.commons.model.ExternalQuestion;
import javax.ejb.Remote;

/**
 *
 * @author jefferson
 */
@Remote
public interface ExternalQuestionRemote {

    public ExternalQuestion getExternalQuestionByQuestion(Long id);
    public Long addExternalParam(ExternalParams externalParams);
    public Long add(ExternalQuestion externalQuestion);
}
