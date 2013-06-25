/**
 * @(#)ContentPackage.java
 *
 * Eldorado Confidential and Proprietary Information
 * Copyright (C)2010 Eldorado, All Rights Reserved
 *
 * Hist�rico de Modifica��es
 *
 * Data           Quem              Descri��o
 * ------------------------------------------------------------------------------------------------
 * 07 May 2010    Rafael Lagoa      (3626)Importa��o de estrutura do Ivela para Challenges.  
 */
package org.ivela.offline.challenger;

import java.util.ArrayList;
import java.util.List;

public class ContentPackage {
	
    private String course = null;
    private String discipline = null;
    private String unit = null;
    private String unitContent = null;
    private List<FileSystem> fileSystemList = new ArrayList<FileSystem>();

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public List<FileSystem> getFileSystemList() {
        return fileSystemList;
    }

    public void setFileSystemList(List<FileSystem> fileSystemList) {
        this.fileSystemList = fileSystemList;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitContent() {
        return unitContent;
    }

    public void setUnitContent(String unitContent) {
        this.unitContent = unitContent;
    }
}