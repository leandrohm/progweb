
<div class="center">
<%
	int code = Integer.parseInt(request.getParameter("msg"));

switch (code) {
	case 1: out.println(msg.getString("USER_ADD") );
		break;
	case 3: out.println( msg.getString("GET_LOAN") );
		break;			
	case 4: out.println( msg.getString("PASSWD_PROBLEM") );
		break;			
	case 6: out.println( msg.getString("USER_CANNOT_FOUND") );
		break;	
	case 2: out.println( msg.getString("INVALID_EMAIL"));
		break;			
		
}
%>
</div>
