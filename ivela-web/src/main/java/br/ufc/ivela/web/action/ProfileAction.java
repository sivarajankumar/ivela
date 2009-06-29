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
# File: ProfileAction.java                                                                  #
# Document: Profile Action                                                                  # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - Leonardo Moreira                  - XXXXXX - Initial Version                #
# 22-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Fixes                  #
#############################################################################################
*/

package br.ufc.ivela.web.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.ContentPackageUtils;
import br.ufc.ivela.commons.model.Address;
import br.ufc.ivela.commons.model.Phone;
import br.ufc.ivela.commons.model.Profile;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.AddressRemote;
import br.ufc.ivela.ejb.interfaces.PhoneRemote;
import br.ufc.ivela.ejb.interfaces.ProfileRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import br.ufc.ivela.interceptors.interfaces.ProfileDataProvider;
import br.ufc.ivela.services.PropertiesUtil;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class ProfileAction extends GenericAction implements ProfileDataProvider {

    private ProfileRemote profileRemote;
    private Profile profile;    
    private SystemUserRemote systemUserRemote;
    private SystemUser systemUser;
    private List<Profile> profileList;    
    private AddressRemote addressRemote;
    private PhoneRemote phoneRemote;
    private Map<String, String> stateList;
    private Map<Integer, String> ethnicityList;
    private Map<String, String> genderList;
    private Map<Boolean, String> disabilitiesList;
//    private List<Language> languageList;
    private Address[] address;
    private Phone[] phone;
    private boolean sucess = false;
    private java.io.File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String oldPhoto;
    private Map<Integer, String> countryList;    
    private List<Phone> listPhones;    
    private Address inAddress;
    private String path = Constants.FILE_UPLOAD_PATH + "profiles/";
    private XStream xStream = new XStream(new JettisonMappedXmlDriver());
    private InputStream inputStream;
    private String dateFormat;
    
    public void prepare() throws Exception {        
        // retrieves the country list
        
    }
    
    /**
     * Add a new profile
     * @return a list of profiles
     */
    public String add() {
        profileRemote.add(profile);
        return list();
    }

    /**
     * Remove a profile 
     * @return a list of profiles
     */
    public String remove() {
        profileRemote.remove(profile.getId());
        return list();
    }

    /**
     * Sets the variables to be used on the input form
     * @return
     */
    public String input() {
        return INPUT;
    }

    /**
     * List all profile
     * @return
     */
    public String list() {
        List<Profile> profileList = profileRemote.getAll();
        setProfileList(profileList);
        return "list";
    }

    /**
     * Edit a profile
     * @return edit
     */
    public String edit() {
        systemUser = systemUserRemote.get(getAuthenticatedUser().getId());

        if (systemUser.getProfileId() != null) {
            profile = profileRemote.get(systemUser.getProfileId());
            inAddress = addressRemote.getByProfile(profile.getId());
       
            listPhones = phoneRemote.getByProfile(profile.getId());
        } else {
            profile = new Profile();            
            Long profileId = profileRemote.add(profile);

            profile.setId(profileId);
            systemUser.setProfileId(profileId);

            systemUserRemote.update(systemUser);
        }

        if (profile != null) {
            oldPhoto = profile.getPhoto();
            logger.log("Setting old photo: " + oldPhoto);
            this.getSession().put("OLD_PHOTO", oldPhoto);
            setOldPhoto(oldPhoto);

            return "edit";
        } else {
            return input();
        }
    }

    /**
     * Update a profile
     * @return a string
     */
    public String update() {
        java.io.File fileIo = upload;
        boolean resul = true;

        logger.log("oldPhoto: ->" + oldPhoto + "<-");
        logger.log("upload: ->" + upload + "<-");

        String oldFromSession = (String) this.getSession().get("OLD_PHOTO");
        if (upload == null || upload.equals(" ")) {
            profile.setPhoto(oldFromSession);
            this.getSession().remove("OLD_PHOTO");
        } else {
            if (!new File(path).exists()) {
                ContentPackageUtils.createDir(new File(Constants.FILE_UPLOAD_PATH), "profiles");
            }

            if (!new File(path + profile.getId()).exists()) {
                ContentPackageUtils.createDir(new File(path), profile.getId().toString());
            }

            profile.setPhoto(path + profile.getId() + "/" + uploadFileName);
            logger.log("file: " + profile.getPhoto());
            
            // verify if the file is an image
            logger.log(uploadContentType);
            if(uploadContentType.startsWith("image")){
                if(!profileRemote.savePhoto(profile, fileIo)){
                    addActionError(getText("profile.upload.error"));
                    profile.setPhoto(oldFromSession);
                    resul = false;
                }
            }else{
                addActionError(getText("profile.upload.invalidType"));
                profile.setPhoto(oldFromSession);
                resul = false;
            }    
        }
        
        resul = (resul) ? profileRemote.edit(profile) : false;

        resul = (resul) ? addressRemote.update(inAddress) : false;

        resul = (resul) ? phoneRemote.update(listPhones.get(0)) : false;

        resul = (resul) ? phoneRemote.update(listPhones.get(1)) : false;

        setSucess(resul);
        
        addActionMessage(NONE);

        return edit();
    }

    public String getStatesByCountry() {
        stateList = PropertiesUtil.getPropertiesUtil().getStates(
                this.inAddress.getCountry());                      
        xStream.alias("state", String.class);
        xStream.alias("list", List.class);
        if (stateList.containsKey("")) {
            stateList.put("", getText("profile.state", stateList.get("")));
        }
        
        // Returns a List where odd numbers are the values for the select
        // and even are the text to show for the user.
        
        List<String> stateKeyValueList = new ArrayList<String>(
                stateList.size() * 2);
        for (Entry<String, String> entry : stateList.entrySet()) {
            stateKeyValueList.add(entry.getKey());
            stateKeyValueList.add(entry.getValue());
        }
        
        String json = xStream.toXML(stateKeyValueList);
        logger.log(json);
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public XStream getXStream() {
        return xStream;
    }

    public void setXStream(XStream xStream) {
        this.xStream = xStream;
    }

    /**
     * Sets the value of profile variables
     * @param profile
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Retrieves the value of profile variable
     * @return profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Retrieves the value of profileRemote variable
     * @return profileRemote
     */
    public ProfileRemote getProfileRemote() {
        return profileRemote;
    }

    /**
     *Sets the value of profileRemote variable
     * @param profileRemote
     */
    public void setProfileRemote(ProfileRemote profileRemote) {
        this.profileRemote = profileRemote;
    }

    public List<Phone> getListPhones() {
        return listPhones;
    }

    public void setListPhones(List<Phone> listPhones) {
        this.listPhones = listPhones;
    }

    public PhoneRemote getPhoneRemote() {
        return phoneRemote;
    }

    public void setPhoneRemote(PhoneRemote phoneRemote) {
        this.phoneRemote = phoneRemote;
    }

    /**
     * Retrieves the value of profileList variable
     * @return profileList
     */
    public List<Profile> getProfileList() {
        return profileList;
    }

    public Address[] getAddress() {
        return address;
    }

    public void setAddress(Address[] address) {
        this.address = address;
    }

    public Map<Integer, String> getEthnicityList() {
        return ethnicityList;
    }

    public void setEthnicityList(Map<Integer, String> ethnicityList) {
        this.ethnicityList = ethnicityList;
    }

//    public List<Language> getLanguageList() {
//        return languageList;
//    }

//    public void setLanguageList(List<Language> languageList) {
//        this.languageList = languageList;
//    }

    public Phone[] getPhone() {
        return phone;
    }

    public void setPhone(Phone[] phone) {
        this.phone = phone;
    }

    /**
     * Sets the value of profileList variable
     * @param profileList
     */
    public void setProfileList(List<Profile> profileList) {
        this.profileList = profileList;
    }

    /**
     * Retrieves the result 
     * @return
     */
    public boolean isSucess() {
        return sucess;
    }

    /**
     * Sets a result
     * @param sucess
     */
    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public AddressRemote getAddressRemote() {
        return addressRemote;
    }

    public void setAddressRemote(AddressRemote addressRemote) {
        this.addressRemote = addressRemote;
    }

    public Address getInAddress() {
        return inAddress;
    }

    public void setInAddress(Address inAddress) {
        this.inAddress = inAddress;
    }

    /**
     * Retrieves the path of the photo
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path of the photo
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Retrieves the upload file
     * @return upload
     */
    public File getUpload() {
        return upload;
    }

    /**
     * Sets the upload file
     * @param upload
     */
    public void setUpload(File upload) {
        this.upload = upload;
    }

    /**
     * Retrieves the name of the upload file
     * @return uploadFileName
     */
    public String getUploadFileName() {
        return uploadFileName;
    }

    /**
     * Sets the name of upload file
     * @param uploadFileName
     */
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    /**
     * Retrieves the old Photo
     * @return oldPhoto
     */
    public String getOldPhoto() {
        return oldPhoto;
    }

    /**
     * Sets the old Photo
     * @param oldPhoto
     */
    public void setOldPhoto(String oldPhoto) {
        this.oldPhoto = oldPhoto;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public SystemUserRemote getSystemUserRemote() {
        return systemUserRemote;
    }

    public void setSystemUserRemote(SystemUserRemote systemUserRemote) {
        this.systemUserRemote = systemUserRemote;
    }

    public Map<Integer, String> getCountryList() {        
        return countryList;
    }

    public void setCountryList(Map<Integer, String> countryList) {
        if (this.countryList == null) {
            if ((countryList == null)||(countryList.isEmpty())) {            
                
            } else {
                this.countryList = countryList;
            }
        }
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
    
    /**
     * Retrieves the Map of Gender based on the Current Set Language;
     * 
     * @return a Map instance with the genders for the current language.
     */
    public Map<String,String> getGenderList() {
        
        return genderList;
    }

    public void setGenderList(Map<String, String> genderList) {
        this.genderList = genderList;
        
    }
    
    public Map<String, String> getStateList() {
        if (null == stateList) {
            if (inAddress != null) {
            stateList = PropertiesUtil.getPropertiesUtil().getStates(
                    inAddress.getCountry());
            } else {
                stateList = PropertiesUtil.getPropertiesUtil().getStates(1);
            }
        }
        
        return stateList;
    }

    public Map<Boolean, String> getDisabilitiesList() {        
       return disabilitiesList;
    }
    
    public void setDisabilitiesList(Map<Boolean, String> disabilitiesList) {
        this.disabilitiesList = disabilitiesList;
    }

    public String getDateFormat() {
       return this.dateFormat; 
    }
    
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        
    }
}
