/**
 * @(#)XMLInterface.java
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

import javax.xml.transform.TransformerConfigurationException;

import org.xml.sax.SAXException;

public interface XMLInterface {
	public String getXML() throws TransformerConfigurationException, SAXException;
}