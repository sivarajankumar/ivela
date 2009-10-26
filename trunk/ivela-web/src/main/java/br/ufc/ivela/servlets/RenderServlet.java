/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.servlets;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.web.action.GenericAction;

/**
 *
 * @author jdamico
 * use this url to test: http://localhost:17026/ivela-web/RenderServlet?file=%2Fimg%2Fjose.gif
 */
public class RenderServlet extends HttpServlet {
    
    /** Common Logging interface */
    protected Log log = LogFactory.getLog(this.getClass());
    
    private static Cache objectCache;
    
    private static Cache textCache;
    
    private static CacheManager cacheManager;
    
    static {
        // Create a CacheManager using a specific config file
        cacheManager = CacheManager.create(GenericAction.class
                .getResource("ehcache.xml"));
        textCache = cacheManager.getCache("renderTextServletCache");
        objectCache = cacheManager.getCache("renderBinaryServletCache");
    } 
    
    
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

                    String key = filename + file.lastModified();                    
                    
                    if(mimeType.equals("text/html") || mimeType.equals("text/json") || mimeType.equals("text/css") || mimeType.equals("text/javascript") || mimeType.equals("text/plain")){
                            BufferedReader in = new BufferedReader(new FileReader(filename));
                            try {                                
                                if(mimeType.equals("text/css")) response.setContentType(mimeType);
                                if(mimeType.equals("text/javascript")) response.setContentType(mimeType);
                    
                                Element cacheElement = textCache.get(key);
                                String content;
                                if (cacheElement != null) {
                                    log.debug("retrieved "+ filename + " from cache ");                                    
                                    content = (String) cacheElement.getValue();
                                } else {
                                                    
                                    StringBuilder builder = new StringBuilder();
                                    String str;
                                    while ((str = in.readLine()) != null) {
                                        builder.append(str);
                                        builder.append("\n\r");
                                    }
                                    
                                    if (mimeType.equals("text/css")) {
                                        parseCSSFile(builder, reqFile);
                                    }

                                    content = builder.toString();
                                                                                       
                                    textCache.put(new Element(key, content));                                                                       
                                }
                                response.getWriter().println(content);
                                
                            } catch (IOException ioe) {
                                err = true;
                                errMsg = errMsg + ioe.getMessage();
                                log.error(errMsg, ioe);
                            } finally {
                                in.close();
                            }

                    } else {

                        Element cacheElement = objectCache.get(key);
                        byte[] content = null;

                        response.setContentType(mimeType);
                        response.setContentLength((int) file.length());
                        OutputStream out = response.getOutputStream();
                        FileInputStream in = new FileInputStream(file);
                        try {
                            if (cacheElement != null) {
                                log.debug("retrieved " + filename + " from cache ");
                                content = (byte[]) cacheElement.getValue();
                                out.write(content);
                            } else {
                                content = new byte[(int) file.length()];

                                byte[] buf = new byte[1024];
                                int count = 0;
                                int position = 0;
                                while ((count = in.read(buf)) >= 0) {
                                    out.write(buf, 0, count);
                                    System.arraycopy(buf, 0, content, position, count);
                                    position += count;
                                }

                                objectCache.put(new Element(key, content));
                            }
                        } catch (IOException e) {
                            err = true;
                            errMsg = errMsg + e.getMessage();
                            log.error(errMsg, e);
                        } finally {
                            in.close();
                            out.close();
                        }
                    }
                    
                }else{
                    err = true;
                }
            } catch(NullPointerException npe){
                err = true;
                errMsg = errMsg + npe.getMessage();
                log.error(errMsg, npe);
            }
        }else{
            err = true;
        }

        if(err) response.getWriter().println(errMsg);
    }
    
    private void parseCSSFile(StringBuilder builder, String cssFile) {
        String css = "url(";
        String cssFiletoSplit = cssFile.substring(0, cssFile.indexOf("css")-1);
        String[] splitCssFile = cssFiletoSplit.split("/");
        StringBuilder pathBuffer = new StringBuilder();
        pathBuffer.append("RenderServlet?file=");
        for (int i=0;i<splitCssFile.length;i++) {
        	pathBuffer.append(splitCssFile[i]);
        	pathBuffer.append("/");
        }
        if (pathBuffer.substring(pathBuffer.length() - 1).equals("/"))
        	pathBuffer = new StringBuilder(pathBuffer.substring(0, pathBuffer.length() - 1));              
        String path = pathBuffer.toString();
        int position = -1;
        int tempPosition = 0;
        while ((position = builder.indexOf(css, tempPosition)) > -1) {            
            int start = builder.indexOf("'", position + 1);
            int start_2 = builder.indexOf("\"", position + 1);
            start = start < 0? builder.length() - 1: start;
            start_2 = start_2 < 0? builder.length() - 1 : start_2;
            start = start > start_2? start_2 : start;
            builder.replace(start + 1,  start + 3, path);
            tempPosition = start;
        }
    }
}

