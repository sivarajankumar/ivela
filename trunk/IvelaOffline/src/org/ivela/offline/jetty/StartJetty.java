package org.ivela.offline.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.ivela.offline.servlets.JsHelper;

/**
 * Classe principal que configura e inicia o Jetty
 * 
 * @author julianom
 * 
 */
public class StartJetty extends AbstractHandler implements Runnable {

	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		response.getWriter().println("<h1>Hello World</h1>");
	}

	public static void main(String[] args) throws Exception {

	}

	@Override
	public void run() {
		try {
			System.out.println("Ivella Offline");
			Server server = new Server(8080);

			ServletContextHandler context = new ServletContextHandler(
					ServletContextHandler.SESSIONS);

			ResourceHandler resource_handler = new ResourceHandler();
			resource_handler.setDirectoriesListed(false);

			resource_handler.setResourceBase("./webcontent");

			HandlerList handlers = new HandlerList();
			handlers.setHandlers(new Handler[] { resource_handler, context });

			context.addServlet(new ServletHolder(new JsHelper("JsHelper")),
					"/jshelper/*");

			server.setHandler(handlers);
			
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}