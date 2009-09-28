/*    
#############################################################################################
# Copyright(c) 2009 by IBM Brasil Ltda and others                                           #
# This file is part of ivela project, an open-source                                        #
# Program URL   : http://code.google.com/p/ivela/                                           #  
#                                                                                           #
# This program is free software; you can redistribute it and/or modify it under the terms   #
# of the GNU General Public License as published by the Free Software Foundation; either    #
# version 3 of the License, or (at your option) any later version.                          #
#                                                                                           #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. #
# See the GNU General Public License for more details.                                      #  
#                                                                                           #
#############################################################################################
# File: PropertiesUtil.java                                                                 #
# Document: Utility for Properties Data                                                     # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 24-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Fixes                  #
#############################################################################################
*/

package br.ufc.ivela.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility Class to access and manage Application Properties.
 */
public class PropertiesUtil {
       
    public enum IVELA_PROPERTIES {
        /**
         * Web Path for the Application
         */
        WEB_PATH("web.path");
        
        private String key;
        
        private IVELA_PROPERTIES(String key) {
            this.key = key;
        }
        
        public String getKey() {
            return key;
        }
    }
    
    private static final String PROPERTIES_FILE = "ivela.properties";

    private static final String ENTRY_DELIMITER = "\\|";

    private static final String ID_DELIMITER = "\\#";
    
    private static final String PROPERTY_DEFAULT_LANGUAGE = "default.language";
    
    private static final String PROPERTY_COUNTRY = "country";
    
    private static final String PROPERTY_STATES = "states_";        
    
    private static PropertiesUtil instance;

    private static Properties propertiesReader;

    private static Log logger = LogFactory.getLog(PropertiesUtil.class);
    
    private final Map<Integer, Map<String, String>> statesMap = new HashMap<Integer, Map<String, String>>();

    private Map<Integer, String> countryMap;
    
    private String defaultLanguage;
    
    // Guarantee Thread Safety
    static {
        try {
        ClassLoader classL = PropertiesUtil.class.getClassLoader();
            propertiesReader = new Properties();                
            propertiesReader.load(classL.getResourceAsStream(PROPERTIES_FILE));
        } catch (IOException ioe) {
            logger.error( PROPERTIES_FILE + " not loaded", ioe);
        } catch (Exception e) {
            logger.warn(PROPERTIES_FILE + "may not have been loaded: ", e);
        }
    }
    
    private PropertiesUtil() {}
    
    /**
     * Retrieve the PropertiesUtil Instance in use.
     * 
     * @return
     */
    public static PropertiesUtil getPropertiesUtil() {
        if (PropertiesUtil.instance == null) {
            synchronized (PropertiesUtil.class) {
                if (PropertiesUtil.instance == null) 
                    instance = new PropertiesUtil();
            }
        }

        return instance;
    }
        
    /**
     * Retrieves the Default Language to be used by the Application
     */
    public String getDefaultLanguage() {
        if (null == defaultLanguage) {
            //TODO: Implement validation of the Language value
            String language = propertiesReader.getProperty(PROPERTY_DEFAULT_LANGUAGE);            
            if (null == language) {
                this.defaultLanguage = "en";
            }
        }        
        return defaultLanguage;
    }
    
    /**
     * Retrieve the Default List of Countries in the default Language
     */
    public Map<Integer, String> getCountries() {              
        if (null == countryMap) {
            countryMap = new TreeMap<Integer, String>();
            synchronized (countryMap) {
                if (countryMap.isEmpty()) {
                    List<String> initialCountries = parseProperty(propertiesReader
                            .getProperty(PROPERTY_COUNTRY));
                    int limit = initialCountries.size() + 20;
                    String prop = PROPERTY_COUNTRY + '.';
                    for (int i = 1; i < 128; i++) {
                        String value = propertiesReader.getProperty(prop + i);
                        if (value != null) {
                            countryMap.put(i, value);
                        } else {
                            // Probably no more values? Stop parsing
                            if (i > limit)
                                break;
                        }
                    }

                    for (int i = 0; i < initialCountries.size(); i++) {
                        int actualKey = i + 1;
                        if (!countryMap.containsKey(actualKey)) {
                            countryMap.put(actualKey, initialCountries.get(i));
                        }
                    }
                }
            }
        }
        
        return countryMap;        
    }
    
    /**
     * Retrieves the states for a specific country
     * 
     * @param country The Id of the country
     * 
     * @return a Map with the states where the key and values are the name of
     *     the state. It will return an empty map if there is no states 
     *     stored for the selected country.
     */
    public Map<String, String> getStates(Integer country) {
        if (null == country) {
            return new HashMap<String, String>();
        }
        
        Map<String, String> statesList = statesMap.get(country);
        if (null == statesList) {
            synchronized (statesMap) {       
                statesList = new TreeMap<String, String>();                
                parseProperty(propertiesReader.getProperty(PROPERTY_STATES + country), statesList);                
                statesMap.put(country, statesList);
            }
        }
        return statesList;
    }
    
    /**
     * Retrieve a Property from the Ivela properties file.
     * 
     * @param property the Property to retrieve
     * 
     * @return the value in the specified property
     */
    public String getProperty(IVELA_PROPERTIES property) {
        return propertiesReader.getProperty(property.getKey());   
    }
    
    private void parseProperty(String property, Map<String, String> parsedProperty) {        
        if ((null != property) && (!property.isEmpty())) {
            for (String entry : property.split(ENTRY_DELIMITER)) {
                if (entry.isEmpty())
                    continue;
                String[] parsedEntry = entry.split(ID_DELIMITER);                
                switch (parsedEntry.length) {
                    case 1:                        
                        parsedProperty.put(parsedEntry[0], parsedEntry[0]);
                        break;
                    case 2:
                        parsedProperty.put(parsedEntry[0], parsedEntry[1]);
                        break;
                    default:                        
                        parsedProperty.put(parsedEntry[0], entry
                            .substring(parsedEntry[0].length()));
                }
            }
        }
    }
    
    private List<String> parseProperty(String property) {
        List<String> parsedProperty = new ArrayList<String>();
        if ((null != property) && (!property.isEmpty())) {
            for (String entry : property.split(ENTRY_DELIMITER)) {
                if (entry.isEmpty())
                    continue;
                parsedProperty.add(entry);                                                
            }
        }
        return parsedProperty;
    }
    
    
}
