/*###########################################################################################
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
# File: GenericAction.java                                                                  #
# Document: Generic Action                                                                  #
# Date        - Author(Company)                    - Issue# - Summary                       #
# XX-XXX-XXX -  marcus                             - XXXXXX - Initial Version               #
# 19-JUN-2009 - Mileine Assato (Instituto Eldorado)- 000010 - Post owner username/role added#
# 26-JUN-2009 - otofuji (Instituto Eldorado)       - 000010 - General Fixes                 #
# 16-SEP-2009 - otofuji (Instituto Eldorado)       - 000016 - General Fixes                 #
#############################################################################################    
 */
package br.ufc.ivela.web.action;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.sf.ehcache.CacheManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import br.ufc.ivela.commons.Logger;
import br.ufc.ivela.commons.model.History;
import br.ufc.ivela.commons.model.HistoryParams;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.SystemUser.AUTHORITY;
import br.ufc.ivela.ejb.interfaces.HistoryParamsRemote;
import br.ufc.ivela.ejb.interfaces.HistoryRemote;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public abstract class GenericAction extends ActionSupport {

    protected static CacheManager cacheManager;
    
    static {
        // Create a CacheManager using a specific config file
        cacheManager = CacheManager.create(GenericAction.class
                .getResource("ehcache.xml"));        
    } 
    
    /** Serial Version UID */
    private static final long serialVersionUID = 1720091751419475881L;
    
    /** Authority for Administration Users */
    public static final String AUTHORITY_ADMIN = AUTHORITY.ROLE_ADMIN.getAuthority();
    /** Authority for Coordinator Users */
    public static final String AUTHORITY_COORD = AUTHORITY.ROLE_COORD.getAuthority();
    /** Authority for Professor Users */
    public static final String AUTHORITY_PROFESSOR = AUTHORITY.ROLE_PROFESSOR.getAuthority();
    /** Authority for Tutor Users */
    public static final String AUTHORITY_TUTOR = AUTHORITY.ROLE_TUTOR.getAuthority();
    /** Authority for Student Users */
    public static final String AUTHORITY_USER = AUTHORITY.ROLE_USER.getAuthority();
    /** Authority for EAD Student Users */
    public static final String AUTHORITY_EAD_USER = AUTHORITY.ROLE_EAD_USER.getAuthority();
    /** Authority for Non Authorized Users */
    public static final String AUTHORITY_NONE = AUTHORITY.ROLE_NONE.getAuthority();
    
    @Deprecated 
    protected Logger logger;
    
    /** Common Logging interface */
    protected Log log = LogFactory.getLog(this.getClass());
    
    private HistoryRemote historyRemote;
    private HistoryParamsRemote historyParamsRemote;        
    private SystemUser systemUser;       
    protected String authority;

    public HistoryParamsRemote getHistoryParamsRemote() {
        return historyParamsRemote;
    }

    public void setHistoryParamsRemote(HistoryParamsRemote historyParamsRemote) {
        this.historyParamsRemote = historyParamsRemote;
    }

    public HistoryRemote getHistoryRemote() {
        return historyRemote;
    }

    public void setHistoryRemote(HistoryRemote historyRemote) {
        this.historyRemote = historyRemote;
    }

    public SystemUser getAuthenticatedUser() {
        if (systemUser != null) {
            return systemUser;
        }
        
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        systemUser = null;
        if (obj != null) {
            if (obj instanceof UserDetails) {
            	systemUser	= (SystemUser)obj;
            	GrantedAuthority[] authorities = ((SystemUser) obj).getAuthorities();

                for (GrantedAuthority authority : authorities) {
                    
                    String authentication = authority.getAuthority();
                    
                    if (AUTHORITY.ROLE_ADMIN.hasAuthority(authentication) || 
                        AUTHORITY.ROLE_COORD.hasAuthority(authentication) || 
                        AUTHORITY.ROLE_PROFESSOR.hasAuthority(authentication) ||
                        AUTHORITY.ROLE_TUTOR.hasAuthority(authentication) ) {
                    	Map<String,String> session = ActionContext.getContext().getSession();
                		session.put("role",authentication);break;
                        
                    }
                    else {
                    	Map<String,String> session = ActionContext.getContext().getSession();
                    	session.put("role","student");
                    }
                }
            	Map<String,String> session = ActionContext.getContext().getSession();
            		session.put("username",systemUser.getUsername());
            	
            	return systemUser ;
            
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean isUserLogged() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (obj != null) {
            if (obj instanceof UserDetails) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * Retrieves the session
     * @return a session
     */
    public Map getSession() {
        return ActionContext.getContext().getSession();
    }

    public void addHistory(String title, String description, String... params) {
        if (historyRemote == null) {
            try {
                InitialContext initialContext = new InitialContext();
                java.lang.Object ejbRemoteRef = initialContext.lookup("HistoryBean#br.ufc.ivela.ejb.interfaces.HistoryRemote");
                historyRemote = (HistoryRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, HistoryRemote.class);
            } catch (NamingException e) {
                e.printStackTrace();
                historyRemote = null;
            }
        }

        List<History> historyList = historyRemote.getBySystemUser(getAuthenticatedUser().getId());
        boolean insert = true;
        for (History h : historyList) {
            if (h.getDescription().equalsIgnoreCase(description) && h.getTitle().equalsIgnoreCase(title)) {
                List<HistoryParams> historyParamsList = historyParamsRemote.getByHistory(h.getId());
                int equal = 0;
                for (int i = 0; i < params.length; i++) {
                    for (HistoryParams hp : historyParamsList) {
                        if (hp.getParam().equalsIgnoreCase(String.valueOf(i)) && hp.getValue().equalsIgnoreCase(params[i])) {
                            equal++;
                            break;
                        }
                    }
                }
                if (equal == params.length) {
                    insert = false;
                    break;
                }
            }
        }
        if (insert) {
            History history = new History();
            history.setSystemUser(getAuthenticatedUser());
            history.setDatetime(new Date());
            history.setTitle(title);
            history.setDescription(description);
            Long id = historyRemote.add(history);
            for (int i = 0; params != null && i < params.length; i++) {
                HistoryParams historyParams = new HistoryParams();
                historyParams.setHistoryId(id);
                historyParams.setParam(String.valueOf(i));
                historyParams.setValue(params[i]);
                historyParamsRemote.add(historyParams);
            }
        }
    }

    /**
     * Retrieves the logger
     * @return logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Sets the logger 
     * @param logger
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    
    public void addHistory(String title, String description, SystemUser systemUser, String... params) {
        if (historyRemote == null) {
            try {
                InitialContext initialContext = new InitialContext();
                java.lang.Object ejbRemoteRef = initialContext.lookup("HistoryBean#br.ufc.ivela.ejb.interfaces.HistoryRemote");
                historyRemote = (HistoryRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, HistoryRemote.class);
            } catch (NamingException e) {
                e.printStackTrace();
                historyRemote = null;
            }
        }
        List<History> historyList = historyRemote.getBySystemUser(systemUser.getId());
        boolean insert = true;
        for (History h : historyList) {
            if (h.getDescription().equalsIgnoreCase(description) && h.getTitle().equalsIgnoreCase(title)) {
                List<HistoryParams> historyParamsList = historyParamsRemote.getByHistory(h.getId());
                int equal = 0;
                for (int i = 0; i < params.length; i++) {
                    for (HistoryParams hp : historyParamsList) {
                        if (hp.getParam().equalsIgnoreCase(String.valueOf(i)) && hp.getValue().equalsIgnoreCase(params[i])) {
                            equal++;
                            break;
                        }
                    }
                }
                if (equal == params.length) {
                    insert = false;
                    break;
                }
            }
        }
        if (insert) {
            History history = new History();
            history.setSystemUser(systemUser);
            history.setDatetime(new Date());
            history.setTitle(title);
            history.setDescription(description);
            Long id = historyRemote.add(history);
            for (int i = 0; params != null && i < params.length; i++) {
                HistoryParams historyParams = new HistoryParams();
                historyParams.setHistoryId(id);
                historyParams.setParam(String.valueOf(i));
                historyParams.setValue(params[i]);
                historyParamsRemote.add(historyParams);
            }
        }
    }
    
    /**
     * Gets a List to be used in a Radio Boolean Button
     *  
     * @return a Map with the proper translations for the current language.
     */
    public Map<Boolean, String> getRadioBooleanList() {
        Map<Boolean, String> radioBooleanList = new LinkedHashMap<Boolean, String>();
        radioBooleanList.put(Boolean.TRUE, getText("general.true", "true"));
        radioBooleanList.put(Boolean.FALSE, getText("general.false", "false"));
        
        return radioBooleanList;
    }

    /**
     * Utility method to check if the authenticated user has authority to execute an action.
     * <p>
     * Override this method in your action for custom checks, by default it just checks 
     * the authority for the user and see if its equal to the passed argument. 
     * 
     * @param authority String representing the necessary Authority for the user.
     * 
     * @return <code> true </code> if the Authenticated user has permission for the action, <code> false </code> otherwise
     */
    public boolean hasAuthorization(String authority) {       
        return authority.equals(AUTHORITY_ADMIN);
    }
 
    /**
     * Utility method to check if the authenticated user is an Administrator.
     * 
     * @return <code> true </code> if the Authenticated is an Administrator, <code> false </code> otherwise
     */
    public final boolean isAdmin() {
       return getAuthority().equals(AUTHORITY_ADMIN); 
    }
    
    /**
     * Retrieves the Authority Level for the Authenticated User
     * 
     * @return the Authority for the Authenticated User
     */
    public String getAuthority() {
        if (authority != null && !authority.isEmpty()) {
            return authority;
        }
        
        SystemUser user = getAuthenticatedUser();
        if (user == null) {
            return "";
        }
        
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (AUTHORITY.ROLE_USER.hasAuthority(authority)) {
                this.authority = AUTHORITY.ROLE_USER.getAuthority();
                break;
            } else if (AUTHORITY.ROLE_ADMIN.hasAuthority(authority)) {
                this.authority = AUTHORITY.ROLE_ADMIN.getAuthority();
                break;
            } else if (AUTHORITY.ROLE_PROFESSOR.hasAuthority(authority)) {
                this.authority = AUTHORITY.ROLE_PROFESSOR.getAuthority();
                break;
            } else if (AUTHORITY.ROLE_TUTOR.hasAuthority(authority)) {
                this.authority = AUTHORITY.ROLE_TUTOR.getAuthority();
                break;
            } else if (AUTHORITY.ROLE_COORD.hasAuthority(authority)) {
                this.authority = AUTHORITY.ROLE_COORD.getAuthority();
                break;
            } else {
                this.authority = AUTHORITY.ROLE_NONE.getAuthority();
            }
        }
        return authority;        
    }

    /**
     * Sets the Authority Level for the authenticated User
     */
    protected void setAuthority(String role) {
        this.authority = role;
    }
    
}
