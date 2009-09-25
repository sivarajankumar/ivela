package br.ufc.ivela.commons.challenger.xml.dataprocessor;


import br.ufc.ivela.commons.challenger.config.Constants;
import br.ufc.ivela.commons.challenger.dataobject.ChallengeRef;
import br.ufc.ivela.commons.challenger.dataobject.IvelaHeader;
import br.ufc.ivela.commons.challenger.dataobject.IvelaObj;
import br.ufc.ivela.commons.challenger.dataobject.IvelaPayload;
import br.ufc.ivela.commons.challenger.xml.TransformFactory;
import br.ufc.ivela.commons.model.xml.Parser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX data processor implementation that scans a xml string
 * to build an {@link IvelaObj} which contains a {@link ChallengeRef} 
 * @see Parser
 * @see ChallengeRefTransformer2Object
 * @see TransformFactory
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public class ChallengeRefDataProcessor extends DefaultHandler {
	
	private IvelaObj ivelaObj = new IvelaObj();
	private IvelaHeader ivelaHeader = new IvelaHeader();
	private IvelaPayload ivelaPayLoad = new IvelaPayload();
	
	public void startElement (String namespaceUri, String localName, String qualifiedName, Attributes attributes) {
		if(qualifiedName.equals(Constants.TAG_HEADER)){

			ivelaHeader.setDocType(attributes.getValue(Constants.DOCTYPE));
			ivelaHeader.setDocSubType(attributes.getValue(Constants.DOCSUBTYPE));
			ivelaObj.setIvelaHeader(ivelaHeader);
			
		}else if(qualifiedName.equals(Constants.TAG_CHALLENGEREF)){

			ChallengeRef cRef = new ChallengeRef();
			cRef.setValue(attributes.getValue(Constants.CHALLENGEREF_VALUE));
			ivelaPayLoad.setChallengeRef(cRef);
			ivelaObj.setIvelaPayload(ivelaPayLoad);
			
		}
	}
	
	public void characters(char[] chars,int start,int length){
		
	}
	
	public void endElement(String uri, String name, String qualifiedName){
		
	}
	
	public IvelaObj getIvelaObj(){
		return ivelaObj;
	}

}
