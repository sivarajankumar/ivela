package br.ufc.ivela.commons.challenger.config.prefs;

import br.ufc.ivela.commons.Constants;

public class Converter2ObjFactory {
	public static ObjConverters getConverter(int type, String xml){
		
		switch(type){
		case Constants.PREFS:
			return new PrefsObjConverter(xml);
		default:
			return null;
		}
	}

}