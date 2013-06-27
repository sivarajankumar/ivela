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

import org.ivela.offline.utils.Utils;



public interface Constants {
	


    public static String PROPERTIES_PORTAL_URL = null;
    public static String PROPERTIES_SERVICE_URL = null;
    public static String PROPERTIES_SERVICE_NAMESPACE = null;
    public static String PROPERTIES_SERVICE_SYNC = null;

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
    
    public static final String USER_DATA_FOLDER = Utils.getInstance().getLocalAppData()+"iveladata/";
    public static final String LOG_NAME = "ivela-offline.log";
    public static final String SEVERE_LOGLEVEL = " S ";
    public static final String NORMAL_LOGLEVEL = " N ";
    public static final int FIXED_LOGLIMIT = 5000000;
    public static final String DEFAULT_DATETIME_FORMAT = "dd/mm/yyyy H:m:s";
}