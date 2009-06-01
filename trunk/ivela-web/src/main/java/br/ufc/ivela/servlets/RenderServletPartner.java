/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.servlets;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 */
public class RenderServletPartner extends HttpServlet {

    CourseRemote courseRemote = getCourseRemote();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("ISO-8859-1");
        response.setDateHeader("Expires",System.currentTimeMillis(  ) + 24*60*60*1000);
        String id = request.getParameter("id");
        File file = null;
        Boolean err = false;
        String errMsg = "Incorrect file path or incorrect file name.";

        if (id != null) {
            
            Course course = courseRemote.get(new Long(id));
            String filename = course.getImage();
            if (filename == null || filename.trim().length() == 0)
                filename = Constants.FILE_UPLOAD_PATH + "foto_partner.jpg";
            file = new File(filename);
            if (!file.exists())
                filename = Constants.FILE_UPLOAD_PATH + "foto_partner.jpg";
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
                        java.io.OutputStream out = response.getOutputStream();
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

    private CourseRemote getCourseRemote() {
        CourseRemote p = null;
        
        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("CourseBean#br.ufc.ivela.ejb.interfaces.CourseRemote");
            p = (CourseRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, CourseRemote.class);
        } catch (NamingException e) {
            e.printStackTrace();
            p = null;
        }
        return p;
    }
}
