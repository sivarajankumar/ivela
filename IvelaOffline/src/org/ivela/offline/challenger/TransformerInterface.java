/**
 * @(#)TransformerInterface.java
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

import java.io.IOException;

import org.xml.sax.SAXException;

public interface TransformerInterface {
	public IvelaObj getTransformerType() throws SAXException, IOException;
}