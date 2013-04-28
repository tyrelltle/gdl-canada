<%try{ %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%HttpSession ses = request.getSession(true);
  boolean loggedon=(null!=ses.getAttribute("username"));
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
			<title>GDL - CANADA</title>
			<link href="./staticFiles/style.css" rel="stylesheet" type="text/css">
			<link href="./staticFiles/menu.css" rel="stylesheet" type="text/css">
			<meta http-equiv="Content-Style-Type" content="text/css">
			<meta http-equiv="Content-Script-type" content="text/javascript">
			<script type="text/javascript" async="" src="./staticFiles/ga.js"></script>
			<script type="text/javascript" src="./staticFiles/jquery-1.9.1.min.js"></script>
			<script type="text/javascript" src="./staticFiles/jscroll.js"></script>
			<script type="text/javascript">
			$(document).ready(function(){
					$('#gallaryHref').click(function(e){e.preventDefault();$('#ifr').prop('src','gallaryAdmin.jsp');});	
					$('#messageHref').click(function(e){e.preventDefault();$('#ifr').prop('src','messageadmin.jsp');});	
					
					<%String logonurl=loggedon?"user/logout":"user/logon.jsp";%>
					$('#loginHref').click(function(e){window.location="<%=logonurl%>";});	
			});
			</script>

							<style type="text/css">
.contents td {
	text-align: justify;
}

.select_box {
	width: 545px;
	height: 320px;
	float: left;
	overflow: auto;
	overflow-x: hidden;
}
</style>
</head>


<body>

	<div
		style="width: 1000px; height: 630px; margin-left: auto; margin-right: auto;">
		<div id="apDiv4" class="foot_right">

		</div>
		<div id="apDiv2">
			<img id="jingleft"
				src="./staticFiles/menu_bg03.png"
				width="323" height="1000" border="0" usemap="#Map" hidefocus="true">

				<map name="Map" id="Map">
					<area shape="rect" coords="50,537,185,565"
						href="http://customer.asiantigers-mobility.com/Login/Login.aspx?BranchCode=AP">
						<area shape="rect" coords="54,502,184,532"
							href="http://www.asiantigers-mobility.com/Contact_Us.aspx">
				</map>
		</div>

		
		<div id="apDiv4_guojia">
			<div class="bmenu"></div>
		</div>
		<div id="apDiv1">
			<ul class="bg">
				<div class="left">
					<a href="http://www.asiantigers-mobility.com/home.aspx"><img
						src="./staticFiles/header_logo.gif"
						width="227" height="63" alt="" style="border: 0px;"></a>
				</div>
				<div class="right">
					

				</div>
			</ul>
		</div>
		<div>
			<map name="Map_HomePage" id="Map_HomePage">
				<area shape="rect" coords="22,24,250,91"
					href="http://www.asiantigers-mobility.com/home.aspx">
			</map>
		</div>


		<div class="menu" style="z-index: 1500">
			<ul>
				<li style="margin-left: 120px;">
					<a class="hide"
						style="width: 80px;"
						href="http://www.asiantigers-mobility.com/home.aspx"> Home
							&nbsp;&nbsp;</a>
				</li>
				<li style="margin-left: 104px;">
					<a class="hide"
					style="width: 90px; color: #e2480c; background-image: url(images/menu_icon2.JPG); background-repeat: no-repeat; background-position: right 6px;"
					href="http://www.asiantigers-mobility.com/AboutUs_Company.aspx?name=Company+Profile#">About Us &nbsp;&nbsp;</a>
				</li>
				
				<li style="margin-left: 51px;">
					<a id="gallaryHref" href="" class="hide" style="width: 140px;">product gallary &nbsp;&nbsp;</a>
				</li>
				
				<li style="margin-left: 51px;">
					<a id="messageHref" href="" class="hide" style="width: 140px;">Leave Messages&nbsp;&nbsp;</a>
				</li>
				
				<li style="margin-left: 19px;">

					<a id="loginHref" class="hide"
					style="width: 170px;"
					><%if(loggedon)out.print("logout"); else out.print("logon");%> &nbsp;&nbsp;</a>
					
				
				</li>
			</ul>
		</div>

		<div id="apDiva">
			<img src="./staticFiles/about_company.jpg"
				width="793" alt="" height="131">

				

				<iframe id="ifr" width="800" height="777" frameBorder="0">Browser not compatible.</iframe>

		</div>
	</div>


</body>
<%}catch(Exception e){e.printStackTrace();} %>
</html>
