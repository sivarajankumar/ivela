/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.challenger.util;

/**
 *
 * @author jdamico
 */
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
