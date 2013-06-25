/**
 * @(#)LogWrapper.java
 *
 * Eldorado Confidential and Proprietary Information
 * Copyright (C)2010 Eldorado, All Rights Reserved
 *
 * Hist�rico de Modifica��es
 *
 * Data           Quem              Descri��o
 * ------------------------------------------------------------------------------------------------
 * 10 May 2010    Rafael Lagoa      (4115)Classe respons�vel por logging de informa��es.  
 */
package org.ivela.offline.commons;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogWrapper {

    private static Logger logger = null;
    public static final String SPACE = " ";
    public static final String TAB = "\t";
    private static final String END_OF_LINE = System.getProperty("line.separator");
    public static final String LOGGER_PROPERTY_FILE = "logger.properties";
    public static final String LOG_FILE_NAME = "log.file.name";
    public static final String LOG_LEVEL = "logger.level";
    public static final String LOG_FILE_SIZE = "logger.file.size";
    public static final String LOG_NUMBER_OF_FILES = "logger.numberof.files";
    public static final String LOGGER_NAME = "logger.name";
    public static final String MESSAGE_FORMAT = "logger.message.format";
    public String className = "";

    private void init(Class clazz) {
    	InputStream is = null;
		try {
            is = this.getClass().getClassLoader().getResourceAsStream(LOGGER_PROPERTY_FILE);
            Properties prop = new Properties();
            prop.load(is);
            if(logger == null) {
                logger= Logger.getLogger(prop.getProperty(LOGGER_NAME));
    	        String fileName = prop.getProperty(LOG_FILE_NAME);
    	        FileHandler fh = new FileHandler(fileName.concat("%g.log"),
    	        		Integer.parseInt(prop.getProperty(LOG_FILE_SIZE)),
    	        		Integer.parseInt(prop.getProperty(LOG_NUMBER_OF_FILES)),true);
    	        fh.setFormatter(new CustomMessageFormatter(new MessageFormat(prop.getProperty(MESSAGE_FORMAT))));
    	        logger.setLevel(Level.parse(prop.getProperty(LOG_LEVEL)));
    	        logger.addHandler(fh);
            }
            this.className = clazz.getName();
        } catch(FileNotFoundException fnfe) {
        	fnfe.printStackTrace();
        } catch(IOException ioe) {
        	ioe.printStackTrace();
        }
    }

    public LogWrapper(Class clazz) {
		init(clazz);
    }
    public LogWrapper() {
    }

    public void info(String...strings) {
        logger.info(constructMessage(strings));
    }

    public void debug(String...strings) {
        logger.fine(constructMessage(strings));
    }

    public void warn(String...strings) {
        logger.warning(constructMessage(strings));
    }

    public void entering(String className, String methodName, Object[] data) {
        logger.entering(className, methodName, data);
    }
    public void entering(String className, String methodName) {
        logger.entering(className, methodName);
    }
    public void entering(String className, String methodName, Object data) {
        logger.entering(className, methodName, data);
    }

    public void exiting(String className, String methodName, Object data) {
        logger.exiting(className, methodName, data);
    }
    public void exiting(String className, String methodName) {
        logger.exiting(className, methodName);
    }

    public void error(Exception trw, String...strings) {
        logger.severe(constructMessage(strings)+ constructStackTrace(trw));
    }

    private String constructMessage(String[] strings) {
        StringBuilder sbl = new StringBuilder();
        sbl.append(SPACE);
        sbl.append(className);
        if(strings != null || strings.length >0){
            for(int count=0; count< strings.length; count++) {
                sbl.append(SPACE);
                sbl.append(strings[count]);
            }
        }
        return sbl.toString();
    }
    private String constructStackTrace(Exception exe) {
        StringBuilder sbl = new StringBuilder();
    	sbl.append(END_OF_LINE);
        sbl.append(exe.toString());
        for(StackTraceElement ste:exe.getStackTrace()) {
        	sbl.append(END_OF_LINE);
        	sbl.append(TAB);
        	sbl.append(ste.toString());
        }
        return sbl.toString();
    }
}