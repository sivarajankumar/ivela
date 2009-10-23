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
# File: Page.java                                                                           #
# Document: Page Model                                                                      # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 07-JAN-2009 - Marcus (UFC)           - XXXXXX - Initial Version                           #
# 16-SEP-2009 - Otofuji (Instituto Eldorado)      - 000016 - General Fixes                  #
#############################################################################################
*/
package br.ufc.ivela.commons.dao;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{

    private List results;
    private int pageSize;
    private int page;
    private GenericDao dao;
    private int count;

    public Page(List results,int page, int pageSize, int count){
        if (page <= 0) page = 1;
        
        this.results = results;
        this.page = page;
        this.pageSize = pageSize;
        this.count = count;
    }
    
    public Page(String query, String countQuery, Object[] params, Object[] countParams, int page, int pageSize) {
        if (page <= 0) page = 1;
        
        this.page = page;
        this.pageSize = pageSize;
        dao = DaoFactory.getInstance();

        results = dao.paginatedFind(query, params, pageSize, page);
        count = dao.getCount(countQuery, countParams);
    }

    

    public boolean isNextPage() {
        return results.size() > pageSize;
    }

    public List getList() {
        return isNextPage() ? results.subList(0, pageSize) : results;
    }

    public int getCount() {
        return count;
    }

    public int getPageCount() {
        int div = (count / pageSize);       
        
        if((count % pageSize) == 0){
            return div;
        } else {
            return div + 1;
        }
    }
}
