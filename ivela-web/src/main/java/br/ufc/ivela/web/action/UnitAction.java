/*    
#############################################################################################
# Copyright(c) 2009 by IBM Brasil Ltda and others                                           #
# This file is part of ivela project, an open-source                                        #
# Program URL   : http://code.google.com/p/ivela/                                           #  
#                                                                                           #
# This program is free software; you can redistribute it and/or modify it under the terms   #
# of the GNU General Public License as published by the Free Software Foundation; either    #
# version 3 of the License, or (at your option) any later version.                          #
#                                                                                           #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. #
# See the GNU General Public License for more details.                                      #  
#                                                                                           #
#############################################################################################
# File: UnitAction.java                                                                     #
# Document: Action for Units                                                                # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - Maristella Myrian                 - XXXXXX - Initial Version                #
# 24-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Fixes                  #
#############################################################################################
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

public class UnitAction extends GenericAction {

    private UnitRemote unitRemote;
    private Unit unit;
    private Discipline discipline;
    private List<Unit> unitList;
    private InputStream inputStream;

    /**
     * List all Unit of 
     */
    public String list() {
        List<Unit> unitList = unitRemote.getAll();
        setUnitList(unitList);
        return "list";
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


