package org.usp.stockmarket.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import org.usp.stockmarket.config.*;
import org.usp.stockmarket.utils.*;
import org.usp.stockmarket.core.*;
import org.hibernate.*;

public class BankServlet extends HttpServlet implements Default {
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
		throws ServletException, java.io.IOException {

			String targetUrl = null;

			/* Redirecionar a pagina */
			PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
			HttpSession session = request.getSession();
			PrintWriter out = response.getWriter();


			int type = -1;
			try {
				type = Integer.parseInt(request.getParameter("type"));
			} catch (Exception e) {
				targetUrl = "index.jsp";
				response.sendRedirect(targetUrl);
			}

			String name	= request.getParameter("name");
			String juros = request.getParameter("juros");
			User user = null;
			switch (type) {
				case REGISTER_BANK:
					user = (User) session.getAttribute("user");
					if(user == null){
						targetUrl = "index.jsp";
						break;
					}
					Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
					dbSession.beginTransaction();
					Bank bank = new Bank();
					bank.setName(name);
					bank.setJuros(Double.parseDouble(juros));   //valor inicial para juros
					bank.setTradVol(0.0);
					bank.setExpVol(10000.0);
					bank.setAlpha(0.0);
					
					dbSession.save(bank);
					dbSession.getTransaction().commit(); 
					targetUrl = "index.jsp";
					break;


				default:
					targetUrl = "index.jsp"; /* acao default para uma mensagem inexistente */
					break;
			}
			response.sendRedirect(targetUrl); /* sempre redireciona a pagina */
		}

	public void doPost(HttpServletRequest request,HttpServletResponse response) 
		throws ServletException, java.io.IOException {
			doGet(request, response);
		}
}
