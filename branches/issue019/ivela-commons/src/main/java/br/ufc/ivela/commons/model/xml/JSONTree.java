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
public class JSONTree {
    private String label = "name";
    private List<JSONItem> items;

    public List<JSONItem> getItems() {
        return items;
    }

    public void setItems(List<JSONItem> items) {
        this.items = items;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
}
