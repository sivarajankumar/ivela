package br.ufc.ivela.commons.challenger.dataobject;

import br.ufc.ivela.commons.challenger.xml.TransformFactory;
import java.io.IOException;

import org.xml.sax.SAXException;

/**
 * Provides method rule for {@link TransformFactory}
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public interface TransformerInterface {
	/**
	 * 
	 * @return {@link IvelaObj}
	 * @throws SAXException
	 * @throws IOException
	 */
	public IvelaObj getTransformerType() throws SAXException, IOException;
}
