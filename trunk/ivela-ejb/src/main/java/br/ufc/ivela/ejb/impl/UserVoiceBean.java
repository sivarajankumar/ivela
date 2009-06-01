/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.NpdPhrase;
import br.ufc.ivela.commons.model.NpdUser;
import br.ufc.ivela.commons.model.UserVoice;
import br.ufc.ivela.commons.model.VoiceFeedBack;
import br.ufc.ivela.commons.model.WordVoice;
import br.ufc.ivela.ejb.interfaces.UserVoiceRemote;
import javax.ejb.Stateless;

/**
 *
 * @author jefferson
 */
@Stateless(mappedName="UserVoiceBean")
public class UserVoiceBean implements UserVoiceRemote{

    private GenericDao<UserVoice> daoUserVoice = DaoFactory.getInstance(UserVoice.class);
    private GenericDao<WordVoice> daoWordVoice = DaoFactory.getInstance(WordVoice.class);
    private GenericDao<NpdUser> daoNpdUser = DaoFactory.getInstance(NpdUser.class);
    private GenericDao<NpdPhrase> daoNpdPhrase = DaoFactory.getInstance(NpdPhrase.class);
    private GenericDao<VoiceFeedBack> daoVoiceFeedBack = DaoFactory.getInstance(VoiceFeedBack.class);
    
    public Long add(UserVoice userVoice) {
       return  (Long)daoUserVoice.save(userVoice);
    }

    public void addWordVoiceForUserVoice(Long userVoiceId, String word, int tries){
        
        UserVoice userVoice = daoUserVoice.get(userVoiceId);
        if(userVoice!=null){
        
            WordVoice wordVoice = new WordVoice();
            wordVoice.setWord(word);
            wordVoice.setTries(tries);
            wordVoice.setUserVoice(userVoice);
            
            daoWordVoice.save(wordVoice);
        }
        
        
    }
    
    public Long addNpdUser() {
       return  (Long)daoNpdUser.save(new NpdUser());
    }

    public void addNpdPhraseForNpdUser(Long npdUserid, Integer phrase, int tries){
        
        NpdUser npdUser = daoNpdUser.get(npdUserid);
        if(npdUser!=null){
        
            NpdPhrase npdPhrase = new NpdPhrase();
            npdPhrase.setNpdUser(npdUser);
            npdPhrase.setPhrase(phrase);
            npdPhrase.setTries(tries);
             
            daoNpdPhrase.save(npdPhrase); 
        }
        
        
    }
    
    public void addVoiceFeedBack(int mark, String coment){
        VoiceFeedBack voiceFeedBack = new VoiceFeedBack();
        voiceFeedBack.setComent(coment);
        voiceFeedBack.setMark(mark);
        daoVoiceFeedBack.save(voiceFeedBack);
    }
}
