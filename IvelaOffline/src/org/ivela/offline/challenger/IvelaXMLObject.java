/**
 * @(#)IvelaXMLObject.java
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

public interface IvelaXMLObject {
	
	public void setIvelaHeader(IvelaHeader ivelaHeader);
	public IvelaHeader getIvelaHeader();
	
	public void setIvelaPayload(IvelaPayload ivelaPayLoad);
	public IvelaPayload getIvelaPayload();
}