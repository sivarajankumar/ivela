/*
 *  RepositoryResource
 *
 * Created on June 11, 2008, 1:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.ufc.ivela.services;

import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.GenericDaoImpl;
import br.ufc.ivela.commons.model.File;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.xml.XMLDirectory;
import br.ufc.ivela.commons.model.xml.XMLFile;
import br.ufc.ivela.commons.model.xml.XMLRepository;
import com.thoughtworks.xstream.XStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.BodyPart;
import javax.mail.internet.MimeMultipart;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ProduceMime;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import org.springframework.util.StringUtils;

/**
 * REST Web Service
 *
 * @author marcus
 */
@Path("repository")
public class RepositoryResource {

    @Context
    private UriInfo context;
    private XStream xStream = new XStream();
    private GenericDao<File> daoFile = new GenericDaoImpl(File.class);
    private GenericDao<SystemUser> daoUser = new GenericDaoImpl(SystemUser.class);
    private GenericDao<Grade> daoGrade = new GenericDaoImpl(Grade.class);
    private XMLRepository repository;
    private final String path = "/home/marcus/files/";

    public RepositoryResource() {
        xStream.alias("file", XMLFile.class);
        xStream.alias("directory", XMLDirectory.class);
        xStream.alias("repository", XMLRepository.class);
        xStream.addImplicitCollection(XMLRepository.class, "directories", XMLDirectory.class);
        xStream.addImplicitCollection(XMLDirectory.class, "directories", XMLDirectory.class);
        xStream.addImplicitCollection(XMLDirectory.class, "files", XMLFile.class);
    }

    /**
     * Retrieves a XML representation of the Repository Structure of a Grade
     * @param gradeId the grade ID
     * @return the repository XML structure
     */
    @Path("/{grade_id}")
    @GET
    @ProduceMime("application/xml")
    public String get(@PathParam("grade_id") String gradeId) {

        // if no class id was provided        
        if (gradeId.equals("") || gradeId == null) {
            return xStream.toXML(null);
        }

        Grade clazz = daoGrade.get(new Long(gradeId));

        if (clazz == null) {
            return xStream.toXML(null);
        }

        return clazz.getRepositoryStructure();
    }

    /**
     * Adds a new XML representation on the Repository Directory
     * @param gradeId the grade ID
     * @param parentId the ID of the parent directory (if no parent directory exists, 0 must be provided)
     * @param name the name of the directory 
     * @return the directory XML structure
     */
    @GET
    @Path("/newdir/{grade_id}/{parent_id}/{name}")
    @ProduceMime("application/xml")
    public String newDirectory(@PathParam("grade_id") String gradeId,
            @PathParam("parent_id") String parentId,
            @PathParam("name") String name) {

        String xmlStructure = get(gradeId);
        repository = (XMLRepository) xStream.fromXML(xmlStructure);
        String nextValue = repository.getNextValue();

        XMLDirectory directory = new XMLDirectory();
        directory.setId(nextValue);
        directory.setName(name);
        directory.setCreatedAt(new java.util.Date().toString());

        if (repository.getDirectories() == null) {
            repository.setDirectories(new ArrayList<XMLDirectory>());
        }

        if (parentId.equals("0")) {
            repository.getDirectories().add(directory);
        } else {
            if (directoryExists(repository.getDirectories(), parentId)) {
                addDirectory(repository.getDirectories(), directory, parentId);
            }
        }

        nextValue = String.valueOf(Long.valueOf(nextValue) + 1L);
        repository.setNextValue(nextValue);

        Grade grade = daoGrade.get(new Long(gradeId));
        grade.setRepositoryStructure(xStream.toXML(repository));
        daoGrade.update(grade);

        return xStream.toXML(directory);
    }

    /**
     * removes XML representational directory
     * @param gradeId the grade ID
     * @param dirId the ID of the directory to be removed 
     */
    @GET
    @Path("/removedir/{grade_id}/{dir_id}")
    @ProduceMime("application/xml")
    public String removeDirectory(@PathParam("grade_id") String gradeId,
            @PathParam("dir_id") String dirId) {

        String xmlStructure = get(gradeId);

        repository = (XMLRepository) xStream.fromXML(xmlStructure);

        if (StringUtils.hasText(dirId)) {
            for (XMLDirectory directory : repository.getDirectories()) {
                if (directory.getId().equals(dirId)) {
                    repository.getDirectories().remove(directory);
                    break;
                } else {
                    if (directoryExists(repository.getDirectories(), dirId)) {
                        removeDirectory(repository.getDirectories(), dirId);
                        break;
                    }
                }
            }
        }

        Grade grade = daoGrade.get(new Long(gradeId));
        grade.setRepositoryStructure(xStream.toXML(repository));

        return xStream.toXML(daoGrade.update(grade));
    }

    @Path("/addfile/{gradeId}/{dirId}/{sentBy}/{author}/{description}/{title}/{keywords}/{name}")
    @POST
    //@ConsumeMime("multipart/form-data")
    public void addFile(@PathParam("gradeId") String gradeId,
                        @PathParam("dirId") String dirId,
                        @PathParam("sentBy") String sentBy,
                        @PathParam("author") String author,
                        @PathParam("description") String description,
                        @PathParam("title") String title,
                        @PathParam("keywords") String keywords,
                        @PathParam("name") String name,
                        MimeMultipart form) {
        
        String xmlStructure = get(gradeId);
        repository = (XMLRepository) xStream.fromXML(xmlStructure);
        
        try {
            int c = form.getCount();
            
            if (c > 0) {
                BodyPart body = form.getBodyPart(0);
                
                writeFile(body, name);               
                            
                String mimetype = new MimetypesFileTypeMap().getContentType(new java.io.File(path+name));
                
                File dbFile = new File();
                dbFile.setAuthor(author);
                dbFile.setDescription(description);
                dbFile.setFilename(name);
                dbFile.setGrade(daoGrade.get(new Long(gradeId)));
                dbFile.setKeywords(keywords);
                dbFile.setMimetype(mimetype);
                dbFile.setPath(path);
                dbFile.setTitle(title);
                dbFile.setUploadDate(new Date());
                dbFile.setSentBy(daoUser.get(new Long(sentBy)));
                
                Long id = (Long) daoFile.save(dbFile);                
                
                XMLFile file = new XMLFile();
                file.setId(id.toString());
                file.setMimetype(mimetype);
                file.setName(name);
                file.setTitle(title);
                
                if(directoryExists(repository.getDirectories(), dirId)){
                    addFile(repository.getDirectories(), file, dirId);
                }
                
                Grade grade = daoGrade.get(new Long(gradeId));
                grade.setRepositoryStructure(xStream.toXML(repository));
                xStream.toXML(daoGrade.update(grade));
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void writeFile(BodyPart body, String name) {

        try {
            java.io.File f = new java.io.File(path + name);
            InputStream data = body.getInputStream();
            //int size = body.getSize();
            OutputStream out = new FileOutputStream(f);
            byte buf[] = new byte[1024];
            int len;
            while ((len = data.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            data.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addFile(List<XMLDirectory> directories, XMLFile file, String dirId) {

        for (XMLDirectory dir : directories) {
            if (dir.getId().equals(dirId)) {
                if (dir.getFiles() == null) {
                    dir.setFiles(new ArrayList<XMLFile>());
                }

                dir.getFiles().add(file);
            } else {
                if (dir.getDirectories() != null) {
                    addFile(dir.getDirectories(), file, dirId);
                }
            }
        }
    }
    
    private void addDirectory(List<XMLDirectory> directories, XMLDirectory directory, String parentId) {

        for (XMLDirectory dir : directories) {
            if (dir.getId().equals(parentId)) {
                if (dir.getDirectories() == null) {
                    dir.setDirectories(new ArrayList<XMLDirectory>());
                }

                dir.getDirectories().add(directory);
            } else {
                if (dir.getDirectories() != null) {
                    addDirectory(dir.getDirectories(), directory, parentId);
                }
            }
        }
    }

    private void removeDirectory(List<XMLDirectory> directories, String id) {

        for (XMLDirectory dir : directories) {
            if (dir.getId().equals(id)) {
                directories.remove(dir);
                break;
            } else {
                if (dir.getDirectories() != null) {
                    removeDirectory(dir.getDirectories(), id);
                    break;
                }
            }
        }
    }

    private boolean directoryExists(List<XMLDirectory> directories, String id) {

        boolean retorno = false;

        for (XMLDirectory directory : directories) {
            if (directory.getId().equals(id)) {
                retorno = true;
            } else {
                if (directory.getDirectories() != null) {
                    retorno = directoryExists(directory.getDirectories(), id);
                }
            }
        }

        return retorno;
    }
}
