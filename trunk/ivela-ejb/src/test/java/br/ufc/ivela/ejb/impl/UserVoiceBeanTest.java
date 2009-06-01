/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.model.UserVoice;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jefferson
 */
public class UserVoiceBeanTest {

    private UserVoiceBean userVoiceBean = null;
    
    public UserVoiceBeanTest() {
        
        userVoiceBean = new UserVoiceBean();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testAdd() {
        
         //userVoiceBean.addWordVoiceForUserVoice(1L, "Teste", 3);
        //userVoiceBean.addVoiceFeedBack(5, "eu não achei essas coisas todas mas...");
        Long id = userVoiceBean.addNpdUser();
        userVoiceBean.addNpdPhraseForNpdUser(id, 1, 10);
    }

}