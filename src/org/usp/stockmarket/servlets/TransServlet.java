package org.usp.stockmarket.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import org.usp.stockmarket.config.*;
import org.usp.stockmarket.utils.*;
import org.usp.stockmarket.core.*;
import org.hibernate.*;

public class TransServlet extends HttpServlet implements Default, Symbol {
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
			
			String email = request.getParameter("email");
			String quote = request.getParameter("quote");
			String quantity = request.getParameter("quantity");
			String val = request.getParameter("value");

			User user = null;
			Bank bank = null;
			Investment invest = null;

			switch (type) {
				case LOAN:
					user = (User) session.getAttribute("user");
					if(user == null){
						targetUrl = "message.jsp?msg=0";
						break;
					}
					Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
					dbSession.beginTransaction();
					Double qtMoney = Double.parseDouble(request.getParameter("qtmoney"));
					Integer bankId = Integer.parseInt(request.getParameter("idbank"));

					user = (User) dbSession.load(User.class, user.getEmail()); 
					bank = (Bank) dbSession.load(Bank.class, bankId);


					user.setMoney(user.getMoney() + qtMoney );  //incrementa a quantidade de dinheiro do usuario
					bank.setTradVol(qtMoney); //Seto o valor do emprestimo
					dbSession.save(user); 

					Loan loan = new Loan();
					loan.setEmail(user.getEmail());        			
					loan.setBank(bank.getBid());	 			
					try{
						loan.setDate(MyDate.now());			
					}catch(Exception e){
						targetUrl = "index.jsp"; //redirecionar para uma pagina de erros por causa da data errada
						dbSession.getTransaction().commit();
						break;
					}
					loan.setVal(qtMoney);					//qt de dinheiro emprestada
					loan.setJuros(bank.calcJuros());		//valor do juros associado a dinheiro emprestado
					loan.isPayed(false);  //emprestimo nao esta pago
					dbSession.save(loan);
					dbSession.getTransaction().commit();
					targetUrl = "message.jsp?msg=3";
					break; 

				case BUY:
					user = (User) session.getAttribute("user");
					if(user == null){
						targetUrl = "index.jsp";
						break;
					}
					Session dbSession1 = HibernateUtil.getSessionFactory().getCurrentSession();
					dbSession1.beginTransaction();
					//antes da compra ver o saldo do usuario
					user = (User) dbSession1.load(User.class, user.getEmail());
					if(user.getMoney() < Double.parseDouble(val)*Integer.parseInt(quantity)){
						targetUrl = "message.jsp?msg=8"; //redirecionar para saldo insuficiente
						dbSession1.getTransaction().commit();
						break;
					}
					invest = new Investment();
					invest.setEmail(user.getEmail());
					invest.setQuote(quote);
					invest.setVal(Double.parseDouble(val));
					invest.setQuantity(Integer.parseInt(quantity));
					invest.isBuy(true);

					try{
						invest.setDate(MyDate.now());
					} catch(Exception e){
						targetUrl = "index.jsp"; //redirecionar para uma pagina de erros por causa da data errada
						dbSession1.getTransaction().commit();
						break;
					}			

					user.setMoney(user.getMoney() - Double.parseDouble(val)*Integer.parseInt(quantity)); //atualiza o saldo do usuario
					dbSession1.save(user); 
					dbSession1.save(invest); //salva a transacao
					dbSession1.getTransaction().commit();
					break;

				case SALE:
					user = (User) session.getAttribute("user");
					if(user == null){
						targetUrl = "index.jsp";
						break;
					}
					Session dbSession2 = HibernateUtil.getSessionFactory().getCurrentSession();
					dbSession2.beginTransaction();

					user = (User) dbSession2.load(User.class, user.getEmail());
					
					//Verificar se o usuario tem acoes disponiveis





					invest = new Investment();
					invest.setEmail(user.getEmail());
					invest.setQuote(quote);
					invest.setVal(Double.parseDouble(val));
					invest.setQuantity(Integer.parseInt(quantity));
					invest.isBuy(false);
					try{

						invest.setDate(MyDate.now());
					} catch(Exception e){
						targetUrl = "index.jsp"; //redirecionar para uma pagina de erros por causa da data errada
						dbSession2.getTransaction().commit();
						break;
					}			

					user.setMoney(user.getMoney() + Double.parseDouble(val)*Integer.parseInt(quantity)); //atualiza o saldo do usuario
					dbSession2.save(user); 
					dbSession2.save(invest); //salva a transacao
					dbSession2.getTransaction().commit();

					break;

				case SALDO:
					user = (User) session.getAttribute("user");
					if(user == null){
						targetUrl = "index.jsp";
						break;
					}
					out.println(user.getMoney());  
					break;

				default:
					targetUrl = "index.jsp"; 
					break;
			}
			response.sendRedirect(targetUrl); 
		}

	public void doPost(HttpServletRequest request,HttpServletResponse response) 
		throws ServletException, java.io.IOException {
			doGet(request, response);
		}
}
