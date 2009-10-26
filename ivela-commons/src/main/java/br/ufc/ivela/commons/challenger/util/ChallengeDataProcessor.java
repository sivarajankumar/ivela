/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons.challenger.util;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import br.ufc.ivela.commons.challenger.dataobject.IvelaHeader;
import br.ufc.ivela.commons.challenger.dataobject.IvelaObj;
import br.ufc.ivela.commons.challenger.dataobject.IvelaPayload;

/**
 *
 * @author jdamico
 */
public class ChallengeDataProcessor extends DefaultHandler {
    
    private IvelaObj challenge = new IvelaObj();
    private IvelaHeader ivelaHeader = new IvelaHeader();
    private IvelaPayload ivelaPayload = new IvelaPayload();
    private Challenge chall = new Challenge();
    
    private StringBuffer buffer = null;
    private boolean isTagActive = false;
    private String activeTag = null;
    private List<Field> fields = new ArrayList<Field>();

    public static final String TAG_HEADER = "header";
    public static final String TAG_PAYLOAD = "payload";
    public static final String TAG_CHALLENGE = "challenge";
    public static final String TAG_FIELD = "field";
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
            chall.setName(attributes.getValue("name"));
            
            if (textArea == null) {
                textArea = new StringBuffer();
            }
            if (buffer == null) {
                buffer = new StringBuffer();
            }
            isTagActive = true;

        } else if (qualifiedName.equals(TAG_FIELD)) {
            activeTag = TAG_FIELD;
            Field field = new Field();
            //log.setOutput("attributes.getValue(\"name\"): " + attributes.getValue("name"));
            //log.setOutput("attributes.getValue(\"value\"): " + attributes.getValue("value"));
            field.setName(attributes.getValue("name"));
            field.setValue(attributes.getValue("value"));
            fields.add(field);
            
            if (binArea == null) {
                binArea = new StringBuffer();
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
        } else if (isTagActive && activeTag.equals(TAG_FIELD)) {
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

        } else if (qualifiedName.equals(TAG_FIELD)) {
            isTagActive = true;
            buffer = null;
        }
    }

    public IvelaObj getIvelaObj() {
        chall.setField(fields);
        ivelaPayload.setChallenge(chall);
        challenge.setIvelaHeader(ivelaHeader);
        challenge.setIvelaPayload(ivelaPayload);
        return challenge;
    }

    
}
