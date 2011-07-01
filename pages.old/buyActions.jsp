<%@page import="org.hibernate.*, org.hibernate.Criteria, org.hibernate.Session, org.hibernate.criterion.ProjectionList, org.hibernate.criterion.Projections, org.usp.stockmarket.utils.HibernateUtil, org.usp.stockmarket.core.Quote, org.usp.stockmarket.core.User, java.io.*, java.util.*" %>
<%@include file="header.jsp" %>
<%@include file="leftMenu.jsp" %>
<div class="content">
	<form action="/stockmarket/transservelet" method="post">
		<input type="hidden" name="type" id="type" value="0" />
		<select multiple="multiple" name="idbank" id="idbank" size="6">
                <table border="1">
                    <tr>
                        <th><%= msg.getString("NAME") %></th>
                        <th><%= msg.getString("DATE") %></th>
                        <th><%= msg.getString("VALUE") %></th>
                    </tr>
                <%
			Session transaction  = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction.beginTransaction();
			//List quotes = transaction.createQuery("SELECT name, max(date), close FROM Quote q GROUP BY q.name").list();
                        List results = session.createCriteria(Quote.class)
                            .setProjection( Projections.projectionList()
                                .add( Projections.max("data") )
                                .add( Projections.groupProperty("nome") )
                                .add( Projections.groupProperty("close") )
                                 .addOrder( Order.desc("avgWeight") )
                            ).list();
			Iterator i = quotes.iterator();
			while(i.hasNext()){
				Object[] obj = (Object[]) i.next();
                                out.print("<tr>");
				out.print("<td>"+obj[0]+"</td>");
                                out.print("</tr>");
			}
			transaction.getTransaction().commit();
		%>
                </table>
		</select>
		<input type="submit" value='<%= msg.getString("ADD") %>' />
	</form>
</div> 
<%@include file="footer.jsp" %>
