/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.UserVoice;
import javax.ejb.Remote;

/**
 *
 * @author jefferson
 */
@Remote
public interface UserVoiceRemote {
    
    public Long add(UserVoice userVoice);
    public void addWordVoiceForUserVoice(Long userVoiceId, String word, int tries);
    public void addVoiceFeedBack(int mark, String coment);
    public Long addNpdUser();
    public void addNpdPhraseForNpdUser(Long npdUserid, Integer phrase, int tries);
}
