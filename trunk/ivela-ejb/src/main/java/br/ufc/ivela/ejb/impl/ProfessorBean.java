/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Profile;
import br.ufc.ivela.ejb.interfaces.ProfessorRemote;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Rodrigo Félix de Almeida / Leonardo Oliveira Moreira
 */
@Stateless
public class ProfessorBean implements ProfessorRemote {

    private GenericDao<SystemUser> daoSystemUser = DaoFactory.getInstance(SystemUser.class);

    public List<SystemUser> getAll() {
        return daoSystemUser.find("from SystemUser su where su.id IN (select p.systemUser.id from Professor p group by p.systemUser.id and p.systemUser.enabled = true)", null);
    }

    public List<Grade> getGradeListByProfessorSystemUser(Long systemUserId) {
        return daoSystemUser.find("from Grade g where g.id = (select distinct p.grade.id from Professor p where p.systemUser.id = ? and p.systemUser.enabled = true)", new Object[] { systemUserId });
    }

    
    
}