/**
 * @(#)StoreConverter2Object.java
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

public class ScoreConverter2Object implements ObjectInterface {

    private static LogWrapper logger = new LogWrapper(ScoreConverter2Object.class);
    private String xmlSource;
    
    public ScoreConverter2Object(String entireXml) {
        this.xmlSource = entireXml;
    }
    
    public IvelaObj getConverterType() {
        IvelaObj score = null;
        try {
            Parser parser = new Parser(xmlSource);
            ScoreDataProcessor scoreDp = parser.getRawScoreData();
            score = scoreDp.getIvelaObj();
        } catch (SAXException saxe) {
            logger.error(saxe);
        } catch (IOException ioe) {
        	logger.error(ioe);
        }
        return score;
    }
}