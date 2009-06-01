/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons;

import br.ufc.ivela.commons.model.Exercise;
import java.util.List;

/**
 *
 * @author emanuelle
 */
public class ExerciseRequisites {

    private List<String> requisitesTitle;
    private Exercise exercise;

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public List getRequisitesTitle() {
        return requisitesTitle;
    }

    public void setRequisitesTitle(List requisitesTitle) {
        this.requisitesTitle = requisitesTitle;
    }
    
    
}
