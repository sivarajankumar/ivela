/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.servlets.challenge;

/**
 * Main service object definition
 * see {@link IvelaXMLObject}
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public class IvelaObj implements IvelaXMLObject {

	private IvelaHeader ivelaHeader = null;
	private IvelaPayload ivelaPayLoad = null;
	
	public IvelaHeader getIvelaHeader() {
		return ivelaHeader;
	}

	public IvelaPayload getIvelaPayload() {
		return ivelaPayLoad;
	}

	public void setIvelaHeader(IvelaHeader ivelaHeader) {
		this.ivelaHeader = ivelaHeader;
		
	}

	public void setIvelaPayload(IvelaPayload ivelaPayLoad) {
		this.ivelaPayLoad = ivelaPayLoad;
	}

	
}

