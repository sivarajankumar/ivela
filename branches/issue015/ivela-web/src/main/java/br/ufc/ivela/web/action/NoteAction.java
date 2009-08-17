/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.model.Note;
import br.ufc.ivela.ejb.interfaces.NoteRemote;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nelson
 */
public class NoteAction extends GenericAction {

    private NoteRemote noteRemote;
    private Note note;
    private List<Note> noteList;
    private int day;
    private int month;
    private int year;
    private String dateNote;
    private InputStream inputStream;
    private int method;
    private String noteId;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
    private Date date;
    private int page;

    /**
     * Add a new note
     * try to parse a date to the correct format and gets the user authenticated
     * @return
     */
    public String add() {

        try {

            date = dateFormat.parse(dateNote);
            note.setDatetime(date);

        } catch (ParseException error) {
            error.printStackTrace();
        }

        note.setSystemUser(getAuthenticatedUser());
        noteRemote.add(note);

        return list();
    }

    /**
     * Edit a note
     * retrives the id of a note  and parse a date to the correct format
     * @return
     */
    public String edit() {

        Note n = noteRemote.get(note.getId());
        dateNote = dateFormat.format(n.getDatetime());
        setNote(n);
        return "edit";
    }

    /**
     * Update a note
     * try to parse a note to the correct format than set the date
     * @return
     */
    public String update() {

        try {

            date = dateFormat.parse(dateNote);
            note.setDatetime(date);

        } catch (ParseException error) {
            error.printStackTrace();
        }

        noteRemote.update(note);
        return list();
    }

    /**
     * Update ajax
     * try to parse the date to the correct format
     * retrives the authenticated user and set the data
     * @return
     */
    public String updateAjax() {

        try {

            date = dateFormat.parse(dateNote);
            note.setDatetime(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        note.setSystemUser(getAuthenticatedUser());

        noteRemote.update(note);
        setInputStream(new ByteArrayInputStream(noteRemote.updateAjax(note).getBytes()));


        return "xml";
    }

    /**
     * Remove ajax
     * retrives a note and remove the data
     * @return
     */
    public String removeAjax() {
        setInputStream(new ByteArrayInputStream(noteRemote.removeAjax(note.getId()).getBytes()));
        return "xml";

    }

    /**
     * Remove a note
     * @return
     */
    public String remove() {
        noteRemote.remove(note.getId());
        return list();
    }

    /**
     * Add Ajax
     * Try to parse the date to the correct format then add to ajax
     * @return
     */
    public String addAjax() {

        note.setSystemUser(getAuthenticatedUser());

        try {

            date = dateFormat.parse(dateNote);
            note.setDatetime(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        setInputStream(new ByteArrayInputStream(noteRemote.addAjax(note).getBytes()));
        return "xml";
    }

    @Override
    public String input() {
        return INPUT;
    }
    
    public String show(){
        return "show";
    }

    /**
     * Retrieves a list of notes
     * @return
     */
    public String list() {
        //noteList = noteRemote.getByUser(getAuthenticatedUser().getId());
        Calendar c = Calendar.getInstance();

        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);
        year = c.get(Calendar.YEAR);

        dateNote = month + "/" + day + "/" + year;

        return "list";
    }

    /**
     * List the notes by Date
     * @return
     */
    public String listByDate() {

        SimpleDateFormat dateFormatOnly = new SimpleDateFormat("yyyy/MM/dd");
        try {
            date = dateFormatOnly.parse(year + "/" + month + "/" + day);

            setInputStream(new ByteArrayInputStream(noteRemote.getNotesByDate(method, date, getAuthenticatedUser().getId(), page).getBytes()));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "xml";
    }

    /**
     * Retrieves a note
     * @return
     */
    public Note getNote() {
        return note;
    }

    /**
     * Set a note
     * @param note
     */
    public void setNote(Note note) {
        this.note = note;
    }

    /**
     * Retrieves a noteList
     * @return
     */
    public List<Note> getNoteList() {
        return noteList;
    }

    /**
     * Set a note list
     * @param noteList
     */
    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    /**
     * Retrieves a remote note
     * @return
     */
    public NoteRemote getNoteRemote() {
        return noteRemote;
    }

    /**
     * Set a Remote Note
     * @param noteRemote
     */
    public void setNoteRemote(NoteRemote noteRemote) {
        this.noteRemote = noteRemote;
    }

    /**
     * Retrieves the day
     * @return
     */
    public int getDay() {
        return day;
    }

    /**
     * Set the day
     * @param day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Retrieves the month
     * @return
     */
    public int getMonth() {
        return month;
    }

    /**
     * Set the month
     * @param month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Retrieves the year
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the year
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Retrieves a Date note
     * @return
     */
    public String getDateNote() {
        return dateNote;
    }

    /**
     * Set a Date note
     * @param dateNote
     */
    public void setDateNote(String dateNote) {
        this.dateNote = dateNote;
    }

    /**
     * Retrieves a input Stream
     * @return
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Set a input Stream
     * @param inputStream
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Retrieves the method
     * @return
     */
    public int getMethod() {
        return method;
    }

    /**
     * Set a method
     * @param method
     */
    public void setMethod(int method) {
        this.method = method;
    }

    /**
     * Retrieves a note id
     * @return
     */
    public String getNoteId() {
        return noteId;
    }

    /**
     * Set a note id
     * @param noteId
     */
    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    /**
     * Retrieves a date format
     * @return
     */
    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * Set a date format
     * @param dateFormat
     */
    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * Retrieves a date
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set a date
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Retrieves the page
     * @return page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the page
     * @param page
     */
    public void setPage(int page) {
        this.page = page;
    }
}
