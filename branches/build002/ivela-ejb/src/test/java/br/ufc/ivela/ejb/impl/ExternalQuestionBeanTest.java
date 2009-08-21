/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.model.ExternalQuestion;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jefferson
 */
public class ExternalQuestionBeanTest {

    ExternalQuestionBean externalQuestionBean;
    public ExternalQuestionBeanTest() {
        this.externalQuestionBean = new ExternalQuestionBean();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testGetExternalQuestionByQuestion() {
    
        ExternalQuestion eq = this.externalQuestionBean.getExternalQuestionByQuestion(102L);
        System.out.println(eq.getUrlBinary());
        System.out.println(eq.getUrlResult());
        System.out.println(eq.getBinType());
        //assertTrue(eq!=null);
        //System.out.println(eq.getUrlBinary());
        //ExternalQuestion eq = new ExternalQuestion();
        //eq.setUrlBinary("/uol/com/bin.jar");
        //eq.setUrlResult("/uol/com/res.xml");
        //eq.setBinType(Constants.JAVA_TYPE);
        //ExternalParams ep = new ExternalParams();
        //ep.setKey("KEY1");
        //ep.setValue("VALUE1");
        //externalQuestionBean.addExternalParam(ep);
               
    }

}