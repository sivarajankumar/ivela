/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.ejb.interfaces.ChallengeItemsRemote;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.ChallengeItems;
import br.ufc.ivela.commons.model.ChallengerResult;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author marcus
 */
@Stateless(mappedName="ChallengeItemsBean")
public class ChallengeItemsBean implements ChallengeItemsRemote {

    private GenericDao<ChallengeItems> daoChallengeItems = DaoFactory.getInstance(ChallengeItems.class);
    private GenericDao<ChallengerResult> daoChallengeResult = DaoFactory.getInstance(ChallengerResult.class);

    public Long add(ChallengeItems challengeItems) {
        return (Long) daoChallengeItems.save(challengeItems);
    }

    public ChallengeItems get(Long id) {
        if (id == null) {
            return null;
        }
        return daoChallengeItems.get(id);
    }

    public Boolean remove (ChallengeItems challengeItems){
        return daoChallengeItems.remove(challengeItems);
    }
    
    public Boolean update (ChallengeItems challengeItems){
        return daoChallengeItems.update(challengeItems);
    }
    
    public ChallengeItems get(String name) {
        if (name == null) {
            return null;
        }
        
        List<ChallengeItems> results = daoChallengeItems.find("from ChallengeItems c where c.name = ?", new Object[]{name});
        
        if(results != null && results.size() > 0){
            return results.get(0);
        } else {
            return null;
        }
    }
    
    
    public List<ChallengeItems> getByUnit(Long unitId){
        return (List<ChallengeItems>)daoChallengeItems.find("from ChallengeItems c where c.unit.id=?", new Object[]{unitId});
    }
    public ChallengerResult getChallengerResult(Long id){
        return daoChallengeResult.get(id);
    }
    
    public List<ChallengerResult> getChallengerResultByUnitContent(Long unitContentId,Long studentId, Long gradeId){
        return daoChallengeResult.find("from ChallengerResult ch where ch.unitContent.id =? and ch.student.id =? and ch.grade.id =? ", new Object[]{unitContentId,studentId,gradeId});    
    }
}
