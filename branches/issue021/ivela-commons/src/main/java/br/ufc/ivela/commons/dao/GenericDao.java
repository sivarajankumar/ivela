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
# File: GenericDao.java                                                                     #
# Document: Generic Dao Interface                                                           # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - marcus (UFC)                      - XXXXXX - Initial Version                #
# 10-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Forum                   #
#############################################################################################
*/
package br.ufc.ivela.commons.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.criterion.MatchMode;

/**
 * interface that describe a list of methods for database interaction 
 */
public interface GenericDao<T> {

    public static final MatchMode LIKE_ANYWHERE = MatchMode.ANYWHERE;
    public static final MatchMode LIKE_START = MatchMode.START;
    public static final MatchMode LIKE_END = MatchMode.END;
    
    public static final int ORDER_ASC = 0;
    public static final int ORDER_DESC = 1;

    /**
     * method to get an object from the given ID 
     * @param id - the database ID 
     * @return the Oject of a T class 
     */
    T get(Serializable id);

    /**
     * method to get all the entities of a given class
     * 
     * @return a list with all the objects
     */
    List<T> getAll();

    /**
     * method to get all the entities of a given class in order
     * @parm fieldToOrder
     * @param orderType (asc ou desc)
     * @return a list with all the objects
     */
    List<T> getAll(String fieldToOrder, int orderType);
    
    /**
     * method to get the last records of a given class in order
     * @param fieldToOrder
     * @param orderType
     * @param number
     * @return a list with all the objects
     */
    List<T> getLastRecords(String fieldToOrder, int orderType, int number);
    
    /**
     * method to get an object from an example
     * @param t - the example object parameter
     * @return a list of objects that matches the given example
     */
    List<T> getByExample(T t);
        
    /**
     * method to get an object from an example using SQL-like capabilities
     * @param t - the example object parameter
     * @param matchMode - an object of the org.hibernate.criterion.MatchMode class
     *                    specifying the match mode
     * @return a list of objects that matches the given example
     */
    List<T> getByExampleLike(T t, MatchMode matchMode);

    /**
     * method to persist an object
     * @param t - an object to be saved
     * @return the ID generated
     */
    Serializable save(T t);

    /**
     * method to remove a persistent object
     * @param t - an object to be removed
     * @return true, if the removal was successful
     *         false, otherwise
     */    
    boolean remove(T t);

    /**
     * method to remove a persistent object
     * @param t - an object to be removed
     * @return true, if the removal was successful
     *         false, otherwise
     */
    boolean remove(Serializable id);

    boolean removeAll(Collection<T> collection);

    boolean update(T t);

    List getByNamedQuery(String namedQuery, String[] paramNames, Object[] paramValues);

    List getByFK(String fk, Object value);
    
    List find(String query, Object[] params);
    
    List paginatedFind(String query, Object[] params, int pageSize, int page);
    
    int getCount(String query, Object[] countParams); 
}
