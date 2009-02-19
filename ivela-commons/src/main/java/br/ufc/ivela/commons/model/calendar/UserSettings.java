/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.model.calendar;

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
@Table(name = "user_settings", schema="public")
public class UserSettings {

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "user_settings_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "defaultcalendarview")
    private Integer defaultCalendarView;
    
    @Column(name = "firstdayofweek")
    private Integer firstDayOfWeek;    
    
    @Column(name = "numberofagendadays")
    private Integer numberOfAgendaDays;      
    
    @Column(name = "dateformat")
    private String dateformat; 		
            
    @Column(name = "timeformat")
    private String timeformat; 
            
    @Column(name = "usuario")
    private String usuario;

    public UserSettings() {
    }

    public String getDateformat() {
        return dateformat;
    }

    public void setDateformat(String dateformat) {
        this.dateformat = dateformat;
    }

    public Integer getDefaultCalendarView() {
        return defaultCalendarView;
    }

    public void setDefaultCalendarView(Integer defaultCalendarView) {
        this.defaultCalendarView = defaultCalendarView;
    }

    public Integer getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    public void setFirstDayOfWeek(Integer firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfAgendaDays() {
        return numberOfAgendaDays;
    }

    public void setNumberOfAgendaDays(Integer numberOfAgendaDays) {
        this.numberOfAgendaDays = numberOfAgendaDays;
    }

    public String getTimeformat() {
        return timeformat;
    }

    public void setTimeformat(String timeformat) {
        this.timeformat = timeformat;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserSettings other = (UserSettings) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.defaultCalendarView != other.defaultCalendarView && (this.defaultCalendarView == null || !this.defaultCalendarView.equals(other.defaultCalendarView))) {
            return false;
        }
        if (this.firstDayOfWeek != other.firstDayOfWeek && (this.firstDayOfWeek == null || !this.firstDayOfWeek.equals(other.firstDayOfWeek))) {
            return false;
        }
        if (this.numberOfAgendaDays != other.numberOfAgendaDays && (this.numberOfAgendaDays == null || !this.numberOfAgendaDays.equals(other.numberOfAgendaDays))) {
            return false;
        }
        if (this.dateformat != other.dateformat && (this.dateformat == null || !this.dateformat.equals(other.dateformat))) {
            return false;
        }
        if (this.timeformat != other.timeformat && (this.timeformat == null || !this.timeformat.equals(other.timeformat))) {
            return false;
        }
        if (this.usuario != other.usuario && (this.usuario == null || !this.usuario.equals(other.usuario))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 19 * hash + (this.defaultCalendarView != null ? this.defaultCalendarView.hashCode() : 0);
        hash = 19 * hash + (this.firstDayOfWeek != null ? this.firstDayOfWeek.hashCode() : 0);
        hash = 19 * hash + (this.numberOfAgendaDays != null ? this.numberOfAgendaDays.hashCode() : 0);
        hash = 19 * hash + (this.dateformat != null ? this.dateformat.hashCode() : 0);
        hash = 19 * hash + (this.timeformat != null ? this.timeformat.hashCode() : 0);
        hash = 19 * hash + (this.usuario != null ? this.usuario.hashCode() : 0);
        return hash;
    }
}
