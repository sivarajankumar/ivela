package br.ufc.ivela.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import br.ufc.ivela.commons.model.FinishedUnitContent;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.DisciplineRemote;
import br.ufc.ivela.ejb.interfaces.FinishedUnitContentRemote;
import br.ufc.ivela.ejb.interfaces.GradeUnitContentRemote;
import br.ufc.ivela.ejb.interfaces.UnitContentRemote;
import br.ufc.ivela.ejb.interfaces.UnitRemote;

public class ContentInfoAction extends GenericAction {

    private InputStream inputStream;   

    private CourseRemote courseRemote;
    private DisciplineRemote disciplineRemote;
    private UnitRemote unitRemote;
    private UnitContentRemote unitContentRemote;
    private GradeUnitContentRemote gradeUnitContentRemote;
    private FinishedUnitContentRemote finishedUnitContentRemote;

    private String courseId;
    private String disciplineId;
    private String unitId;
    private String unitContentId;

    private String course;
    private String discipline;
    private String unit;
    private String unitContent;

    public String getLastCompletedLesson() {
        List<FinishedUnitContent> finishedUnitContentlist = finishedUnitContentRemote.getByUnitContentAndSystemUser(new Long(1), getAuthenticatedUser().getId());
        String html = "";
        html += "<ul>";
        if (!finishedUnitContentlist.isEmpty()) {
            for (FinishedUnitContent fuc : finishedUnitContentlist) {
                html += "<li id=\"" + fuc.getId() + "\">" + fuc.getUnitContent() + "</li>";
            }
        }
        html += "</ul>";        
        setInputStream(new ByteArrayInputStream(html.getBytes()));
        return "text";
    }

//    public String goToPage() {
//        discipline = disciplineRemote.get(discipline.getId());
//        discipline.setCourse(courseRemote.get(discipline.getCourseId()));
//        unit = unitRemote.get(unit.getId());
//        if (discipline != null) {
//            disciplineUnitList = unitRemote.getByDisciplineOpen(discipline.getId());
//        }
//        if (disciplineUnitList == null) {
//            disciplineUnitList = new ArrayList<Unit>();
//        }
//        this.unitContentList = new ArrayList<List>();
//        for (Unit unit : disciplineUnitList) {
//            List<UnitContent> temp = unitContentRemote.getByUnitOrdered(unit.getId());
//            unit.setUnitContents(temp);
//        }
//        return "text";
//    }

    public String getSystemUser() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SystemUser systemUser = null;
        if (obj instanceof UserDetails) {
        	systemUser = (SystemUser) obj;
        }
        setInputStream(new ByteArrayInputStream((systemUser.getId()+" - "+systemUser.getUsername()+" - "+systemUser.getEmail()).getBytes()));
        return "text";
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setGradeUnitContentRemote(
            GradeUnitContentRemote gradeUnitContentRemote) {
        this.gradeUnitContentRemote = gradeUnitContentRemote;
    }

    public void setFinishedUnitContentRemote(
            FinishedUnitContentRemote finishedUnitContentRemote) {
        this.finishedUnitContentRemote = finishedUnitContentRemote;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(String disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitContentId() {
        return unitContentId;
    }

    public void setUnitContentId(String unitContentId) {
        this.unitContentId = unitContentId;
    }
}