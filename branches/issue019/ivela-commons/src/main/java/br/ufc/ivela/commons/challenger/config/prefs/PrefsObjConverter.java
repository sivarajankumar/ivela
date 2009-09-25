package br.ufc.ivela.commons.challenger.config.prefs;

import java.io.IOException;
import org.xml.sax.SAXException;
import br.ufc.ivela.commons.exception.IvelaException;
import br.ufc.ivela.commons.model.xml.Parser;


public class PrefsObjConverter implements ObjConverters {

	private String xml;
	
	public PrefsObjConverter(String xml) {
		this.xml = xml;
	}

	public PreferencesXMLObject exec() throws IvelaException {
		Parser parser = null;
		PreferencesXMLObject pObj =null;
		try {
			parser = new Parser(xml);
			PrefsDataProcessor dp = parser.getRawPrefsData();
			pObj = dp.getData();
		} catch (SAXException e) {
			
			throw new IvelaException(e.getStackTrace());
			
		} catch (IOException e) {

			throw new IvelaException(e.getStackTrace());
		}
		
		return pObj;
	}

}
