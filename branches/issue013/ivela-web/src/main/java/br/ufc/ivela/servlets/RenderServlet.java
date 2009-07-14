/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.servlets;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.ivela.commons.Constants;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author jdamico
 * use this url to test: http://localhost:17026/ivela-web/RenderServlet?file=%2Fimg%2Fjose.gif
 */
public class RenderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        response.setCharacterEncoding("ISO-8859-1");
        response.setDateHeader("Expires",System.currentTimeMillis(  ) + 24*60*60*1000);
        String reqFile = request.getParameter(Constants.RENDER_SERVLET_FILE_PARAM);
        String filename = Constants.DEFAULT_CONTENTPKG_PATH+reqFile;
        File file = null;
        Boolean err = false;
        String errMsg = "Incorrect file path or incorrect file name. ("+Constants.DEFAULT_CONTENTPKG_PATH+request.getParameter(Constants.RENDER_SERVLET_FILE_PARAM)+") ";
        
        if(reqFile!=null){
            try{
                file = new File(filename);
                if(file.exists()){
                    ServletContext sc = getServletContext();
                    String mimeType = sc.getMimeType(filename);

                    if(mimeType.equals("text/html") || mimeType.equals("text/json") || mimeType.equals("text/css") || mimeType.equals("text/javascript") || mimeType.equals("text/plain")){
                            try {
                                BufferedReader in = new BufferedReader(new FileReader(filename));
                                String str;
                                if(mimeType.equals("text/css")) response.setContentType(mimeType);
                                
                                while ((str = in.readLine()) != null) {
                                    response.getWriter().println(str);
                                }
                                in.close();
                            } catch (IOException ioe) {
                                err = true;
                                errMsg = errMsg + ioe.getMessage();
                            }

                    }else{
                        
                        
                        response.setContentType(mimeType);
                        response.setContentLength((int)file.length());
                        FileInputStream in = new FileInputStream(file);
                        OutputStream out = response.getOutputStream();
                        byte[] buf = new byte[1024];
                        int count = 0;
                        while ((count = in.read(buf)) >= 0) {
                            out.write(buf, 0, count);
                        }
                        in.close();
                        out.close();
                        }     
                    
                }else{
                    err = true;
                }
            } catch(NullPointerException npe){
                err = true;
                errMsg = errMsg + npe.getMessage();
            }
        }else{
            err = true;
        }

        if(err) response.getWriter().println(errMsg);
       
        
      
    
    
    }
       
    
}
