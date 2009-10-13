/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.ContentPackageUtils;
import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.UnitContentRemote;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author emanuelle
 */
@Stateless(mappedName="UnitContentBean")
public class UnitContentBean implements UnitContentRemote {

    private GenericDao<UnitContent> daoUnitCont = DaoFactory.getInstance(UnitContent.class);
    String path = Constants.FILE_UPLOAD_PATH;
    

    public List<UnitContent> getByUnit(Long idUnit) {
        return daoUnitCont.getByFK("unitId", idUnit);
    }

    public List<UnitContent> getByUnitOrdered(Long idUnit) {
        return daoUnitCont.find("from UnitContent uc where uc.unitId = ? order by orderN", new Object[]{idUnit});
    }

    public UnitContent getByDisciplineAndTag(Long idDiscipline,String tag) {
    	UnitContent unitContent = null;
    	List<UnitContent> unitContents = daoUnitCont.find("select uc from UnitContent uc,Unit u where uc.unitId=u.id and u.disciplineId=? and uc.tag = ?",new Object[]{idDiscipline,tag}); 
    	if (!unitContents.isEmpty()) {
    		unitContent = unitContents.get(0);
    	}    	
    	return unitContent;
    }
 
    public Long add(UnitContent unitContent) {
        return (Long) daoUnitCont.save(unitContent);
    }

    public Boolean update(UnitContent unitContent) {
        return daoUnitCont.update(unitContent);
    }

    public UnitContent get(Long id) {
        return daoUnitCont.get(id);
    }  

    public boolean uploadPackage(File file, String fileName, String contentType) {
        String finalPath = ContentPackageUtils.UPLOAD_PATH + fileName;
        boolean ok = true;
        
        try {
            java.io.File f = new java.io.File(finalPath);
            InputStream data = new FileInputStream(file);
            OutputStream out = new FileOutputStream(f);
            byte buf[] = new byte[1024];
            int len;
            while ((len = data.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            data.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            
            ok = false;
        }
        
        return ok;
    }

    public boolean isUnitContentFinished(Long studentId, Long unitContentId, long gradeId){
        
        ExerciseBean exerciseBean = new ExerciseBean();
        ExamBean examBean = new ExamBean();
        
        int exeRes = exerciseBean.finishedExerciseForUnitContent(studentId, unitContentId, gradeId);
        int examRes = examBean.finishedExamsForUnitContent(studentId, unitContentId, gradeId);
        
        if(exeRes==examRes && examRes==1)
            return true;
        return false;
    }
    
    public boolean remove(UnitContent unitContent) {
        return daoUnitCont.remove(unitContent);
    }
}
