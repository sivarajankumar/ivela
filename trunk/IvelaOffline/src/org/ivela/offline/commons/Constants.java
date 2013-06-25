/**
 * @(#)Constants.java
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
package org.ivela.offline.commons;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.org.eldorado.offline.gui.Main;

public class Constants {
	
    private static LogWrapper logger = new LogWrapper(Constants.class);

    public static String PROPERTIES_PORTAL_URL = null;
    public static String PROPERTIES_SERVICE_URL = null;
    public static String PROPERTIES_SERVICE_NAMESPACE = null;
    public static String PROPERTIES_SERVICE_SYNC = null;

    static {
		InputStream is = Main.class.getClassLoader().getResourceAsStream("configuration.properties");
        Properties prop = new Properties();
        try {
			prop.load(is);
		} catch (IOException e) {
			logger.error(e);
		}
		PROPERTIES_PORTAL_URL = prop.getProperty("itcorner.portal.url");
        PROPERTIES_SERVICE_URL = prop.getProperty("itcorner.service.url");
        PROPERTIES_SERVICE_NAMESPACE = prop.getProperty("itcorner.service.namespace");
        PROPERTIES_SERVICE_SYNC = prop.getProperty("itcorner.service.synchronizeCourse");
	}

    public static String INSTALL_PATH = "file:///" + new File("").getAbsolutePath().replace("\\", "/") + "/content/";
    public static String LOG_PATH = System.getProperty("user.dir") + "/offline.log";
    public static String WINDOW_ICON_SMALL = "img/iconSmall.png";
    public static String WINDOW_ICON_LARGE = "img/iconLarge.png";
    public static String WINDOW_ICON_WARNING = "img/iconWarning.gif";
    public static String WINDOW_ICON_INVALID = "img/iconInvalid.png";
    public static String WINDOW_ICON_VALID = "img/iconValid.png";
    public static String WINDOW_ICON_SYNC = "img/iconSync.gif";
    public static final int ID_COURSE = 0;
    public static final int ID_DISCIPLINE = 1;
    public static final int ID_UNIT = 2;
    public static final int ID_LESSON = 3;
}