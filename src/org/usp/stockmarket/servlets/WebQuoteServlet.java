package org.usp.stockmarket.servlets;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import org.hibernate.*;
import org.usp.stockmarket.config.*;
import org.usp.stockmarket.utils.*;
import org.usp.stockmarket.core.*;
import org.usp.stockmarket.robot.*;

public class WebQuoteServlet extends HttpServlet implements Default, Symbol {
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
		throws ServletException, java.io.IOException {

			String targetUrl = null;

			/* Redirecionar a pagina */
			PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
			HttpSession session = request.getSession();
			PrintWriter out = response.getWriter();

		
			//String symbol = request.getParameter("symbol");
			//String path = HISTPRICES + symbol;

			int type = -1;
			try {
				type = Integer.parseInt(request.getParameter("type"));
			} catch (Exception e) {
				targetUrl = "index.jsp";
				response.sendRedirect(targetUrl);
			}

			Quote quote = null;
			User user = null;
			switch (type) {
				case GETQUOTE:
					user = (User) session.getAttribute("user");
					if(user == null){
						targetUrl = "index.jsp";
						break;
					}
					QUOTES.add(PETR4);
					QUOTES.add(OGXP3);
					QUOTES.add(VALE5);
					QUOTES.add(USIM5);
					QUOTES.add(GGBR4);

					Session dbSession = HibernateUtil.getSessionFactory().getCurrentSession();
					dbSession.beginTransaction();
					String path = null;
					
					for(int i=0; i < QUOTES.size() ;i++){
					path = HISTPRICES + QUOTES.get(i);
						try{
							String linkDownload = Robot.getLink(path, Robot.hpParser);
							/* fazendo download da tabela e armazenando linha por linha no banco */

							URL url = new URL(linkDownload);
							URLConnection uconn = url.openConnection();
							InputStream is = uconn.getInputStream();
							BufferedReader br = new BufferedReader(new InputStreamReader(is));
							String str = null;
							br.readLine();
							while ((str = br.readLine()) != null) {
								String[] data = str.split(",");
								quote = new Quote();
								quote.setName(QUOTES.get(i));

								System.out.println(data[0]);	
								System.out.println(data[4]);	

								quote.setDate(MyDate.format(data[0]));
								quote.setClose(Double.parseDouble(data[4]));
								dbSession.save(quote);
							}
						} catch(Exception e) {
							System.out.println("Problemas na conexao!!!");
							dbSession.getTransaction().commit();
							break;
						}
					}
					dbSession.getTransaction().commit();
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
