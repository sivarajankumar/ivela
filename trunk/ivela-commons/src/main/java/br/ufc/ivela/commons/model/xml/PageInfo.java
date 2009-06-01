/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.model.xml;

import java.io.Serializable;

/**
 *
 * @author nelson
 */
public class PageInfo implements Serializable {
    private int page;
    private int pageLast;
    private int pageSize;
    private int total;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageLast() {
        this.pageLast = (total / pageSize);       
        
        if((total % pageSize) == 0){
            return this.pageLast;
        } else {
            return this.pageLast + 1;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    

    public void setPageLast(int pageLast) {
        this.pageLast = pageLast;
    }

}
