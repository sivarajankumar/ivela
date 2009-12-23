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
# File: SystemUserService.java                                                                     #
# Document: Web Service for System User                                                            #
# Date        - Author(Company)                   - Issue# - Summary                               #
# 02-DEC-2009 - Otofuji (Eldorado)                - 000021 - Create Initial Ivela Services         #
##################################################################################################*/
package br.ufc.ivela.ejb.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.model.Address;
import br.ufc.ivela.commons.model.Authentication;
import br.ufc.ivela.commons.model.Profile;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.AddressRemote;
import br.ufc.ivela.ejb.interfaces.ProfileRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;

/**
 * Bean class for Ivela User Services (Register a new user)
 */
@Stateless
@WebService(name = "User", serviceName = "IvelaServices")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@TransactionAttribute(TransactionAttributeType.NEVER)
public class SystemUserService extends AbstractService {
            
    private static Log logger = LogFactory.getLog(SystemUserService.class);
    
    @EJB
    private SystemUserRemote systemUserRemote;        
        
    @EJB
    private ProfileRemote profileRemote;
    
    @EJB
    private AddressRemote addressRemote;
    
    @WebMethod    
    @WebResult(name = "status")    
    public String register(
            @WebParam(name = "userName") String username,
            @WebParam(name = "email") String email, 
            @WebParam(name = "password") String password,            
            @WebParam(name = "socialNumber") String socialNumber,
            @WebParam(name = "context") String context) {
        
            if ((username == null)||(username.isEmpty())) {
                return "Error: Invalid Username parameter";
            } else {
                if (systemUserRemote.exists(username)) {
                    return "Error: Login already exists";
                }
            }
            
            if ((email == null)||(email.isEmpty())) {
                return "Error: Invalid E-mail parameter";
            }

            if ((password == null)||(password.isEmpty())) {
                return "Error: Invalid Password parameter";    
            }
            
            if ((socialNumber == null)||(socialNumber.isEmpty())) {
                return "Error: Invalid Social Number parameter";    
            }
            
            SystemUser systemUser = new SystemUser();                                                
            systemUser.setUsername(username);
            systemUser.setEmail(email);
            systemUser.setPassword(systemUserRemote.encrypt(password));            
            systemUser.setSocialNumber(socialNumber);
            systemUser.setAuthentication(new Authentication(Constants.ROLE_USER));
            systemUser.setEnabled(true);
            try {                    
                systemUser = systemUserRemote.get(createNewUser(systemUser));
            } catch (Exception e) {
                logger.error("IvelaServices:User:Error: Creating user", e);
                return "Error: Creating user - " + e.getMessage() ;
            }
        
            try {
                addHistory("history.createuser.title", "history.createuser.description", systemUser, systemUser
                    .getUsername());
            } catch (Exception e) {
                logger.warn("IvelaServices:User:Warn: Creating History for new User ", e);
            }
            
        return "OK";
    }        
        
    @Transactional
    private Long createNewUser(SystemUser systemUser) {        
        Profile profile = new Profile();            
        
        Long profileId = null;
        if (profile != null) {
            if (systemUser.getSocialNumber() != null) {
                profile.setSocialNumber(systemUser.getSocialNumber());
            }            
            profileId = profileRemote.add(profile);
            profile = profileRemote.get(profileId);

            systemUser.setProfileId(profileId);
        }

        if (profileId != null) {
            Address inAddress = new Address();
            inAddress.setProfileId(profile.getId());
            inAddress.setProfile(profile);
            inAddress.setLocation("");
            inAddress.setCity("");
            inAddress.setCountry(new Integer(1));
            inAddress.setZipCode("");
            inAddress.setNumber(""); 
            addressRemote.add(inAddress);
        }
                
        return systemUserRemote.add(systemUser);
    }
}
