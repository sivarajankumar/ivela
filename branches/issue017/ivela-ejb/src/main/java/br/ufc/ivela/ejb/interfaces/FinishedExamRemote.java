/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.FinishedExam;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 */
@Remote
public interface FinishedExamRemote {

    public List<FinishedExam> getByUnitContent(Long unitContentId);
    
    public List<FinishedExam> getByExamAndSystemUser(Long examId, Long systemUserId);

    public Long add(FinishedExam finishedExam);
    
    public boolean update(FinishedExam finishedExam);
    
    public FinishedExam get(Long finishedExamId);
    
    public boolean remove(Long finishedExamId);  
    
}
