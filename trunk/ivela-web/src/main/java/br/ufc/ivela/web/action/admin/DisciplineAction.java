/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action.admin;

import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.web.action.*;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.commons.util.Validators;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.DisciplineRemote;
import br.ufc.ivela.ejb.interfaces.UnitContentRemote;
import br.ufc.ivela.ejb.interfaces.UnitRemote;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.SAXmyHtmlHandler;
import com.lowagie.text.pdf.PdfWriter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author rodrigo
 */
public class DisciplineAction extends GenericAction {

    private DisciplineRemote disciplineRemote;
    private CourseRemote courseRemote;
    private Discipline discipline;
    private Course course;
    private List<Discipline> disciplineList;
    private List<Unit> unitList;
    private UnitRemote unitRemote;
    private UnitContent unitContent;
    private UnitContentRemote unitContentRemote;
    private InputStream inputStream;

    /**
     * Remove a discipline, perform a validation.
     * @return if it does not have any error, return a string in the json format
     */
    public String removeDiscipline() {
        discipline = disciplineRemote.get(discipline.getId());
        discipline.setUnits(unitRemote.getByDiscipline(discipline.getId()));
        performValidateRemove();
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        if (!hasActionErrors()) {
            boolean result = disciplineRemote.remove(discipline);
            xStream.alias("boolean", Boolean.class);
            String json = xStream.toXML(new Boolean(result));
            json = json.replaceAll("boolean", "result");
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        } else {
            xStream.alias("boolean", Boolean.class);
            String json = xStream.toXML(new Boolean(false));
            json = json.replaceAll("boolean", "result");
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        }
        return "json";
    }

    /**
     * Add a new discipline, perform a validation.
     * @return if it does not have any error, return a string in the json format
     */
    public String addDiscipline() {
        //validates the add
        performValidateAdd();
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        if (!hasActionErrors()) {
            Long id = disciplineRemote.add(discipline);
            discipline = disciplineRemote.get(id);
            xStream.alias("discipline", Discipline.class);
            String json = xStream.toXML(discipline);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        } else {
            Discipline disciplineTemp = new Discipline();
            xStream.alias("discipline", Discipline.class);
            String json = xStream.toXML(disciplineTemp);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        }
        return "json";
    }

    /**
     * Updated a new discipline, perform a validation.
     * @return if it does not have any error, return a string in the json format
     */
    public String updateDiscipline() {
        performValidateAdd();
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        if (!hasActionErrors()) {
            boolean result = disciplineRemote.update(discipline);
            discipline = disciplineRemote.get(discipline.getId());
            xStream.alias("discipline", Discipline.class);
            String json = xStream.toXML(discipline);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        } else {
            Discipline disciplineTemp = new Discipline();
            xStream.alias("discipline", Discipline.class);
            String json = xStream.toXML(disciplineTemp);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        }
        return "json";
    }

    /**
     * Show in the format of pdf
     * @return a string int he pdf format
     */
    public String showPdf() {
        unitContent = unitContentRemote.get(unitContent.getId());
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
        /*
        try {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        ByteArrayInputStream inputStreamDescription = new ByteArrayInputStream("<p>html inválido</p>".getBytes());
        parser.parse(inputStreamDescription, new SAXmyHtmlHandler(document));
        setInputStream(new ByteArrayInputStream(outputStream.toByteArray()));
        } 
        catch (Exception ex) {
        
        }
         * */
        }
        return "pdf";
    }


    public String showHtml() {
        unitContent = unitContentRemote.get(unitContent.getId());

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            String string = unitContent.getDescription();
            ByteArrayInputStream inputStreamDescription = new ByteArrayInputStream(string.getBytes());

            byte[] buf = new byte[1024];
                        int count = 0;
                        while ((count = inputStreamDescription.read(buf)) >= 0) {
                            outputStream.write(buf, 0, count);
                        }

            

            setInputStream(new ByteArrayInputStream(outputStream.toByteArray()));

            inputStreamDescription.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "html";
    }




    /**
     * Add a new Discipline
     * @return a list of disciplines
     */
    public String add() {
        // validates the add
        performValidateAdd();
        // if it has some error
        if (hasActionErrors()) {
            logger.log("\n\nError on add");
            return input();
        }
        discipline.setCourse(course);
        disciplineRemote.add(discipline);
        return list();
    }

    /**
     * Remove a Discipline
     * @return a list of disciplines
     */
    public String remove() {
        discipline = disciplineRemote.get(discipline.getId());
        // validates the remove
        performValidateRemove();

        // if it has some error
        if (hasActionErrors()) {
            logger.log("\n\nError on remove");
            return list();
        }

        disciplineRemote.remove(discipline.getId());

        return list();
    }

    /**
     * Sets the variables to be used on the input Discipline
     * @return 
     */
    @Override
    public String input() {

        setCourse(courseRemote.get(course.getId()));

        return INPUT;
    }

    /**
     * Edit a discipline
     * @return a string 
     */
    public String edit() {

        setCourse(courseRemote.get(course.getId()));

        setDiscipline(disciplineRemote.get(discipline.getId()));

        return "edit";
    }

    /**
     * Update a discipline
     * @return list of disciplines
     */
    public String update() {
        // validates the update
        performValidateUpdate();

        logger.log("\n\nValidade update finished");

        // if it has some error
        if (hasActionErrors()) {
            logger.log("\n\nError on update");
            return "edit";
        }

        discipline.setCourse(course);

        disciplineRemote.update(discipline);

        return list();
    }

    /**
     * List all Discipline
     * @return a list of disciplines
     */
    public String list() {
        // validates the update
        performValidateList();

        logger.log("\n\nValidade list finished");

        // if it has some error
        if (hasActionErrors()) {
            logger.log("\n\nError on list");
            return "edit";
        }

        // retrieves and sets the course object
        setCourse(courseRemote.get(course.getId()));

        // retrieves the list of disciplines
        setDisciplineList(disciplineRemote.getByCourse(course.getId()));

        return "list";
    }

    /**
     * validates the data of the method add
     */
    private void performValidateAdd() {

        logger.log("\n\nValidating add");

        // verifies if the discipline is null
        if (discipline == null) {
            addActionError(getText("discipline.validation.requiredDiscipline"));
        } else {
            // verifies if the name is empty
            if (!Validators.hasText(discipline.getName())) {
                addActionError(getText("discipline.validation.requiredName"));
            }
        }
    }

    /**
     * Validates the data of the method update
     */
    public void performValidateUpdate() {

        logger.log("\n\nValidating update");

        // verifies if the discipline is null
        if (discipline == null) {
            addActionError(getText("discipline.validation.requiredDiscipline"));
        } else {
            // verifies if the discipline id is null
            if (course == null) {
                addActionError(getText("discipline.validation.requiredDiscipline"));
            } else {
                // verifies if the discipline id is null
                if (course.getId() == null) {
                    addActionError(getText("discipline.validation.requiredDisciplineId"));
                }
            }

            // verifies if this id is valid
            if (disciplineRemote.get(discipline.getId()) == null) {
                addActionError(getText("discipline.validation.invalidDiscipline"));
            }

            // verifies if the name is empty
            if (!Validators.hasText(discipline.getName())) {
                addActionError(getText("discipline.validation.requiredName"));
            }

        }
    }

    /**
     * Validates the data of the method remove
     */
    public void performValidateRemove() {

        logger.log("\n\nValidating remove");

        // verifies if the discipline is null
        if (discipline == null || discipline.getId() == null) {
            addActionError(getText("discipline.validation.requiredDiscipline"));
        } else {
            // verifies if this id is valid
            if (disciplineRemote.get(discipline.getId()) == null) {
                addActionError(getText("discipline.validation.invalidDiscipline"));
            }
        }
    }

    /**
     * Validates the data of the method remove
     */
    public void performValidateList() {

        logger.log("\n\nValidating list");

        // verifies if the discipline is null
        if (course == null) {
            addActionError(getText("discipline.validation.requiredCourse"));
        } else {
            // verifies if the discipline id is null
            if (course.getId() == null) {
                addActionError(getText("discipline.validation.requiredCourseId"));
            }
        }
    }

    /**
     * Retrieves infos about a unit
     * @return a string in the json format
     */
    public String getUnitsInfo() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("unit", Unit.class);
        List<Unit> results = unitRemote.getByDiscipline(discipline.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(results).getBytes()));
        return "json";
    }

    /**
     * Retrieves a info about a discipline in the json format
     * @return a string in the json format
     */
    public String getDisciplineInfo() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("discipline", Discipline.class);
        discipline = disciplineRemote.get(discipline.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(discipline).getBytes()));
        return "json";
    }

    /**
     * Sets the value of Discipline variable
     * @param discipline
     */
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    /**
     * Retrieves the value of discipline variable
     * @return discipline
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * Retrieves the value of disciplineRemote variable
     * @return disciplineRemote
     */
    public DisciplineRemote getDisciplineRemote() {
        return disciplineRemote;
    }

    /**
     * Sets the value of disciplineRemote variable
     * @param disciplineRemote
     */
    public void setDisciplineRemote(DisciplineRemote disciplineRemote) {
        this.disciplineRemote = disciplineRemote;
    }

    /**
     * Retrieves the value of disciplineList variable
     * @return disciplineList
     */
    public List<Discipline> getDisciplineList() {
        return disciplineList;
    }

    /**
     * Sets the value of disciplineList variable
     * @param disciplineList
     */
    public void setDisciplineList(List<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }

    /**
     * Retrieves the value of course variable
     * @return course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Sets the value of course variable
     * @param course
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Retrieves the value of courseRemote variable
     * @return courseRemote
     */
    public CourseRemote getCourseRemote() {
        return courseRemote;
    }

    /**
     * Sets the value of courseRemote variable
     * @param courseRemote
     */
    public void setCourseRemote(CourseRemote courseRemote) {
        this.courseRemote = courseRemote;
    }

    /**
     * Retrieves the value of content of a unit
     * @return unitContent
     */
    public UnitContent getUnitContent() {
        return unitContent;
    }

    /**
     * Sets a content unit
     * @param unitContent
     */
    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }

    /**
     * Retrieves a remote unit content
     * @return unitContentRemote
     */
    public UnitContentRemote getUnitContentRemote() {
        return unitContentRemote;
    }

    /**
     * Sets a remote unit content
     * @param unitContentRemote
     */
    public void setUnitContentRemote(UnitContentRemote unitContentRemote) {
        this.unitContentRemote = unitContentRemote;
    }

    /**
     * Retrieves a input stream
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
     * Retrieves a list of units
     * @return unitList
     */
    public List<Unit> getUnitList() {
        return unitList;
    }

    /**
     * Sets a list of unit 
     * @param unitList
     */
    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }

    /**
     * Retrieves a remote unit
     * @return unitRemote
     */
    public UnitRemote getUnitRemote() {
        return unitRemote;
    }

    /**
     * Sets a remote unit
     * @param unitRemote
     */
    public void setUnitRemote(UnitRemote unitRemote) {
        this.unitRemote = unitRemote;
    }
}