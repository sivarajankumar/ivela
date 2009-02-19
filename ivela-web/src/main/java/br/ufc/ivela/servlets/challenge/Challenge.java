/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.servlets.challenge;

import java.util.List;

/**
 *
 * @author jdamico
 */
public class Challenge {
    
    private String score = null;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    private String name = null;
    
    private List<Field> fields = null;
    
    public void setField(List<Field> fields){
        this.fields = fields;
    }
    
    public List<Field> getField(){
        return fields;
    }
}
