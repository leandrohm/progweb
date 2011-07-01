<%@page import="org.hibernate.*, org.usp.stockmarket.utils.HibernateUtil, org.usp.stockmarket.core.Bank, org.usp.stockmarket.core.User, java.io.*, java.util.*" %>
<%@include file="header.jsp" %>
<%@include file="leftMenu.jsp" %>
<div class="content">
	<form action="/stockmarket/transservlet" method="post">
		<input type="hidden" name="type" id="type" value="0" />
		<label for="value"><%= msg.getString("VALUE") %></label>
		
		<input type="text" name="qtmoney" id="qtmoney" /><br />
		<select multiple="multiple" name="idbank" id="idbank" size="6">
		<%
			Session transaction  = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction.beginTransaction();
			List banks = transaction.createQuery("SELECT b FROM Bank b").list();
			Iterator i = banks.iterator();
			while(i.hasNext()){
				Bank bank = (Bank) i.next();
				out.print("<option value=\""+bank.getBid()+"\">"+bank.getName()+"</option>");
			}
			transaction.getTransaction().commit();
		%>
		</select>
		<input type="submit" value='<%= msg.getString("ADD_LOAN") %>' />
	</form>
</div> 
<%@include file="footer.jsp" %>
