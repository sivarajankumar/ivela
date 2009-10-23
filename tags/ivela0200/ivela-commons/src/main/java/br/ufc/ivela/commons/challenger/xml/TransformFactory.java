package br.ufc.ivela.commons.challenger.xml;

import br.ufc.ivela.commons.challenger.config.Constants;
import br.ufc.ivela.commons.challenger.dataobject.IvelaObj;
import br.ufc.ivela.commons.challenger.dataobject.IvelaStringObj;
import br.ufc.ivela.commons.challenger.dataobject.TransformerInterface;

/**
 * Produces {@link IvelaObj} based on a {@link IvelaStringObj}
 * that contains xml string representation of an IvelaObj
 * @see TransformerInterface
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public class TransformFactory {

	public static TransformerInterface getTransformer(IvelaStringObj ivelaStrObj){
		switch(ivelaStrObj.getType()) {
        case Constants.XML_INTERNALS_CHALLENGEREF:;
            return new ChallengeRefTransformer2Object(ivelaStrObj.getXml());
        case Constants.XML_PLUGIN_QUESTIONSET:;
        	return new QuestionSetTransformer2Object(ivelaStrObj.getXml());
        case Constants.CONTENTPACKAGE:;
            return new ContentPackageTransformer2Object(ivelaStrObj.getXml());
        default:
            // fazer
            return null;
		}
	}
	
}
