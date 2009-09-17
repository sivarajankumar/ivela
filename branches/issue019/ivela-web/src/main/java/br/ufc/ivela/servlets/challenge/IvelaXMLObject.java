/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.servlets.challenge;

/**
 * Stores method rules for {@link IvelaObj}
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public interface IvelaXMLObject {
	
	public void setIvelaHeader(IvelaHeader ivelaHeader);
	public IvelaHeader getIvelaHeader();
	
	public void setIvelaPayload(IvelaPayload ivelaPayLoad);
	public IvelaPayload getIvelaPayload();
	
}
