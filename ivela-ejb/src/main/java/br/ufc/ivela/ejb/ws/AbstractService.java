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
# File: AbstractService.java                                                                       #
# Document: Abstract class for the Web Services                                                    #
# Date        - Author(Company)                   - Issue# - Summary                               #
# 02-DEC-2009 - Otofuji (Eldorado)                - 000021 - Create Initial Ivela Services         #
##################################################################################################*/
package br.ufc.ivela.ejb.ws;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.ufc.ivela.commons.model.History;
import br.ufc.ivela.commons.model.HistoryParams;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.HistoryParamsRemote;
import br.ufc.ivela.ejb.interfaces.HistoryRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;


public abstract class AbstractService {

    @EJB 
    protected HistoryRemote historyRemote;
    
    @EJB
    protected HistoryParamsRemote historyParamsRemote;

    @EJB 
    protected SystemUserRemote systemUserRemote;
    
    protected SystemUser getSystemUser(String studentName, String password) {
        List<SystemUser> users = systemUserRemote.getByUsername(studentName);
        
        if (users.isEmpty()) {
            return null;
        }
         
        SystemUser user = users.get(0);
        if (!user.getPassword().equals(systemUserRemote.encrypt(password))) {
            return null;    
        }
        
        return user;
    }
    protected void addHistory(String title, String description, SystemUser systemUser, String... params) {
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
}
