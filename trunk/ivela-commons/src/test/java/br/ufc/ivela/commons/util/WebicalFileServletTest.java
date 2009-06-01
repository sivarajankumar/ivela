/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.util;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.calendar.AuthUser;
import br.ufc.ivela.commons.model.calendar.AuthUserRole;
import br.ufc.ivela.commons.model.calendar.Calendar;
import br.ufc.ivela.commons.model.calendar.UserSettings;
import br.ufc.ivela.commons.model.calendar.UserTable;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author marcus
 */
public class WebicalFileServletTest {

    GenericDao<Calendar> calendarDao;
    GenericDao<AuthUser> authUserDao;
    GenericDao<AuthUserRole> authUserRoleDao;
    GenericDao<UserTable> userTableDao;
    GenericDao<UserSettings> userSettingsDao;
    
    @Before
    public  void setUp() {
        calendarDao = DaoFactory.getInstance(Calendar.class);
        authUserDao = DaoFactory.getInstance(AuthUser.class);
        authUserRoleDao = DaoFactory.getInstance(AuthUserRole.class);
        userTableDao = DaoFactory.getInstance(UserTable.class);
        userSettingsDao = DaoFactory.getInstance(UserSettings.class);
    }

    @After
    public  void tearDown() {
        calendarDao = null;
        authUserDao = null;
        authUserRoleDao = null;
        userTableDao = null;
        userSettingsDao = null;
    }
    
    //@Test
    public void testAuthUser(){
        AuthUser authUser = new AuthUser();
        authUser.setUsername("test");
        authUser.setUserpass("test");                
        
        String user = (String) authUserDao.save(authUser);
        
        System.out.println("%%%%%%%%%%%%% " + user);
        
        assertTrue(user != null);
    }
    
    //@Test
    public void testCalendar(){
        Calendar calendar = new Calendar("Agenda", "http://localhost:8080/webical/dav/calendars/test.ics", "test");
        
        Long id = (Long) calendarDao.save(calendar);
        
        System.out.println("%%%%%%%%%%%%% " + id);
        
        assertTrue(id != null);
    }
}