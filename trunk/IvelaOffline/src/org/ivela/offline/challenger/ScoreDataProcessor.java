/**
 * @(#)ScoreDataProcessor.java
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

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ScoreDataProcessor  extends DefaultHandler {
    
    private IvelaObj score = new IvelaObj();
    private IvelaHeader ivelaHeader = new IvelaHeader();
    private IvelaPayload ivelaPayload = new IvelaPayload();

    
    private StringBuffer buffer = null;
    private boolean isTagActive = false;
    private String activeTag = null;
    private List<Challenge> challs = new ArrayList<Challenge>();

    public static final String TAG_HEADER = "header";
    public static final String TAG_PAYLOAD = "payload";
    public static final String TAG_CHALLENGE = "challenge";

    private StringBuffer textArea = null;
    private StringBuffer binArea = null;

    public void startElement(String namespaceUri, String localName, String qualifiedName, Attributes attributes) {

        if (qualifiedName.equals(TAG_HEADER)) {
            activeTag = TAG_HEADER;
            buffer = new StringBuffer();
            //log.setOutput("attributes.getValue(\"doctype\"): " + attributes.getValue("doctype"));
            ivelaHeader.setDocType(attributes.getValue("doctype"));
            ivelaHeader.setDocSubType(attributes.getValue("docsubtype"));
            isTagActive = true;

        } else if (qualifiedName.equals(TAG_PAYLOAD)) {
            activeTag = TAG_PAYLOAD;
            buffer = new StringBuffer();
            isTagActive = true;

        } else if (qualifiedName.equals(TAG_CHALLENGE)) {
            activeTag = TAG_CHALLENGE;
            
            Challenge chall = new Challenge();
            chall.setName(attributes.getValue("name"));
            chall.setScore(attributes.getValue("score"));
            challs.add(chall);
            
            if (textArea == null) {
                textArea = new StringBuffer();
            }
            if (buffer == null) {
                buffer = new StringBuffer();
            }
            isTagActive = true;

        
        } else {
            isTagActive = false;
            buffer = null;
        }

    }

    public void characters(char[] chars, int start, int length) {

        if (isTagActive && activeTag.equals(TAG_PAYLOAD)) {
            if (buffer == null) {
                buffer = new StringBuffer();
            }
            buffer.append(chars, start, length);
        } else if (isTagActive && activeTag.equals(TAG_CHALLENGE)) {
            if (buffer == null) {
                buffer = new StringBuffer();
            }
            buffer.append(chars, start, length);
      
        } else {
            buffer = null;
        }

    }

    public void endElement(String uri, String name, String qualifiedName) {

        if (qualifiedName.equals(TAG_HEADER)) {
            isTagActive = true;
            buffer = null;

        } else if (qualifiedName.equals(TAG_PAYLOAD)) {
            isTagActive = true;
            buffer = null;

        } else if (qualifiedName.equals(TAG_CHALLENGE)) {
            isTagActive = true;
            buffer = null;

        }
    }

    public IvelaObj getIvelaObj() {
        ivelaPayload.setListChallenge(challs);
        score.setIvelaHeader(ivelaHeader);
        score.setIvelaPayload(ivelaPayload);
        return score;
    }
}