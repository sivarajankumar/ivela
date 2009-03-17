/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.mail.MailSender;
import br.ufc.ivela.commons.model.Address;
import br.ufc.ivela.commons.model.Authentication;
import br.ufc.ivela.commons.model.Country;
import br.ufc.ivela.commons.model.Ethnicity;
import br.ufc.ivela.commons.model.Honorific;
import br.ufc.ivela.commons.model.LanguageInternationalization;
import br.ufc.ivela.commons.model.LocationType;
import br.ufc.ivela.commons.model.Phone;
import br.ufc.ivela.commons.model.Profile;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.util.Validators;
import br.ufc.ivela.ejb.interfaces.AddressRemote;
import br.ufc.ivela.ejb.interfaces.CalendarRemote;
import br.ufc.ivela.ejb.interfaces.CountryRemote;
import br.ufc.ivela.ejb.interfaces.EthnicityRemote;
import br.ufc.ivela.ejb.interfaces.HonorificRemote;
import br.ufc.ivela.ejb.interfaces.LanguageInternationalizationRemote;
import br.ufc.ivela.ejb.interfaces.LanguageRemote;
import br.ufc.ivela.ejb.interfaces.LocationTypeRemote;
import br.ufc.ivela.ejb.interfaces.PhoneRemote;
import br.ufc.ivela.ejb.interfaces.ProfileRemote;
import br.ufc.ivela.ejb.interfaces.StateRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import com.opensymphony.xwork2.Preparable;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Controller that offers the logic of the process of creation users of the portal
 */
public class SystemUserAction extends GenericAction implements Preparable {

    private CalendarRemote calendarRemote;
    private SystemUserRemote systemUserRemote;
    private SystemUser systemUser;
    private ProfileRemote profileRemote;
    private PhoneRemote phoneRemote;
    private Profile profile;
    private Address[] address;
    private Phone[] phone;
    private HonorificRemote honorificRemote;
    private LocationTypeRemote locationTypeRemote;
    private CountryRemote countryRemote;
    private LanguageInternationalizationRemote languageInternationalizationRemote;
    private EthnicityRemote ethnicityRemote;
    private LanguageRemote languageRemote;
    private AddressRemote addressRemote;
    private StateRemote stateRemote;
    private List<SystemUser> systemUserList;
    private List<Honorific> honorificList;
    private List<LocationType> locationTypeList;
    private List<Country> countryList;
    private List<LanguageInternationalization> languageInternationalizationList;
    private List<Ethnicity> ethnicityList;
    private String captchaValue;
    private String retypePassword;
    private String retypeEmail;
    private String username;
    private String email;
    private InputStream inputStream;
    private String password;
    private String newPassword;
    private int locatioType;
    private int scorePassword;    //personal
    private String honorific;
    private String birthDate;    //language and ethnicity
    private String language;
    private String ethnicity;    //phone
    private String mainPhone;
    private String mobile;    //location
    private Address inAddress;
    private String country;

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
            if (!systemUserRemote.encrypt(password).equals(getAuthenticatedUser().getPassword())) {
                addActionError(getText("systemUser.password.error"));
            } else {
                if (!newPassword.equals(retypePassword)) {
                    addActionError(getText("systemUser.password.match"));
                } else {
                    setSystemUser(systemUserRemote.get(getAuthenticatedUser().getId()));
                    getSystemUser().setPassword(systemUserRemote.encrypt(newPassword));
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
        List<SystemUser> systemUserList = systemUserRemote.getByUsername(username);
        if (systemUserList != null && systemUserList.size() == 1 && systemUserList.get(0).getEmail().equalsIgnoreCase(email)) {
            SystemUser su = systemUserList.get(0);
            String pwd = generatePassword();
            su.setPassword(systemUserRemote.encrypt(pwd));
            result = systemUserRemote.update(su);
            if (result) {
                String body = "Your new password is: <b>" + pwd + "</b>";

                HttpServletRequest request = ServletActionContext.getRequest();
                String url = "http://" + request.getServerName() + ":" + request.getServerPort() + Constants.WEB_PATH;
                if (!url.endsWith("/")) {
                    url += "/";
                }

                System.out.println("body: " + body);
                System.out.println("url: " + url);
                System.out.println("server: " + request.getRequestURL().toString());

                MailSender.send(new String[]{su.getEmail()}, "[ivela] Request password", getLayoutEmail(body, url));
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

    public String getLayoutEmail(String body, String url) {
        System.out.println("entrei");
        
        Properties properties = new Properties();
            try {
                properties.load(new FileInputStream("layout.properties"));
                return properties.getProperty("layout.email").replaceAll("[--body--]", body).replaceAll("[--linkSite--]", url);
            } catch (IOException e) {
                return body;
            }
        
        
        /*
        try {
            



        System.out.println("antes de criar");
        ResourceBundle layout = ResourceBundle.getBundle("resources/layout");
        System.out.println("depois de criar");
        if(layout == null){
        System.out.println("layout eh null");
        }
        return layout.getString("layout.email").replaceAll("[--body--]", body).replaceAll("[--linkSite--]", url);
         
        } catch (NullPointerException e) {
            System.out.println("Error while retrieving layout property file");
            e.printStackTrace();
            return body;
        }*/
    }

    public CalendarRemote getCalendarRemote() {
        return calendarRemote;
    }

    public void setCalendarRemote(CalendarRemote calendarRemote) {
        this.calendarRemote = calendarRemote;
    }

    /**
     * Add a new system user, perform a validation
     * if hasn't error add a new system user
     * if has return error
     */
    public String add() {

        if (systemUser == null) {
            return INPUT;
        }

        performValidationAdd();

        if (!hasActionMessages()) {

//            if (systemUser.getProfile() == null) {
//                Profile profile = new Profile();
//                systemUser.setProfile(profile);
//            }
//
//            systemUser.getProfile().setSystemUser(systemUser);
//
//            
//            systemUser.setSocialNumber("1");
//            systemUser.getProfile().setEthnicity(1);
//            systemUser.getProfile().setLanguage(1L);


            systemUser.setEnabled(true);
            String password = systemUser.getPassword();
            password = systemUserRemote.encrypt(password);
            systemUser.setPassword(password);
            systemUser.setAuthentication(new Authentication(Constants.ROLE_USER));



            //*******PROFILE*******
            //---personal info
            if (honorific != null && !honorific.equals("")) {

                Integer honoroficId = new Integer(honorific);
                Honorific honor = honorificList.get(honoroficId.intValue() - 1);

                profile.setHonorific(honor);
            //System.out.println("honorific: " + profile.getHonorific().getTitle());
            }

            if (birthDate != null && !birthDate.equals("")) {
                Date date = this.generateDate(birthDate);
                profile.setBirthDate(date);
            }


            //System.out.println("birthDate: " + profile.getBirthDate());


            //language and enth
            if (language != null && !language.equals("")) {
                Integer languageId = new Integer(language);
                LanguageInternationalization li = languageInternationalizationList.get(languageId - 1);
                profile.setLanguage(li);
            //System.out.println("language: " + profile.getLanguage().getLanguage().getName());
            }

            if (ethnicity != null && !ethnicity.equals("")) {
                Integer ethId = new Integer(ethnicity);
                Ethnicity ethnicityObj = ethnicityList.get(ethId - 1);
                profile.setEthnicity(ethnicityObj);
            //System.out.println("language: " + profile.getEthnicity().getName());
            }


            //SAVING PROFILE, GENERATING ID
            Long profileId = null;
            if (profile != null) {
                if (systemUser.getSocialNumber() != null) {
                    profile.setSocialNumber(systemUser.getSocialNumber());
                }
                profileId = profileRemote.add(profile);
                profile = profileRemote.get(profileId);


                systemUser.setProfileId(profileId);

            }
            //END SAVING PROFILE

            //--location

            if (profileId != null) {

                if (inAddress.getState() == null) {
                    inAddress.setState(stateRemote.get(1L));
                }
                inAddress.setProfileId(profile.getId());
                inAddress.setProfile(profile);
                addressRemote.add(inAddress);


//               if(country!=null && !country.equals("")){
//                 
//                Integer coId = new Integer(country);
//                Country countryObj = countryList.get(coId-1);
//                //System.out.println("country: " + countryObj.getName());
//                if(inAddress!=null && inAddress.getState()!=null){
//                     
//                    State state = inAddress.getState();
//                    state.setCountry(countryObj);
//                    Long stateId = stateRemote.add(state);
//                    state.setId(stateId);
//                    //Set setOfAddress = new HashSet<Address>();
//                    
//                    Long addressId = new Long(addressRemote.lastId()+1);
//                    //System.out.println("ADDRESSID: " + addressId);
//                    inAddress.setId(addressId);
//                    inAddress.setLocation(inAddress.getAdditionalInformation());
//                    inAddress.setProfileId(profileId);
//                    logger.log("dsfsdf"+inAddress.getLocationType().getId());
//                    inAddress.setLocationType(locationTypeRemote.get(inAddress.getLocationType().getId()));
//                    addressRemote.add(inAddress);
//                    //setOfAddress.add(inAddress);
//                    //profile.setAddresses(setOfAddress);
//                    
//                }
//            
//            }
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

            //SAVING USER!
            Long id = systemUserRemote.add(systemUser);
            systemUser = systemUserRemote.get(id);
            //--- create the user in webical application
            HttpServletRequest request = ServletActionContext.getRequest();
            logger.log(request.getServerName());
            logger.log(String.valueOf(request.getServerPort()));
            calendarRemote.addInfo(request.getServerName(), String.valueOf(request.getServerPort()), systemUser.getUsername());
            //System.out.println("#### usuario criado");
            // boolean result = calendarRemote.addInfo("200.17.41.215", String.valueOf(8080), systemUser.getUsername());

            //*****HISTORY****
            addHistory("history.createuser.title", "history.createuser.description", systemUser, systemUser.getUsername());
            return "login";

        } else {
            return INPUT;
        }
    }

    /**
     * Edit a system user
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
        //Map session = ActionContext.getContext().getSession();

        //String sessionValue = (String) session.get(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY);

        // captcha
//        if (!sessionValue.equals(captchaValue)) {
//            addActionMessage(getText("systemUser.validation.captcha"));
//        }

        // email
        if (scorePassword < 24) {
            addActionMessage(getText("systemUser.validation.scorePassword"));
        } else if (!StringUtils.hasText(systemUser.getEmail())) {
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
     * @param systemUser
     */
    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    /**
     * Retrieves the value of systemUser variable
     * @return systemUser
     */
    public SystemUser getSystemUser() {
        return systemUser;
    }

    /**
     * Retrieves the value of systemUser variable
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
     * @param systemUserRemote
     */
    public void setSystemUserRemote(SystemUserRemote systemUserRemote) {
        this.systemUserRemote = systemUserRemote;
    }

    /**
     * Retrieves the value of systemUserRemote variable
     * 
     * @return systemUserRemote
     */
    public CountryRemote getCountryRemote() {
        return countryRemote;
    }

    /**
     *Sets the value of systemUserRemote variable
     * @param systemUserRemote
     */
    public void setCountryRemote(CountryRemote countryRemote) {
        this.countryRemote = countryRemote;
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
     * Retrieves the value of systemUserRemote variable
     * @return systemUserRemote
     */
    public LocationTypeRemote getLocationTypeRemote() {
        return locationTypeRemote;
    }

    /**
     *Sets the value of systemUserRemote variable
     * @param systemUserRemote
     */
    public void setLocationTypeRemote(LocationTypeRemote locationTypeRemote) {
        this.locationTypeRemote = locationTypeRemote;
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
     * @param systemUserList
     */
    public void setSystemUserList(List<SystemUser> systemUserList) {
        this.systemUserList = systemUserList;
    }

    /**
     * Retrieves the value of capchar
     * @return captchaValue
     */
    public String getCaptchaValue() {
        return captchaValue;
    }

    /**Sets the value of capchar
     * 
     * @param captchaValue
     */
    public void setCaptchaValue(String captchaValue) {
        this.captchaValue = captchaValue;
    }

    /**
     * Retrieves the retyped password
     * @return retypePassword
     */
    public String getRetypePassword() {
        return retypePassword;
    }

    /**
     * Sets the retyped password
     * @param retypePassword
     */
    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    /**
     * Retrieves the retyped email
     * @return retypeEmail
     */
    public String getRetypeEmail() {
        return retypeEmail;
    }

    /**
     * Sets the retyped email
     * @param retypeEmail
     */
    public void setRetypeEmail(String retypeEmail) {
        this.retypeEmail = retypeEmail;
    }

    /**
     * Retrieves the list of country
     * @return countryList
     */
    public List<Country> getCountryList() {
        return countryList;
    }

    /**
     * Sets the list of country
     * @param countryList
     */
    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    /**
     * Retrieves a list of ethnicity 
     * @return ethnicityList
     */
    public List<Ethnicity> getEthnicityList() {
        return ethnicityList;
    }

    /**
     * Sets a list of ethnicity 
     * @param ethnicityList
     */
    public void setEthnicityList(List<Ethnicity> ethnicityList) {
        this.ethnicityList = ethnicityList;
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
     * Retrieves a list of language
     * @return languageInternationalizationList
     */
    public List<LanguageInternationalization> getLanguageInternationalizationList() {
        return languageInternationalizationList;
    }

    /**
     * Sets a list of language
     * @param languageInternationalizationList
     */
    public void setLanguageInternationalizationList(List<LanguageInternationalization> languageInternationalizationList) {
        this.languageInternationalizationList = languageInternationalizationList;
    }

    /**
     * Retrieves the list of the type of location
     * @return locationTypeList
     */
    public List<LocationType> getLocationTypeList() {
        return locationTypeList;
    }

    /**
     * Sets the list of the type of location
     * @param locationTypeList
     */
    public void setLocationTypeList(List<LocationType> locationTypeList) {
        this.locationTypeList = locationTypeList;
    }

    public void prepare() throws Exception {
        // retrieves the honorific list
        honorificList = honorificRemote.getAll();
        // retrieves the locationType list
        locationTypeList = locationTypeRemote.getAll();
        // retrieves the country list
        countryList = countryRemote.getAll();
        // retrieves the language internationalization list
        languageInternationalizationList = languageInternationalizationRemote.getAll();
        // retrieves the ethnicity list
        ethnicityList = ethnicityRemote.getAll();
    }

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

    //personal information
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

    public LanguageRemote getLanguageRemote() {
        return languageRemote;
    }

    public void setLanguageRemote(LanguageRemote languageRemote) {
        this.languageRemote = languageRemote;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
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

    public StateRemote getStateRemote() {
        return stateRemote;
    }

    public void setStateRemote(StateRemote stateRemote) {
        this.stateRemote = stateRemote;
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
}