package com.tix11.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet principal, invocado para mostrar menu de opções
 * 
 * @author julianom
 * 
 */
public class JsHelper extends HttpServlet {
	private static final long serialVersionUID = -7433379500358268888L;

	public JsHelper() {
	}

	public JsHelper(String greeting) {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if ("login".equals(acao)) {
			if(request.getParameter("login").equals("admin")) {
				if(request.getParameter("senha").equals("banana")) {
					HttpSession sessao = request.getSession();
					sessao.setAttribute("login","admin");
					String pagina = "./";
					response.sendRedirect(pagina);
				}
			}
		} else {
			HttpSession sessao = request.getSession();
			sessao.invalidate();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print(createHtmlHeader());
			out.print(createPageHeader());

			out.print("<p align=center><form method='get' action='login'>");
			out.print("<h3>Login</h3>");
			out.print("<table border=1 align=center>");
			out.print("<tr>");
			out.print("<td>Usuário:</td>");
			out.print("<td><input type='text' name='login' size='10' value='admin' /></td></tr>");
			out.print("<tr>");
			out.print("<td>Senha:</td>");
			out.print("<td><input type='password' name='senha' size='10' value='banana' /></td>");
			out.print("</tr>");
			out.print("<tr>");
			out.print("<td colspan='2'><input type='submit' value='Entrar' /></td>");
			out.print("</tr>");
			out.print("</table>");
			out.print("<input type='hidden' name='acao' value='login' />");
			out.print("</form></p>");

			out.print(getHtmlFooter());
		}
	}

	public static String createHtmlHeader() {
		String htmlHeader = null;
		htmlHeader = "<html><head><title>SIMTIX Web Manager 0.1 </title></head><body align=center>";
		return htmlHeader;
	}

	public static String createPageHeader() {
		String htmlHeader = null;
		htmlHeader = "<h3>SIMTIX Web Manager 0.1 </h3><hr>";
		return htmlHeader;
	}

	public static String getHtmlFooter() {
		String htmlFooter = "<hr><pre align=center>Contact us: <a href=mailto:tix11@tix11.com>tix11@tix11.com</a>	+55 (11) 2182-0300 (BR) 	+1 (408) 730-8690 (US)<br>TIX11 2013(r) - Todos os direitos reservados</pre></body></html>";
		return htmlFooter;
	}
}