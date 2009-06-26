/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.Faq;
import br.ufc.ivela.ejb.interfaces.FaqRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Leonardo Oliveira Moreira
 */
@Stateless(mappedName="FaqBean")
public class FaqBean implements FaqRemote {

    private GenericDao<Faq> daoFaq = DaoFactory.getInstance(Faq.class);

    /**
     * Connect to the server and add a new faq
     * @param faq
     * @return true if the new faq can be add, and false if can't
     */
    public Long add(Faq faq) {
        return (Long) daoFaq.save(faq);
    }

    /**
     * Connect to the server and get the faq by id
     * @param id
     * @return faq
     */
    public Faq get(Long id) {
        if (id == null) {
            return null;
        }
        return daoFaq.get(id);
    }

    /**
     * Connect to the server and remove a faq by id
     * @param id
     * @return true if the new faq can be remove, and false if cannot
     */
    public boolean remove(Long id) {
        return daoFaq.remove(daoFaq.get(id));
    }

    /**
     * try to connect to the server and get all faq and lists
     *
     * @return a list of faq
     */
    public List<Faq> getAll() {
       return daoFaq.getAll();
    }

    /**
     * try to connect to the server and update a faq
     * @param faq
     * @return
     */
    public boolean update(Faq faq) {
        return daoFaq.update(faq);

    }
    
}
