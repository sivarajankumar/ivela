package br.ufc.ivela.commons.challenger.config.prefs;

public class Pref {
	public String prefType;
	public String prefValue;
	
	public Pref(String prefType, String prefValue) {
		this.prefType = prefType;
		this.prefValue = prefValue;
	}
	public String getPrefType() {
		return prefType;
	}
	public void setPrefType(String prefType) {
		this.prefType = prefType;
	}
	public String getPrefValue() {
		return prefValue;
	}
	public void setPrefValue(String prefValue) {
		this.prefValue = prefValue;
	}
	
	
}
