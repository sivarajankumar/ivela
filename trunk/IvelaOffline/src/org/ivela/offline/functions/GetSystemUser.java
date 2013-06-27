/**
 * @(#)GetSystemUser.java
 *
 * Eldorado Confidential and Proprietary Information
 * Copyright (C)2010 Eldorado, All Rights Reserved
 *
 * Hist�rico de Modifica��es
 *
 * Data           Quem              Descri��o
 * ------------------------------------------------------------------------------------------------
 * 07 May 2010    Rafael Lagoa      (4115)Reajuste arquitetural.  
 */
package org.ivela.offline.functions;

import java.sql.SQLException;
import java.util.List;

import org.ivela.offline.commons.ConnectionUtil;
import org.ivela.offline.commons.LogWrapper;
import org.ivela.offline.dao.SystemUserDAO;
import org.ivela.offline.dao.SystemUserDAOImpl;
import org.ivela.offline.domain.SystemUser;
import org.ivela.offline.domain.SystemUserExample;

public class GetSystemUser implements BrowserFunction {

    private static LogWrapper logger = new LogWrapper(GetSystemUser.class);
    private static SystemUserDAO systemUserDao = new SystemUserDAOImpl(ConnectionUtil.getSqlMapClient());

   
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