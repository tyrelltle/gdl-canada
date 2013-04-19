<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gallery aDMIN</title>
<link rel="stylesheet" type="text/css" href="staticFiles/shadowbox-3.0.3/shadowbox.css">
	<script type="text/javascript" src="staticFiles/shadowbox-3.0.3/shadowbox.js"></script>
	<script type="text/javascript">
	Shadowbox.init();
	</script>
	
	
	
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

<jsp:useBean id="gallaryBean" class="com.allen.website.bean.GallaryBean" /> 
<%

	HttpSession ses = request.getSession(true);
	Boolean issa=(Boolean)ses.getAttribute("admin");
  gallaryBean.init(); 
  String titles[]=gallaryBean.getTitles();
  %>



<%  if(null!=issa && issa){ %>
	<FORM ENCTYPE="multipart/form-data" ACTION="addImage" METHOD=POST>
		<br><br><br>
			<table  border="2" float="center">
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
<%} %>


	<table>
		<%int numcol=0; %>
		<% for (int i=0;i<titles.length;i++) {
		if(numcol==0){ %>
		<tr>
		<%} 
			numcol++;
			if(numcol>2)
			numcol=0;%>
			<td>
				<a href="getImage?title=<%=titles[i]%>" rel="shadowbox;player=img">
				<img alt="" src="getImage?isthum=1&title=<%=titles[i]%>"></img> 
				</a>
				<br />
				
				<%if(null!=issa && issa) {%>
				<FORM ENCTYPE="multipart/form-data"
					ACTION="delimage?title=<%=titles[i] %>" METHOD=POST>

					<INPUT TYPE="submit" VALUE="delete">

				</FORM>
				<%} %>
				</td>
			<%if(numcol==0){ %>
		</tr>
		<%  } %>
		<%} %>

	</table>
</body>
</html>