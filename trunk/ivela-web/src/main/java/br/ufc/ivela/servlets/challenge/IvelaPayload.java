/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.servlets.challenge;

import java.util.List;

/**
 * Fundamental element of {@link IvelaObj}
 * Stores Challenge details
 * @see {@link ChallengeDesc}
 * @see {@link ChallengeRef}
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public class IvelaPayload {

	private Challenge challenge = null;
        private List<Challenge> listChallenge = null;

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
