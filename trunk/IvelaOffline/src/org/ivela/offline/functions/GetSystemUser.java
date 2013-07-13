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

import org.ivela.offline.dao.SystemUserDAO;
import org.ivela.offline.dao.SystemUserDAOImpl;
import org.ivela.offline.utils.LoggerManager;

public class GetSystemUser implements BrowserFunction {

	private static SystemUserDAO systemUserDao = new SystemUserDAOImpl();

	public Object function(Object[] arguments) {
		/*
		 * SystemUserExample systemUserExample = new SystemUserExample();
		 * List<SystemUser> systemUsers = null; try { systemUsers =
		 * systemUserDao.selectByExample(systemUserExample); } catch
		 * (SQLException e) { logger.error(e); } return
		 * systemUsers.get(0).getUsername();
		 */
		try {
			return systemUserDao.getUserName();
		} catch (SQLException e) {
			LoggerManager.getInstance().logAtDebugTime("GetSystemUser",
					"Problem when getting user from DB: " + e.getMessage());
		}
		return null;
	}
}