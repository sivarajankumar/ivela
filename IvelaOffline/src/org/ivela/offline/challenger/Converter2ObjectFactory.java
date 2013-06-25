/**
 * @(#)Converter2ObjectFactory.java
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

public class Converter2ObjectFactory {
   
	public static int CHALLENGE_TYPE = 0;
	public static int SCORE_TYPE = 1;
    public static ObjectInterface getConverter(XMLObject xo) {
        switch(xo.getIntType()) {
            case 0:
                return new ChallengeConverter2Object(xo.getEntireXml());
            case 1:
                return new ScoreConverter2Object(xo.getEntireXml());
            default:
                return null;
        }
    }
}