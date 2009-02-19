/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Note;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author nelson
 */
@Remote
public interface NoteRemote {
    
    /**
     * retrieves a Note of the database by id
     * @param id
     * @return
     */
    public Note get(Long id);
    
    
    /**
     * 
     * @param note
     * @return
     */
    public Long add(Note note);
    
    public String addAjax(Note note);
    
    
    /**
     * remvoe a Note of the database by id
     * @param id
     * @return
     */
    public boolean remove(Long id);
    public String removeAjax(Long id);
    
    
    /**
     * retrieves a Note of the database by id to systemUser
     * @param systemUser
     * @return
     */
    public List<Note> getByUser(Long systemUserId);
    
    
    /**
     * retrieves a List of the database
     * @return
     */
    public List<Note> getAll();
    
    
    public List<Note> getByDay(Date date, Long userId, int pageSize, int page);
    public List<Note> getByWeek(Date date, Long userId, int pageSize, int page);
    public List<Note> getByMonth(Date date, Long userId, int pageSize, int page);
    
    public boolean update(Note note);
    public String updateAjax(Note note);
    String getNotesByDate(int method, Date date, Long userId,int page);
    
    public List<Note> getRecentNotes(int count, Long userId);
}
