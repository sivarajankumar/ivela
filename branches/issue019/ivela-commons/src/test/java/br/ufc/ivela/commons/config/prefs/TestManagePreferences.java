package br.ufc.ivela.commons.config.prefs;

import java.util.Map;

import junit.framework.TestCase;

import br.ufc.ivela.commons.challenger.config.prefs.ManagePreferences;
import br.ufc.ivela.commons.exception.IvelaException;


public class TestManagePreferences extends TestCase {
	public void testPrefs(){
		try {
			ManagePreferences.getInstance().fillPreferencesMap();
			Map<String, String> prefsMap = ManagePreferences.getInstance().getPreferencesMap();
			System.out.println("******************* "+prefsMap.get("mailImpl"));
		} catch (IvelaException e) {
			e.printStackTrace();
		}
	}
}
