/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.UnitRemote;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author Maristella Myrian
 */
public class UnitAction extends GenericAction {

    private UnitRemote unitRemote;
    private Unit unit;
    private Discipline discipline;
    private List<Unit> unitList;
    private InputStream inputStream;

    /**
     * Add a new Unit by discipline
     */
    public String add() {
        discipline = unit.getDiscipline();
        unitRemote.add(unit);
        return listByDiscipline();
    }

    /**
     * Remove a Unit of one discipline
     */
    public String remove() {
        unitRemote.remove(unit.getId());
        return listByDiscipline();
    }

    /**
     * Sets the variables to be used on the input Unit
     */
    @Override
    public String input() {
        return INPUT;
    }

    /**
     * List all Unit of 
     */
    public String list() {
        List<Unit> unitList = unitRemote.getAll();
        setUnitList(unitList);
        return "list";
    }

    /**
     * Edit a unit
     * @return edit
     */
    public String edit() {
        Unit aux = unitRemote.get(unit.getId());
        setUnit(aux);
        return "edit";
    }

    /**
     * List all unit by one discipline
     * @return a list
     */
    public String listByDiscipline() {
        List<Unit> unitList = unitRemote.getByDiscipline(discipline.getId());
        setUnitList(unitList);
        return "list";
    }

    /**
     * List in json form all unit from one discipline
     * @return
     */
    public String listJsonByDiscipline() {
        List<Unit> unitList = unitRemote.getByDisciplineOpen(discipline.getId());
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("units", List.class);
        xStream.alias("unit", Unit.class);
        if (unitList != null && unitList.size() > 0) {
            setInputStream(new ByteArrayInputStream(xStream.toXML(unitList).getBytes()));
        } else {
            xStream.alias("units", String.class);
            setInputStream(new ByteArrayInputStream(xStream.toXML("").getBytes()));
        }
        return "json";
    }

    /**
     * Sets the value of Unit variable
     * @param unit
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Retrives the value of unit variable
     * @return unit
     */
    public Unit getUnit() {
        return unit;
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
     * Retrives the value of unitRemote variable
     * @return unitRemote
     */
    public UnitRemote getUnitRemote() {
        return unitRemote;
    }

    /**
     *Sets the value of unitRemote variable
     * @param unitRemote
     */
    public void setUnitRemote(UnitRemote unitRemote) {
        this.unitRemote = unitRemote;
    }

    /**
     * Retrives the value of unitList variable
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
     * Update a unit
     * @return a string
     */
    public String update() {
        discipline = unit.getDiscipline();
        unitRemote.update(unit);
        return listByDiscipline();
    }

    /**
     * Retrieves a discipline
     * @return discipline
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * Sets a discipline
     * @param discipline
     */
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    
    public String getUnitJson() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("unitContent", UnitContent.class);
        xStream.alias("unit", Unit.class);
        xStream.alias("discipline", Discipline.class);
        unit = unitRemote.get(unit.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(unit).getBytes()));
        return "json";
    }
}


