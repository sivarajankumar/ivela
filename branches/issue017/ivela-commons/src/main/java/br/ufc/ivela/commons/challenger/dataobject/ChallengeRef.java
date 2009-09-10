package br.ufc.ivela.commons.challenger.dataobject;

/**
 * Inner element of {@link IvelaPayload} 
 * responsible for store an outside reference for
 * a Challenge
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */
public class ChallengeRef {
	
	private String value = null;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
