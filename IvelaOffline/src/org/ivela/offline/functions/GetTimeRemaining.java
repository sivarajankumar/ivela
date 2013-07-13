/**
 * @(#)GetTimeRemaining.java
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
import java.util.ArrayList;
import java.util.List;

import org.ivela.offline.commons.Constants;
import org.ivela.offline.commons.FunctionUtil;
import org.ivela.offline.commons.LogWrapper;
import org.ivela.offline.dao.FinishedUnitContentDAO;
import org.ivela.offline.dao.FinishedUnitContentDAOImpl;
import org.ivela.offline.dao.UnitContentDAO;
import org.ivela.offline.dao.UnitContentDAOImpl;
import org.ivela.offline.dao.UnitDAO;
import org.ivela.offline.dao.UnitDAOImpl;
import org.ivela.offline.domain.FinishedUnitContent;
import org.ivela.offline.domain.FinishedUnitContentExample;
import org.ivela.offline.domain.Unit;
import org.ivela.offline.domain.UnitContent;
import org.ivela.offline.domain.UnitContentExample;
import org.ivela.offline.domain.UnitExample;

public class GetTimeRemaining extends BrowserFunction {

    private static LogWrapper logger = new LogWrapper(GetTimeRemaining.class);
    private static UnitDAO unitDao = new UnitDAOImpl();
    private static UnitContentDAO unitContentDao = new UnitContentDAOImpl();
    private static FinishedUnitContentDAO finishedUnitContentDao = new FinishedUnitContentDAOImpl();

    public GetTimeRemaining (Browser browser, String name) {
        super (browser, name);
    }
    public Object function (Object[] arguments) {
        List<UnitContent> unitContents = null;
        List<FinishedUnitContent> finishedUnitContents = null;
        try {
            UnitContentExample unitContentExample = new UnitContentExample();
            if (arguments.length!=0) {
                Long[] location = FunctionUtil.getLocation(arguments[0].toString());
                if (location[Constants.ID_DISCIPLINE] != null) {
                    UnitExample unitExample = new UnitExample();
                    unitExample.createCriteria().andDisciplineEqualTo(location[Constants.ID_DISCIPLINE]);
                    List<Unit> unitIds = unitDao.selectByExample(unitExample);
                    unitContentExample.createCriteria().andUnitEqualTo(unitIds.get(0).getId());
                }
            }
            unitContents = unitContentDao.selectByExample(unitContentExample);
	        FinishedUnitContentExample finishedUnitContentExample = new FinishedUnitContentExample();
            finishedUnitContents = finishedUnitContentDao.selectByExample(finishedUnitContentExample);
        } catch (SQLException e) {
        	logger.error(e);
        }
        List<Long> finishedUnitContentsAux = new ArrayList();
        for (FinishedUnitContent finishedUnitContent : finishedUnitContents) {
            finishedUnitContentsAux.add(finishedUnitContent.getUnitContent());
        }

        int hours = 0;
        int minutes = 0;
        for (UnitContent unitContent : unitContents) {
            if (!finishedUnitContentsAux.contains(unitContent.getId())) {
                hours += unitContent.getDuration().getHours();
                minutes += unitContent.getDuration().getMinutes();
            }
        }
        hours = hours + minutes/60;
        minutes = minutes%60;
        return (hours!=0 ? hours + " hora(s) e ":"") + minutes + " minuto(s)";
    }
}