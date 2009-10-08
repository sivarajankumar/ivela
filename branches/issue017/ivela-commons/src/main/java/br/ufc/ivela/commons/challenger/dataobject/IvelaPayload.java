package br.ufc.ivela.commons.challenger.dataobject;

import java.util.List;

import br.ufc.ivela.commons.challenger.dataobject.ContentPackage;
import br.ufc.ivela.commons.challenger.util.Challenge;

/**
 * Fundamental element of {@link IvelaObj}
 * Stores Challenge details
 * @see {@link ChallengeDesc}
 * @see {@link ChallengeRef}
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public class IvelaPayload {

	private ChallengeRef challengeRef = null;
	private ChallengeDesc challengeDesc = null;
        

    private Challenge challenge = null;
    private List<Challenge> listChallenge = null;
    
        private ContentPackage contentPackage = null;

        public ContentPackage getContentPackage() {
            return contentPackage;
        }

        public void setContentPackage(ContentPackage contentPackage) {
            this.contentPackage = contentPackage;
        }

	public ChallengeRef getChallengeRef() {
		return challengeRef;
	}

	public void setChallengeRef(ChallengeRef challengeRef) {
		this.challengeRef = challengeRef;
	}

	public ChallengeDesc getChallengeDesc() {
		return challengeDesc;
	}

	public void setChallengeDesc(ChallengeDesc challengeDesc) {
		this.challengeDesc = challengeDesc;
	}	

   public List<Challenge> getListChallenge() {
       return listChallenge;
   }

   public void setListChallenge(List<Challenge> listChallenge) {
       this.listChallenge = listChallenge;
   }

   public Challenge getChallenge() {
       return challenge;
   }

   public void setChallenge(Challenge challenge) {
       this.challenge = challenge;
   }

	
	
}
