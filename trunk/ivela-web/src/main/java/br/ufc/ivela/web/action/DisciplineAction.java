/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.FinishedUnitContent;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.DisciplineRemote;
import br.ufc.ivela.commons.util.Validators;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.FinishedUnitContentRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import br.ufc.ivela.ejb.interfaces.UnitContentRemote;
import br.ufc.ivela.ejb.interfaces.UnitRemote;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.SAXmyHtmlHandler;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import org.springframework.util.StringUtils;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author nelson
 */
public class DisciplineAction extends GenericAction {

    private Grade grade;
    private String gradeId;
    private GradeRemote gradeRemote;
    private CourseRemote courseRemote;
    private DisciplineRemote disciplineRemote;
    private UnitRemote unitRemote;
    private UnitContentRemote unitContentRemote;
    private Discipline discipline;
    private Course course;
    private List<Discipline> disciplineList;
    private List<List> unitList;
    private List<Unit> disciplineUnitList;
    private List<List> unitContentList;
    private Unit unit;
    private InputStream inputStream;
    private OutputStream outputStream;
    private UnitContent unitContent;
    private SystemUserRemote systemUserRemote;
    private String unitContentId;
    private boolean hasExam;
    private boolean hasExercise;
    private FinishedUnitContentRemote finishedUnitContentRemote;

    public FinishedUnitContentRemote getFinishedUnitContentRemote() {
        return finishedUnitContentRemote;
    }

    public void setFinishedUnitContentRemote(FinishedUnitContentRemote finishedUnitContentRemote) {
        this.finishedUnitContentRemote = finishedUnitContentRemote;
    }
    
    /**
     * If hasn't erros, add a new discipline, 
     * if has, return the error
     */
    public String add() {
        // performValidates the add
        performValidateAdd();
        if (!hasActionErrors()) {
            course = discipline.getCourse();
            disciplineRemote.add(discipline);
            return listByCourse();
        }
        addActionError("Unable to add a discipline");
        return listByCourse();
    }

    /**If hasn't erros,  remove a discipline
     * If has, return the error
     */
    public String remove() {
        performValidateRemove();
        if (!hasActionErrors()) {
            disciplineRemote.remove(discipline.getId());
            return listByCourse();
        }
        addActionError("Unable to remove a course");
        return listByCourse();
    }

    /**
     * Sets the variables to be used on the input discipline
     */
    @Override
    public String input() {
        return INPUT;
    }

    /**
     * List all disciplines
     */
    public String list() {
        disciplineList = disciplineRemote.getAll();
        setDisciplineList(disciplineList);
        return "list";
    }

    /**
     * List the units from the Discipline of the course.
     * @return a list of unit
     */
//    public String listByCourse() {
//        course = courseRemote.get(course.getId());
//        disciplineList = disciplineRemote.getByCourse(course.getId());
//        this.unitList = new ArrayList<List>();
//        for (Discipline discipline : disciplineList) {
//            List<Unit> unitList = unitRemote.getByDiscipline(discipline.getId());
//            this.unitList.add(unitList);
//        }
//        return "list";
//    }

    /**
     * Show the units of the disciplines
     * @return units
     */
    public String showContent() {
        
        discipline = disciplineRemote.get(discipline.getId());
        discipline.setCourse(courseRemote.get(discipline.getCourseId()));
        unit = unitRemote.get(unit.getId());
        if (discipline != null) {
            disciplineUnitList = unitRemote.getByDisciplineOpen(discipline.getId());
        }
        if (disciplineUnitList == null) {
            disciplineUnitList = new ArrayList<Unit>();
        }
        this.unitContentList = new ArrayList<List>();
        for (Unit unit : disciplineUnitList) {
            List<UnitContent> temp = unitContentRemote.getByUnitOrdered(unit.getId());
            unit.setUnitContents(temp);
        }
        return "show";
    }
    
    public String showLastContent() {
        
        SystemUser atual = systemUserRemote.get(getAuthenticatedUser().getId());
        unitContent =  unitContentRemote.get(atual.getLastUnitContentId());
        unit = unitRemote.get(unitContent.getUnitId());
        discipline = disciplineRemote.get(unit.getDisciplineId());  
        discipline.setCourse(courseRemote.get(discipline.getCourseId()));
        
        if (discipline != null) {
            disciplineUnitList = unitRemote.getByDiscipline(discipline.getId());
        }
        if (disciplineUnitList == null) {
            disciplineUnitList = new ArrayList<Unit>();
        }
        this.unitContentList = new ArrayList<List>();
        for (Unit un : disciplineUnitList) {
            List<UnitContent> temp = unitContentRemote.getByUnitOrdered(un.getId());
            un.setUnitContents(temp);
        }
        return "show";
    }

    /**
     * Show in PDF, gets the content of the unit and transforms to a pdf
     * @return
     */
    public String showPdf() {
        if (unitContentId != null && !unitContentId.equalsIgnoreCase("")) {
            unitContent = unitContentRemote.get(new Long(unitContentId));
        } else {
            unitContent = unitContentRemote.get(unitContent.getId());
        }
        addHistory("history.viewunitcontent.title", "history.viewunitcontent.description", unitContent.getTitle());
        List<FinishedUnitContent> list = finishedUnitContentRemote.getByUnitContentAndSystemUser(unitContent.getId(), getAuthenticatedUser().getId());
        if (list.size() == 0) {
            FinishedUnitContent finishedUnitContent = new FinishedUnitContent();
            finishedUnitContent.setSystemUser(getAuthenticatedUser().getId());
            Unit u = unitRemote.get(unitContent.getUnitId());
            Discipline d = disciplineRemote.get(u.getDisciplineId());
            Course c = courseRemote.get(d.getCourseId());
            finishedUnitContent.setCourse(c.getId());
            finishedUnitContent.setUnitContent(unitContent.getId());
            finishedUnitContentRemote.add(finishedUnitContent);
        }
        if (gradeId != null && !gradeId.equalsIgnoreCase("")) {
            grade = gradeRemote.get(new Long(gradeId));
        }

        SystemUser user = systemUserRemote.get(getAuthenticatedUser().getId());
        user.setLastUnitContentId(unitContent.getId());
        systemUserRemote.update(user);

        Document document = new Document(PageSize.A4, 80, 50, 30, 65);
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, outputStream);
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            String string = unitContent.getDescription();
            string = string.trim();
            string = string.replaceAll("[\n]", "");
            //System.out.println(string);
            ByteArrayInputStream inputStreamDescription = new ByteArrayInputStream(string.getBytes());
            parser.parse(inputStreamDescription, new SAXmyHtmlHandler(document));
            setInputStream(new ByteArrayInputStream(outputStream.toByteArray()));
        } catch (Exception e) {
            e.printStackTrace();

        }

        return "pdf";
    }


    

    /**
     * Makes the testo to verifies the transformation
     * @return
     */
    public String listByCourse() {
        setInputStream(new ByteArrayInputStream(disciplineRemote.getByCourseOrderByName(course.getId()).getBytes()));
        return "json";
    }

    /**
     * Update a discipline
     * @return a string
     */
    public String update() {
        //performValidate the update
        performValidateUpdate();
        if (!hasActionErrors()) {
            course = discipline.getCourse();
            disciplineRemote.update(discipline);
            return listByCourse();
        }
        addActionError("Unable to update a discipline");
        return listByCourse();
    }

    /**
     * Edit a discipline
     * @return edit
     */
    public String edit() {
        Discipline d = disciplineRemote.get(discipline.getId());
        setDiscipline(d);
        return "edit";
    }

    /**
     * Perfom a validation in adicional method
     */
    private void performValidateAdd() {
        // verifies if the discipline is null
        if (discipline == null) {
            addActionError(getText("discipline.input.validation.required"));
        }
        // verifies if the title is empty
        if (!StringUtils.hasText(discipline.getName())) {
            addActionError(getText("discipline.input.validation.name"));
        }
    }

    /**
     * Perform a validation in remove method
     */
    private void performValidateRemove() {
        // verifies if there is an id
        if (discipline.getId() == null) {
            addActionError(getText("discipline.input.validation.requiredId"));
        } else {
            // verifies if this id is valid
            if (disciplineRemote.get(discipline.getId()) == null) {
                addActionError(getText("discipline.input.validation.invalidId"));
            }
        }
    }

    /**
     * Perform a validation in the update method
     */
    private void performValidateUpdate() {
        // verifies if the  is null
        if (discipline == null || Validators.isPositive(discipline.getId())) {
            addActionError(getText("discipline.edit.validation.requiredDiscipline"));
        } else {
            // verifies if this id is valid
            if (disciplineRemote.get(discipline.getId()) == null) {
                addActionError(getText("discipline.edit.validation.invalidId"));
            }
        }

        // verifies if the title is empty
        if (!StringUtils.hasText(discipline.getName())) {
            addActionError(getText("discipline.edit.validation.requiredName"));
        }
    }
    
    /**
     * Retrieves a Course
     * @return course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Sets a course
     * @param course
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Retrieves a Discipline
     * @return discipline
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * Sets a Discipline
     * @param discipline
     */
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    /**
     * Retrieves a List of Disciplines
     * @return disciplineList
     */
    public List<Discipline> getDisciplineList() {
        return disciplineList;
    }

    /**
     * Sets a List of Discipline
     * @param disciplineList
     */
    public void setDisciplineList(List<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }

    /**
     * Retrieves a Discipline Remote
     * @return disciplineRemote
     */
    public DisciplineRemote getDisciplineRemote() {
        return disciplineRemote;
    }

    /**
     * Sets a discipline Remote
     * @param disciplineRemote
     */
    public void setDisciplineRemote(DisciplineRemote disciplineRemote) {
        this.disciplineRemote = disciplineRemote;
    }

    /**
     * Retrieves a grade
     * @return grade
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * Sets a grade
     * @param grade
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * Retrieves a grade Remote
     * @return gradeRemote
     */
    public GradeRemote getGradeRemote() {
        return gradeRemote;
    }

    /**
     * Sets a grade Remote
     * @param gradeRemote
     */
    public void setGradeRemote(GradeRemote gradeRemote) {
        this.gradeRemote = gradeRemote;
    }

    /**
     * Retrieves a course Remote
     * @return courseRemote
     */
    public CourseRemote getCourseRemote() {
        return courseRemote;
    }

    /**
     * Sets a course Remote
     * @param courseRemote
     */
    public void setCourseRemote(CourseRemote courseRemote) {
        this.courseRemote = courseRemote;
    }

    /**
     * Retrieves a List of Units
     * @return unitList
     */
    public List<List> getUnitList() {
        return unitList;
    }

    /**
     * Sets a list of units
     * @param unitList
     */
    public void setUnitList(List<List> unitList) {
        this.unitList = unitList;
    }

    /**
     * Retrieves a Unit Remote
     * @return unitRemote
     */
    public UnitRemote getUnitRemote() {
        return unitRemote;
    }

    /**
     * Sets a unit remote
     * @param unitRemote
     */
    public void setUnitRemote(UnitRemote unitRemote) {
        this.unitRemote = unitRemote;
    }

    /**
     * Retrieves a Input Stream
     * @return inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Sets a input Stream
     * @param inputStream
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Retrieves a interface of Unit Content
     * @return unitContentRemote
     */
    public UnitContentRemote getUnitContentRemote() {
        return unitContentRemote;
    }

    /**
     * Sets the interface of unit content
     * @param unitContentRemote
     */
    public void setUnitContentRemote(UnitContentRemote unitContentRemote) {
        this.unitContentRemote = unitContentRemote;
    }

    /**
     * Retrieves a a system user remote
     * @return systemUserRemote
     */
    public SystemUserRemote getSystemUserRemote() {
        return systemUserRemote;
    }

    /**
     * Sets a System User Remote
     * @param systemUserRemote
     */
    public void setSystemUserRemote(SystemUserRemote systemUserRemote) {
        this.systemUserRemote = systemUserRemote;
    }

    /**
     * Retrieves a grade Id
     * @return gradeId
     */
    public String getGradeId() {
        return gradeId;
    }

    /**
     * Sets a grade id
     * @param gradeId
     */
    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    /**
     * Retrieves a id of the unti Content
     * @return unitContentId
     */
    public String getUnitContentId() {
        return unitContentId;
    }

    /**
     * Sets the id of the unit content
     * @param unitContentId
     */
    public void setUnitContentId(String unitContentId) {
        this.unitContentId = unitContentId;
    }

    /**
     * Retrieves a unit content
     * @return
     */
    public UnitContent getUnitContent() {
        return unitContent;
    }

    /**
     * Sets a unit content
     * @param unitContent
     */
    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }

    /**
     * Retrieves a a output Stream
     * @return outputStream
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Sets the output Stream
     * @param outputStream
     */
    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Retrieves a unit
     * @return unit
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets a unit
     * @param unit
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Unit a list of unit content
     * @return
     */
    public List<List> getUnitContentList() {
        return unitContentList;
    }

    /**
     * Sets a List of unit content
     * @param unitContentList
     */
    public void setUnitContentList(List<List> unitContentList) {
        this.unitContentList = unitContentList;
    }

    /**
     * Verifies if has a exam
     * @return true if has and false if hasn't
     */
    public boolean isHasExam() {
        return hasExam;
    }

    /**
     * Sets he hasExam variable
     * @param hasExam
     */
    public void setHasExam(boolean hasExam) {
        this.hasExam = hasExam;
    }

    /**
     * Verifies if has exercise
     * @return true if has and false if hasn't
     */
    public boolean isHasExercise() {
        return hasExercise;
    }

    /**
     * Sets the hasExercise variable
     * @param hasExercise
     */
    public void setHasExercise(boolean hasExercise) {
        this.hasExercise = hasExercise;
    }

    /**
     * Retrieves a List of unit disciplines
     * @return disciplineUnitList
     */
    public List<Unit> getDisciplineUnitList() {
        return disciplineUnitList;
    }

    /**
     * Sets a Discipline Unit List
     * @param disciplineUnitList
     */
    public void setDisciplineUnitList(List<Unit> disciplineUnitList) {
        this.disciplineUnitList = disciplineUnitList;
    }

}
