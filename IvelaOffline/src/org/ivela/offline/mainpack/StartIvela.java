package org.ivela.offline.mainpack;

import javax.swing.JOptionPane;

import org.ivela.offline.jetty.StartJetty;
import org.ivela.offline.utils.BrowserStart;
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
			// Inicia o jetty
			// vai ter que verificar se o jetty ja nao esta iniciado (o
			// usuario pode ter fechado o navegador),
			// caso esteja, nao subir, e levantar o navegador

			Thread tJetty = new Thread(new StartJetty());
			tJetty.start();

			// startar o navegador padrao apontando para a pagina inicial
			Thread tBrowser = new Thread(new BrowserStart());
			tBrowser.start();
		} else {
			JOptionPane.showMessageDialog(null, "Problema com o banco de dados, verifique o Log.");
		}

	}

}
