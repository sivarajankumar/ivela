/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        //System.out.println(isMultipart);
        //System.out.print("servlet");
        // Create a factory for disk-based file items
//        FileItemFactory factory = new DiskFileItemFactory();
//
//
//
//// Create a new file upload handler
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        System.out.print("servlet3");
//        try {
//
//// Parse the request
//            List items = upload.parseRequest(request); /* FileItem */
//            System.out.print("size: "+items.size()); 
//        } catch (FileUploadException ex) {
//            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
//System.out.print("servlet2");
        try {
            List fileItemsList = servletFileUpload.parseRequest(request);
            // System.out.print("size: "+fileItemsList.size()); 
        } catch (FileUploadException ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
