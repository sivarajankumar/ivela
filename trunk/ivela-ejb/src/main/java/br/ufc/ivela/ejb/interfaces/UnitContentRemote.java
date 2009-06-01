/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.UnitContent;
import java.io.File;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author emanuelle
 */
@Remote
public interface UnitContentRemote {

    public List<UnitContent> getByUnit(Long idUnit);

    public List<UnitContent> getByUnitOrdered(Long idUnit);

    public Long add(UnitContent unitContent);
    
    public Boolean update(UnitContent unitContent);
    
    public UnitContent get(Long id);
    
    public boolean remove(UnitContent unitContent);
    
    public boolean uploadPackage(File file, String fileName, String contentType);  
    
    public boolean isUnitContentFinished(Long studentId, Long unitContentId, long gradeId);
}
