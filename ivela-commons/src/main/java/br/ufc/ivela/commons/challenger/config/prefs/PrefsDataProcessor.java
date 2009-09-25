package br.ufc.ivela.commons.challenger.config.prefs;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/*
 * 
<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<preference type="" value="">
<preference type="" value="">
<preference type="" value="">
</ivela>
 */


public class PrefsDataProcessor extends DefaultHandler  {

	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	private PreferencesXMLObject pObj = new PreferencesXMLObject();
	private List<Pref> prefsList = new ArrayList<Pref>();
	
	private StringBuffer buffer = null;
	private boolean isTagActive = false;
	private String activeTag = null;

	public static final String TAG_PREFERENCES = "preference";

	public void startElement (String namespaceUri, String localName, String qualifiedName, Attributes attributes) {


		if(qualifiedName.equals(TAG_PREFERENCES)){
			activeTag = TAG_PREFERENCES;
			Pref pref = new Pref(attributes.getValue("type"), attributes.getValue("value"));
			
			
			prefsList.add(pref);
			isTagActive = true;
		}else{
			isTagActive = false;
			buffer = null;
		}

	}

	public void characters(char[] chars,int start,int length){

		if(isTagActive && activeTag.equals(TAG_PREFERENCES)){
			if(buffer==null) buffer = new StringBuffer();
			buffer.append(chars,start,length);

		}else{
			buffer = null;
		}

	}

	public void endElement(String uri, String name, String qualifiedName){

		if(qualifiedName.equals(TAG_PREFERENCES)){ 
			isTagActive = true;
			if(buffer!=null){ /* get content */ 
				
			}
			buffer = null;

		}

	}


	public PreferencesXMLObject getData() {
		pObj.setPrefsList(prefsList);
		return pObj;
	}

}

