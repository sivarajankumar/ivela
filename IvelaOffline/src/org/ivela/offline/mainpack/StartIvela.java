package org.ivela.offline.mainpack;

import org.ivela.offline.jetty.StartJetty;
import org.ivela.offline.utils.BrowserStart;
/**
 * Classe principal que inicia BANCO, JETTY e Navegador
 * 
 * @author pdamico
 *
 */
public class StartIvela {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// startar o banco
		
		// Inicia o jetty
		Thread tJetty = new Thread(new StartJetty());
		tJetty.start();
		
		// startar o navegador padrao apontando para a pagina inicial
		Thread tBrowser = new Thread(new BrowserStart());
		tBrowser.start();

	}

}
