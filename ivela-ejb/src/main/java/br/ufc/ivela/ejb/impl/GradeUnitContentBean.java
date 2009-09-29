package br.ufc.ivela.ejb.impl;

import javax.ejb.Stateless;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.GradeUnitContent;
import br.ufc.ivela.ejb.interfaces.GradeUnitContentRemote;

@Stateless(mappedName="GradeUnitContentBean")
public class GradeUnitContentBean implements GradeUnitContentRemote {

    private GenericDao<GradeUnitContent> daoGradeUnitCont = DaoFactory.getInstance(GradeUnitContent.class);

    public Long add(GradeUnitContent gradeUnitContent) {
        return (Long) daoGradeUnitCont.save(gradeUnitContent);
    }

    public Boolean update(GradeUnitContent gradeUnitContent) {
        return daoGradeUnitCont.update(gradeUnitContent);
    }

    public GradeUnitContent get(Long id) {
        return daoGradeUnitCont.get(id);
    }

    public boolean remove(GradeUnitContent gradeUnitContent) {
        return daoGradeUnitCont.remove(gradeUnitContent);
    }
}