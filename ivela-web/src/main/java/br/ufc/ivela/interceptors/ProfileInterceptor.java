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
# File: ProfileInterceptor.java                                                             #
# Document: Interceptor For Actions that Use Profile Default Data                           # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 24-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Fixes                  #
#############################################################################################
*/

package br.ufc.ivela.interceptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import br.ufc.ivela.interceptors.interfaces.ProfileDataProvider;
import br.ufc.ivela.services.PropertiesUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Interceptor For Actions that need User Profile Data 
 */
public class ProfileInterceptor extends AbstractInterceptor {

    /**
     * Default Serial Version ID
     */
    private static final long serialVersionUID = 5455911011583363372L;
    
    private static final String KEY_OVERRIDE_COUNTRY = "country.override.";
    
    // Store Ethnicities by language
    private Map<String, Map<Integer, String>> ethnicityMap = new HashMap<String, Map<Integer, String>>();

    // Store Gender by language
    private Map<String, Map<String, String>> genderMap = new HashMap<String, Map<String, String>>();;

    // Store Disabilities by language    
    private Map<String, Map<Boolean, String>> disabilitiesMap = new HashMap<String, Map<Boolean, String>>();    
    
    // Store Countries by language
    private Map<String, Map<Integer, String>> countriesMap = new HashMap<String, Map<Integer, String>>();
    
    // Default Language to be used
    private final String defaultLanguage = PropertiesUtil.getPropertiesUtil().getDefaultLanguage();
    
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Object object = invocation.getAction();

        if ((object instanceof ActionSupport)
                && (object instanceof ProfileDataProvider)) {
            ActionSupport action = (ActionSupport) object;
            ProfileDataProvider provider = (ProfileDataProvider) object;
            Locale locale = invocation.getInvocationContext().getLocale();
            provider.setEthnicityList(getEthnicityList(locale, action));
            provider.setGenderList(getGenderList(locale, action));   
            provider.setDisabilitiesList(getDisabilitiesList(locale, action));
            provider.setCountryList(getCountriesList(locale, action));
        }

        return invocation.invoke();
    }

    private Map<Integer, String> getEthnicityList(Locale locale,
            ActionSupport action) {
        Map<Integer, String> ethnicityList = ethnicityMap.get(locale
                .getLanguage());

        if (null == ethnicityList) {
            synchronized (ethnicityMap) {
                ethnicityList = new TreeMap<Integer, String>();
                String ethnicity = action.getText("profile.ethnicity.1", "");
                if (!ethnicity.isEmpty()) {
                    int i = 1;
                    ethnicityList.put(i, ethnicity);
                    while (!(ethnicity = action.getText("profile.ethnicity."
                            + ++i, "")).isEmpty()) {
                        ethnicityList.put(i, ethnicity);
                    }

                    ethnicityMap.put(locale.getLanguage(), ethnicityList);
                } else {
                    ethnicityMap.put(locale.getLanguage(), ethnicityList);
                    ethnicityList = ethnicityMap.get(defaultLanguage);
                }
            }
        }

        return ethnicityList;
    }

    private Map<String, String> getGenderList(Locale locale,
            ActionSupport action) {
        Map<String, String> genderList = genderMap.get(locale.getLanguage());

        if (null == genderList) {
            synchronized (genderMap) {
                genderList = new LinkedHashMap<String, String>();
                String male = action.getText("profile.gender.male", "");
                if (!male.isEmpty()) {
                    genderList
                    .put("0", action.getText("profile.gender.female"));
                    genderList.put("1", male);                    
                    genderMap.put(locale.getLanguage(), genderList);
                } else {
                    genderMap.put(locale.getLanguage(), genderList);
                    genderList = genderMap.get(defaultLanguage);
                }
            }
        }
        return genderList;
    }
    
    private Map<Boolean, String> getDisabilitiesList(Locale locale,
            ActionSupport action) {
        Map<Boolean, String> disabilitieList = disabilitiesMap.get(locale
                .getLanguage());

        if (null == disabilitieList) {
            synchronized (disabilitiesMap) {
                disabilitieList = new LinkedHashMap<Boolean, String>();
                String yes = action.getText("general.yes", "");
                if (!yes.isEmpty()) {
                    disabilitieList.put(Boolean.TRUE, yes);
                    disabilitieList.put(Boolean.FALSE, action
                            .getText("general.no"));
                    disabilitiesMap.put(locale.getLanguage(), disabilitieList);
                } else {
                    disabilitiesMap.put(locale.getLanguage(), disabilitieList);
                    disabilitieList = disabilitiesMap.get(defaultLanguage);
                }
            }
        }
        return disabilitieList;
    }
    
    private Map<Integer, String> getCountriesList(Locale locale,
            ActionSupport action) {
        Map<Integer, String> countryList = countriesMap.get(locale
                .getLanguage());
        
        if (null == countryList) {
            synchronized (countriesMap) {
                // get Default Countries in the Default language
                countryList = PropertiesUtil.getPropertiesUtil().getCountries();
                // New Country List that will hold the countries in alphabetical order.
                Map<Integer, String> newCountryList = new LinkedHashMap<Integer,String>();                
                for (Integer key :countryList.keySet()) {
                    String value = action.getText(KEY_OVERRIDE_COUNTRY + key, "");                    
                    if (!value.isEmpty()) {                        
                        countryList.put(key, value);
                    }
                }    
                
                List<String> orderedList = new ArrayList<String>(countryList.values());                
                Collections.sort(orderedList);
                Set<Entry<Integer, String>> oldEntries = countryList.entrySet();                
                for (String country: orderedList) {
                    for (Entry<Integer, String> entry: oldEntries) {
                        if (entry.getValue().equals(country)) {
                            newCountryList.put(entry.getKey(), entry.getValue());
                            continue;
                        }
                    }
                    
                }
                
                if (countryList.size() == newCountryList.size()) {
                    countriesMap.put(locale.getLanguage(), newCountryList);
                    countryList = newCountryList;
                } else {
                    countriesMap.put(locale.getLanguage(), countryList);
                }
                
            }
        }       
        
        return countryList;
    }
}
