/**
 * @(#)LabelUnitStatus.java
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
import java.util.List;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import br.org.eldorado.offline.common.ConnectionUtil;
import br.org.eldorado.offline.common.LogWrapper;
import br.org.eldorado.offline.dao.DisciplineDAO;
import br.org.eldorado.offline.dao.FinishedUnitContentDAO;
import br.org.eldorado.offline.dao.UnitContentDAO;
import br.org.eldorado.offline.dao.UnitDAO;
import br.org.eldorado.offline.dao.ibatis.DisciplineDAOImpl;
import br.org.eldorado.offline.dao.ibatis.FinishedUnitContentDAOImpl;
import br.org.eldorado.offline.dao.ibatis.UnitContentDAOImpl;
import br.org.eldorado.offline.dao.ibatis.UnitDAOImpl;
import br.org.eldorado.offline.domain.Discipline;
import br.org.eldorado.offline.domain.DisciplineExample;
import br.org.eldorado.offline.domain.FinishedUnitContent;
import br.org.eldorado.offline.domain.FinishedUnitContentExample;
import br.org.eldorado.offline.domain.Unit;
import br.org.eldorado.offline.domain.UnitContent;
import br.org.eldorado.offline.domain.UnitContentExample;
import br.org.eldorado.offline.domain.UnitExample;

public class LabelUnitStatus extends BrowserFunction {

    private static LogWrapper logger = new LogWrapper(LabelUnitStatus.class);
    private static DisciplineDAO disciplineDao = new DisciplineDAOImpl(ConnectionUtil.getSqlMapClient());
    private static UnitDAO unitDao = new UnitDAOImpl(ConnectionUtil.getSqlMapClient());
    private static UnitContentDAO unitContentDao = new UnitContentDAOImpl(ConnectionUtil.getSqlMapClient());
    private static FinishedUnitContentDAO finishedUnitContentDao = new FinishedUnitContentDAOImpl(ConnectionUtil.getSqlMapClient());

    public LabelUnitStatus (Browser browser, String name) {
        super (browser, name);
    }
    public Object function (Object[] arguments) {
        String disciplineStatus = "";
        try {
            DisciplineExample disciplineExample = new DisciplineExample();
            disciplineExample.createCriteria().andTagEqualTo(arguments[0].toString());
            List<Discipline> discIds = disciplineDao.selectByExample(disciplineExample);

            UnitExample unitExample = new UnitExample();
            unitExample.createCriteria().andDisciplineEqualTo(discIds.get(0).getId());
            List<Unit> unitIds = unitDao.selectByExample(unitExample);

            UnitContentExample unitContentExample = new UnitContentExample();
            unitContentExample.createCriteria().andUnitEqualTo(unitIds.get(0).getId()).andTagEqualTo(arguments[1].toString());
            List<UnitContent> unitContentIds = unitContentDao.selectByExample(unitContentExample);

            String onClickFunc = "javascript:goToUnit(\"./"+unitIds.get(0).getId()+"/"+unitContentIds.get(0).getId()+"/"+arguments[2].toString()+"\");";
            if (true == isUnitContentCompleted(unitContentIds.get(0).getId())) {
                disciplineStatus = "<a href='"+onClickFunc+"'><img src='./images/modulo_selo_concluido_menor.png'></a>";
            } else {
                // ATTENTION: This "if" is only applicable for the actual implementation for the Professional English Course
                // because not always the previous tag of a lesson will be like: 'lesson3' -> 'lesson2'
                if ((Integer.valueOf(arguments[1].toString().substring(6))-1==0) ||
                    (true == isUnitContentCompleted(unitContentIds.get(0).getId()-1))) {
                    disciplineStatus = "<a href='"+onClickFunc+"'><img src='./images/modulo_selo_acesse_menor.png'></a>";
                } else {
                    disciplineStatus = "<img src='./images/modulo_selo_bloqueado_menor.png'>";
                }
            }
        } catch (SQLException e) {
        	logger.error(e);
        }
        return disciplineStatus;
    }

    public boolean isUnitContentCompleted(String disciplineTag, String unitContentTag) throws SQLException {
        try {
            UnitContentExample unitContentExample = new UnitContentExample();
            unitContentExample.createCriteria().andTagEqualTo(unitContentTag);
            UnitContent unitContent = (UnitContent)unitContentDao.selectByExample(unitContentExample).get(0);

            FinishedUnitContentExample finishedUnitContentExample = new FinishedUnitContentExample();
            finishedUnitContentExample.createCriteria().andUnitContentEqualTo(unitContent.getId());
            List<FinishedUnitContent> fucIds = finishedUnitContentDao.selectByExample(finishedUnitContentExample);
            if (!fucIds.isEmpty()) {
                return true;
            }
        } catch (IndexOutOfBoundsException e) {
            logger.error(e);
        }
        return false;
    }

    public boolean isUnitContentCompleted(Long unitContentId) throws SQLException {
        try {
            FinishedUnitContentExample finishedUnitContentExample = new FinishedUnitContentExample();
            finishedUnitContentExample.createCriteria().andUnitContentEqualTo(unitContentId);
            List<FinishedUnitContent> fucIds = finishedUnitContentDao.selectByExample(finishedUnitContentExample);
            if (!fucIds.isEmpty()) {
                return true;
            }
        } catch (IndexOutOfBoundsException e) {
            logger.error(e);
        }
        return false;
    }
}