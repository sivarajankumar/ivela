package org.ivela.offline.runtime;

import javax.swing.JOptionPane;


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

		// Banco de dados
		if (CheckDB.checkDB()) {
			
		} else {
			JOptionPane.showMessageDialog(null, "Problema com o banco de dados, verifique o Log.");
		}

	}

}
