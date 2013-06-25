/**
 * @(#)IvelaObj.java
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