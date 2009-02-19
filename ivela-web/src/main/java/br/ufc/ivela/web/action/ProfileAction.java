/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.ContentPackageUtils;
import br.ufc.ivela.commons.model.Address;
import br.ufc.ivela.commons.model.Country;
import br.ufc.ivela.commons.model.Ethnicity;
import br.ufc.ivela.commons.model.Honorific;
import br.ufc.ivela.commons.model.LanguageInternationalization;
import br.ufc.ivela.commons.model.LocationType;
import br.ufc.ivela.commons.model.Phone;
import br.ufc.ivela.commons.model.Profile;
import br.ufc.ivela.commons.model.State;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.AddressRemote;
import br.ufc.ivela.ejb.interfaces.CountryRemote;
import br.ufc.ivela.ejb.interfaces.EthnicityRemote;
import br.ufc.ivela.ejb.interfaces.HonorificRemote;
import br.ufc.ivela.ejb.interfaces.LanguageInternationalizationRemote;
import br.ufc.ivela.ejb.interfaces.LocationTypeRemote;
import br.ufc.ivela.ejb.interfaces.PhoneRemote;
import br.ufc.ivela.ejb.interfaces.ProfileRemote;
import br.ufc.ivela.ejb.interfaces.StateRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import com.opensymphony.xwork2.Preparable;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author Leonardo Moreira
 */
public class ProfileAction extends GenericAction implements Preparable {

    private ProfileRemote profileRemote;
    private Profile profile;
    private Country country;
    private SystemUserRemote systemUserRemote;
    private SystemUser systemUser;
    private List<Profile> profileList;
    private HonorificRemote honorificRemote;
    private AddressRemote addressRemote;
    private PhoneRemote phoneRemote;
    private StateRemote stateRemote;
    private List<Honorific> honorificList;
    private List<LanguageInternationalization> languageInternationalizationList;
    private LanguageInternationalizationRemote languageInternationalizationRemote;
    private EthnicityRemote ethnicityRemote;
    private List<Ethnicity> ethnicityList;
    private Address[] address;
    private Phone[] phone;
    private boolean sucess = false;
    private java.io.File upload;
    private String uploadFileName;
    private String oldPhoto;
    private List<Country> countryList;
    private List<LocationType> locationTypeList;
    private List<Phone> listPhones;
    private LocationTypeRemote locationTypeRemote;
    private CountryRemote countryRemote;
    private Address inAddress;
    private String path = Constants.FILE_UPLOAD_PATH + "profiles/";
    private XStream xStream = new XStream(new JettisonMappedXmlDriver());
    private InputStream inputStream;

    public void prepare() throws Exception {
        //retrieves the honorific list
        honorificList = honorificRemote.getAll();

        // retrieves the language internationalization list
        languageInternationalizationList = languageInternationalizationRemote.getAll();

        // retrieves the ethnicity list
        ethnicityList = ethnicityRemote.getAll();


        // retrieves the locationType list
        locationTypeList = locationTypeRemote.getAll();
        // retrieves the country list
        countryList = countryRemote.getAll();

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
            if (inAddress == null) {
                inAddress = new Address();
                inAddress.setState(stateRemote.get(1L));
            }
            inAddress.getState().getCountry().setStates(stateRemote.getByCountry(inAddress.getState().getCountry().getId()));
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

        if (upload == null || upload.equals(" ")) {
            String oldFromSession = (String) this.getSession().get("OLD_PHOTO");
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
            logger.log("file" + profile.getPhoto());

            profileRemote.savePhoto(profile, fileIo);
        }
        resul = resul && profileRemote.edit(profile);

        resul = resul && addressRemote.update(inAddress);

        resul = resul && phoneRemote.update(listPhones.get(0));

        resul = resul && phoneRemote.update(listPhones.get(1));

        setSucess(resul);

        return edit();
    }

    public String getStatesByCountry() {

        List<State> l = stateRemote.getByCountry(country.getId());
        logger.log(l.toString());
        xStream.alias("state", State.class);
        xStream.alias("list", List.class);
        xStream.omitField(Country.class, "country");
        String json = xStream.toXML(l);
        logger.log(json);
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public StateRemote getStateRemote() {
        return stateRemote;
    }

    public void setStateRemote(StateRemote stateRemote) {
        this.stateRemote = stateRemote;
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

    public List<Ethnicity> getEthnicityList() {
        return ethnicityList;
    }

    public void setEthnicityList(List<Ethnicity> ethnicityList) {
        this.ethnicityList = ethnicityList;
    }

    public List<LanguageInternationalization> getLanguageInternationalizationList() {
        return languageInternationalizationList;
    }

    public void setLanguageInternationalizationList(List<LanguageInternationalization> languageInternationalizationList) {
        this.languageInternationalizationList = languageInternationalizationList;
    }

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
     * Retrieves the value of systemUserRemote variable
     * 
     * @return systemUserRemote
     */
    public EthnicityRemote getEthnicityRemote() {
        return ethnicityRemote;
    }

    /**
     *Sets the value of systemUserRemote variable
     * @param systemUserRemote
     */
    public void setEthnicityRemote(EthnicityRemote ethnicityRemote) {
        this.ethnicityRemote = ethnicityRemote;
    }

    /**
     * Retrieves the value of honorificRemote variable
     * 
     * @return honorificRemote
     */
    public HonorificRemote getHonorificRemote() {
        return honorificRemote;
    }

    /**
     *Sets the value of honorificRemote variable
     * @param honorificRemote
     */
    public void setHonorificRemote(HonorificRemote honorificRemote) {
        this.honorificRemote = honorificRemote;
    }

    /**
     * Retrieves the value of systemUserRemote variable
     * 
     * @return systemUserRemote
     */
    public LanguageInternationalizationRemote getLanguageInternationalizationRemote() {
        return languageInternationalizationRemote;
    }

    /**
     *Sets the value of systemUserRemote variable
     * @param systemUserRemote
     */
    public void setLanguageInternationalizationRemote(LanguageInternationalizationRemote languageInternationalizationRemote) {
        this.languageInternationalizationRemote = languageInternationalizationRemote;
    }

    /**
     * Retrieves a list of honorific
     * @return honorificList
     */
    public List<Honorific> getHonorificList() {
        return honorificList;
    }

    /**
     * Sets a list of honorific
     * @param honorificList
     */
    public void setHonorificList(List<Honorific> honorificList) {
        this.honorificList = honorificList;
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

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public CountryRemote getCountryRemote() {
        return countryRemote;
    }

    public void setCountryRemote(CountryRemote countryRemote) {
        this.countryRemote = countryRemote;
    }

    public List<LocationType> getLocationTypeList() {
        return locationTypeList;
    }

    public void setLocationTypeList(List<LocationType> locationTypeList) {
        this.locationTypeList = locationTypeList;
    }

    public LocationTypeRemote getLocationTypeRemote() {
        return locationTypeRemote;
    }

    public void setLocationTypeRemote(LocationTypeRemote locationTypeRemote) {
        this.locationTypeRemote = locationTypeRemote;
    }
}
