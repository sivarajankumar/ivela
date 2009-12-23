/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Enrollment;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author emanuelle
 */
@Remote
public interface EnrollmentRemote {
    public Long add(Enrollment enrollment);
    public boolean update(Enrollment enrollment);
    public Enrollment get(Long id);
    public List<Enrollment> getByUserAndStatus(Long idUser, int status);
    public List<Enrollment> getByUser(Long idUser);
    public List<Enrollment> getByGrade(Long idGrade);
    public List<Enrollment> getByStatus(int status);
    public boolean remove(Long id);
    public boolean getByUserGrade(Long userId, Long gradeId);
    public boolean remove(Long userId, Long gradeId);

}
