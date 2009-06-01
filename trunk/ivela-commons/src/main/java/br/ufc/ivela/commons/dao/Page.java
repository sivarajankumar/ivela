/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author marcus
 */
public class Page implements Serializable{

    private List results;
    private int pageSize;
    private int page;
    private GenericDao dao;
    private int count;

    public Page(List results,int page, int pageSize, int count){
        this.results = results;
        this.page = page;
        this.pageSize = pageSize;
        this.count = count;
    }
    
    public Page(String query, String countQuery, Object[] params, Object[] countParams, int page, int pageSize) {

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
