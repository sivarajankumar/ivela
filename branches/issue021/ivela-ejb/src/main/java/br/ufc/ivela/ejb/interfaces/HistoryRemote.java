/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.History;
import br.ufc.ivela.commons.model.Message;
import br.ufc.ivela.commons.model.Transcript;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Interface of ejb representing the methods of contracting business history
 */
@Remote
public interface HistoryRemote {
    
    /**
     * Method which represents the recovery of a message by identifier
     * 
     * @param id
     * @return
     */
    public History get(Long id);
    
    /**
     * Method that is the insertion of a history
     * 
     * @param discipline
     * @return
     */
    public Long add(History history);
    
    /**
     * Method which represents the removal of a message
     * 
     * @param id
     * @return
     */
    public boolean remove(Long id);
    
    /**
     * Method which represents the recovery of all messages
     * 
     * @return
     */
    public List<History> getAll();
    
    /**
     * Method which represents the recovery of all messages recipient by system user
     * 
     * @param systemUserId
     * @return
     */
    public List<History> getBySystemUser(Long systemUserId);

    /**
     * Method which represents the update of the messsage
     * 
     * @param message
     * @return
     */
    public boolean update(History history);
    
    
    public List<Transcript> getTranscriptsByStudent(Long studentId);
    
    public Boolean updateTranscript(Transcript transcript);
    
    public Transcript calcAverageCourse(Long gradeId, Long studentId);
    
    public Transcript getTranscript(Long transcriptId);
    
    public Long addTranscript(Long gradeId, Long studentId);
    
    public String getScoresStudent(Long courseId,Long studentId);
    
    public List<Transcript> getTranscriptsByStudentByGrade(Long studentId, Long gradeId);

}
