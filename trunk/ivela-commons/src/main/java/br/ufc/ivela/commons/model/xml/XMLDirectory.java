/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.model.xml;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author marcus
 */
public class XMLDirectory implements Serializable{
    
    String id;
    String name;
    String created_by;
    String created_at;
    
    ArrayList<XMLDirectory> directories;
    ArrayList<XMLFile> files;

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public String getCreatedBy() {
        return created_by;
    }

    public void setCreatedBy(String created_by) {
        this.created_by = created_by;
    }

    public ArrayList<XMLDirectory> getDirectories() {
        return directories;
    }

    public void setDirectories(ArrayList<XMLDirectory> directories) {
        this.directories = directories;
    }

    public ArrayList<XMLFile> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<XMLFile> files) {
        this.files = files;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
}