package org.ivela.offline.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.ivela.offline.commons.Constants;

public class ManageProperties{
	
	private static ManageProperties INSTANCE;

    public static ManageProperties getInstance() {
            if (INSTANCE == null) {
                    INSTANCE = new ManageProperties();
            }
            return INSTANCE;
    }

    private ManageProperties(){}
    
    public String read(String propName) {
            Properties properties = new Properties();
            try {
                    properties.load(new FileInputStream(Constants.USER_DATA_FOLDER+Constants.PROPERTIES_FILE_NAME));
            } catch (IOException e) {
            	LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), "read() "+e.getMessage());
            }
            return properties.getProperty(propName);
    }
	
}