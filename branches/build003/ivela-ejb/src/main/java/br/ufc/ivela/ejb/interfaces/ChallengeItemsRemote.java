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
# File: ChallengeItemsRemote.java                                                                  #
# Document: Challenge Item Remote                                                                  #
# Date        - Author(Company)                   - Issue# - Summary                               #
# XX-XXX-XXXX - Marcus                            - XXXXXX - Initial Version                       #
# 07-OCT-2009 - Otofuji (Instituto Eldorado)      - 000017 - Review Course, Challenge refactory    #
##################################################################################################*/
package br.ufc.ivela.ejb.interfaces;

import java.util.List;

import javax.ejb.Remote;

import br.ufc.ivela.commons.model.ChallengeItems;
import br.ufc.ivela.commons.model.ChallengerResult;

@Remote
public interface ChallengeItemsRemote {

    /**
     * Method that is the insertion of a ChallengeItems
     * 
     * @param question
     * @return
     */
    public Long add(ChallengeItems course);

    /**
     * Method which represents the recovery of a ChallengeItems by identifier
     * 
     * @param id
     * @return a ChallengeItems
     */
    public ChallengeItems get(Long id);
    
    /**
     * Method which represents the recovery of a ChallengeItems by name
     * 
     * @param name
     * @return a ChallengeItems
     */
    public ChallengeItems get(String name);
    
    public List<ChallengeItems> getByUnit(Long unitId);
    
    public ChallengerResult getChallengerResult(Long id);
    
    public Boolean remove (ChallengeItems challengeItems);
    public Boolean update (ChallengeItems challengeItems);
    
    public List<ChallengerResult> getChallengerResultByUnitContent(Long unitContentId,Long studentId, Long gradeId);
    
    public ChallengeItems getByUnit(String name, Long unitId);
}

