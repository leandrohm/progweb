package org.usp.stockmarket.servlets;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import org.usp.stockmarket.config.*;
import org.usp.stockmarket.utils.*;
import org.usp.stockmarket.core.*;
import org.hibernate.*;

public class UserServlet extends HttpServlet implements Default {
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
			String email	= request.getParameter("email");
			String passwd = request.getParameter("passwd");
			User user = null;

			switch (type) {
				case LOGIN:
					Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
					dbSession.beginTransaction();

					try{
					   user = (User) dbSession.load(User.class, email); 
					} catch(Exception e){
						targetUrl = "message.jsp?msg=6";
						dbSession.getTransaction().commit();
						break;
					}
						
					try {
						passwd = MD5.encode(passwd);
					} catch(Exception e){
						targetUrl = "message.jsp?msg=4";  //problemas na encriptacao
						break;
					}

					if(user != null && user.getPasswd().equals(passwd)) {
						
						session.setAttribute("user",user); 		
					} else {
						targetUrl = "message.jsp?msg=6";  //usuario nao existe
						break;
					}
					targetUrl = "index.jsp";  
					dbSession.getTransaction().commit();
					break; 

				case LOGOUT:
					session.invalidate();
					targetUrl = "index.jsp";
					break;

					/*Cadastro do usuario no sistema */
				case REGISTER:
				
					Session dbSession1 = HibernateUtil.getSessionFactory().getCurrentSession();
					dbSession1.beginTransaction();
					user = new User();
					try{
						user.setEmail(email);
					} catch(Exception e){
						targetUrl = "message.jsp?msg=2";  //email invalido
						dbSession1.getTransaction().commit(); /* devolve a conexao com o banco */
					}
								
					user.setName(name);
					user.setMoney(100.0); //inicia com $100 para investir
					user.setLoanToPay(0.0);
					
					try {
						user.setPasswd(passwd);
					} catch(Exception e){
						targetUrl = "index.jsp";  //problemas na senha
						dbSession1.getTransaction().commit(); /* devolve a conexao com o banco */
						break;
					}

					dbSession1.save(user);	/*Salva o usuário no banco */
					dbSession1.getTransaction().commit(); /* devolve a conexao com o banco */
					targetUrl = "message.jsp?msg=1";
					break;

				/*lista os emprestimos feitos pelo usuario*/
				case LIST_LOAN:
					user = (User) session.getAttribute("user");
					if(user == null){
						targetUrl = "index.jsp";
						break;
					}
					Session dbSession2 = HibernateUtil.getSessionFactory().getCurrentSession();
					dbSession2.beginTransaction();
					List loans = dbSession2.createQuery("SELECT l FROM Loan where l.email = :email")
									.setParameter("email", user.getEmail()).list();
					Iterator i = loans.iterator();
					while(i.hasNext()){
						Loan l = (Loan) i.next();
						out.println(l.getDate());
						out.println(l.getVal());
						out.println(l.getJuros());
						if(l.getPayed())
							out.println("pago");
						else
							out.println("nao pago");
					}
					dbSession2.getTransaction().commit();
					targetUrl = "index.jsp";
					break;

				case LIST_QUOTE:
					user = (User) session.getAttribute("user");
					if(user == null){
						targetUrl = "index.jsp";
						break;
					}
					Session dbSession3 = HibernateUtil.getSessionFactory().getCurrentSession();
					dbSession3.beginTransaction();
					List quotesBuy = dbSession3.createQuery("SELECT FROM Investment where in.email = :email")
						.setParameter("email", user.getEmail()).list();
					Iterator ite = quotesBuy.iterator();
					while(ite.hasNext()) {
						Investment in = (Investment) ite.next();
						out.println(in.getQuote());
						out.println(in.getVal());
						out.println(in.getQuantity());
						
						System.out.println(in.getQuote());
						System.out.println(in.getVal());
						System.out.println(in.getQuantity());
												
						if(in.getBuy()){
							out.println("Compra");
							System.out.println("Compra");
						}
						else
							out.println("Venda");
							System.out.println("Venda");
					}
					dbSession3.getTransaction().commit();
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
