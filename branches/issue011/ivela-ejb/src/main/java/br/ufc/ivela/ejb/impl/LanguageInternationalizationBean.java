/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.LanguageInternationalization;
import br.ufc.ivela.ejb.interfaces.LanguageInternationalizationRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author rodrigo
 */
@Stateless(mappedName="LanguageInternationalizationBean")
public class LanguageInternationalizationBean implements LanguageInternationalizationRemote {

    private GenericDao<LanguageInternationalization> daoLanguageInternationalization = DaoFactory.getInstance(LanguageInternationalization.class);

    public List<LanguageInternationalization> getAll() {
        return daoLanguageInternationalization.getAll();
    }

    public Long add(LanguageInternationalization languageInternationalization) {
        return (Long) daoLanguageInternationalization.save(languageInternationalization);
    }

    public LanguageInternationalization get(Long Id) {
        return daoLanguageInternationalization.get(Id);
    }

    public boolean remove(Long Id) {
        return daoLanguageInternationalization.remove(Id);
    }

    public boolean update(LanguageInternationalization languageInternationalization) {
        return daoLanguageInternationalization.update(languageInternationalization);
    }
}
