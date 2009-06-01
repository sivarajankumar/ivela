/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons.model.calendar;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author marcus
 */
@Entity
@Table(name = "calendar", schema="public")
public class Calendar implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "calendar_calendarid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "calendarid", nullable = false)
    private Long calendarid;
    
    private String name;
    
    private String type;
    
    private String url;
    
    private String username;
    
    private String password;
    
    private boolean visible;
    
    @Column(name = "offsetfrom")
    private Long offSetFrom; 	 	
          
    @Column(name = "offsetto")
    private Long offSetTo;		
         
    @Column(name = "lastrefreshtimestamp")
    private Long lastRefreshTimestamp;
            
    private String usrif;

    public Calendar() {
    }
    
    public Calendar(String name, String url, String user) {
        this.name = name;
        this.type = "ical-webdav";
        this.url = url;
        this.visible = true;
        this.offSetFrom = -1L;
        this.offSetTo = -1L;
        this.lastRefreshTimestamp = new Date().getTime();
        this.usrif = user;
    }

    public Long getCalendarid() {
        return calendarid;
    }

    public void setCalendarid(Long calendarid) {
        this.calendarid = calendarid;
    }

    public Long getLastRefreshTimestamp() {
        return lastRefreshTimestamp;
    }

    public void setLastRefreshTimestamp(Long lastRefreshTimestamp) {
        this.lastRefreshTimestamp = lastRefreshTimestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOffSetFrom() {
        return offSetFrom;
    }

    public void setOffSetFrom(Long offSetFrom) {
        this.offSetFrom = offSetFrom;
    }

    public Long getOffSetTo() {
        return offSetTo;
    }

    public void setOffSetTo(Long offSetTo) {
        this.offSetTo = offSetTo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsrif() {
        return usrif;
    }

    public void setUsrif(String usrif) {
        this.usrif = usrif;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Calendar other = (Calendar) obj;
        if (this.calendarid != other.calendarid && (this.calendarid == null || !this.calendarid.equals(other.calendarid))) {
            return false;
        }
        if (this.name != other.name && (this.name == null || !this.name.equals(other.name))) {
            return false;
        }
        if (this.type != other.type && (this.type == null || !this.type.equals(other.type))) {
            return false;
        }
        if (this.url != other.url && (this.url == null || !this.url.equals(other.url))) {
            return false;
        }
        if (this.username != other.username && (this.username == null || !this.username.equals(other.username))) {
            return false;
        }
        if (this.password != other.password && (this.password == null || !this.password.equals(other.password))) {
            return false;
        }
        if (this.usrif != other.usrif && (this.usrif == null || !this.usrif.equals(other.usrif))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.calendarid != null ? this.calendarid.hashCode() : 0);
        hash = 37 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 37 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 37 * hash + (this.url != null ? this.url.hashCode() : 0);
        hash = 37 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 37 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 37 * hash + (this.usrif != null ? this.usrif.hashCode() : 0);
        return hash;
    }
}
