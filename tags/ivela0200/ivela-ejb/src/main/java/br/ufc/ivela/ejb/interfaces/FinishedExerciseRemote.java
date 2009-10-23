/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.FinishedExercise;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 */
@Remote
public interface FinishedExerciseRemote {

    public List<FinishedExercise> getByUnitContent(Long unitContentId);
    
    public List<FinishedExercise> getByExerciseAndSystemUser(Long exerciseId, Long systemUserId);

    public Long add(FinishedExercise finishedExercise);
    
    public boolean update(FinishedExercise finishedExercise);
    
    public FinishedExercise get(Long finishedExerciseId);
    
    public boolean remove(Long finishedExerciseId);
    
}
