package br.ufc.ivela.commons.challenger.config.prefs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.exception.IvelaException;

public class ManagePreferences {
	
	private static ManagePreferences INSTANCE = null;
	public static ManagePreferences getInstance(){
		if(INSTANCE == null) INSTANCE = new ManagePreferences();
		return INSTANCE;
	}
	
	private static Map<String, String> prefsMap = new HashMap<String, String>();
	
	public void fillPreferencesMap() throws IvelaException{
		PreferencesXMLObject pObj = Converter2ObjFactory.getConverter(Constants.PREFS, readXMLPrefFile(Constants.PREFERENCES_XML_FILE)).exec();
		List<Pref> prefsList = pObj.getPrefsList();
		for(int i=0; i<prefsList.size(); i++){
			prefsMap.put(prefsList.get(i).getPrefType(), prefsList.get(i).getPrefValue());
		}
	}
	
	public Map<String, String> getPreferencesMap(){
		return prefsMap;
	}
	
	private String readXMLPrefFile(String filePath){
		StringBuffer sb = new StringBuffer();
		String row = null;
		FileReader fr = null;
		BufferedReader input = null;
		try {
			fr = new FileReader(filePath);
			input = new BufferedReader(fr);
			while ((row = input.readLine()) != null) {
				sb.append(row);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}finally{
			try {
				if(fr!=null) fr.close();
				if(input!=null) input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}				
		}
		
		
		
		return sb.toString();
	}
}
