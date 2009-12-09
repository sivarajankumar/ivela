package br.ufc.ivela.commons.challenger.dataobject;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores entire ContentPackage data for a given PayLoad
 * @see {@link ContentPackage}
 * @author Leonardo Moreira - leoomoreira@gmail.com (23Sep2008)
 *
 */
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
