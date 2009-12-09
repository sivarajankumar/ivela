/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.File;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.xml.XMLDirectory;
import br.ufc.ivela.commons.model.xml.XMLFile;
import br.ufc.ivela.commons.model.xml.XMLRepository;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.RepositoryRemote;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import org.springframework.security.context.SecurityContextHolder;

/**
 *
 * @author marcus
 */
public class RepositoryAction extends GenericAction {

    private Long courseId;
    private String dirId;
    private Long fileId;
    private File file;
    private String dirName;
    private RepositoryRemote repositoryRemote;
    private CourseRemote courseRemote;
    private XMLRepository repository;
    private java.io.File upload;
    private String uploadContentType;
    private String uploadFileName;
    private InputStream downloadStream;
    private InputStream inputStream;
    private String contentLength;
    private String contentDisposition;
    private String contentType = "application/download";






    public String show(){
        XMLRepository xmlr = repositoryRemote.getStructure(courseId);

        if(xmlr == null) {
            Course c = courseRemote.get(courseId);
            c.setRepositoryStructure(repositoryRemote.getInitialStructure());
            courseRemote.update(c);
            setRepository(repositoryRemote.getStructure(courseId));
        } else {
            setRepository(xmlr);
        }

        return "show";
    }

    public String getFileInfo() {
        file = repositoryRemote.getFile(fileId);

        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("file", File.class);

        setInputStream(new ByteArrayInputStream(xStream.toXML(file).getBytes()));

        return "json";
    }

    public String getDirInfo() {
        repository = repositoryRemote.getStructure(courseId);
  
        XMLDirectory xmld = getDirectory(repository.getDirectories(), dirId);

        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("directory", XMLDirectory.class);
        xStream.alias("file", XMLFile.class);
        logger.log(xmld.getName());
        logger.log(xmld.toString());
        setInputStream(new ByteArrayInputStream(xStream.toXML(xmld).getBytes()));

        return "json";
    }

    /**
     * Perform a download to the files 
     * @return download
     */
    public String download() {
        File dbfile = repositoryRemote.getFile(fileId);

        java.io.File f = new java.io.File(dbfile.getPath());
        try {
            downloadStream = new FileInputStream(f);
        } catch (FileNotFoundException ex) {
            log.error("Repository download error!", ex);           
        }

        String mime = dbfile.getMimetype();
        if (mime != null && !mime.isEmpty()) {
            setContentType(mime);
        }
        setContentLength(new Long(f.length()).toString());
        setContentDisposition(dbfile.getFilename());

        return "download";
    }
    

    public XMLDirectory getDirectory(List<XMLDirectory> directories, String dirId) {

        XMLDirectory result = null;

        for (XMLDirectory directory : directories) {
            if (directory.getId().equals(dirId)) {
                result = directory;
            } else {
                if (directory.getDirectories() != null) {
                    result = getDirectory(directory.getDirectories(), dirId);
                }
            }
        }

        return result;
    }

    public void setRepository(XMLRepository repository) {
        this.repository = repository;
    }

    public XMLRepository getRepository() {
        return repository;
    }

    public RepositoryRemote getRepositoryRemote() {
        return repositoryRemote;
    }

    public void setRepositoryRemote(RepositoryRemote repositoryRemote) {
        this.repositoryRemote = repositoryRemote;
    }

    public String getDirId() {
        return dirId;
    }

    public void setDirId(String dirId) {
        this.dirId = dirId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String name) {
        this.dirName = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public java.io.File getUpload() {
        return upload;
    }

    public void setUpload(java.io.File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream getDownloadStream() {
        return downloadStream;
    }

    public void setDownloadStream(InputStream downloadStream) {
        this.downloadStream = downloadStream;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getContentDisposition() {
        return contentDisposition;
    }

    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    public String getContentLength() {
        return contentLength;
    }

    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }

    public CourseRemote getCourseRemote() {
        return courseRemote;
    }

    public void setCourseRemote(CourseRemote courseRemote) {
        this.courseRemote = courseRemote;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }
}
