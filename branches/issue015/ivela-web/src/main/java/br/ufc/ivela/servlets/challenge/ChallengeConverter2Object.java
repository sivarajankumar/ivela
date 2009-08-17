/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.servlets.challenge;

import java.io.IOException;
import org.xml.sax.SAXException;

/**
 *
 * @author jdamico
 */
public class ChallengeConverter2Object implements ObjectInterface {

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
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return challenge;
    }
}
