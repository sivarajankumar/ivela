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
public class XMLRepository implements Serializable{
    String id;
    String next_value;
    String created_at;
    ArrayList<XMLDirectory> directories;

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public ArrayList<XMLDirectory> getDirectories() {
        return directories;
    }

    public void setDirectories(ArrayList<XMLDirectory> directories) {
        this.directories = directories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNextValue() {
        return next_value;
    }

    public void setNextValue(String next_value) {
        this.next_value = next_value;
    }    
}