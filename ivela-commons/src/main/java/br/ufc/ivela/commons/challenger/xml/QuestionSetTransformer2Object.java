package br.ufc.ivela.commons.challenger.xml;

import br.ufc.ivela.commons.challenger.dataobject.ChallengeDesc;
import br.ufc.ivela.commons.challenger.dataobject.IvelaObj;
import br.ufc.ivela.commons.challenger.dataobject.Question;
import br.ufc.ivela.commons.challenger.dataobject.TransformerInterface;
import br.ufc.ivela.commons.challenger.util.DebugLogger;
import br.ufc.ivela.commons.challenger.xml.dataprocessor.QuestionSetDataProcessor;
import br.ufc.ivela.commons.model.xml.Parser;

import java.io.IOException;


import org.xml.sax.SAXException;

/**
 * Logic of {@link TransformFactory} to
 * {@link ChallengeDesc} which contains a Question set
 * @see Question
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public class QuestionSetTransformer2Object implements TransformerInterface {

	private String xml = null;
	
	public QuestionSetTransformer2Object(String xml) {
		DebugLogger.getInstance().logAtDebugTime(this.getClass().getName(), "Calling QuestionSetTransformer2Object...");
		this.xml = xml;
	}

	public IvelaObj getTransformerType() throws SAXException, IOException {
		Parser parser = new Parser(xml);
		QuestionSetDataProcessor qSetDp = parser.getRawQuestionSet();
		
		
		return qSetDp.getIvelaObj();
	}

}
