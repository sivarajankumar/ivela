/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.calendar.AuthUser;
import br.ufc.ivela.commons.model.calendar.AuthUserRole;
import br.ufc.ivela.commons.model.calendar.Calendar;
import br.ufc.ivela.commons.model.calendar.Event;
import br.ufc.ivela.commons.model.calendar.UserSettings;
import br.ufc.ivela.commons.model.calendar.UserTable;
import br.ufc.ivela.ejb.interfaces.CalendarRemote;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;

/**
 *
 * @author marcus
 */
@Stateless(mappedName="CalendarBean")
public class CalendarBean implements CalendarRemote {

    private GenericDao<AuthUser> authUserDao = DaoFactory.getInstance(AuthUser.class);
    private GenericDao<AuthUserRole> authUserRoleDao = DaoFactory.getInstance(AuthUserRole.class);
    private GenericDao<Calendar> calendarDao = DaoFactory.getInstance(Calendar.class);
    private GenericDao<UserSettings> userSettingsDao = DaoFactory.getInstance(UserSettings.class);
    private GenericDao<UserTable> userTableDao = DaoFactory.getInstance(UserTable.class);
    private GenericDao<Event> eventDao = DaoFactory.getInstance(Event.class);

    public boolean addInfo(String host, String port, String username) {
        boolean result = true;

        AuthUser authUser = new AuthUser();
        authUser.setUsername(username);
        authUser.setUserpass(username);
        authUserDao.save(authUser);

        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setRole("webicaluser");
        authUserRole.setUsername(username);
        authUserRoleDao.save(authUserRole);

        UserSettings userSettings = new UserSettings();
        userSettings.setDefaultCalendarView(1);
        userSettings.setFirstDayOfWeek(1);
        userSettings.setNumberOfAgendaDays(4);
        userSettings.setDateformat("dd/MM/yyyy");
        userSettings.setTimeformat("HH:mm");
        userSettings.setUsuario(username);
        userSettingsDao.save(userSettings);

        UserTable userTable = new UserTable();
        userTable.setUsrif(username);
        userTable.setFirstName(" ");
        userTable.setLastName(" ");
        userTable.setLastNamePrefix(" ");
        userTable.setBirthdate(new Date());
        userTableDao.save(userTable);

        Calendar calendar = new Calendar("Agenda", "http://" + host + ":" + port + "/webical/dav/calendars/" + username + ".ics", username);
        calendarDao.save(calendar);

        try {
            java.net.URL tempURL = new java.net.URL("http://" + host + ":" + port + "/webical/WebicalFileServlet?user=" + username);
            java.net.URLConnection uc = tempURL.openConnection();
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) uc;
            connection.connect();
            java.io.InputStream is = connection.getInputStream();
            java.io.InputStreamReader isr = new java.io.InputStreamReader(is);
            java.io.BufferedReader br = new java.io.BufferedReader(isr);
            String response = br.readLine();
            String buf = "";
            while (buf != null) {
                buf = br.readLine();
                if (buf != null) {
                    response = response + "\n" + buf;
                }
            }
            br.close();
            //System.out.println("r: " + response);
            if (response != null && response.trim().length() > 0) {
                response = response.trim();
                if (response.indexOf("success") < 0) {
                    result = false;
                }
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public boolean addEvent(String host, String port, String username, String description, Date dtStart, Date dtEnd, String where, String what) {
        boolean result = true;
        String uid = "WEBICAL-ff808081-" + getUniqueID() + "-" + "011d" + getUniqueID() + "-" + getRandomNumber() + "webical.org";
        Event event = new Event();
        event.setDescription(description);
        event.setDtstart(dtStart);
        event.setDtend(dtEnd);
        event.setLocation(where);
        event.setSummary(what);
        event.setAllday(false);
        Date dtNow = new Date();
        event.setDtstamp(dtNow);
        List<Calendar> calendars = calendarDao.getAll();
        for (Calendar c : calendars) {
            if (c.getUsrif().equalsIgnoreCase(username)) {
                event.setCalendarId(c.getCalendarid());    
                break;
            }   
        }
        event.setUid(uid);
        
        result = (eventDao.save(event) != null);
        System.out.println(result);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
        try {
            String url = "http://" + host + ":" + port + "/webical/WebicalFileServlet?user=" + username +
                    "&description=" + URLEncoder.encode(description, "UTF-8") +
                    "&dtStart=" + URLEncoder.encode(sdf.format(dtStart), "UTF-8") +
                    "&dtEnd=" + URLEncoder.encode(sdf.format(dtEnd), "UTF-8") +
                    "&where=" + URLEncoder.encode(where, "UTF-8") +
                    "&uid=" + URLEncoder.encode(uid, "UTF-8") +
                    "&dtNow=" + URLEncoder.encode(sdf.format(dtNow), "UTF-8") +
                    "&what=" + URLEncoder.encode(what, "UTF-8");
            System.out.println("%%%%%%%%%%%%%%");
            System.out.println(url);
            System.out.println("%%%%%%%%%%%%%%");
            java.net.URL tempURL = new java.net.URL(url);
            java.net.URLConnection uc = tempURL.openConnection();
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) uc;
            connection.connect();
            java.io.InputStream is = connection.getInputStream();
            java.io.InputStreamReader isr = new java.io.InputStreamReader(is);
            java.io.BufferedReader br = new java.io.BufferedReader(isr);
            String response = br.readLine();
            String buf = "";
            while (buf != null) {
                buf = br.readLine();
                if (buf != null) {
                    response = response + "\n" + buf;
                }
            }
            br.close();
            System.out.println("r: " + response);
            if (response != null && response.trim().length() > 0) {
                response = response.trim();
                if (response.indexOf("success") < 0) {
                    result = false;
                }
            }
        } catch (Exception e) {
            result = false;
        }
        System.out.println(result);
        return result;
    }

    public List<Calendar> getAll() {
        return calendarDao.getAll();
    }
    
    private static String chars = "abcdefghijklmonpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static String digits = "0123456789";
    private static Random r = new Random();

    private static String getUniqueID() {
        char[] buf = new char[8];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = chars.charAt(r.nextInt(chars.length()));
        }
        return new String(buf);
    }
    
    private static String getRandomNumber() {
        char[] buf = new char[4];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = digits.charAt(r.nextInt(digits.length()));
        }
        return new String(buf);
    }
    
}
