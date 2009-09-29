package br.ufc.ivela.ejb.interfaces;

import javax.ejb.Remote;

import br.ufc.ivela.commons.model.GradeUnitContent;

@Remote
public interface GradeUnitContentRemote {

    public Long add(GradeUnitContent gradeUnitContent);

    public Boolean update(GradeUnitContent unitContent);

    public GradeUnitContent get(Long id);

    public boolean remove(GradeUnitContent unitContent);
}