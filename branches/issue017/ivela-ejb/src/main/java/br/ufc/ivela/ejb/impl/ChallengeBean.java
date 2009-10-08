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
# File: ChallengeBean.java                                                                         #
# Document: Challenge Bean                                                                         #
# Date        - Author(Company)                   - Issue# - Summary                               #
# XX-XXX-XXXX - Jose Damico                       - XXXXXX - Initial Version                       #
# 07-OCT-2009 - Otofuji (Instituto Eldorado)      - 000017 - Review Course, Challenge refactory    #
##################################################################################################*/
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Challenge;
import br.ufc.ivela.ejb.interfaces.ChallengeRemote;
import java.util.List;
import javax.ejb.Stateless;


@Stateless(mappedName="ChallengeBean")
public class ChallengeBean implements ChallengeRemote {


    private GenericDao<Challenge> daoChallenge = DaoFactory.getInstance(Challenge.class);

    public Long add(Challenge challenge) {
        Challenge c = get(challenge.getChallid(), challenge.getUid());
        if (c == null)
            return (Long) daoChallenge.save(challenge);
        else {
            c.setChallvalue(challenge.getChallvalue());
            c.setRetries(c.getRetries() + 1);
            if (update(c))
                return challenge.getId();
        }
        return null;
    }
    
    public Challenge get(String challid, long uid) {
        if (challid == null || challid.trim().length() < 1)
            return null;
        Object[] params = new Object[]{challid, uid};
        Challenge challengeResult = null;
        List list = daoChallenge.find("from Challenge n WHERE n.challid = ? and n.uid = ?", params);
        
        if (list != null && list.size() > 0) {
            try{
                challengeResult = ((Challenge) list.get(0));
            } catch (Exception e) {
                challengeResult = null;
            }
        }

        return challengeResult;            
    }
    
    public Challenge get(Long uid, Long challengeItemId) {
        if (uid == null || challengeItemId == null) return null;
        
        Object[] params = new Object[]{uid, challengeItemId};
        Challenge challengeResult = null;
        List list = daoChallenge.find("from Challenge n WHERE n.uid = ? and n.challengeId = ?", params);
        
        if (list != null && list.size() > 0) {
            try{
                challengeResult = ((Challenge) list.get(0));
            } catch (Exception e) {
                challengeResult = null;
            }
        }

        return challengeResult;        
    }
    
    private boolean update(Challenge challenge) {
        return  daoChallenge.update(challenge);
    }

    public Long add(String challid, long uid, double challvalue, long challengeItemId) {
        List<Challenge> list = daoChallenge.find("select c from Challenge c where c.challid = ? and c.uid = ? and c.challengeId = ?", new Object[]{challid,uid, challengeItemId});        
        if(list!=null && list.size() > 0){
            Challenge chal = list.get(0);
            chal.setChallvalue(challvalue);
            chal.setRetries(chal.getRetries() + 1);
            this.update(chal);
            return list.get(0).getId();
        }
        else{        
            Challenge chall =  new Challenge();
            chall.setChallid(challid);
            chall.setUid(uid);
            chall.setChallvalue(challvalue);
            chall.setChallengeId(challengeItemId);
            return add(chall);
            
        }
    }


    public double getChallValue(String challid, long uid) {

        Object[] params = new Object[]{challid, uid};

        List list = daoChallenge.find("from Challenge n WHERE n.challid = ? and ? and n.uid = ?", params);
        
        if (list != null && list.size() == 1) {
            
            return ((Challenge) list.get(0)).getChallvalue();
            
        }
        
        return -1;        
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
 
}
