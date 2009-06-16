/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.calendar.AuthUser;
import br.ufc.ivela.commons.model.calendar.AuthUserRole;
import br.ufc.ivela.commons.model.calendar.UserSettings;
import br.ufc.ivela.commons.model.calendar.UserTable;
import br.ufc.ivela.ejb.interfaces.WebicalRemote;
import java.util.Calendar;

/**
 *
 * @author marcus
 */
public class WebicalBean implements WebicalRemote {

    GenericDao<Calendar> calendarDao = DaoFactory.getInstance(Calendar.class);
    GenericDao<AuthUser> authUserDao = DaoFactory.getInstance(AuthUser.class);
    GenericDao<AuthUserRole> authUserRoleDao = DaoFactory.getInstance(AuthUserRole.class);
    GenericDao<UserTable> userTableDao = DaoFactory.getInstance(UserTable.class);
    GenericDao<UserSettings> userSettingsDao = DaoFactory.getInstance(UserSettings.class);

    public boolean add(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
