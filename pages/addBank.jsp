<%@include file="header.jsp" %>
<%@include file="leftMenu.jsp" %>
<div class="content">
	<h1><%=msg.getString("ADD_BANK")%></h1>
	<form action="/stockmarket/bankservlet" mathod="post" />
		<input type="hidden" name="type" id="type" value="0" />
		<label for="name"><%=msg.getString("NAME")%></label>
		<input type="text" id="name" name="name"/><br />
		<label for="juros"><%=msg.getString("JUROS")%></label>
		<input type="text" id="juros" name="juros"/><br />
		<input type="submit" value='<%= msg.getString("CAD") %>' />
	</form>
</div>
<%@include file="footer.jsp" %>
