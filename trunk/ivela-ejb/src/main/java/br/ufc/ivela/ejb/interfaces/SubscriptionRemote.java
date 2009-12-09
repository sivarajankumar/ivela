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
# File: SubscriptionRemote.java                                                             #
# Document: Subscription Remote                                                             # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 24-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Mail                    #
#############################################################################################
*/
package br.ufc.ivela.ejb.interfaces;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import br.ufc.ivela.commons.model.Subscription;
import br.ufc.ivela.commons.model.SystemUser;

@Remote
public interface SubscriptionRemote {
    public Long add(Subscription subscription);
        
    public boolean update(Subscription subscription);
    
    public Collection<Subscription> getAll(Subscription subscription);
    
    public Subscription get(Long subscriptionId);
    
    public boolean remove(Collection<Subscription>  subscriptions);
    
    public boolean remove(Long subscriptionId);
    
    public Subscription createSubscription(final Object to, final String type,
            final SystemUser user, final String email);
    
    public List<Subscription> getSubscriptionByClassAndType(Object object,
            String type);
    
    public List<Subscription> getSubscriptionByClass(Object object);
}
