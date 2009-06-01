/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.model.xml;

import java.util.List;

/**
 *
 * @author marcus
 */
public class JSONItem {
    private String name;
    private String id;
    private List<JSONItem> children;

    public List<JSONItem> getChildren() {
        return children;
    }

    public void setChildren(List<JSONItem> children) {
        this.children = children;
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
