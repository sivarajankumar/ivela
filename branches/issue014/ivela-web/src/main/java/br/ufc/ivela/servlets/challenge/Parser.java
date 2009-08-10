/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.servlets.challenge;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author jdamico
 */
public class Parser {

    private SAXParserFactory factory = null;
    private XMLReader parser = null;
    private InputSource iso = null;
	
    public Parser(String xmlSource) throws SAXException{
	factory = SAXParserFactory.newInstance();
	parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
	parser.setFeature("http://xml.org/sax/features/namespaces",true);
	byte[] bis = xmlSource.getBytes();
        InputStream is = new ByteArrayInputStream(bis);
        iso =  new InputSource(is);
    }
	
	public ChallengeDataProcessor getRawChallData() throws IOException, SAXException{
		ChallengeDataProcessor dp = new ChallengeDataProcessor();
		parser.setContentHandler(dp);
		parser.parse(iso);
		return dp;
	}
	
        public ScoreDataProcessor getRawScoreData() throws IOException, SAXException{
		ScoreDataProcessor dp = new ScoreDataProcessor();
		parser.setContentHandler(dp);
		parser.parse(iso);
		return dp;
	}
	

}
