/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.calendar.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author marcus
 */

@Remote
public interface CalendarRemote {

    boolean addInfo(String host, String port, String username);
    
    boolean addEvent(String host, String port, String usermame, String description, Date dtStart, Date dtEnd, String where, String what);
    
    List<Calendar> getAll();

}
