<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.google.appengine.labs.repackaged.org.json.JSONObject,java.util.*,com.allen.website.DBBean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Messages</title>
<jsp:useBean id="msgBean" class="com.allen.website.bean.MessageBean" /> 
<%

	HttpSession ses = request.getSession(true);
	Boolean issa=(Boolean)ses.getAttribute("admin");
	
	msgBean.init(request); 
	List<Message> msgs=msgBean.msgs;
  %>
<script type="text/javascript" src="./staticFiles/jquery-1.9.1.min.js"></script>

	
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


<script>
$("document").ready(
		function(){	
			var cursor=0;
			var numpages=<%=msgBean.numPages%>;
			var start=<%=msgBean.start%>;
			var end=<%=msgBean.end%>;
			var npp=<%=msgBean.numPostPerPage%>;
			var curpage=<%=msgBean.curpage%>;
			var txt="";
 
			txt=curpage+1+" of"+numpages+" pages   ";
			
			if(start-npp>=0)
				{txt+="<a href='messageadmin.jsp?start="+(start-npp)+
												"&end="+(end-npp)+
												"&curpage="+(curpage-1)+
												"&numpage="+numpages+"'> back </a>         ";}
			if(end+npp<=numpages*npp)
				{txt+="<a href='messageadmin.jsp?start="+end+
					"&end="+(end+npp)+
					"&curpage="+(curpage+1)+
					"&numpage="+numpages+"'> forward </a>";}
			
			
			$("div[id='pagenumbers']").html(txt);//"<a href='messageadmin.jsp?start=0&end=8&numpage=10'>aloha</a>");
			
		}
		
		);

</script>	
</head>
<body id="Content">





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
				
				User  <em><%=m.getUsername()%></em> at <em><%=m.getDate() %></em>
			</td>	
		</tr>
		
		<tr>
				
				
			<td><%=m.getMessage() %>
				
				<%if(null!=issa && issa) {%>
				<FORM ACTION="../message?action=delete" method="POST"> 
					<input type="hidden" name="id" value="<%=m.getId().getId() %>">
					<INPUT TYPE="submit" VALUE="delete">

				</FORM>
				<%} %>
			
			</td>
		</tr>
		
		<%} %>

	</table>
<div id="pagenumbers">

</div>
</body>
</html>