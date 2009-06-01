/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.model.Topic;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Class that implements the test of ejb TopicBean
 */
public class TopicBeanTest {

    TopicBean topicBean;
    private long lastId;
    
    public TopicBeanTest() {
        topicBean = new TopicBean();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getAll method, of class TopicBean.
     */
    @Test
    public void testGetAll() {
        List<Topic> list = topicBean.getRecentTopics(3);
        
        assertTrue(list.size() > 0);
    }
    
//    /**
//     * Test of get method, of class TopicBean.
//     */
//    @Test
//    public void testGet() {
//        assertNotNull(topicBean.get(lastId));
//    }
//
//    /**
//     * Test of add method, of class TopicBean.
//     */
//    @Test
//    public void testAdd() {
//        Topic topic = new Topic();
//        GenericDao<Forum> daoForum = DaoFactory.getInstance(Forum.class);
//        GenericDao<SystemUser> daoSystemUser = DaoFactory.getInstance(SystemUser.class);
//        topic.setTitle("Test of Topic");
//        topic.setForum(daoForum.get(1));
//        topic.setCreatedAt(new Date());
//        topic.setCreatedBy(daoSystemUser.get(1));
//        assertNotNull(topicBean.add(topic));
//    }
//
//    /**
//     * Test of remove method, of class TopicBean.
//     */
//    @Test
//    public void testRemove() {
//        assertTrue(topicBean.remove(lastId));
//    }

}