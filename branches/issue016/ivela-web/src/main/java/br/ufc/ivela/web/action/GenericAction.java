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
#############################################################################################    
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.Logger;
import br.ufc.ivela.commons.model.History;
import br.ufc.ivela.commons.model.HistoryParams;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.HistoryParamsRemote;
import br.ufc.ivela.ejb.interfaces.HistoryRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.GrantedAuthority;

/**
 *
 * @author marcus
 */
public abstract class GenericAction extends ActionSupport {

    protected Logger logger;
    private HistoryRemote historyRemote;
    private HistoryParamsRemote historyParamsRemote;

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

        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SystemUser systemUser = null;
        if (obj != null) {
            if (obj instanceof UserDetails) {
            	systemUser	= (SystemUser)obj;
            	GrantedAuthority[] authorities = ((SystemUser) obj).getAuthorities();

                for (GrantedAuthority authority : authorities) {
                    
                    String authentication = authority.getAuthority();
                    
                    if (authentication.equals("ROLE_ADMIN") || 
                        authentication.equals("ROLE_COORD") || 
                        authentication.equals("ROLE_TUTOR") ||
                        authentication.equals("ROLE_PROFESSOR") ) {
                    	Map<String,String> session = ActionContext.getContext().getSession();
                		session.put("role","admin");break;
                        
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
    
}
