/*    
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
# File: ProfileDataProvider.java                                                            #
# Document: Actions that use ProfileInterceptor should implement this Interface             # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 24-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Fixes                  #
#############################################################################################
*/

package br.ufc.ivela.interceptors.interfaces;

import java.util.Map;

/**
 * Implement this Interface if your Action need Default User Profile Data
 */
public interface ProfileDataProvider {
       
    /**
     * Sets the Ethnicity List Options according to the language set
     * 
     * @param ethnicityList Disabilities Options for Profiles
     */
    public void setGenderList(Map<String, String> ethnicityList);
    
    /**
     * Retrieves he Gender List 
     */
    public Map<String, String> getGenderList();
    
    /**
     * Sets the Gender List Options for Profiles according to the language set
     * 
     * @param genderList Disabilities Options
     */
    public void setEthnicityList(Map<Integer, String> genderList);
    
    /**
     * Retrieves the Ethnicity List
     * 
     * @return a Map with the Ethnicity ID as key and its String Value
     */
    public Map<Integer, String> getEthnicityList();
    
    /**
     * Sets the list of countries to be used according to the language set
     * 
     * @param countryList
     */
    public void setCountryList(Map<Integer, String> countryList);
    
    /**
     * Retrieves the Country List.
     */
    public Map<Integer, String> getCountryList();
    
    /**
     * Sets the Disabilities List Options for Profiles according to the language set
     * 
     * @param disabilitiesList Disabilities Options
     */
    public void setDisabilitiesList(Map<Boolean, String> disabilitiesList);
    
    /**
     * Retrieves the Disabilities List
     */
    public Map<Boolean, String> getDisabilitiesList();
    
    /**
     * Sets the Date format.
     * 
     * @param dateFormat a String in the format %d/%m/%Y
     */
    public void setDateFormat(String dateFormat);
    
    /**
     * Returns the Date Format being used.
     * 
     * @return a String in the format %d/%m/%Y
     */
    public String getDateFormat();
}
