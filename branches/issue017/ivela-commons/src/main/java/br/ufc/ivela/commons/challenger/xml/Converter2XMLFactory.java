package br.ufc.ivela.commons.challenger.xml;

import br.ufc.ivela.commons.challenger.config.Constants;
import br.ufc.ivela.commons.challenger.dataobject.IvelaObj;
import br.ufc.ivela.commons.challenger.dataobject.XMLInterface;

/**
 * Produces String representations of xml
 * based upon {@link IvelaObj}
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public class Converter2XMLFactory {
	public static XMLInterface getConverter(IvelaObj ivelaObj) {
		XMLInterface xmlOut = null;
		if(ivelaObj.getIvelaHeader().getDocSubType().equals(Constants.SUBTYPE_QUESTIONSET)){
			xmlOut = new QuestionSetXMLConverter(ivelaObj);
		}
		return xmlOut;
    }
}
