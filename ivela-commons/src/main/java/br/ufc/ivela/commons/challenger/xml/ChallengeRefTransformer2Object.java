package br.ufc.ivela.commons.challenger.xml;

import br.ufc.ivela.commons.challenger.dataobject.ChallengeRef;
import br.ufc.ivela.commons.challenger.dataobject.IvelaObj;
import br.ufc.ivela.commons.challenger.dataobject.TransformerInterface;
import br.ufc.ivela.commons.challenger.util.DebugLogger;
import br.ufc.ivela.commons.challenger.xml.dataprocessor.ChallengeRefDataProcessor;
import br.ufc.ivela.commons.model.xml.Parser;

import java.io.IOException;


import org.xml.sax.SAXException;

/**
 * Logic implementation of {@link TransformFactory}
 * to {@link ChallengeRef}
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public class ChallengeRefTransformer2Object implements TransformerInterface {

	private String xml = null;
	
	public ChallengeRefTransformer2Object(String xml) {
		DebugLogger.getInstance().logAtDebugTime(this.getClass().getName(), "Calling ChallengeRefTransformer2Object...");
		this.xml = xml;
	}

	public IvelaObj getTransformerType() throws SAXException, IOException {
		
		
		Parser parser = new Parser(xml);
		ChallengeRefDataProcessor cRefDp = parser.getRawChallengeRef();
		
		
		return cRefDp.getIvelaObj();
	}



}
