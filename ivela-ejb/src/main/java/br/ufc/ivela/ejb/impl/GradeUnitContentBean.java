package br.ufc.ivela.ejb.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public boolean isUnlocked(Long gradeId, Long unitContentId) {
        boolean isUnlocked = true;
        Object[] params = new Object[]{gradeId, unitContentId};
        List<GradeUnitContent> results = daoGradeUnitCont.find("from GradeUnitContent WHERE gradeId = ? and unitContentId = ?", params);
        if (results != null && results.size() > 0 && results.get(0) != null) {
            GradeUnitContent guc = (GradeUnitContent)results.get(0);
            Date actualDate = new Date();
            Date startDate = guc.getStartDatetime();
            Date finishDate = guc.getFinishDatetime();

            if (actualDate.before(startDate) && actualDate.after(finishDate)) {
                isUnlocked = false;
            }
        }
        return isUnlocked;
    }

    public boolean isUnlocked(Long gradeId, String unitContentTag) {
        boolean isUnlocked = true;
        Object[] params = new Object[]{gradeId, unitContentTag};
        List<GradeUnitContent> results = daoGradeUnitCont.find("select guc from GradeUnitContent guc, UnitContent uc WHERE guc.unitContentId = uc.id and guc.gradeId = ? and uc.tag = ?", params);
        if (results != null && results.size() > 0 && results.get(0) != null) {
            GradeUnitContent guc = (GradeUnitContent)results.get(0);
            Date actualDate = new Date();
            Date startDate = guc.getStartDatetime();
            Date finishDate = guc.getFinishDatetime();

            if (actualDate.before(startDate) || actualDate.after(finishDate)) {
                isUnlocked = false;
            }
        }
        return isUnlocked;
    }

    public boolean sendMail(Long gradeId, Long unitContentId) {
        boolean sendMail = false;
        Object[] params = new Object[]{gradeId, unitContentId};
        List<GradeUnitContent> results = daoGradeUnitCont.find("from GradeUnitContent WHERE gradeId = ? and unitContentId = ?", params);
        if (results != null && results.size() > 0 && results.get(0) != null) {
            GradeUnitContent guc = (GradeUnitContent)results.get(0);
            sendMail = guc.getMailFlag().booleanValue();
        }
        return sendMail;
    }
}