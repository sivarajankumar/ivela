/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Challenge;
import br.ufc.ivela.ejb.interfaces.ChallengeRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author jdamico
 */
@Stateless(mappedName="ChallengeBean")
public class ChallengeBean implements ChallengeRemote {


    private GenericDao<Challenge> daoChallenge = DaoFactory.getInstance(Challenge.class);

    public Long add(Challenge challenge) {
        Challenge c = get(challenge.getChallid(), challenge.getUid());
        if (c == null)
            return (Long) daoChallenge.save(challenge);
        else {
            c.setChallvalue(challenge.getChallvalue());
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
    
    private boolean update(Challenge challenge) {
        return  daoChallenge.update(challenge);
    }

    public Long add(String challid, long uid, double challvalue) {
        List<Challenge> list = daoChallenge.find("select c from Challenge c where c.challid = ? and c.uid = ? ", new Object[]{challid,uid});
        System.out.print("dsfsd");
        if(list!=null && list.size() > 0){
            list.get(0).setChallvalue(challvalue);
            this.update(list.get(0));
            return list.get(0).getId();
        }
        else{
        
            Challenge chall =  new Challenge();
            chall.setChallid(challid);
            chall.setUid(uid);
            chall.setChallvalue(challvalue);
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
