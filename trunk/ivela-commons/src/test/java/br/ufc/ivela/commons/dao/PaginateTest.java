/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons.dao;

import java.util.List;
import org.junit.Test;

/**
 *
 * @author marcus
 */
public class PaginateTest {

    Page page;

    @Test
    public void testPaginate() {
        int p = 1;
        int pageSize = 3;
        String query = "from SystemUser";
        String countQuery = "select count(id) from SystemUser";

        page = new Page(query, countQuery, null, null, p, pageSize);

        List list = page.getList();

        System.out.println(p + " de " + page.getPageCount() + " ("+ page.getCount() + " resultados )" +" - " + list.size());

        p++;

        while (page.isNextPage()) {
            page = new Page(query, countQuery, null, null, p, pageSize);

            list = page.getList();

            System.out.println(p + " de " + page.getPageCount() + " - " + list.size());
            
            p++;
        }
    }
}
