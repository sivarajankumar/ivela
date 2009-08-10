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
import br.ufc.ivela.commons.model.Profile;
import br.ufc.ivela.ejb.interfaces.ProfileRemote;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author marcus
 * use this url to test: http://localhost:8080/ivela-web/RenderServletProfile?id=1
 */
public class RenderServletProfile extends HttpServlet {

    ProfileRemote profileRemote = getProfileRemote();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("ISO-8859-1");
        response.setDateHeader("Expires",System.currentTimeMillis(  ) + 24*60*60*1000);
        String id = request.getParameter("id");
        File file = null;
        Boolean err = false;
        String errMsg = "Incorrect file path or incorrect file name. (" + Constants.FILE_UPLOAD_PATH + request.getParameter(Constants.RENDER_SERVLET_FILE_PARAM) + ") ";

        if (id != null && id.trim().length() > 0) {
            String filename = filename = Constants.FILE_UPLOAD_PATH + "foto_profile.jpg";
            try {
                Profile profile = profileRemote.getBySystemUserId(new Long(id));
                if (profile != null)
                    filename = profile.getPhoto();
            }
            catch(Exception e){
                
            }
            if (filename == null || filename.trim().length() == 0)
                filename = Constants.FILE_UPLOAD_PATH + "foto_profile.jpg";
            file = new File(filename);
            if (!file.exists())
                filename = Constants.FILE_UPLOAD_PATH + "foto_profile.jpg";
            try {
                file = new File(filename);
                if (file.exists()) {
                    ServletContext sc = getServletContext();
                    String mimeType = sc.getMimeType(filename);
                    if (mimeType.equals("text/html")) {
                        try {
                            BufferedReader in = new BufferedReader(new FileReader(filename));
                            String str;
                            while ((str = in.readLine()) != null) {
                                response.getWriter().println(str);
                            }
                            in.close();
                        } catch (IOException ioe) {
                            err = true;
                            errMsg = errMsg + ioe.getMessage();
                        }

                    } else {
                        response.setContentType(mimeType);
                        response.setContentLength((int) file.length());
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

                } else {
                    err = true;
                }
            } catch (NullPointerException npe) {
                err = true;
                errMsg = errMsg + npe.getMessage();
            }
        } else {
            err = true;
        }

        if (err) {
            response.getWriter().println(errMsg);
        }
    }

    private ProfileRemote getProfileRemote() {
        ProfileRemote p = null;
        
        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("ProfileBean#br.ufc.ivela.ejb.interfaces.ProfileRemote");
            p = (ProfileRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, ProfileRemote.class);
        } catch (NamingException e) {
            e.printStackTrace();
            p = null;
        }
        return p;
    }
}
