package br.ufc.ivela.commons.challenger.xml;

import br.ufc.ivela.commons.challenger.xml.dataprocessor.ChallengeRefDataProcessor;
import br.ufc.ivela.commons.challenger.xml.dataprocessor.ContentPackageDataProcessor;
import br.ufc.ivela.commons.challenger.xml.dataprocessor.QuestionSetDataProcessor;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.SAXParserFactory;


import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Saxon parser implementation for {@link TransformFactory}
 * @see SAXParserFactory
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
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
	
	public ChallengeRefDataProcessor getRawChallengeRef() throws IOException, SAXException{
		ChallengeRefDataProcessor dp = new ChallengeRefDataProcessor();
		parser.setContentHandler(dp);
		parser.parse(iso);
		return dp;
	}
	
	public QuestionSetDataProcessor getRawQuestionSet() throws IOException, SAXException{
		QuestionSetDataProcessor dp = new QuestionSetDataProcessor();
		parser.setContentHandler(dp);
		parser.parse(iso);
		return dp;
	}
        
        public ContentPackageDataProcessor getRawContentPackage() throws IOException, SAXException{
		ContentPackageDataProcessor dp = new ContentPackageDataProcessor();
		parser.setContentHandler(dp);
		parser.parse(iso);
		return dp;
	}
}
