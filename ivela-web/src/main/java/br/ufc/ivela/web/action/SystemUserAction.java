/*##################################################################################################
# Copyright(c) 2008-2009 by IBM Brasil Ltda and others                                             #
# This file is part of ivela project, an open-source                                               #
# Program URL   : http://code.google.com/p/ivela/                                                  #   
#                                                                                                  #
# This program is free software; you can redistribute it and/or modify it under the terms          #
# of the GNU General Public License as published by the Free Software Foundation; either           #
# version 3 of the License, or (at your option) any later version.                                 #
#                                                                                                  #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;        #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.        #
# See the GNU General Public License for more details.                                             #  
#                                                                                                  #
####################################################################################################
# File: SystemUserAction.java                                                                      #
# Document: Controller that offers the logic of the process of creation users of the portal        #                                
# Date        - Author(Company)                   - Issue# - Summary                               #
# XX-XXX-XXXX - Leonardo Oliveira Moreira         - XXXXXX - Initial Version                       #
# 16-JUN-2009 - mileine (Instituto Eldorado)      - 000010 - Password strength validation disabled #
# 22-JUN-2009 - mileine (Instituto Eldorado)      - 000010 - General Issues Initial Fix            #
##################################################################################################*/

package br.ufc.ivela.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.model.Address;
import br.ufc.ivela.commons.model.Authentication;
import br.ufc.ivela.commons.model.Phone;
import br.ufc.ivela.commons.model.Profile;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.util.Validators;
import br.ufc.ivela.ejb.interfaces.AddressRemote;
import br.ufc.ivela.ejb.interfaces.CalendarRemote;
import br.ufc.ivela.ejb.interfaces.PhoneRemote;
import br.ufc.ivela.ejb.interfaces.ProfileRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import br.ufc.ivela.interceptors.interfaces.ProfileDataProvider;
import br.ufc.ivela.util.Mailer;
import br.ufc.ivela.util.PropertiesUtil;
import br.ufc.ivela.util.PropertiesUtil.IVELA_PROPERTIES;

public class SystemUserAction extends GenericAction implements
        ProfileDataProvider {

    private CalendarRemote calendarRemote;
    private SystemUserRemote systemUserRemote;
    private SystemUser systemUser;
    private ProfileRemote profileRemote;
    private PhoneRemote phoneRemote;
    private Profile profile;
    private Address[] address;
    private Phone[] phone;
    // private LanguageRemote languageRemote;
    private AddressRemote addressRemote;
    private List<SystemUser> systemUserList;
    private Map<Integer, String> countryList;
    // private List<Language> languageList;
    private Map<Integer, String> ethnicityList;
    private Map<String, String> genderList;
    private Map<Boolean, String> disabilitiesList;
    private String captchaValue;
    private String retypePassword;
    private String retypeEmail;
    private String username;
    private String email;
    private InputStream inputStream;
    private String password;
    private String newPassword;
    private int locatioType;
    private int scorePassword; // personal
    private String honorific;
    private String birthDate; // language and ethnicity
    private String language;
    private String ethnicity; // phone
    private String mainPhone;
    private String mobile; // location
    private Address inAddress;
    private Integer country;
    private String dateFormat;
    private Mailer mailer;

    public String inputChange() {
        return "change";
    }

    public String change() {

        boolean ok = true;

        if (!StringUtils.hasText(password)) {
            addActionError(getText("systemUser.password.noactual"));
            ok = false;
        }
        if (!StringUtils.hasText(newPassword)) {
            addActionError(getText("systemUser.password.nonew"));
            ok = false;
        }
        if (!StringUtils.hasText(retypePassword)) {
            addActionError(getText("systemUser.password.noretype"));
            ok = false;
        }

        if (ok) {
            if (!systemUserRemote.encrypt(password).equals(
                    getAuthenticatedUser().getPassword())) {
                addActionError(getText("systemUser.password.error"));
            } else {
                if (!newPassword.equals(retypePassword)) {
                    addActionError(getText("systemUser.password.match"));
                } else {
                    setSystemUser(systemUserRemote.get(getAuthenticatedUser()
                            .getId()));
                    getSystemUser().setPassword(
                            systemUserRemote.encrypt(newPassword));
                    systemUserRemote.update(getSystemUser());

                    addActionMessage(getText("systemUser.password.ok"));
                }
            }
        }

        return "change";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String forgotPassword() {
        return "forgotPassword";
    }

    private static String chars = "abcdefghijklmonpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static Random r = new Random();

    private static String generatePassword() {
        char[] buf = new char[8];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = chars.charAt(r.nextInt(chars.length()));
        }
        return new String(buf);
    }

    public String forgotPasswordExecute() {
        boolean result = false;
        String message = "success";
        List<SystemUser> systemUserList = systemUserRemote
                .getByUsername(username);
        if (systemUserList != null && systemUserList.size() == 1
                && systemUserList.get(0).getEmail().equalsIgnoreCase(email)) {
            SystemUser su = systemUserList.get(0);
            String pwd = generatePassword();
            su.setPassword(systemUserRemote.encrypt(pwd));
            result = systemUserRemote.update(su);
            if (result) {
                String body = "Your new password is: <b>" + pwd + "</b>";

                HttpServletRequest request = ServletActionContext.getRequest();
                String url = "http://" + request.getServerName() + ":"
                        + request.getServerPort() + PropertiesUtil.getPropertiesUtil().getProperty(IVELA_PROPERTIES.WEB_PATH);
                if (!url.endsWith("/")) {
                    url += "/";
                }

                mailer.send(new String[] { su.getEmail() }, null,
                        "[ivela] Request password", body, true);
            }
        } else {
            message = "inconsistence";
            result = false;
        }
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"result\": \"" + result + "\",");
        json.append("\"message\": \"" + message + "\"");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }

    public CalendarRemote getCalendarRemote() {
        return calendarRemote;
    }

    public void setCalendarRemote(CalendarRemote calendarRemote) {
        this.calendarRemote = calendarRemote;
    }

    /**
     * Add a new system user, perform a validation if hasn't error add a new
     * system user if has return error
     */
    public String add() {

        if (systemUser == null) {
            return INPUT;
        }

        performValidationAdd();

        if (!hasActionMessages()) {                        
            // if (systemUser.getProfile() == null) {
            // Profile profile = new Profile();
            // systemUser.setProfile(profile);
            // }
            //
            // systemUser.getProfile().setSystemUser(systemUser);
            //
            //            
            // systemUser.setSocialNumber("1");
            // systemUser.getProfile().setEthnicity(1);
            // systemUser.getProfile().setLanguage(1L);

            systemUser.setEnabled(true);
            String password = systemUser.getPassword();
            password = systemUserRemote.encrypt(password);
            systemUser.setPassword(password);
            systemUser
                    .setAuthentication(new Authentication(Constants.ROLE_USER));

            // *******PROFILE*******

            if (birthDate != null && !birthDate.equals("")) {
                Date date = this.generateDate(birthDate);
                profile.setBirthDate(date);
            }

            // language and enth
            // if (language != null && !language.equals("")) {
            // Integer languageId = new Integer(language);
            // Language li = languageList.get(languageId - 1);
            // profile.setLanguage(li);
            // }

            if (ethnicity != null && !ethnicity.equals("")) {
                Integer ethId = new Integer(ethnicity);
                profile.setEthnicity(ethId);
            }

            // SAVING PROFILE, GENERATING ID
            Long profileId = null;
            if (profile != null) {
                if (systemUser.getSocialNumber() != null) {
                    profile.setSocialNumber(systemUser.getSocialNumber());
                }
                profileId = profileRemote.add(profile);
                profile = profileRemote.get(profileId);

                systemUser.setProfileId(profileId);

            }
            // END SAVING PROFILE

            // --location

            if (profileId != null) {

                inAddress.setProfileId(profile.getId());
                inAddress.setProfile(profile);
                addressRemote.add(inAddress);

                // if(country!=null && !country.equals("")){
                //                 
                // Integer coId = new Integer(country);
                // Country countryObj = countryList.get(coId-1);
                // //System.out.println("country: " + countryObj.getName());
                // if(inAddress!=null && inAddress.getState()!=null){
                //                     
                // State state = inAddress.getState();
                // state.setCountry(countryObj);
                // Long stateId = stateRemote.add(state);
                // state.setId(stateId);
                // //Set setOfAddress = new HashSet<Address>();
                //                    
                // Long addressId = new Long(addressRemote.lastId()+1);
                // //System.out.println("ADDRESSID: " + addressId);
                // inAddress.setId(addressId);
                // inAddress.setLocation(inAddress.getAdditionalInformation());
                // inAddress.setProfileId(profileId);
                // logger.log("dsfsdf"+inAddress.getLocationType().getId());
                // inAddress.setLocationType(locationTypeRemote.get(inAddress.getLocationType().getId()));
                // addressRemote.add(inAddress);
                // //setOfAddress.add(inAddress);
                // //profile.setAddresses(setOfAddress);
                //                    
                // }
                //            
                // }
            }

            if (profileId != null && mainPhone != null) {
                Phone mPhone = new Phone();
                mPhone.setNumber(mainPhone);
                mPhone.setProfileId(profileId);
                phoneRemote.add(mPhone);
            }

            if (profileId != null && mobile != null) {
                Phone mPhone = new Phone();
                mPhone.setNumber(mobile);
                mPhone.setProfileId(profileId);
                phoneRemote.add(mPhone);
            }

            // saving user
            Long id = systemUserRemote.add(systemUser);
            systemUser = systemUserRemote.get(id);

            // create the user in webical application
            HttpServletRequest request = ServletActionContext.getRequest();
            log.debug(request.getServerName());
            log.debug(String.valueOf(request.getServerPort()));
            calendarRemote
                    .addInfo(request.getServerName(), String.valueOf(request
                            .getServerPort()), systemUser.getUsername());

            // send a confirmation mail

            String url = "http://" + request.getServerName() + ":" +
            request.getServerPort() + Constants.WEB_PATH;
            String url = "http://200.17.41.212:8080" + PropertiesUtil.getPropertiesUtil().getProperty(IVELA_PROPERTIES.WEB_PATH);
            if (!url.endsWith("/")) {
              url += "/";
            }
            Map<String, String> map = new HashMap<String, String>();
            map.put("url", url);
            String subject = "[ivela] You were successfully registered";
            mailer.send(systemUser, subject,
                    "welcome_user_en.vm",
                    map);

            // create a history register
            addHistory("history.createuser.title",
                    "history.createuser.description", systemUser, systemUser
                            .getUsername());
            return "login";
        } else {
            return INPUT;
        }
    }

    /**
     * Edit a system user
     * 
     * @return edit
     */
    public String edit() {
        setSystemUser(systemUserRemote.get(getSystemUser().getId()));
        return "edit";
    }

    /**
     * Sets the variables to be used on the input system user
     */
    @Override
    public String input() {
        return INPUT;
    }

    /**
     * Perform a Validation in the method add new user
     */
    public void performValidationAdd() {
        // Map session = ActionContext.getContext().getSession();

        // String sessionValue = (String)
        // session.get(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY);

        // captcha
        // if (!sessionValue.equals(captchaValue)) {
        // addActionMessage(getText("systemUser.validation.captcha"));
        // }

        // scorePassord low value should not stop user from signing in
        /*
         * if (scorePassword < 24) {
         * addActionMessage(getText("systemUser.validation.scorePassword")); }
         * else
         */

        // email
        if (!StringUtils.hasText(systemUser.getEmail())) {
            addActionMessage(getText("systemUser.validation.email"));
        }
        if (!Validators.validateEmail(systemUser.getEmail())) {
            addActionMessage(getText("systemUser.validation.emailformat"));
        } else if (!retypeEmail.equals(systemUser.getEmail())) {
            addActionMessage(getText("systemUser.validation.reemail"));
        }

        // username
        if (!StringUtils.hasText(systemUser.getUsername())) {
            addActionMessage(getText("systemUser.validation.username"));
        } else {
            if (systemUserRemote.exists(systemUser.getUsername())) {
                addActionMessage(getText("systemUser.input.repeatedLogin"));
            }
        }

        // password
        if (!StringUtils.hasText(systemUser.getPassword())) {
            addActionMessage(getText("systemUser.validation.password"));
        }
        if (!StringUtils.hasText(retypePassword)) {
            addActionMessage(getText("systemUser.validation.repassword"));
        } else if (!retypePassword.equals(systemUser.getPassword())) {
            addActionMessage(getText("systemUser.validation.repassword"));
        }
    }

    /**
     * Sets the value of systemUser variable
     * 
     * @param systemUser
     */
    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    /**
     * Retrieves the value of systemUser variable
     * 
     * @return systemUser
     */
    public SystemUser getSystemUser() {
        return systemUser;
    }

    /**
     * Retrieves the value of systemUser variable
     * 
     * @return systemUser
     */
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Retrives the value of systemUserRemote variable
     * 
     * @return systemUserRemote
     */
    public SystemUserRemote getSystemUserRemote() {
        return systemUserRemote;
    }

    /**
     *Sets the value of systemUserRemote variable
     * 
     * @param systemUserRemote
     */
    public void setSystemUserRemote(SystemUserRemote systemUserRemote) {
        this.systemUserRemote = systemUserRemote;
    }

    /**
     * Retrieves the value of systemUserList variable
     * 
     * @return systemUserList
     */
    public List<SystemUser> getSystemUserList() {
        return systemUserList;
    }

    /**
     *Sets the value of systemUserList variable
     * 
     * @param systemUserList
     */
    public void setSystemUserList(List<SystemUser> systemUserList) {
        this.systemUserList = systemUserList;
    }

    /**
     * Retrieves the value of capchar
     * 
     * @return captchaValue
     */
    public String getCaptchaValue() {
        return captchaValue;
    }

    /**
     * Sets the value of capchar
     * 
     * @param captchaValue
     */
    public void setCaptchaValue(String captchaValue) {
        this.captchaValue = captchaValue;
    }

    /**
     * Retrieves the retyped password
     * 
     * @return retypePassword
     */
    public String getRetypePassword() {
        return retypePassword;
    }

    /**
     * Sets the retyped password
     * 
     * @param retypePassword
     */
    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    /**
     * Retrieves the retyped email
     * 
     * @return retypeEmail
     */
    public String getRetypeEmail() {
        return retypeEmail;
    }

    /**
     * Sets the retyped email
     * 
     * @param retypeEmail
     */
    public void setRetypeEmail(String retypeEmail) {
        this.retypeEmail = retypeEmail;
    }

    /**
     * Retrieves the list of country
     * 
     * @return countryList
     */
    public Map<Integer, String> getCountryList() {
        return countryList;
    }

    /**
     * Sets the list of country
     * 
     * @param countryList
     */
    public void setCountryList(Map<Integer, String> countryList) {
        if (this.countryList == null) {
            if ((countryList == null) || (countryList.isEmpty())) {

            } else {
                this.countryList = countryList;
            }
        }
    }

    /**
     * Retrieves a list of ethnicity
     * 
     * @return ethnicityList
     */
    public Map<Integer, String> getEthnicityList() {
        return ethnicityList;
    }

    /**
     * Sets a list of ethnicity
     * 
     * @param ethnicityList
     */
    public void setEthnicityList(Map<Integer, String> ethnicityList) {
        this.ethnicityList = ethnicityList;
    }

    // /**
    // * Retrieves a list of language
    // * @return languageInternationalizationList
    // */
    // public List<Language> getLanguageList() {
    // return languageInternationalizationList;
    // }
    //
    // /**
    // * Sets a list of language
    // * @param languageInternationalizationList
    // */
    // public void setLanguageList(List<Language> languageList) {
    // this.languageList = languageList;
    // }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public int getScorePassword() {
        return scorePassword;
    }

    public void setScorePassword(int scorePassword) {
        this.scorePassword = scorePassword;
    }

    // personal information
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getHonorific() {
        return honorific;
    }

    public void setHonorific(String honorific) {
        this.honorific = honorific;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    // public LanguageRemote getLanguageRemote() {
    // return languageRemote;
    // }
    //
    // public void setLanguageRemote(LanguageRemote languageRemote) {
    // this.languageRemote = languageRemote;
    // }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Address getInAddress() {
        return inAddress;
    }

    public void setInAddress(Address inAddress) {
        this.inAddress = inAddress;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public ProfileRemote getProfileRemote() {
        return profileRemote;
    }

    public void setProfileRemote(ProfileRemote profileRemote) {
        this.profileRemote = profileRemote;
    }

    public AddressRemote getAddressRemote() {
        return addressRemote;
    }

    public void setAddressRemote(AddressRemote addressRemote) {
        this.addressRemote = addressRemote;
    }

    public PhoneRemote getPhoneRemote() {
        return phoneRemote;
    }

    public void setPhoneRemote(PhoneRemote phoneRemote) {
        this.phoneRemote = phoneRemote;
    }

    public Address[] getAddress() {
        return address;
    }

    public void setAddress(Address[] address) {
        this.address = address;
    }

    public static String getChars() {
        return chars;
    }

    public static void setChars(String chars) {
        SystemUserAction.chars = chars;
    }

    public int getLocatioType() {
        return locatioType;
    }

    public void setLocatioType(int locatioType) {
        this.locatioType = locatioType;
    }

    public Phone[] getPhone() {
        return phone;
    }

    public void setPhone(Phone[] phone) {
        this.phone = phone;
    }

    public static Random getR() {
        return r;
    }

    public static void setR(Random r) {
        SystemUserAction.r = r;
    }

    private Date generateDate(String dateStr) {
        String tokens[] = dateStr.split("/");
        int day = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]) - 1;
        int year = Integer.parseInt(tokens[2]);
        Date date = new Date();
        date.setDate(day);
        date.setMonth(month);
        date.setYear(year);
        return date;
    }

    public void setGenderList(Map<String, String> genderList) {
        this.genderList = genderList;
    }

    public Map<Boolean, String> getDisabilitiesList() {
        return disabilitiesList;
    }

    public void setDisabilitiesList(Map<Boolean, String> disabilitiesList) {
        this.disabilitiesList = disabilitiesList;
    }

    public Map<String, String> getGenderList() {
        return this.genderList;
    }

    public String getDateFormat() {
        return this.dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;

    }

    public Mailer getMailer() {
        return mailer;
    }

    public void setMailer(Mailer mailer) {
        this.mailer = mailer;
    }
}