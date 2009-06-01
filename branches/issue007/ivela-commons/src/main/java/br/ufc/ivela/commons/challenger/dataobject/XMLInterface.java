package br.ufc.ivela.commons.challenger.dataobject;

import br.ufc.ivela.commons.challenger.xml.Converter2XMLFactory;
import javax.xml.transform.TransformerConfigurationException;


import org.xml.sax.SAXException;

/**
 * Provides a method rule for {@link Converter2XMLFactory}
 * @author Jose Damico - damico@dcon.com.br (17Sep2008)
 *
 */

public interface XMLInterface {
	/**
	 * 
	 * @return String representation of {@link IvelaObj}
	 * @throws TransformerConfigurationException
	 * @throws SAXException
	 */
	public String getXML() throws TransformerConfigurationException, SAXException;
}
