/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.model.Forum;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Class that implements the test of ejb ForumBean
 */
public class ForumBeanTest {

    ForumBean forumBean;
    private long lastId;
    
    public ForumBeanTest() {
        forumBean = new ForumBean();
    }

//    @BeforeClass
//    public static void setUpClass() throws Exception {
//    }
//
//    @AfterClass
//    public static void tearDownClass() throws Exception {
//    }
//
//    /**
//     * Test of getAll method, of class ForumBean.
//     */
//    @Test
//    public void testGetAll() {
//        List<Forum> list = forumBean.getAll();
//        assertTrue(list.size() > 0);
//        lastId = list.get(list.size() - 1).getId();
//    }
//    
//    /**
//     * Test of get method, of class ForumBean.
//     */
//    @Test
//    public void testGet() {
//        assertNotNull(forumBean.get(lastId));
//    }
//
//    /**
//     * Test of add method, of class ForumBean.
//     */
//    @Test
//    public void testAdd() {
//        Forum forum = new Forum();
//        GenericDao<Grade> daoGrade = DaoFactory.getInstance(Grade.class);
//        forum.setGrade(daoGrade.get(1));
//        forum.setTitle("Test of Forum");        
//        forum.setStudentCreateTopic(true);
//        forum.setStudentLinkPost(true);
//        forum.setStudentUploadPost(false);
//        forum.setStudentUploadRepository(true);
//        assertNotNull(forumBean.add(forum));
//    }
//
//    /**
//     * Test of remove method, of class ForumBean.
//     */
//    @Test
//    public void testRemove() {
//        assertTrue(forumBean.remove(lastId));
//    }

    @Test
    public void testList() {
        List<Forum> forumList = forumBean.getForumList(2L, false, false, "");
        
        assertNotNull(forumList);
    }
    
}