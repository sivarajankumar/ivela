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
package br.org.eldorado.offline.browser.function;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import br.org.eldorado.offline.common.ConnectionUtil;
import br.org.eldorado.offline.common.Constants;
import br.org.eldorado.offline.common.FunctionUtil;
import br.org.eldorado.offline.common.LogWrapper;
import br.org.eldorado.offline.dao.FinishedUnitContentDAO;
import br.org.eldorado.offline.dao.UnitContentDAO;
import br.org.eldorado.offline.dao.UnitDAO;
import br.org.eldorado.offline.dao.ibatis.FinishedUnitContentDAOImpl;
import br.org.eldorado.offline.dao.ibatis.UnitContentDAOImpl;
import br.org.eldorado.offline.dao.ibatis.UnitDAOImpl;
import br.org.eldorado.offline.domain.FinishedUnitContent;
import br.org.eldorado.offline.domain.FinishedUnitContentExample;
import br.org.eldorado.offline.domain.Unit;
import br.org.eldorado.offline.domain.UnitContent;
import br.org.eldorado.offline.domain.UnitContentExample;
import br.org.eldorado.offline.domain.UnitExample;

public class GetTimeRemaining extends BrowserFunction {

    private static LogWrapper logger = new LogWrapper(GetTimeRemaining.class);
    private static UnitDAO unitDao = new UnitDAOImpl(ConnectionUtil.getSqlMapClient());
    private static UnitContentDAO unitContentDao = new UnitContentDAOImpl(ConnectionUtil.getSqlMapClient());
    private static FinishedUnitContentDAO finishedUnitContentDao = new FinishedUnitContentDAOImpl(ConnectionUtil.getSqlMapClient());

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