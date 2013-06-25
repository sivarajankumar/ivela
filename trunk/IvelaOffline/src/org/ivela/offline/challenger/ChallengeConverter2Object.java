/**
 * @(#)ChallengeConverter2Object.java
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

import org.ivela.offline.commons.LogWrapper;
import org.xml.sax.SAXException;

public class ChallengeConverter2Object implements ObjectInterface {

    private static LogWrapper logger = new LogWrapper(ChallengeConverter2Object.class);
    private String xmlSource;

    public ChallengeConverter2Object(String entireXml) {

        this.xmlSource = entireXml;
    }

    public IvelaObj getConverterType() {
        IvelaObj challenge = null;
        try {
            Parser parser = new Parser(xmlSource);
            ChallengeDataProcessor challDp = parser.getRawChallData();
            challenge = challDp.getIvelaObj();
        } catch (SAXException saxe) {
        	logger.error(saxe);
        } catch (IOException ioe) {
        	logger.error(ioe);
        }
        return challenge;
    }
}