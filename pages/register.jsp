<%@include file="header.jsp" %>
<%@include file="leftMenu.jsp" %>
<div class="content">
    <h1><%=msg.getString("REGISTER")%></h1>
	<form action="/stockmarket/userservlet" method="post">
		<input type="hidden" name="type" id="type" value="2" />
		<label for="name"><%=msg.getString("NAME")%></label>
		<input type="text" name="name" id="name"/><br/>
		<label for="email"><%=msg.getString("EMAIL")%></label>
		<input type="text" name="email" id="email"/> <br/>
		<label for="passwd"><%=msg.getString("PASSWD")%></label>
		<input type="password" name="passwd" id="passwd"/> <br/>
		<input type="submit" value='<%= msg.getString("CAD") %>' />
	</form>
</div>
<%@include file="footer.jsp" %>
