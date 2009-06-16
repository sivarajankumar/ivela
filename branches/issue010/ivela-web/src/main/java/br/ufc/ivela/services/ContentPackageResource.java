/*
 *  ContentPackageResource
 *
 * Created on September 24, 2008, 6:17 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.ufc.ivela.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ProduceMime;
import javax.ws.rs.ConsumeMime;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * REST Web Service
 *
 * @author leoomoreira
 */

@Path("contentPackage")
public class ContentPackageResource {
    
    @Context
    private UriInfo context;
    
    public static final String UPLOAD_PATH = "/uploads/";
    public static final String PROCESS_PATH = "/opt/ivela/";

    /** Creates a new instance of ContentPackageResource */
    public ContentPackageResource() {
        
    }
    
    @Path("/get/{id}")
    @GET
    @ProduceMime("application/xml")
    public String get(@PathParam("id") String id) {
        /*
        if (id == null || "".equals(id.trim())) {
            return xStream.toXML(null);
        }
        Course course = courseRemote.get(new Long(id));
        if (course == null) {
            return xStream.toXML("");
        }
        xStream.alias("course", Course.class);
        return xStream.toXML(course);
        */
        return "";
    }    
/*
    @Path("/uploadFile/{id}")
    @POST
    @ConsumeMime("multipart/form-data")
    public void uploadFile(@PathParam("id") String id, MimeMultipart form) {
        System.out.println("id: " + id);
        try {
            int c = form.getCount();
            if (c > 0) {
                BodyPart body = form.getBodyPart(0);
                writeFile(body);
                //String mimetype = new MimetypesFileTypeMap().getContentType(new java.io.File(path + body.getFileName()));
                unzip(new File(UPLOAD_PATH + body.getFileName()), new File(PROCESS_PATH));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/
    
    
    public static void unzip(File zipFile, File dir) throws IOException {
        ZipFile zip = null;
        File arquivo = null;
        InputStream is = null;
        OutputStream os = null;
        byte[] buffer = new byte[1024];

        try {
            // cria diretório informado, caso não exista
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!dir.exists() || !dir.isDirectory()) {
                throw new IOException("O diretório " + dir.getName() + " não é um diretório válido");
            }

            zip = new ZipFile(zipFile);
            Enumeration e = zip.entries();
            while (e.hasMoreElements()) {
                ZipEntry entrada = (ZipEntry) e.nextElement();
                arquivo = new File(dir, entrada.getName());
                // se for diretório inexistente, cria a estrutura e pula pra próxima entrada
                if (entrada.isDirectory() && !arquivo.exists()) {
                    arquivo.mkdirs();
                    continue;
                }
                // se a estrutura de diretórios não existe, cria
                if (!arquivo.getParentFile().exists()) {
                    arquivo.getParentFile().mkdirs();
                }
                try {
                    // lê o arquivo do zip e grava em disco
                    is = zip.getInputStream(entrada);
                    os = new FileOutputStream(arquivo);
                    int bytesLidos = 0;
                    if (is == null) {
                        //throw new ZipException("Erro ao ler a entrada do zip: " + entrada.getName());
                        throw new IOException("Erro ao ler a entrada do zip: " + entrada.getName());
                    }
                    while ((bytesLidos = is.read(buffer)) > 0) {
                        os.write(buffer, 0, bytesLidos);
                    }
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (Exception ex) {
                            
                        }
                    }
                    if (os != null) {
                        try {
                            os.close();
                        } catch (Exception ex) {
                            
                        }
                    }
                }
            }
        } finally {
            if (zip != null) {
                try {
                    zip.close();
                } catch (Exception e) {
                    
                }
            }
        }
    }
    
    /**
     * Retrieves representation of an instance of br.ufc.ivela.services.ContentPackageResource
     * @return an instance of java.lang.String
     */
    @GET
    @ProduceMime("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ContentPackageResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @ConsumeMime("application/xml")
    public void putXml(String content) {
        
    }
}
