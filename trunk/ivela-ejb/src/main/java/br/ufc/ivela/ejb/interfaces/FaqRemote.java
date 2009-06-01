/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Faq;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Leonardo Oliveira Moreira
 */
@Remote
public interface FaqRemote {

    /**
     * Method that is the insertion of a faq
     * 
     * @param question
     * @return
     */
    public Long add(Faq course);

    /**
     * Method which represents the recovery of a faq by identifier
     * 
     * @param id
     * @return a faq
     */
    public Faq get(Long id);

    /**
     * Method which represents the removal of a faq
     * 
     * @param id
     * @return true if can be remove a faq by id or false if can't
     */
    public boolean remove(Long id);   

    /**
     * Method which represents the recovery of all faq
     * 
     * @return a list of faq
     */
    public List<Faq> getAll();
    
    /**
     * Method to update a faq
     * @param faq
     * @return true if can update a faq or false if can't
     */
    public boolean update(Faq faq);

}

