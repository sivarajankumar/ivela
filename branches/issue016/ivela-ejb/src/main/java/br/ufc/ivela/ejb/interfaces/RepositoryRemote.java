/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.xml.XMLFile;
import br.ufc.ivela.commons.model.xml.XMLRepository;
import java.io.File;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author marcus
 */
@Remote
public interface RepositoryRemote {

    XMLRepository getStructure(Long courseId);

    String getInitialStructure();
    
    boolean newDirectory(Long courseId, String parentId, String name);
    
    boolean removeDirectory(Long courseId, String dirId);
    
    boolean addFile(File file, br.ufc.ivela.commons.model.File dbFile, String dirId);
    
    List<XMLFile> listFiles(Long courseId, String dirId);    
    
    String getJSONStructure(Long courseId);
    
    br.ufc.ivela.commons.model.File getFile(Long fileId);
    
    boolean removeFile(Long courseId, Long fileId);
}
