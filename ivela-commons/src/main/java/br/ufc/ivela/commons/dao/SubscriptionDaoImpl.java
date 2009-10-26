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
# File: SubscriptionDaoImpl.java                                                            #
# Document: Subscription DAO Implementation                                                 # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 24-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Mail                    #
#############################################################################################
*/
package br.ufc.ivela.commons.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufc.ivela.commons.dao.interfaces.SubscriptionDao;
import br.ufc.ivela.commons.model.Subscription;
import br.ufc.ivela.commons.model.Topic;

public class SubscriptionDaoImpl<T> extends GenericDaoImpl<T> implements
        SubscriptionDao<T> {

    public List<Subscription> getSubscriptionByClass(String clazz, Long id) {
        Object[] params = new Object[]{clazz, id};        
        
        List<Subscription> results = find("from Subscription s where s.category = ? and s.category_id = ?", params);
        
        if(results == null){
            return new ArrayList<Subscription>(0);
        } else {
            return results;
        }
    }

    public List<Subscription> getSubscriptionByClassAndType(String clazz,
            String type, Long id) {
        Object[] params = new Object[]{clazz, type, id};
        List<Subscription> results = find("from Subscription s where s.category = ? and s.type = ? and s.category_id = ?", params);
        
        if(results ==null){
            return new ArrayList<Subscription>(0);
        } else {
            return results;
        }
    }

}
