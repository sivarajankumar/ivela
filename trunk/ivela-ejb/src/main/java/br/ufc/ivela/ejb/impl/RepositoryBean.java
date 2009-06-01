/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.xml.JSONItem;
import br.ufc.ivela.commons.model.xml.JSONTree;
import br.ufc.ivela.commons.model.xml.XMLDirectory;
import br.ufc.ivela.commons.model.xml.XMLFile;
import br.ufc.ivela.ejb.interfaces.RepositoryRemote;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.xml.XMLRepository;
import br.ufc.ivela.ejb.component.RepositoryComponent;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.springframework.util.StringUtils;

/**
 *
 * @author marcus
 */
@Stateless(mappedName="RepositoryBean")
public class RepositoryBean implements RepositoryRemote {

    private GenericDao<Course> daoCourse = DaoFactory.getInstance(Course.class);
    private GenericDao<br.ufc.ivela.commons.model.File> daoFile = DaoFactory.getInstance(br.ufc.ivela.commons.model.File.class);
    private XStream xStream = new XStream();
    private final String path = Constants.FILE_UPLOAD_PATH;
    private RepositoryComponent repositoryComponent = new RepositoryComponent();
    private Logger logger;

    public RepositoryBean() {
        xStream.alias("file", XMLFile.class);
        xStream.alias("directory", XMLDirectory.class);
        xStream.alias("repository", XMLRepository.class);
        xStream.addImplicitCollection(XMLRepository.class, "directories", XMLDirectory.class);
        xStream.addImplicitCollection(XMLDirectory.class, "directories", XMLDirectory.class);
        xStream.addImplicitCollection(XMLDirectory.class, "files", XMLFile.class);
    }

    public br.ufc.ivela.commons.model.File getFile(Long fileId) {
        return daoFile.get(fileId);
    }

    public String getInitialStructure() {
        XMLRepository repository = new XMLRepository();
        repository.setId("1");
        repository.setNextValue("2");
        repository.setCreatedAt(new Date().toString());

        XMLDirectory dir = new XMLDirectory();
        dir.setName("directory 1");
        dir.setCreatedAt(new Date().toString());
        dir.setCreatedBy("");
        dir.setId("1");

        repository.setDirectories(new ArrayList<XMLDirectory>());
        repository.getDirectories().add(dir);

        return xStream.toXML(repository);
    }

    public XMLRepository getStructure(Long courseId) {
        if (courseId == null) {
            return null;
        }

        Course course = daoCourse.get(new Long(courseId));

        if (course == null || course.getRepositoryStructure() == null) {
            return null;
        }

        return (XMLRepository) xStream.fromXML(course.getRepositoryStructure());
    }

    public boolean newDirectory(Long courseId, String parentId, String name) {
        XMLRepository repository = getStructure(courseId);

        String nextValue = repository.getNextValue();

        XMLDirectory directory = new XMLDirectory();
        directory.setId(nextValue);
        directory.setName(name);
        directory.setCreatedAt(new java.util.Date().toString());

        if (repository.getDirectories() == null) {
            repository.setDirectories(new ArrayList<XMLDirectory>());
        }

        if (parentId == null || parentId.equals("")) {
            repository.getDirectories().add(directory);
        } else {
            repositoryComponent.addDirectory(repository.getDirectories(), directory, parentId);
        }

        nextValue = String.valueOf(Long.valueOf(nextValue) + 1L);
        repository.setNextValue(nextValue);

        Course course = daoCourse.get(new Long(courseId));
        course.setRepositoryStructure(xStream.toXML(repository));

        return daoCourse.update(course);
    }

    public boolean removeDirectory(Long courseId, String dirId) {
        XMLRepository repository = getStructure(courseId);
        XMLDirectory deletedDirectory = null;
                
        if (StringUtils.hasText(dirId)) {
            for (XMLDirectory directory : repository.getDirectories()) {
                if (directory.getId().equals(dirId)) {                  
                    repository.getDirectories().remove(directory);        
                    deletedDirectory = directory;
                    break;
                } else {
                    if((deletedDirectory = repositoryComponent.removeDirectory(repository.getDirectories(), dirId)) != null){
                        break;
                    }
                }
            }
        }
    
        if (deletedDirectory != null) {
            removeAllChildrenFiles(courseId, deletedDirectory);

            Course course = daoCourse.get(new Long(courseId));
            course.setRepositoryStructure(xStream.toXML(repository));
            
            return daoCourse.update(course);
        } else {
            return false;
        }
    }

    public boolean addFile(File file, br.ufc.ivela.commons.model.File dbFile, String dirId) {
        XMLRepository repository = getStructure(dbFile.getCourseId());

        String finalPath = path + dbFile.getCourseId().toString() + "/" + dbFile.getFilename();

        File gradeDir = new File(path + dbFile.getCourseId().toString() + "/");

        if (!gradeDir.exists()) {
            gradeDir.mkdir();
        }

        dbFile.setPath(finalPath);
        dbFile.setUploadDate(new Date());

        try {
            java.io.File f = new java.io.File(finalPath);
            InputStream data = new FileInputStream(file);
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

        Long id = (Long) daoFile.save(dbFile);

        XMLFile xmlf = new XMLFile();
        xmlf.setId(id.toString());
        xmlf.setMimetype(dbFile.getMimetype());
        xmlf.setName(dbFile.getFilename());
        xmlf.setTitle(dbFile.getTitle());

        repositoryComponent.addFile(repository.getDirectories(), xmlf, dirId);

        Course course = daoCourse.get(dbFile.getCourseId());
        course.setRepositoryStructure(xStream.toXML(repository));
        
        return daoCourse.update(course);
    }

    public List<XMLFile> listFiles(Long courseId, String dirId) {
        XMLRepository repository = getStructure(courseId);

        return repositoryComponent.getFiles(repository.getDirectories(), dirId);
    }

    public boolean removeFile(Long courseId, Long fileId) {
        XMLRepository repository = getStructure(courseId);
       repositoryComponent.removeFile(repository.getDirectories(), fileId.toString());

        Course course = daoCourse.get(courseId);
        course.setRepositoryStructure(xStream.toXML(repository));
        daoCourse.update(course);

        br.ufc.ivela.commons.model.File file = daoFile.get(fileId);

        if (file == null) {
            return false;
        }

        File sysFile = new File(file.getPath());

        sysFile.delete();
        
        return daoFile.remove(file);
    }

    public String getJSONStructure(Long courseId) {
        XMLRepository repository = getStructure(courseId);

        if (repository == null) {
            return "";
        }

        JSONTree tree = new JSONTree();

        XStream xStream2 = new XStream(new JsonHierarchicalStreamDriver());
        xStream2.alias("tree", JSONTree.class);
        xStream2.alias("item", JSONItem.class);

        if (repository.getDirectories() != null) {
            tree.setItems(new ArrayList<JSONItem>());

            for (XMLDirectory directory : repository.getDirectories()) {
                JSONItem item = new JSONItem();
                item.setId(directory.getId());
                item.setName(directory.getName());

                if (directory.getDirectories() != null) {
                    item = repositoryComponent.addItens(directory.getDirectories(), item);
                }

                tree.getItems().add(item);
            }
        }

        String result = xStream2.toXML(tree);

        return result.substring(result.indexOf(":") + 1, result.lastIndexOf("}"));
    }

    private void removeAllChildrenFiles(Long courseId, XMLDirectory directory) {

        if (directory.getFiles() != null) {
            for (XMLFile f : directory.getFiles()) {
                removeFile(courseId, new Long(f.getId()));
            }
        }
        
        if(directory.getDirectories() != null){
            for (XMLDirectory d : directory.getDirectories()) {
                removeAllChildrenFiles(courseId, d);
            }
        }
    }    
    
    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}