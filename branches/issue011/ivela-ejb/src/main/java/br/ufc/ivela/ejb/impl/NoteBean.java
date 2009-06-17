/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.ejb.interfaces.NoteRemote;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.Note;
import br.ufc.ivela.commons.model.xml.PageInfo;
import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author nelson
 */
@Stateless(mappedName="NoteBean")
public class NoteBean implements NoteRemote {

    private GenericDao<Note> daoNote = DaoFactory.getInstance(Note.class);
    private Calendar calStart = Calendar.getInstance();
    private Calendar calEnd = Calendar.getInstance();
    private PageInfo p = new PageInfo();

    public Note get(Long id) {
        if (id == null) {
            return null;
        }

        return daoNote.get(new Long(id));
    }

    public Long add(Note note) {

        return (Long) daoNote.save(note);
    }

    public String addAjax(Note note) {

        XStream xStream = new XStream();
        xStream.alias("longNote", Long.class);
        return xStream.toXML(daoNote.save(note));
    }

    public String removeAjax(Long id) {
        XStream xStream = new XStream();
        xStream.alias("booleanNote", boolean.class);
        return xStream.toXML(daoNote.remove(id));
    }

    public boolean remove(Long id) {
        return daoNote.remove(id);
    }

    public List<Note> getByUser(Long systemUserId) {
        calStart.set(Calendar.HOUR_OF_DAY, Constants.MINIMUM_HOUR);
        calStart.set(Calendar.MINUTE, Constants.MINIMUM_MINUTE);
        calStart.set(Calendar.SECOND, Constants.MINIMUM_SECOND);

        calEnd.set(Calendar.HOUR_OF_DAY, Constants.MAXIMUM_HOUR);
        calEnd.set(Calendar.MINUTE, Constants.MAXIMUM_MINUTE);
        calEnd.set(Calendar.SECOND, Constants.MAXIMUM_SECOND);


        Object[] params = new Object[]{calStart.getTime(), calEnd.getTime(), systemUserId};

        return daoNote.find("from Note n WHERE n.datetime between ? and ? and n.systemUser.id = ?", params);
    }

    public List<Note> getAll() {
        return daoNote.getAll();
    }

    public boolean update(Note note) {
        return daoNote.update(note);
    }

    public String updateAjax(Note note) {
        XStream xStream = new XStream();
        xStream.alias("booleanNote", boolean.class);
        return xStream.toXML(daoNote.update(note));
    }

    public int maxDayOfMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public List<Note> getByDay(Date date, Long userId, int pageSize, int page) {

        calStart.setTime(date);
        calEnd.setTime(date);

        calStart.set(Calendar.HOUR_OF_DAY, Constants.MINIMUM_HOUR);
        calStart.set(Calendar.MINUTE, Constants.MINIMUM_MINUTE);
        calStart.set(Calendar.SECOND, Constants.MINIMUM_SECOND);

        calEnd.set(Calendar.HOUR_OF_DAY, Constants.MAXIMUM_HOUR);
        calEnd.set(Calendar.MINUTE, Constants.MAXIMUM_MINUTE);
        calEnd.set(Calendar.SECOND, Constants.MAXIMUM_SECOND);

        Object[] params = new Object[]{calStart.getTime(), calEnd.getTime(), userId};

        p.setPage(page);
        p.setTotal(daoNote.getCount("select count(id) from Note n WHERE n.datetime between ? and ? and n.systemUser.id = ?", params));

        return daoNote.paginatedFind("from Note n WHERE n.datetime between ? and ? and n.systemUser.id = ?", params, pageSize, page);

    //return daoNote.find("from Note n WHERE n.datetime between ? and ? and n.systemUser.id = ?", params);
    }

    public List<Note> getByWeek(Date date, Long userId, int pageSize, int page) {
        calStart.setTime(date);
        calEnd.setTime(date);


        calStart.set(Calendar.HOUR_OF_DAY, Constants.MINIMUM_HOUR);
        calStart.set(Calendar.MINUTE, Constants.MINIMUM_MINUTE);
        calStart.set(Calendar.SECOND, Constants.MINIMUM_SECOND);

        calEnd.set(Calendar.HOUR_OF_DAY, Constants.MAXIMUM_HOUR);
        calEnd.set(Calendar.MINUTE, Constants.MAXIMUM_MINUTE);
        calEnd.set(Calendar.SECOND, Constants.MAXIMUM_SECOND);

        int dayOfWeek = calStart.get(Calendar.DAY_OF_WEEK);

        //dayOfWeek = 1..7   =>   Sun..Sat  => week=7days
        //today - dayOfWeek + 1 = "first day of week: Sunday"
        //today + 7 - dayOfWeek = "last day of week: Saturday"
        calStart.add(Calendar.DAY_OF_YEAR, -dayOfWeek + 1);
        calEnd.add(Calendar.DAY_OF_YEAR, 7 - dayOfWeek);


        Object[] params = new Object[]{calStart.getTime(), calEnd.getTime(), userId};

        return daoNote.find("from Note n WHERE n.datetime between ? and ? and n.systemUser.id = ?", params);
    }

    public List<Note> getByMonth(Date date, Long userId, int pageSize, int page) {
        calStart.setTime(date);
        calEnd.setTime(date);

        calStart.set(Calendar.DAY_OF_MONTH, Constants.FIRST_MONTH);
        calStart.set(Calendar.HOUR_OF_DAY, Constants.MINIMUM_HOUR);
        calStart.set(Calendar.MINUTE, Constants.MINIMUM_MINUTE);
        calStart.set(Calendar.SECOND, Constants.MINIMUM_SECOND);


        calEnd.set(Calendar.DAY_OF_MONTH, maxDayOfMonth(calEnd.get(Calendar.MONTH)));
        calEnd.set(Calendar.HOUR_OF_DAY, Constants.MAXIMUM_HOUR);
        calEnd.set(Calendar.MINUTE, Constants.MAXIMUM_MINUTE);
        calEnd.set(Calendar.SECOND, Constants.MAXIMUM_SECOND);


        Object[] params = new Object[]{calStart.getTime(), calEnd.getTime(), userId};

        return daoNote.find("from Note n WHERE n.datetime between ? and ? and n.systemUser.id = ?", params);
    }

    public String getNotesByDate(int method, Date date, Long userId, int page) {

        List<Note> noteList = new ArrayList<Note>();

        switch (method) {
            case Constants.LIST_BY_DAY:
                noteList = getByDay(date, userId, 5, page);
                break;
            case Constants.LIST_BY_WEEK:
                noteList = getByWeek(date, userId, 5, page);
                break;
            case Constants.LIST_BY_MONTH:
                noteList = getByMonth(date, userId, 5, page);
                break;
        }

        p.setPageSize(5);
        p.setPage(page);

        int div = (p.getTotal() / p.getPageSize());

        if ((p.getTotal() % p.getPageSize()) == 0) {
            p.setPageLast(div);
        } else {
            p.setPageLast(div + 1);
        }

        XStream xStream = new XStream();

        if (!noteList.isEmpty()) {

            xStream.alias("notes", List.class);
            xStream.alias("note", Note.class);
            xStream.alias("pageInfo", PageInfo.class);

            xStream.useAttributeFor(Note.class, "id");
            xStream.useAttributeFor(Note.class, "title");
            xStream.useAttributeFor(Note.class, "description");
            xStream.useAttributeFor(Note.class, "datetime");

            xStream.useAttributeFor(PageInfo.class, "page");
            xStream.useAttributeFor(PageInfo.class, "pageLast");
            xStream.useAttributeFor(PageInfo.class, "pageSize");
            xStream.useAttributeFor(PageInfo.class, "total");

            xStream.omitField(Note.class, "systemUser");

            return "<root>\n" + xStream.toXML(noteList) + "\n" + xStream.toXML(p) + "\n</root>";
        } else {
            xStream.alias("notes", String.class);
            return xStream.toXML("");
        }
    }

    public List<Note> getRecentNotes(int count, Long userId) {
        
        List<Note> results = daoNote.find("from Note n where n.datetime >= current_date " +
                "and n.systemUser.id = ? order by n.datetime asc", new Object[]{userId});
        
        if(results!=null && results.size() > count){
            return results.subList(0, count);
        } else {
            return results;
        }
    }
}