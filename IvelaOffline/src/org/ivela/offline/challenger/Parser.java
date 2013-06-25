/**
 * @(#)Parser.java
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Parser {

    private SAXParserFactory factory = null;
    private XMLReader parser = null;
    private InputSource iso = null;
	
    public Parser(String xmlSource) throws SAXException{
	factory = SAXParserFactory.newInstance();
	parser = XMLReaderFactory.createXMLReader("com.sun.org.apache.xerces.internal.parsers.SAXParser");
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