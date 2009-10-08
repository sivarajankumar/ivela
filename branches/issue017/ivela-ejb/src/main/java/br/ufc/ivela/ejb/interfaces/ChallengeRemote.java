/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import java.util.Map;

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
    
    public Long add(String challid, long uid, double challvalue, long challengeItemId);
    
    public Challenge get(String challid, long uid);
    
    public Challenge get(Long uid, Long challengeItemId);
    
    public String executeChallenge(String challid, Long userId, Long unidId, Long gradeId, Map userAnswers);
    
    public String retrieveChallengeAnswers(String challid, Long userId, Long unidId, Long gradeId);
    
}
