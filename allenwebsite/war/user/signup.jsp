<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%try{ %>
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





<p>register</p>
	<FORM ACTION="signup" METHOD=POST>
	username:<input type="text" name="username"/><br/>
	password:<input type="password" name="password"/><br/>
		<input type="submit" name="register!"/>
		
	</FORM>

	
</body>
<%}catch(Exception e){e.printStackTrace();} %>
</html>
