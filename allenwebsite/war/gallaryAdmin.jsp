<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gallery aDMIN</title>
</head>
<body>

<jsp:useBean id="gallaryBean" class="com.allen.website.bean.GallaryBean" /> 
<%

/*	HttpSession ses = request.getSession(true);

  if((Boolean)ses.getAttribute("postback"))
  {
	  
	  response.sendRedirect("gallaryAdmin.jsp");
  }*/
  gallaryBean.init(); 
  String titles[]=gallaryBean.getTitles();
  %>




	<FORM ENCTYPE="multipart/form-data" ACTION="addImage" METHOD=POST>
		<br><br><br>
			<table border="2">
				<tr>	
						<td colspan="2"><p align="center">
								<B>PROGRAM FOR UPLOADING THE FILE</B>
						</td>
				</tr>
				<tr>
					<td><b>Choose the file To Upload:</b></td>
					<td><INPUT NAME="F1" TYPE="file"></td>
				</tr>
				<tr>
					<td colspan="2">
						<p align="right">
							<INPUT TYPE="submit" VALUE="Send File">
						</p>
					</td>
				</tr>

			</table>
	</FORM>







					<table>
						<%int numcol=1; %>
						<% for (int i=0;i<titles.length;i++) {
								numcol++;
								if(numcol>6)
									numcol=0;
						
						%>
						<%if(numcol==0){ %>
							<tr>
						<%  } %>
								<td><img alt="" src="getImage?title=<%=titles[i]%>"></img>
								<br/>
									<FORM ENCTYPE="multipart/form-data" ACTION="delimage?title=<%=titles[i] %>" METHOD=POST>
										
										<INPUT TYPE="submit" VALUE="delete">
										
									</FORM>
								</td>
						<%if(numcol==0){ %>
							</tr>
							<%  } %>
						<% } %>

					</table>
</body>
</html>