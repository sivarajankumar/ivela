/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.challenger.util;

import java.io.IOException;
import org.xml.sax.SAXException;

import br.ufc.ivela.commons.challenger.dataobject.IvelaObj;

/**
 *
 * @author jdamico
 */
public class ScoreConverter2Object implements ObjectInterface {

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
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return score;
    }

}
