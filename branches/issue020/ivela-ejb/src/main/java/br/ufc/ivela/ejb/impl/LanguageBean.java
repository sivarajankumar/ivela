/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Language;
import br.ufc.ivela.ejb.interfaces.LanguageRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author rodrigo
 */
@Stateless
public class LanguageBean implements LanguageRemote {

    private GenericDao<Language> daoLanguage = DaoFactory.getInstance(Language.class);

    public List<Language> getAll() {
        return daoLanguage.getAll();
    }

    public Long add(Language language) {
        return (Long) daoLanguage.save(language);
    }

    public Language get(Long Id) {
        return daoLanguage.get(Id);
    }

    public boolean remove(Long Id) {
        return daoLanguage.remove(Id);
    }

    public boolean update(Language language) {
        return daoLanguage.update(language);
    }
}
