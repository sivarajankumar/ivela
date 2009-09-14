/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.ChallengeItems;
import br.ufc.ivela.commons.model.ChallengerResult;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author marcus
 */
@Remote
public interface ChallengeItemsRemote {

    /**
     * Method that is the insertion of a ChallengeItems
     * 
     * @param question
     * @return
     */
    public Long add(ChallengeItems course);

    /**
     * Method which represents the recovery of a ChallengeItems by identifier
     * 
     * @param id
     * @return a ChallengeItems
     */
    public ChallengeItems get(Long id);
    
    /**
     * Method which represents the recovery of a ChallengeItems by name
     * 
     * @param name
     * @return a ChallengeItems
     */
    public ChallengeItems get(String name);
    
    public List<ChallengeItems> getByUnit(Long unitId);
    
    public ChallengerResult getChallengerResult(Long id);
    
    public Boolean remove (ChallengeItems challengeItems);
    public Boolean update (ChallengeItems challengeItems);
    
     public List<ChallengerResult> getChallengerResultByUnitContent(Long unitContentId,Long studentId, Long gradeId);
}

