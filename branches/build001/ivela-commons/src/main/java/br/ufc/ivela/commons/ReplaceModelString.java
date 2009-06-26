/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons;

import br.ufc.ivela.commons.model.ChallengeItems;
import br.ufc.ivela.commons.model.Dictionary;
import br.ufc.ivela.commons.model.Exam;
import br.ufc.ivela.commons.model.Exercise;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.QuestionText;

/**
 *
 * @author jefferson this is the biggest gamgi that i did in my whole life!
 */
public class ReplaceModelString {

    public static final String IV_IGV = "IV_IGV";
    public static final String IV_EXC = "IV_EXC";
    public static final String IV_CER = "IV_CER";
    public static final String IV_PER = "IV_PER";
    public static final String IV_MEN = "IV_MEN";
    public static final String IV_MAI = "IV_MAI";
    public static final String IV_ASP = "IV_ASP";

    public static void replace(Exercise exercise) {

        exercise.setDescription(exercise.getDescription().replaceAll(ReplaceModelString.IV_IGV, "?"));
        exercise.setTitle(exercise.getTitle().replaceAll(ReplaceModelString.IV_IGV, "?"));

        exercise.setDescription(exercise.getDescription().replaceAll(ReplaceModelString.IV_EXC, "!"));
        exercise.setTitle(exercise.getTitle().replaceAll(ReplaceModelString.IV_EXC, "!"));

        exercise.setDescription(exercise.getDescription().replaceAll(ReplaceModelString.IV_CER, "#"));
        exercise.setTitle(exercise.getTitle().replaceAll(ReplaceModelString.IV_CER, "#"));

        exercise.setDescription(exercise.getDescription().replaceAll(ReplaceModelString.IV_PER, "%"));
        exercise.setTitle(exercise.getTitle().replaceAll(ReplaceModelString.IV_PER, "%"));
    }

    public static void replace(Exam exam) {

        exam.setTitle(exam.getTitle().replaceAll(ReplaceModelString.IV_IGV, "?"));
        exam.setTitle(exam.getTitle().replaceAll(ReplaceModelString.IV_EXC, "!"));
        exam.setTitle(exam.getTitle().replaceAll(ReplaceModelString.IV_CER, "#"));
        exam.setTitle(exam.getTitle().replaceAll(ReplaceModelString.IV_PER, "%"));
    }

    public static void replace(Dictionary dictionary) {

        dictionary.setTitle(dictionary.getTitle().replaceAll(ReplaceModelString.IV_IGV, "?"));
        dictionary.setTitle(dictionary.getTitle().replaceAll(ReplaceModelString.IV_EXC, "!"));
        dictionary.setTitle(dictionary.getTitle().replaceAll(ReplaceModelString.IV_CER, "#"));
        dictionary.setTitle(dictionary.getTitle().replaceAll(ReplaceModelString.IV_PER, "%"));

        if (dictionary.getDescription() != null) {
            dictionary.setDescription(dictionary.getDescription().replaceAll(ReplaceModelString.IV_IGV, "?"));
            dictionary.setDescription(dictionary.getDescription().replaceAll(ReplaceModelString.IV_EXC, "!"));
            dictionary.setDescription(dictionary.getDescription().replaceAll(ReplaceModelString.IV_CER, "#"));
            dictionary.setDescription(dictionary.getDescription().replaceAll(ReplaceModelString.IV_PER, "%"));
        }
    }

    public static void replace(QuestionText questionText) {

        questionText.setText(questionText.getText().replaceAll(ReplaceModelString.IV_IGV, "?"));

        questionText.setText(questionText.getText().replaceAll(ReplaceModelString.IV_EXC, "!"));

        questionText.setText(questionText.getText().replaceAll(ReplaceModelString.IV_CER, "#"));

        questionText.setText(questionText.getText().replaceAll(ReplaceModelString.IV_PER, "%"));
    }

    public static void replace(Question question) {


        question.setQuestion(question.getQuestion().replaceAll(ReplaceModelString.IV_IGV, "?"));

        question.setQuestion(question.getQuestion().replaceAll(ReplaceModelString.IV_EXC, "!"));

        question.setQuestion(question.getQuestion().replaceAll(ReplaceModelString.IV_CER, "#"));

        question.setQuestion(question.getQuestion().replaceAll(ReplaceModelString.IV_PER, "%"));
    }
    
        public static void replace(ChallengeItems challengeItems) {


        challengeItems.setXml(challengeItems.getXml().replaceAll(ReplaceModelString.IV_IGV, "?"));

        challengeItems.setXml(challengeItems.getXml().replaceAll(ReplaceModelString.IV_EXC, "!"));

        challengeItems.setXml(challengeItems.getXml().replaceAll(ReplaceModelString.IV_CER, "#"));

        challengeItems.setXml(challengeItems.getXml().replaceAll(ReplaceModelString.IV_PER, "%"));
        
        challengeItems.setXml(challengeItems.getXml().replaceAll(ReplaceModelString.IV_MEN, "<"));
        
        challengeItems.setXml(challengeItems.getXml().replaceAll(ReplaceModelString.IV_MAI, ">"));
    
        challengeItems.setXml(challengeItems.getXml().replaceAll(ReplaceModelString.IV_ASP, "\""));
        }
}
