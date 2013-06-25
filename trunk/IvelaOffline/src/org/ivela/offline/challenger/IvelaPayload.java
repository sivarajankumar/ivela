/**
 * @(#)IvelaPayload.java
 *
 * Eldorado Confidential and Proprietary Information
 * Copyright (C)2010 Eldorado, All Rights Reserved
 *
 * Hist�rico de Modifica��es
 *
 * Data           Quem              Descri��o
 * ------------------------------------------------------------------------------------------------
 * 07 May 2010    Rafael Lagoa      (3626)Importa��o de estrutura do Ivela para Challenges.  
 */
package org.ivela.offline.challenger;

import java.util.List;

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