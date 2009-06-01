/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action.admin;

import br.ufc.ivela.web.action.*;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.commons.util.Validators;
import br.ufc.ivela.ejb.interfaces.DisciplineRemote;
import br.ufc.ivela.ejb.interfaces.UnitContentRemote;
import br.ufc.ivela.ejb.interfaces.UnitRemote;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author rodrigo
 */
public class UnitAction extends GenericAction {

    private UnitRemote unitRemote;
    private DisciplineRemote disciplineRemote;
    private Unit unit;
    private Discipline discipline;
    private List<Unit> unitList;
    private UnitContentRemote unitContentRemote;
    private InputStream inputStream;
    private UnitContent unitContent;
    private XStream xStream = new XStream(new JettisonMappedXmlDriver());
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    /**
     * Remove a unit, perform a validation, 
     * if hasn't errors add a new unit, 
     * if has return error
     * @return a string in json format
     */
    public String removeUnit() {
        unit = unitRemote.get(unit.getId());
        unit.setUnitContents(unitContentRemote.getByUnitOrdered(unit.getId()));
        performValidationRemove();
        if (!hasActionErrors()) {
            boolean result = unitRemote.remove(unit);
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

    private String treatHtml(String html) {
        html = html.replaceAll("&nbsp;", " ");
        //html = removeAnchor(html);
        return html;
    }
    
    private String removeAnchor(String text) {
        while (text.toLowerCase().indexOf("<a") > -1) {
            int i = text.toLowerCase().indexOf("<a");
            if (i != -1) {
                int f = -1;
                char[] ch = text.toCharArray();
                for (int c = 0; c < ch.length; c++) {
                    if (ch[c] == '>' && c > i) {
                        f = c;
                        break;
                    }
                }
                text = text.substring(0, i) + text.substring(f + 1);
                text = text.replaceAll("</a>", "");
                text = text.replaceAll("</A>", "");
            }
        }
        return text;
    }

    
    /**
     * Add a new content of a unit
     * @return
     */
    public String addUnitContent() {
        //validates the add
        //performValidationAdd();
        if (!hasActionErrors()) {
            unitContent.setDescription("<html><body>" + treatHtml(unitContent.getDescription()) + "</body></html>");
            List<UnitContent> unitContents = unitContentRemote.getByUnit(this.unitContent.getUnitId());
            int order_n = unitContent.getOrderN();
            for (UnitContent uc : unitContents) {
                if (uc.getOrderN() >= order_n) {
                    uc.setOrderN(uc.getOrderN() + 1);
                    unitContentRemote.update(uc);
                }
            }            
            Long id = unitContentRemote.add(unitContent);
            setMessage((id != null) + "");
        }
//            unitContent = unitContentRemote.get(id);
//            xStream.alias("unitContent", UnitContent.class);
//            String json = xStream.toXML(unitContent);
//            setInputStream(new ByteArrayInputStream(json.getBytes()));
//        } else {
//            UnitContent unitContentTemp = new UnitContent();
//            xStream.alias("unitContent", UnitContent.class);
//            String json = xStream.toXML(unitContentTemp);
//            setInputStream(new ByteArrayInputStream(json.getBytes()));
//        }
        return "courses";
    }

    /**
     * Update a unit content
     * @return a string int the json format
     */
    public String updateUnitContent() {
        //performValidationAdd();

        if (!hasActionErrors()) {
            unitContent.setDescription("<html><body>" + treatHtml(unitContent.getDescription()) + "</body></html>");
            
            List<UnitContent> unitContents = unitContentRemote.getByUnit(this.unitContent.getUnitId());
            int order_n = this.unitContent.getOrderN();
            for (UnitContent uc : unitContents) {
                if (uc.getOrderN() >= order_n) {
                    uc.setOrderN(uc.getOrderN() + 1);
                    unitContentRemote.update(uc);
                }
            }
            
            boolean result = unitContentRemote.update(unitContent);
            setMessage(result + "");
            //unitContent = unitContentRemote.get(unitContent.getId());
        }
            //xStream.alias("unitContent", UnitContent.class);
            //String json = xStream.toXML(unitContent);
            //setInputStream(new ByteArrayInputStream(json.getBytes()));
        //} else {
            //UnitContent unitTemp = new UnitContent();
            //xStream.alias("unitContent", UnitContent.class);
            //String json = xStream.toXML(unitTemp);
            //setInputStream(new ByteArrayInputStream(json.getBytes()));
        //}
        return "courses";
    }

    /**
     * Add a new unit, perform a validation
     * if hasn't errors add a new unit
     * if has return error
     * @return return a string in the json format
     */
    public String addUnit() {
        //validates the add
        performValidationAdd();

        if (!hasActionErrors()) {
            Long id = unitRemote.add(unit);
            unit = unitRemote.get(id);
            xStream.alias("unit", Unit.class);
            String json = xStream.toXML(unit);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        } else {
            Unit unitTemp = new Unit();
            xStream.alias("unit", Unit.class);
            String json = xStream.toXML(unitTemp);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        }
        return "json";
    }

    /**
     * Update a unit
     * @return a string in json format
     */
    public String updateUnit() {
        performValidationAdd();

        if (!hasActionErrors()) {
            boolean result = unitRemote.update(unit);
            unit = unitRemote.get(unit.getId());
            xStream.alias("unit", Unit.class);
            String json = xStream.toXML(unit);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        } else {
            Unit unitTemp = new Unit();
            xStream.alias("unit", Unit.class);
            String json = xStream.toXML(unitTemp);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        }
        return "json";
    }

    public String updateActive(){
        Unit u = unitRemote.get(unit.getId());
        u.setActive(unit.isActive());
        String json = "{result:"+unitRemote.update(u)+"}";
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }
    
    /**
     * Add a new Unit
     * @return
     */
    public String add() {
        // validates the add
        performValidationAdd();

        // if it has some error
        if (hasActionErrors()) {
            logger.log("\n\nError on add");
            return input();
        }

        unit.setDiscipline(discipline);
        unitRemote.add(unit);

        return list();
    }

    /**
     * Remove a Unit
     * @return
     */
    public String remove() {
        // validates the remove
        performValidationRemove();

        // if it has some error
        if (hasActionErrors()) {
            logger.log("\n\nError on remove");
            return list();
        }

        unitRemote.remove(unit.getId());

        return list();
    }

    /**
     * Sets the variables to be used on the input Unit
     * @return 
     */
    @Override
    public String input() {
        setDiscipline(disciplineRemote.get(discipline.getId()));

        return INPUT;
    }

    /**
     * Edit a unit
     * @return
     */
    public String edit() {
        setUnit(unitRemote.get(unit.getId()));
        setDiscipline(unit.getDiscipline());

        return "edit";
    }

    /**
     * Update a unit
     * @return a string
     */
    public String update() {
        // validates the update
        performValidationUpdate();

        logger.log("\n\nValidade update finished");

        // if it has some error
        if (hasActionErrors()) {
            logger.log("\n\nError on update");
            return "edit";
        }

        unit.setDiscipline(discipline);

        unitRemote.update(unit);

        return list();
    }

    /**
     * List all Unit
     */
    public String list() {
        // validates the update
        performValidationList();

        logger.log("\n\nValidade list finished");

        // if it has some error
        if (hasActionErrors()) {
            logger.log("\n\nError on list");
            return "edit";
        }

        // retrieves the discipline object
        setDiscipline(disciplineRemote.get(discipline.getId()));

        // retrieves the list of units
        setUnitList(unitRemote.getByDiscipline(discipline.getId()));

        return "list";
    }

    public String getUnitsContentInfo() {
        xStream.alias("unitContent", UnitContent.class);
        List<UnitContent> results = unitContentRemote.getByUnitOrdered(unit.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(results).getBytes()));
        return "json";
    }

    public String getUnitInfo() {
        xStream.alias("unit", Unit.class);
        unit = unitRemote.get(unit.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(unit).getBytes()));
        return "json";
    }

    public String getUnitContentInfo() {
        xStream.alias("unitContent", UnitContent.class);
        unitContent = unitContentRemote.get(unitContent.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(unitContent).getBytes()));
        return "json";
    }

    /**
     * validates the data of the method add
     */
    private void performValidationAdd() {

        logger.log("\n\nValidating add");

        // verifies if the unit is null
        if (unit == null) {
            addActionError(getText("unit.validation.requiredUnit"));
        } else {
            // verifies if the name is empty
            if (!Validators.hasText(unit.getName())) {
                addActionError(getText("unit.validation.requiredName"));
            }
        }
    }

    /**
     * validates the data of the method update
     */
    public void performValidationUpdate() {

        logger.log("\n\nValidating update");

        // verifies if the unit is null
        if (unit == null) {
            addActionError(getText("unit.validation.requiredUnit"));
        } else {
            // verifies if the discipline id is null
            if (discipline == null) {
                addActionError(getText("unit.validation.requiredDiscipline"));
            } else {
                // verifies if the discipline id is null
                if (discipline.getId() == null) {
                    addActionError(getText("unit.validation.requiredDisciplineId"));
                }
            }

            // verifies if this id is valid
            if (unitRemote.get(unit.getId()) == null) {
                addActionError(getText("unit.validation.invalidUnit"));
            }

            // verifies if the name is empty
            if (!Validators.hasText(unit.getName())) {
                addActionError(getText("unit.validation.requiredName"));
            }

        }
    }

    /**
     * validates the data of the method remove
     */
    public void performValidationRemove() {

        logger.log("\n\nValidating remove");

        // verifies if the unit is null
        if (unit == null || unit.getId() == null) {
            addActionError(getText("unit.validation.requiredUnit"));
        } else {
            // verifies if this id is valid
            if (unitRemote.get(unit.getId()) == null) {
                addActionError(getText("unit.validation.invalidUnit"));
            }
        }
    }

    /**
     * validates the data of the method list
     */
    public void performValidationList() {

        logger.log("\n\nValidating list");

        // verifies if the discipline is null
        if (discipline == null) {
            addActionError(getText("unit.validation.requiredDiscipline"));
        } else {
            // verifies if the discipline id is null
            if (discipline.getId() == null) {
                addActionError(getText("unit.validation.requiredDisciplineId"));
            }
        }
    }

    /**
     * Retrieves the content of a unit
     * @return unitContent
     */
    public UnitContent getUnitContent() {
        return unitContent;
    }

    /**
     * Sets the content of a unit
     * @param unitContent
     */
    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }

    /**
     * Retrieves a input stream
     * @return inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Sets a input stream
     * @param inputStream
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
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
     * Sets the value of Unit variable
     * @param unit
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Retrieves the value of unit variable
     * @return unit
     */
    public Unit getUnit() {
        return unit;
    }
    

    /**
     * Retrieves the value of unitRemote variable
     * @return unitRemote
     */
    public UnitRemote getUnitRemote() {
        return unitRemote;
    }

    /**
     * Sets the value of unitRemote variable
     * @param unitRemote
     */
    public void setUnitRemote(UnitRemote unitRemote) {
        this.unitRemote = unitRemote;
    }

    /**
     * Retrieves the value of unitList variable
     * @return unitList
     */
    public List<Unit> getUnitList() {
        return unitList;
    }

    /**
     * Sets the value of unitList variable
     * @param unitList
     */
    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }

    /**
     * Retrieves the value of discipline variable
     * @return disciplie
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * Sets the value of discipline variable
     * @param discipline
     */
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    /**
     * Retrieves the value of discipline variable
     * @return disciplineRemote
     */
    public DisciplineRemote getDisciplineRemote() {
        return disciplineRemote;
    }

    /**
     * Sets the value of discipline variable
     * @param discipline
     */
    public void setDisciplineRemote(DisciplineRemote disciplineRemote) {
        this.disciplineRemote = disciplineRemote;
    }

    /**
     * Retrieves a XStream 
     * @return
     */
    public XStream getXStream() {
        return xStream;
    }

    /**
     * Sets a Xstream
     * @param xStream
     */
    public void setXStream(XStream xStream) {
        this.xStream = xStream;
    }
}

