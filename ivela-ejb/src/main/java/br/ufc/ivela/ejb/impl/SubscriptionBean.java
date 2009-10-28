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
# File: SubscriptionBean.java                                                               #
# Document: Subscription Remote Implementation                                              # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 24-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Mail                    #
#############################################################################################
*/
package br.ufc.ivela.ejb.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.interfaces.SubscriptionDao;
import br.ufc.ivela.commons.model.Subscription;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.SubscriptionRemote;

@Stateless(mappedName="SubscriptionBean")
public class SubscriptionBean implements SubscriptionRemote {
    private static final String METHOD_ID = "getId";
    
    private SubscriptionDao<Subscription> daoSubscription = (SubscriptionDao<Subscription>) DaoFactory.getInstance(Subscription.class);
    
    public Long add(Subscription subscription) {        
        return (Long) daoSubscription.save(subscription);
    }
   
    public Subscription get(Long subscriptionId) {
        if (subscriptionId == null) {
            return null;
        }
        return daoSubscription.get(subscriptionId);
    }

    public Collection<Subscription> getAll(Subscription subscription) {
        return daoSubscription.getByExample(subscription);
    }
    
    public boolean remove(Collection<Subscription> subscriptions) {                       
        return daoSubscription.removeAll(subscriptions);
    }
    
    public boolean remove(Long subscriptionId) {
        return daoSubscription.remove(subscriptionId);
    }

    public boolean update(Subscription subscription) {
        return daoSubscription.update(subscription);
    }  
    
    public Subscription createSubscription(final Object to, final String type,
            final SystemUser user, final String email) {
        Subscription subscription = new Subscription();
        subscription.setCategory(filterClass(to));
        subscription.setType(type);
        subscription.setUser(user);
        subscription.setRecipient(email);

        return subscription;
    }       

    public List<Subscription> getSubscriptionByClass(Object object) {                
        Long id = getObjectId(object);
        String className = filterClass(object);
     
        return daoSubscription.getSubscriptionByClass(className, id);
    }

    public List<Subscription> getSubscriptionByClassAndType(Object object,
            String type) {
        Method[] methods = object.getClass().getDeclaredMethods();
        Long id = getObjectId(object);
        String className = filterClass(object);
        
        return daoSubscription.getSubscriptionByClassAndType(className, type, id);        
    }

    private Long getObjectId(Object object) throws IllegalArgumentException {
        Method[] methods = object.getClass().getDeclaredMethods();
        Long id = null;
        Exception exc = null;
        for (Method method : methods) {
            if (method.getName().equals(METHOD_ID)) {
                try {
                    id = (Long) method.invoke(object, new Object[0]);
                } catch (Exception e) {
                    exc = e;
                }
            }            
        }
             
        if (id == null) {                
            throw new IllegalArgumentException("Invalid object passed as parameter, not a valid model", exc);
        }
        
        return id;
    }
    
    private String filterClass(Object object) {
        String className = object.getClass().getName();
        className = className.substring(className.lastIndexOf('.') + 1,
                className.length());
        return className;
    }
}
