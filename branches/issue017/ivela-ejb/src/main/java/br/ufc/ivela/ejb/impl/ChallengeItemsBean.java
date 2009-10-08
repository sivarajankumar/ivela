/*##################################################################################################
# Copyright(c) 2008-2009 by IBM Brasil Ltda and others                                             #
# This file is part of ivela project, an open-source                                               #
# Program URL   : http://code.google.com/p/ivela/                                                  #
#                                                                                                  #
# This program is free software; you can redistribute it and/or modify it under the terms          #
# of the GNU General Public License as published by the Free Software Foundation; either           #
# version 3 of the License, or (at your option) any later version.                                 #
#                                                                                                  #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;        #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.        #
# See the GNU General Public License for more details.                                             #
#                                                                                                  #
####################################################################################################
# File: ChallengeItemsBean.java                                                                    #
# Document: Challenge Item Bean                                                                    #
# Date        - Author(Company)                   - Issue# - Summary                               #
# XX-XXX-XXXX - Marcus                            - XXXXXX - Initial Version                       #
# 07-OCT-2009 - Otofuji (Instituto Eldorado)      - 000017 - Review Course, Challenge refactory    #
##################################################################################################*/
package br.ufc.ivela.ejb.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.ChallengeItems;
import br.ufc.ivela.commons.model.ChallengerResult;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.ejb.interfaces.ChallengeItemsRemote;

@Stateless(mappedName="ChallengeItemsBean")
public class ChallengeItemsBean implements ChallengeItemsRemote {

    private GenericDao<ChallengeItems> daoChallengeItems = DaoFactory.getInstance(ChallengeItems.class);
    private GenericDao<ChallengerResult> daoChallengeResult = DaoFactory.getInstance(ChallengerResult.class);

    public Long add(ChallengeItems challengeItems) {
        return (Long) daoChallengeItems.save(challengeItems);
    }

    public ChallengeItems get(Long id) {
        if (id == null) {
            return null;
        }
        return daoChallengeItems.get(id);
    }

    public Boolean remove (ChallengeItems challengeItems){
        return daoChallengeItems.remove(challengeItems);
    }
    
    public Boolean update (ChallengeItems challengeItems){
        return daoChallengeItems.update(challengeItems);
    }
    
    public ChallengeItems get(String name) {
        if (name == null) {
            return null;
        }
        
        List<ChallengeItems> results = daoChallengeItems.find("from ChallengeItems c where c.name = ?", new Object[]{name});
        
        if(results != null && results.size() > 0){
            return results.get(0);
        } else {
            return null;
        }
    }
    
    public ChallengeItems getByUnit(String name, Long unitId) {
        if (name == null || unitId == null) {
            return null;
        }
                
        Unit unit = new Unit();
        unit.setId(unitId);
        List<ChallengeItems> results = daoChallengeItems.find("from ChallengeItems c where c.name = ? and c.unit = ?", new Object[]{name, unit});
        
        if(results != null && results.size() > 0){
            return results.get(0);
        } else {
            return null;
        }
    }
    
    public List<ChallengeItems> getByUnit(Long unitId){
        return (List<ChallengeItems>)daoChallengeItems.find("from ChallengeItems c where c.unit.id=?", new Object[]{unitId});
    }
    public ChallengerResult getChallengerResult(Long id){
        return daoChallengeResult.get(id);
    }
    
    public List<ChallengerResult> getChallengerResultByUnitContent(Long unitContentId,Long studentId, Long gradeId){
        return daoChallengeResult.find("from ChallengerResult ch where ch.unitContent.id =? and ch.student.id =? and ch.grade.id =? ", new Object[]{unitContentId,studentId,gradeId});    
    }
}
