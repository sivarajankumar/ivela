/**
 * @(#)XMLObject.java
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

public class XMLObject {
    private String entireXml = null;
    private int intType = 0;
    
    public void setEntireXml(String entireXml){
        this.entireXml = entireXml;
    }
    
    public String getEntireXml(){
        return entireXml;
    }
    
    public void setIntType(int intType){
        this.intType = intType;
    }
    
    public int getIntType(){
        return intType;
    }
}