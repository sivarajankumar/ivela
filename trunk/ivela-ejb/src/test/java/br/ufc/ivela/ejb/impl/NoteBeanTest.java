/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Note;
import br.ufc.ivela.commons.model.SystemUser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nelson
 */
public class NoteBeanTest {
    
    NoteBean noteBean;
    private long lastId;

    public NoteBeanTest() {
        noteBean = new NoteBean();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGet() {
        List result = noteBean.getRecentNotes(3, 7L);        
        
        assertNotNull(result);
        
    }

//    @Test
//    public void testGetByUser() {
//        List<Note> list = noteBean.getByUser(1L);
//        assertTrue(list.size() > 0);
//        lastId = list.get(list.size() - 1).getId();
//    }
//
//    @Test
//    public void testAdd() {
//        Note note = new Note();
//        GenericDao<SystemUser> daoSystemUser = DaoFactory.getInstance(SystemUser.class);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        
//        note.setSystemUser(daoSystemUser.get(1));
////        note.setNote("note de teste");
//        note.setDescription("descrição de teste");
//        try{
//        note.setDatetime(dateFormat.parse("2008-06-28 14:30:58"));
//        }
//        catch (ParseException e){
//            e.printStackTrace();
//        }
//
// //       note.setIsDropable(new Boolean("false"));
//        //note.setCreatedBy(Long.valueOf("2"));
//        
//        assertNotNull(noteBean.add(note));
//    }
//
//    @Test
//    public void testRemove() {
//        assertTrue(noteBean.remove(lastId));
//    }
//
//    @Test
//    public void testGetAll() {
//        List<Note> list = noteBean.getAll();
//        assertTrue(list.size() > 0);
//        lastId = list.get(list.size() - 1).getId();
//    }

}