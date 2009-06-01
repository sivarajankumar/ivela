/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons;

import java.io.Serializable;

/**
 *This class is used only to save the type of an exercise and its answer. 
 * Actually is especific for ExerciseAction and its session.
 * @author jefferson
 */
public class TypeAnswer implements Serializable{

    private int type;
    private Object answer;
    
    public TypeAnswer(int type, Object answer){
        this.type = type;
        this.answer = answer;
    } 

    public Object getAnswer() {
        return answer;
    }

    public void setAnswer(Object answer) {
        this.answer = answer;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
