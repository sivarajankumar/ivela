/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.servlets;

import br.ufc.ivela.commons.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet used to get the messages according to the selected or default locale
 * @author rodrigo
 */
public class I18nServlet extends HttpServlet {

    /**
     * 
     * @param pReq
     * @param pRes
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doGet(HttpServletRequest pReq, HttpServletResponse pRes) throws ServletException, IOException {        
        String locale = Constants.DEFAULT_LOCALE;
        if(pReq.getSession().getAttribute("WW_TRANS_I18N_LOCALE") != null){
            locale = pReq.getSession().getAttribute("WW_TRANS_I18N_LOCALE").toString();
        }
        Locale localeUser = new Locale(locale);
        
        pRes.setCharacterEncoding("ISO-8859-1");
        PrintWriter out = pRes.getWriter();
        out.println(getJsonMessages(localeUser));
        out.flush();
        out.close();
    }
    
    /**
     * retrieve the messages into the file
     * @param localeUser
     * @return
     */
    private String getJsonMessages(Locale localeUser){
        ResourceBundle messages = ResourceBundle.getBundle("messages", localeUser);
        
        StringBuilder json = new StringBuilder();
        json.append("[");
        
        Set<String> keys = messages.keySet();
        for (String key : keys){
            json.append("{\"label\":\"" + key + "\", \"message\":\"" + messages.getString(key) + "\"}, ");
        }
        
        int i = json.lastIndexOf(",");
        json.replace(i, i + 1, "");
        
        json.append("]");
        return json.toString();
    }
    
}
