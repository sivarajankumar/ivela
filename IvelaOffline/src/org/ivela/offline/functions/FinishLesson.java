/**
 * @(#)FinishLesson.java
 *
 * Eldorado Confidential and Proprietary Information
 * Copyright (C)2010 Eldorado, All Rights Reserved
 *
 * Histórico de Modificações
 *
 * Data           Quem              Descrição
 * ------------------------------------------------------------------------------------------------
 * 07 May 2010    Rafael Lagoa      (4115)Reajuste arquitetural.  
 */
package org.ivela.offline.functions;

import java.sql.SQLException;
import java.util.List;


import org.ivela.offline.commons.ConnectionUtil;
import org.ivela.offline.commons.FunctionUtil;
import org.ivela.offline.commons.LogWrapper;
import org.ivela.offline.dao.DisciplineDAO;
import org.ivela.offline.dao.DisciplineDAOImpl;
import org.ivela.offline.dao.FinishedUnitContentDAO;
import org.ivela.offline.dao.FinishedUnitContentDAOImpl;
import org.ivela.offline.dao.UnitContentDAO;
import org.ivela.offline.dao.UnitContentDAOImpl;
import org.ivela.offline.dao.UnitDAO;
import org.ivela.offline.dao.UnitDAOImpl;
import org.ivela.offline.domain.Discipline;
import org.ivela.offline.domain.DisciplineExample;
import org.ivela.offline.domain.FinishedUnitContent;
import org.ivela.offline.domain.FinishedUnitContentExample;
import org.ivela.offline.domain.Unit;
import org.ivela.offline.domain.UnitContent;
import org.ivela.offline.domain.UnitContentExample;
import org.ivela.offline.domain.UnitExample;

public class FinishLesson extends BrowserFunction {

    private static LogWrapper logger = new LogWrapper(FinishLesson.class);
    private static DisciplineDAO disciplineDao = new DisciplineDAOImpl();
    private static UnitDAO unitDao = new UnitDAOImpl();
    private static UnitContentDAO unitContentDao = new UnitContentDAOImpl();
    private static FinishedUnitContentDAO finishedUnitContentDao = new FinishedUnitContentDAOImpl();

    public FinishLesson (Browser browser, String name) {
        super (browser, name);
    }
    public Object function (Object[] arguments) {
        String currentUrl = arguments[0].toString();
        // ATTENTION: This method uses Module and Lesson IDs taken from the file URL, so the database must be compatible to the
        // lesson HTML file names. Example: 'mod1uni1les1p1.html' = Module 1(tag 'module1') and Lesson 1(tag 'lesson1')
        // Other example: 'mod2uni1les12p1.html' = Module 2(tag 'module2') and Lesson 12(tag 'lesson12')
        String moduleTag = "module"+currentUrl.substring(currentUrl.lastIndexOf("mod")+3, currentUrl.lastIndexOf("uni"));
        String unitContentTag = "lesson"+currentUrl.substring(currentUrl.lastIndexOf("les")+3, currentUrl.lastIndexOf("p"));

        try {
            DisciplineExample disciplineExample = new DisciplineExample();
            disciplineExample.createCriteria().andTagEqualTo(moduleTag);
            List<Discipline> discIds = disciplineDao.selectByExample(disciplineExample);

            UnitExample unitExample = new UnitExample();
            unitExample.createCriteria().andDisciplineEqualTo(discIds.get(0).getId());
            List<Unit> unitIds = unitDao.selectByExample(unitExample);

            UnitContentExample unitContentExample = new UnitContentExample();
            unitContentExample.createCriteria().andUnitEqualTo(unitIds.get(0).getId()).andTagEqualTo(unitContentTag);
            List<UnitContent> unitContentIds = unitContentDao.selectByExample(unitContentExample);

            FinishedUnitContentExample finishedUnitContentExample = new FinishedUnitContentExample();
            finishedUnitContentExample.createCriteria().andUnitContentEqualTo(unitContentIds.get(0).getId());
            List<FinishedUnitContent> finisehdUnitContentIds = finishedUnitContentDao.selectByExample(finishedUnitContentExample);

            if ((finisehdUnitContentIds==null) || (finisehdUnitContentIds.size()==0)){
                Long unitContentId = unitContentIds.get(0).getId();
                FinishedUnitContent finishedUnitContent = new FinishedUnitContent();
                finishedUnitContent.setCourse(new Long(1));
                finishedUnitContent.setUnitContent(unitContentId);
                finishedUnitContent.setSystemUser(new Long(1));

                finishedUnitContentDao.insert(finishedUnitContent);
            }

            // Enable synchronization button if current lesson module is completed
            if (0 == FunctionUtil.isDisciplineFinished(moduleTag)) {
            	TopMessageComposite.setSyncActive(true);
            }
        } catch (SQLException e) {
        	logger.error(e);
        }
        return "";
    }
}