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
package br.org.eldorado.offline.browser.function;

import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import br.org.eldorado.offline.common.ConnectionUtil;
import br.org.eldorado.offline.common.FunctionUtil;
import br.org.eldorado.offline.common.LogWrapper;
import br.org.eldorado.offline.dao.DisciplineDAO;
import br.org.eldorado.offline.dao.ibatis.DisciplineDAOImpl;
import br.org.eldorado.offline.domain.Discipline;
import br.org.eldorado.offline.domain.DisciplineExample;

public class LabelDisciplineStatus extends BrowserFunction {

    private static LogWrapper logger = new LogWrapper(LabelDisciplineStatus.class);
    private static DisciplineDAO disciplineDao = new DisciplineDAOImpl(ConnectionUtil.getSqlMapClient());

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