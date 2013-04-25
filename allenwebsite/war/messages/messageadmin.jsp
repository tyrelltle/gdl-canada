<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.google.appengine.labs.repackaged.org.json.JSONObject,java.util.*,com.allen.website.DBBean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Messages</title>
	
	
	
<style type="text/css" media="screen">
body {
	margin:50px 0px; padding:0px; /* Need to set body margin and padding to get consistency between browsers. */
	text-align:center; /* Hack for IE5/Win */
	}
	
#Content {
	width:500px;
	margin:0px auto; /* Right and left margin widths set to "auto" */
	text-align:left; /* Counteract to IE5/Win Hack */
	padding:15px;
	}
</style>	
</head>
<body id="Content">

<jsp:useBean id="msgBean" class="com.allen.website.bean.MessageBean" /> 
<%

	HttpSession ses = request.getSession(true);
	Boolean issa=(Boolean)ses.getAttribute("admin");
	
	msgBean.init(); 
	List<Message> msgs=msgBean.msgs;
  %>



	<FORM ACTION="../message?action=add" METHOD=POST>
		<br><br><br>
			<table  border="2" float="center">
				<tr>	
						<td colspan="2"><p align="center">
								<B>Leave message</B>
						</td>
				</tr>

				<tr>
					<td colspan="2">
						<textarea name="msg" rows="6" cols="60"></textarea><br/>
						<input type="submit" title="go"/>
					</td>
				</tr>

			</table>
	</FORM>

	<table>
		<%Iterator i=msgs.iterator(); %>
		<% while(i.hasNext()) {%>
		
		<tr>
		
			
			<td>
				<%Message m=(Message)i.next(); %>
				user  <%=m.getUsername()%> said <%=m.getMessage() %>
				
			</td>
			
		</tr>
		
		<%} %>

	</table>

</body>
</html>