/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Rodrigo Félix de Almeida / Leonardo Oliveira Moreira
 * 
 * Ejb's interface that implements the methods of business entity for the professors
 */
@Remote
public interface ProfessorRemote {

    public List<SystemUser> getAll();
    
    public List<Grade> getGradeListByProfessorSystemUser(Long systemUserId);
    
}
