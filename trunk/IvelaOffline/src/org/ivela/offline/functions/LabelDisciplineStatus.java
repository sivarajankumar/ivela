/**
 * @(#)LabelDisciplineStatus.java
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

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import org.ivela.offline.commons.ConnectionUtil;
import org.ivela.offline.commons.FunctionUtil;
import org.ivela.offline.commons.LogWrapper;
import org.ivela.offline.dao.DisciplineDAO;
import org.ivela.offline.dao.DisciplineDAOImpl;
import org.ivela.offline.domain.Discipline;
import org.ivela.offline.domain.DisciplineExample;

public class LabelDisciplineStatus extends BrowserFunction {

    private static LogWrapper logger = new LogWrapper(LabelDisciplineStatus.class);
    private static DisciplineDAO disciplineDao = new DisciplineDAOImpl();

    public LabelDisciplineStatus (Browser browser, String name) {
        super (browser, name);
    }

    public Object function (Object[] arguments) {
        DisciplineExample disciplineExample = new DisciplineExample();
        disciplineExample.createCriteria().andTagEqualTo(arguments[0].toString());
        List<Discipline> disciplines = null;
        String disciplineStatus = "";
        try {
            disciplines = disciplineDao.selectByExample(disciplineExample);
		    int status = FunctionUtil.isDisciplineFinished(arguments[0].toString());
		    String onClickFunc = "./"+disciplines.get(0).getId()+"/table_contents.html";
		    if (0 == status) {
		        disciplineStatus = "<a href='"+onClickFunc+"'><img src='./images/modulo_selo_concluido.png'></a>";
		    } else if (-1 == status){
		        disciplineStatus = "<a href='"+onClickFunc+"'><img src='./images/modulo_selo_acesse.png'></a>";
		    } else {
		        disciplineStatus = "<a href='"+onClickFunc+"'><img src='./images/modulo_selo_continuar.png'></a>";
		    }
        } catch (SQLException e) {
        	logger.error(e);
        }
        return disciplineStatus;
    }
}