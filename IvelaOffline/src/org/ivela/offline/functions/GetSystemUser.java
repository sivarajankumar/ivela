/**
 * @(#)GetSystemUser.java
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
import br.org.eldorado.offline.dao.SystemUserDAO;
import br.org.eldorado.offline.dao.ibatis.SystemUserDAOImpl;
import br.org.eldorado.offline.domain.SystemUser;
import br.org.eldorado.offline.domain.SystemUserExample;

public class GetSystemUser extends BrowserFunction {

    private static LogWrapper logger = new LogWrapper(GetSystemUser.class);
    private static SystemUserDAO systemUserDao = new SystemUserDAOImpl(ConnectionUtil.getSqlMapClient());

    public GetSystemUser (Browser browser, String name) {
        super (browser, name);
    }
    public Object function (Object[] arguments) {
        SystemUserExample systemUserExample = new SystemUserExample();
        List<SystemUser> systemUsers = null;
        try {
            systemUsers = systemUserDao.selectByExample(systemUserExample);
        } catch (SQLException e) {
            logger.error(e);
        }
        return systemUsers.get(0).getUsername();
    }
}