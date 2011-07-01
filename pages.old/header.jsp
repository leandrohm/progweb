<%@page import="java.util.*" %>
<%
	Locale currentLocale = request.getLocale();
	ResourceBundle msg = ResourceBundle.getBundle("org.usp.stockmarket.bundles.messages", currentLocale);
%>
<html>
	<head>
		<title><%= msg.getString("TITLE") %></title>
		<link rel="stylesheet" href="general.css">
	</head>
	<body cellspacing="0" cellpadding="0" topmargin="0" leftmargin="0">
			<div id="top">
				<div class="logo">
					<img src="images/logo.png" border="0" width=150 heigth=150>
				</div>
				<div class="login">
					<form action="/stockmarket/userservlet" method="post">
						<input type="hidden" name="type" id="type" value="0" />  
						<label for="email"><%= msg.getString("EMAIL") %></label>
						<input type="text" name="email" id="email" /><br />
						<label for="password"><%= msg.getString("PASSWD") %></label>
						<input type="password" name="passwd" id="passwd" /><br />
						<input type="submit" value='<%= msg.getString("LOGIN") %>' />
					</form>
				</div>
			</div>
                        <div style="float: none; clear: both;"></div>
			<div class="topMenu">
					<a href="index.jsp"><%= msg.getString("HOME") %></a>
					<a href="register.jsp"><%= msg.getString("REGISTER") %></a>
					<a href="/stockmarket/userservlet?type=1"><%= msg.getString("LOGOUT") %></a>
                    <div style="float: none; clear: both;"></div>
			</div>
