/**
 * @(#)ProgressCourse.java
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

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import br.org.eldorado.offline.common.ConnectionUtil;
import br.org.eldorado.offline.common.LogWrapper;
import br.org.eldorado.offline.dao.FinishedUnitContentDAO;
import br.org.eldorado.offline.dao.UnitContentDAO;
import br.org.eldorado.offline.dao.ibatis.FinishedUnitContentDAOImpl;
import br.org.eldorado.offline.dao.ibatis.UnitContentDAOImpl;
import br.org.eldorado.offline.domain.FinishedUnitContentExample;
import br.org.eldorado.offline.domain.UnitContentExample;

public class ProgressCourse extends BrowserFunction {

    private static LogWrapper logger = new LogWrapper(ProgressCourse.class);
    private static UnitContentDAO unitContentDao = new UnitContentDAOImpl(ConnectionUtil.getSqlMapClient());
    private static FinishedUnitContentDAO finishedUnitContentDao = new FinishedUnitContentDAOImpl(ConnectionUtil.getSqlMapClient());

    public ProgressCourse (Browser browser, String name) {
        super (browser, name);
    }
    public Object function (Object[] arguments) {
    	int rate = 0;
		try {
			rate = getProgress();
        } catch (SQLException e) {
        	logger.error(e);
		}
    	return rate;
    }

    public static int getProgress() throws SQLException {
        int cUnitContents = 0;
        int cFinishedUnitContents = 0;

        UnitContentExample unitContentExample = new UnitContentExample();
        cUnitContents = unitContentDao.countByExample(unitContentExample);
	    FinishedUnitContentExample finishedUnitContentExample = new FinishedUnitContentExample();
        cFinishedUnitContents = finishedUnitContentDao.countByExample(finishedUnitContentExample);

        int all = cUnitContents;
        int done = cFinishedUnitContents;
        double rate = (double) ((double) done / (double) all) * 100;

        if (!new Integer(0).equals(rate)) {
            rate = rate/10;
        }
        return (int) rate;
    }
}