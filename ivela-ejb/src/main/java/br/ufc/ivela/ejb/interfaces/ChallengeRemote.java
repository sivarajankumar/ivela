/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Challenge;
import javax.ejb.Remote;

/**
 *
 * @author jdamico
 */
@Remote
public interface ChallengeRemote {

    public Long add(Challenge challenge);

    public Long add(String challid, long uid, double challvalue);
    
    public Challenge get(String challid, long uid);
    
}