package org.ivela.offline.runtime;

import java.io.File;

import javax.swing.JOptionPane;

import org.ivela.offline.commons.Constants;
import org.ivela.offline.utils.CheckDB;

/**
 * Classe principal que inicia BANCO, JETTY e Navegador
 * 
 * @author julianom
 * 
 */
public class StartIvela {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File f = new File(Constants.USER_DATA_FOLDER);
		if(!f.exists()) f.mkdir(); 
		if (!CheckDB.checkDB()) JOptionPane.showMessageDialog(null, "Problema com o banco de dados, verifique o Log.");		
	}

}
