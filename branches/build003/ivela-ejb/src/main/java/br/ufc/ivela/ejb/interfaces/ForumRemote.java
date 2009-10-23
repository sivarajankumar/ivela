/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Forum;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Interface of ejb representing the methods of contracting business forum
 */
@Remote
public interface ForumRemote {
    
    /**
     * Method which represents the recovery of a forum by identifier
     * 
     * @param id
     * @return
     */
    public Forum get(Long id);
    
    /**
     * Method that is the insertion of a forum
     * 
     * @param forum
     * @return
     */
    public Long add(Forum forum);
    
    /**
     * Method which represents the removal of a forum
     * 
     * @param id
     * @return
     */
    public boolean remove(Long id);
    
    /**
     * Method which represents the recovery of all forums
     * 
     * @return
     */
    public List<Forum> getAll();
    
    //public boolean edit();
    
    public List<Forum> getForumListBySystemUser(Long systemUser);
    
    public List<Forum> getForumListBySystemUserGrade(Long systemUser, Long gradeId);
    
    public List<Forum> getForumListByGrade(Long gradeId);
        /**
     * updates a record
     * @param forum
     * @return true, if everything worked
     *         false, otherwise
     */
    public boolean update(Forum forum);
    
    public Page getForumListPageBySystemUser(Long systemUser, String title, int page, int pageSize);
    
    public Page getForumListPageBySystemUserGrade(Long systemUser, Long grade, String title, int page, int pageSize);
    
    public Page getForumListPageByGrade(Long grade, String title, int page, int pageSize);
    
    public List<Forum> getLastRecords(String fieldToOrder, int orderType, int number);
    
    public List<Forum> getOpenedForumList() ;
    
    public boolean isAccess(Long systemUser, Long course);

    public Page getPublicForumListPage(String title, int page, int pageSize);
    
    public Page getAllForumListPage(String title, int page, int pageSize);
    
    @TransactionAttribute( TransactionAttributeType.REQUIRED)
    public Page getForumList(Long systemUser, Long course, boolean isAdministrator, boolean isPublic, String title, int page, int pageSize);

    @TransactionAttribute( TransactionAttributeType.REQUIRED)
    public List<Forum> getForumList(Long systemUser, Long course, boolean isAdministrator, boolean isPublic, String title);
    
    @TransactionAttribute( TransactionAttributeType.REQUIRED)
    public Page getForumList(Long systemUser, boolean isAdministrator, boolean isPublic, String title, int page, int pageSize);
    
    @TransactionAttribute( TransactionAttributeType.REQUIRED)
    public List<Forum> getForumList(Long systemUser, boolean isAdministrator, boolean isPublic, String title);
    
    public Forum getForum(Long systemUser, boolean isAdministrator, Long forum);
    
}
