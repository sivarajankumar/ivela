/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.model.Post;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rodrigo
 */
public class PostBeanTest {

    PostBean postBean;
    
    public PostBeanTest() {
        postBean = new PostBean();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of get method, of class PostBean.
     */
    @Test
    public void testGet() {
        assertNotNull(postBean.get(1L));
    }

    /**
     * Test of add method, of class PostBean.
     */
    @Test
    public void testAdd() {        
        Post post = new Post();
        post.setTitle("Titulo do post de teste unitario");
        post.setTopic(new TopicBean().get(1L));
        post.setCreatedBy(new SystemUserBean().get(1L));
        post.setCreatedAt(new Date());
        post.setMessage("texto do posttexto do posttexto do posttexto do posttexto do posttexto do posttexto do post");
        
        assertNotNull(postBean.add(post));
    }

    /**
     * Test of remove method, of class PostBean.
     */
    @Test
    public void testRemove() {
        assertTrue(postBean.remove(8L));
    }

}