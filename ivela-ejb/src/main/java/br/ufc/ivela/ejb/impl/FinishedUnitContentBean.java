/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.FinishedUnitContent;
import br.ufc.ivela.ejb.interfaces.FinishedUnitContentRemote;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 */
@Stateless(mappedName="FinishedUnitContentBean")
public class FinishedUnitContentBean implements FinishedUnitContentRemote {

    private GenericDao<FinishedUnitContent> daoFinishedUnitContent = DaoFactory.getInstance(FinishedUnitContent.class);
    
    public List<FinishedUnitContent> getByUnitContent(Long unitContentId) {
        return daoFinishedUnitContent.getByFK("unitContent.id", unitContentId);
    }

    public Long add(FinishedUnitContent finishedUnitContent) {
        return (Long) daoFinishedUnitContent.save(finishedUnitContent);
    }

    public boolean update(FinishedUnitContent finishedUnitContent) {
        return daoFinishedUnitContent.update(finishedUnitContent);
    }

    public FinishedUnitContent get(Long finishedUnitContentId) {
        return daoFinishedUnitContent.get(finishedUnitContentId);
    }

    public boolean remove(Long finishedUnitContentId) {
        return daoFinishedUnitContent.remove(daoFinishedUnitContent.get(finishedUnitContentId));
    }

    public List<FinishedUnitContent> getByCourseAndSystemUser(Long courseId, Long systemUserId) {
        Object[] params = new Object[] { courseId, systemUserId };
        List<FinishedUnitContent> list = (List<FinishedUnitContent>) daoFinishedUnitContent.find("from FinishedUnitContent c where c.course = ? and c.systemUser = ?", params);
        if (list == null)
            list = new ArrayList<FinishedUnitContent>();
        return list;
    }
    
    public List<FinishedUnitContent> getByUnitContentAndSystemUser(Long unitContentId, Long systemUserId) {
        Object[] params = new Object[] { unitContentId, systemUserId };
        List<FinishedUnitContent> list = (List<FinishedUnitContent>) daoFinishedUnitContent.find("from FinishedUnitContent c where c.unitContent = ? and c.systemUser = ?", params);
        if (list == null)
            list = new ArrayList<FinishedUnitContent>();
        return list;
    }

    public List<FinishedUnitContent> getByUnitContentTagAndSystemUser(String unitContentTag, Long systemUserId) {
        Object[] params = new Object[] { unitContentTag, systemUserId };
        List<FinishedUnitContent> list = (List<FinishedUnitContent>) daoFinishedUnitContent.find("select fuc from FinishedUnitContent fuc, UnitContent uc where fuc.unitContent=uc.id and uc.tag=? and fuc.systemUser = ?", params);
        if (list == null)
            list = new ArrayList<FinishedUnitContent>();
        return list;
    }
}
