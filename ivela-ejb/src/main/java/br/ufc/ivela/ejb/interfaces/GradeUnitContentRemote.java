package br.ufc.ivela.ejb.interfaces;

import javax.ejb.Remote;

import br.ufc.ivela.commons.model.GradeUnitContent;

@Remote
public interface GradeUnitContentRemote {

    public Long add(GradeUnitContent gradeUnitContent);

    public Boolean update(GradeUnitContent unitContent);

    public GradeUnitContent get(Long id);

    public boolean remove(GradeUnitContent unitContent);

    public boolean isUnlocked(Long gradeId, Long unitContentId);

    public boolean isUnlocked(Long gradeId, String unitContentTag);

    public boolean sendMail(Long gradeId, Long unitContentId);
}