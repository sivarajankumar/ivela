/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.model.History;
import br.ufc.ivela.commons.model.HistoryParams;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Transcript;
import br.ufc.ivela.ejb.interfaces.HistoryParamsRemote;
import br.ufc.ivela.ejb.interfaces.HistoryRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import com.opensymphony.xwork2.ActionContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Action of the history system
 */
public class HistoryAction extends GenericAction {

    private HistoryRemote historyRemote;
    private HistoryParamsRemote historyParamsRemote;
    private SystemUserRemote systemUserRemote;
    private History history;
    private List<History> historyList;
    private List<SystemUser> systemUserList;
    private InputStream inputStream;
    private List<Transcript> transcriptList;
    private SystemUser systemUser; 
  

    public String show() {
        historyList = historyRemote.getBySystemUser(getAuthenticatedUser().getId());
        transcriptList = historyRemote.getTranscriptsByStudent(getAuthenticatedUser().getId());
        return "show";
    }
    
    public String showPrintVersion() {
         
        historyList = historyRemote.getBySystemUser(getAuthenticatedUser().getId());
        transcriptList = historyRemote.getTranscriptsByStudent(getAuthenticatedUser().getId());
        this.systemUser = getAuthenticatedUser();
        return "printVersion";
    }
    
    public String getJson() {
        historyList = historyRemote.getBySystemUser(getAuthenticatedUser().getId());
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.ENGLISH);
        //sdf.setTimeZone(TimeZone.getTimeZone("GMT-06:00"));
        StringBuilder json = new StringBuilder();
        json.append("{");
            json.append("'events' : ");
            json.append("[");
                for (int i = 0; historyList != null && i < historyList.size(); i++) {
                    History h = historyList.get(i);
                    String startDate = sdf.format(h.getDatetime()) + " GMT";
                    json.append("{'start': '" + startDate + "', ");
                    json.append("'title': '" + replaceParams(getHistory(h.getTitle()), h.getId())  + "', ");
                    json.append("'description': '" + replaceParams(getHistory(h.getDescription()), h.getId()) + "'}");
                    if (i != historyList.size() - 1)
                        json.append(", ");
                }
            json.append("]");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes())); 
        return "json";
    }

    public String getHistory(String key) {
        String title = "";
        try {
            if (properties == null) {
                properties = new Properties();
            }
            ClassLoader cl = this.getClass().getClassLoader();
            InputStream is = cl.getResourceAsStream("history_" + ActionContext.getContext().getLocale().getLanguage() + ".properties");
            properties.load(is);
            title = properties.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
            java.util.logging.Logger.getLogger(HistoryAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return title;
    }
    
    private Properties properties = null;
    
    private String replaceParams(String text, Long historyId) {
        if (text == null)
            text = "";
        List<HistoryParams> list = historyParamsRemote.getByHistory(historyId);
        for (HistoryParams h : list)
            text = text.replaceAll("@" + h.getParam(), h.getValue());
        return text;
    }
    
      public HistoryParamsRemote getHistoryParamsRemote() {
        return historyParamsRemote;
    }

    public void setHistoryParamsRemote(HistoryParamsRemote historyParamsRemote) {
        this.historyParamsRemote = historyParamsRemote;
    }
    
    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    public HistoryRemote getHistoryRemote() {
        return historyRemote;
    }

    public void setHistoryRemote(HistoryRemote historyRemote) {
        this.historyRemote = historyRemote;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<SystemUser> getSystemUserList() {
        return systemUserList;
    }

    public void setSystemUserList(List<SystemUser> systemUserList) {
        this.systemUserList = systemUserList;
    }

    public SystemUserRemote getSystemUserRemote() {
        return systemUserRemote;
    }

    public void setSystemUserRemote(SystemUserRemote systemUserRemote) {
        this.systemUserRemote = systemUserRemote;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public List<Transcript> getTranscriptList() {
        return transcriptList;
    }

    public void setTranscriptList(List<Transcript> transcriptList) {
        this.transcriptList = transcriptList;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

     
    
    
    
}
