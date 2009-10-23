/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Enrollment;
import br.ufc.ivela.commons.model.Topic;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Leonardo Oliveira Moreira / Emanuelle Vieira
 * 
 * Interface of ejb representing the methods of contracting business forum
 */
@Remote
public interface TopicRemote {
    
    
    /**
     * Method which represents the recovery of a topic by identifier
     * 
     * @param id
     * @return
     */
    public Topic get(Long id);
    
    /**
     * Method to retrieve a list of topics related to a forum
     * 
     * @param forum
     * @return
     */
    public List<Topic> getByForum(Long forum);
    
    public Page getByForumPage(Long forum, String title, int page, int pageSize);
    
    /**
     * Method that is the insertion of a topic
     * 
     * @param forum
     * @return
     */
    public Long add(Topic topic);

    /**
     * Method which represents the removal of a topic
     * 
     * @param id
     * @return
     */
    public boolean remove(Long id);
    
            /**
     * updates a record
     * @param topic
     * @return true, if everything worked
     *         false, otherwise
     */
    public boolean update(Topic topic);
    
    public Topic getLastTopicByForum(Long forumId);
    
    public List<Topic> getRecentTopics(int count, List<Enrollment> gradeIds);
    
    public boolean isAccess(Long systemUser, Long course);
    
    
    
    public Page getTopicList(Long systemUser, boolean isAdministrator, boolean isPublic, Long forum, String title, int page, int pageSize);
    
    public List<Topic> getTopicList(Long systemUser, boolean isAdministrator, boolean isPublic, Long forum, String title);
    
    public Topic getTopic(Long systemUser, boolean isAdministrator, Long topic);
    
}
