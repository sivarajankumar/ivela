package br.ufc.ivela.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import br.ufc.ivela.commons.challenger.config.prefs.ManagePreferences;
import br.ufc.ivela.commons.exception.IvelaException;

public class PreferencesServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PreferencesServlet() {
        super();
    }
	
	public void init(ServletConfig config) throws ServletException {
		try {
			ManagePreferences.getInstance().fillPreferencesMap();
			System.out.println("#################### Reading Preferences ####################");
		} catch (IvelaException e) {
			e.printStackTrace();
		}
	}
	
}
