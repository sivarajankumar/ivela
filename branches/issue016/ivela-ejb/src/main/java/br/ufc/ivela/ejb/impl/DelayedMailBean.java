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
# File: DelayedMailBean.java                                                                #
# Document: DelayedMail Remote Implementation                                               # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 24-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Mail                    #
#############################################################################################
*/
package br.ufc.ivela.ejb.impl;

import java.util.Collection;

import javax.ejb.Stateless;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.DelayedMail;
import br.ufc.ivela.ejb.interfaces.DelayedMailRemote;

@Stateless(mappedName="DelayedMailBean")
public class DelayedMailBean implements DelayedMailRemote {
    private GenericDao<DelayedMail> daoDelayedMail = DaoFactory.getInstance(DelayedMail.class);    
   
    public Long add(DelayedMail delayedMail) {      
        return (Long) daoDelayedMail.save(delayedMail);
    }

    public void add(Collection<DelayedMail> delayedMails) {
        for (DelayedMail delayedMail: delayedMails) {
            daoDelayedMail.save(delayedMail);
        }
    }
    
    public Collection<DelayedMail> getAll() {
        return daoDelayedMail.getAll();
    }
    
    public DelayedMail get(Long delayedMailId) {
        if (delayedMailId == null) {
            return null;
        }
        return daoDelayedMail.get(delayedMailId);
    }
    
    public boolean remove(Collection<DelayedMail> delayedMails) {
        return daoDelayedMail.removeAll(delayedMails);
    }
    
    public boolean remove(Long delayedMailId) {
        return daoDelayedMail.remove(delayedMailId);
    }

    public boolean update(DelayedMail delayedMail) {
        return daoDelayedMail.update(delayedMail);
    }

}
