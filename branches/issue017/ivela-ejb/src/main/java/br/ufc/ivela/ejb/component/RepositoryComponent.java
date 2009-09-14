/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.component;

import br.ufc.ivela.commons.model.xml.JSONItem;
import br.ufc.ivela.commons.model.xml.XMLDirectory;
import br.ufc.ivela.commons.model.xml.XMLFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author marcus
 */
public class RepositoryComponent {

    public void addFile(List<XMLDirectory> directories, XMLFile file, String dirId) {

        for (XMLDirectory dir : directories) {
            if (dir.getId().equals(dirId)) {
                if (dir.getFiles() == null) {
                    dir.setFiles(new ArrayList<XMLFile>());
                }

                dir.getFiles().add(file);
                break;
            } else {
                if (dir.getDirectories() != null) {
                    addFile(dir.getDirectories(), file, dirId);
                }
            }
        }
    }

    public void addDirectory(List<XMLDirectory> directories, XMLDirectory directory, String parentId) {

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

    public XMLDirectory removeDirectory(List<XMLDirectory> directories, String id) {

        XMLDirectory deletedDirectory = null;
        
        for (XMLDirectory dir : directories) {
            if (dir.getId().equals(id)) {
                deletedDirectory = dir;
                directories.remove(dir);                
                break;
            } else {
                if (dir.getDirectories() != null) {
                    deletedDirectory = removeDirectory(dir.getDirectories(), id);
                }
            }
        }
        
        return deletedDirectory;
    }

    public void removeFile(ArrayList<XMLDirectory> directories, String id) {

         for (XMLDirectory dir : directories) {
            if (dir.getFiles() != null) {
                for (XMLFile xmlf : dir.getFiles()) {
                    if (xmlf.getId().equals(id)) {
                        dir.getFiles().remove(xmlf);
                        break;
                    }
                
                }
            }
            if (dir.getDirectories() != null) {
                 removeFile(dir.getDirectories(), id);
               
            }
        }
        
    }
    
    public boolean directoryExists(List<XMLDirectory> directories, String id) {

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

    public List<XMLFile> getFiles(List<XMLDirectory> directories, String parentId) {
        List _return = null;

        for (XMLDirectory dir : directories) {
            if (dir.getId().equals(parentId)) {
                _return = dir.getFiles();
                break;
            } else {
                if (dir.getDirectories() != null) {
                    _return = getFiles(dir.getDirectories(), parentId);
                }
            }
        }

        return _return;
    }

    public JSONItem addItens(List<XMLDirectory> directories, JSONItem item) {
        item.setChildren(new ArrayList<JSONItem>());

        for (XMLDirectory directory : directories) {
            JSONItem i = new JSONItem();
            i.setId(directory.getId());
            i.setName(directory.getName());

            if (directory.getDirectories() != null) {
                i = addItens(directory.getDirectories(), i);
            }

            item.getChildren().add(i);
        }

        return item;
    }
}
