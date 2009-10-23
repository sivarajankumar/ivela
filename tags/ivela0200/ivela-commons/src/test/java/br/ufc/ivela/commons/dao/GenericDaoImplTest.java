/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.dao;

import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcus
 */ 
public class GenericDaoImplTest {

    GenericDao<Grade> dao;
    GenericDao<SystemUser> daoUser;
    static Long id;
    
    @Before
    public void before(){
        dao = DaoFactory.getInstance(Grade.class);
        daoUser = DaoFactory.getInstance(SystemUser.class);
    }

    @After
    public void after(){
        dao = null;
    }

    /**
     * Test of getAll method, of class GenericDaoImpl.
     */
//    @Test
//    public void testGetAll() {
//        List<SystemUser> list = dao.getAll("id", dao.ORDER_ASC);
//        
//        assertNotNull(list);
//        assertTrue(list.size() > 0);
//        assertNotNull(list.get(0));   
//        
//        System.out.println("\n\nPrimeiro id: " + list.get(0).getId());
//        System.out.println("\n\nUltimo id: " + list.get(list.size() - 1).getId());
//        
//        assertTrue(list.get(0).getId() <= list.get(list.size() - 1).getId());
//    }
    
//    
//    /**
//     * Test of save method, of class GenericDaoImpl.
//     */
//    @Test
//    public void testSave() {        
//        SystemUser user = new SystemUser();
//        user.setUsername("giggity");
//        user.setEmail("lalalalal");
//        user.setUsername("user");
//        user.setPassword("pwd");
//        
//        int beforeSize = dao.getAll().size();
//        
//        id = (Long) dao.save(user);   
//        
//        int actualSize = dao.getAll().size();
//        
//        assertTrue(id.intValue() > 0);
//        assertTrue(actualSize == (beforeSize + 1));       
//    }
//    
//     /**
//     * Test of find method, of class GenericDaoImpl.
//     */
//    @Test
//    public void testFind() {
//        List result = dao.find("from Post p where p.title = ?", new Object[]{"post 1"});   
//        
//        assertNotNull(result);
//    }
//    
//    
//    /**
//     * Test of get method, of class GenericDaoImpl.
//     */
//    @Test
//    public void testGet() {
//        
//        Grade grade = dao.get(3L);
//        
//        SystemUser user = daoUser.get(5L);
//        
//        assertNotNull(grade);   
//        assertTrue(grade.getProfessors().size() == 2);
//        
//        grade.getProfessors().add(user);
//        
//        dao.update(grade);
//        
//        grade = dao.get(3L);
//        
//        assertNotNull(grade);   
//        assertTrue(grade.getProfessors().size() == 3);
//    }
////
//    /**
//     * Test of getAll method, of class GenericDaoImpl.
//     */
//    @Test
//    public void testGetAll() {
//        List<SystemUser> list = dao.getAll();
//        
//        assertNotNull(list);
//        assertTrue(list.size() > 0);
//        assertNotNull(list.get(0));        
//    }
//    
//
//    /**
//     * Test of getByExample method, of class GenericDaoImpl.
//     */
//    @Test
//    public void testGetByExample() {
//        SystemUser user = new SystemUser();
//        user.setUsername("giggity");
//        
//        List<SystemUser> list = dao.getByExample(user);
//        
//        assertNotNull(list);
//        assertTrue(list.size() > 0);
//        assertNotNull(list.get(0)); 
//        assertNotNull(list.get(0).getUsername());       
//    }
//
//    /**
//     * Test of getByExampleLike method, of class GenericDaoImpl.
//     */
//    @Test
//    public void testGetByExampleLike() {
//        SystemUser user = new SystemUser();
//        user.setUsername("gi");
//        
//        List<SystemUser> list = dao.getByExampleLike(user, MatchMode.START);
//        
//        assertNotNull(list);
//        assertTrue(list.size() > 0);
//        assertNotNull(list.get(0)); 
//        assertNotNull(list.get(0).getUsername());   
//    }
//
//    /**
//     * Test of getByNamedQuery method, of class GenericDaoImpl.
//     */
//    @Test
//    public void testGetByNamedQuery() {
//        List list = dao.getByNamedQuery("SystemUser.findByUserName", new String[]{"name"}, new Object[]{"giggity"});
//        
//        assertNotNull(list);
//        assertTrue(list.size() > 0);
//        assertNotNull(list.get(0)); 
//        assertNotNull(((SystemUser) list.get(0)).getUsername());     
//    }
//    
//    /**
//     * Test of update method, of class GenericDaoImpl.
//     */
//    @Test
//    public void testUpdate() {
//        SystemUser user = dao.get(id);
//        
//        user.setUsername("joao");
//        
//        assertTrue(dao.update(user));
//        
//        user = dao.get(id);
//        
//        assertTrue(user.getUsername().equals("joao"));
//    }
//    
//    /**
//     * Test of remove method, of class GenericDaoImpl.
//     */
//    @Test
//    public void testRemove_null() {
//        
//        int beforeSize = dao.getAll().size();
//                
//        SystemUser user = dao.get(id);
//        
//        assertTrue(dao.remove(user));
//        
//        int actualSize = dao.getAll().size();
//        
//        assertTrue(actualSize == (beforeSize - 1)); 
//    }  
//    
//    /**
//     * Test of get objects by a foreign key
//     */
//    @Test
//    public void testGetByFK(){
////        List<Post> list = postDao.getByFK("topic.id", 4);
////        
////        assertNotNull(list);
////        assertTrue(list.size() == 3);
//    }
    
}