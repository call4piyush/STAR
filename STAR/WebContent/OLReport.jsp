<%@page import="java.text.NumberFormat"%>
<%@page import=" java.text.DecimalFormat"%>
<%  	 
		if(request.getSession().getAttribute("empid")==null)
        {      request.getRequestDispatcher("index.jsp").forward(request, response);   
           }else{
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>User Inputs</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body class="panel-body">
	<div class="card">
		<header>
			<h2 align="center">
				<img src="./images/clogo.jpg" alt="Logo" align="left">
				STAR(Shortage Tracking Assessment & Review Tool) <img
					src="./images/logo.jpg" alt="Logo" align="right">
			</h2>
		</header>
		<br> <br>
		<p style="text-align: left;">
			Hello,
			<%= session.getAttribute( "name" ) %>
			<a style="float: right;" href="Logout">Logout</a>
		</p>
		<section>

			<div class="container" style="width: 20%; float: left;">
				<div class="login-card">
					<h3 align="center">Input</h3>
					<ul class="nav nav-pills nav-stacked">
						<li><a href="Inputs.jsp">Master Database</a></li>
						<li><a href="Inputs1.jsp">Weekly Input Files</a></li>
						<li><a href="Inputs2.jsp">Week Selection</a></li>

					</ul>
				</div>
				<br>
				<div class="login-card1">
					<h3 align="center">Calculations</h3>
					<ul class="nav nav-pills nav-stacked">
						<li><a href="run.jsp">Generate Reports</a></li>
					</ul>
				</div>
				<br>

				<div class="login-card1">
					<h3 align="center">Output</h3>
					<ul class="nav nav-pills nav-stacked">
						<li class="active"><a href="OLReport.jsp">Operational
								Level Report</a></li>
						<li><a href="ELReport.jsp">Executional Level Report</a></li>
						<li><a href="CEOLReport.jsp">CEO level Report</a></li>
						<li><a href="#">Dashboard</a></li>
					</ul>
				</div>
			</div>
			<div class="card1">
				<div class="tab-content">
					<center>
						<table class="table-responsive table-condensed"
							style="padding-top: 2%; width: 80%;">
							<tr>
								<td colspan="2" align="center"><h3>Operational Level
										Report</h3></td>
							</tr>
							<tr>
								<td></td>
							</tr>
							<tr>
								<td>Overall Raw Material Shortage Report</td>
								<td align="center"><a href="#" onclick="window.open('RawMatReport.jsp', 'newwindow', 'width=1200, height=700'); return false;"><input type="button"
										value="View"></a></td>
										<td><a href="DownloadRM"><input type="button" value="Download"></a></td>
							</tr>
							<tr>
								<td>Overall Semi-Finished Good Shortage Report</td>
								<td align="center"><a href="#" onclick="window.open('SFGReport.jsp', 'newwindow', 'width=1200, height=700'); return false;"><input type="button"
										value="View"></a></td>
										<td><a href="DownloadSFG"><input type="button" value="Download"></a></td>
							</tr>
							<tr>
								<td>Raw Material Shortage Report-SKU Level</td>
								<td align="center"><a href="#"><input type="button"
										value="View"></a></td>
										<td><a href="#"><input type="button" value="Download"></a></td>
							</tr>
							<tr>
								<td>Semi-Finished Good Shortage Report-SKU Level</td>
								<td align="center"><a href="#"><input type="button"
										value="View"></a></td>
										<td><a href="#"><input type="button" value="Download"></a></td>
							</tr>
							<tr>
								<td>Buyer SKU Level RM Shortage Report</td>
								<td align="center"><a href="#"><input type="button"
										value="View"></a></td>
										<td><a href="#"><input type="button" value="Download"></a></td>
							</tr>
							<tr>
								<td>Buyer SKU Level SFG Shortage Report</td>
								<td align="center"><a href="#"><input type="button"
										value="View"></a></td>
										<td><a href="#"><input type="button" value="Download"></a></td>
							</tr>
						</table>
					</center>
				</div>
			</div>
		</section>
		<div class="panel-body"></div>
		<footer class="panel-footer"
			style="bottom: 2%; text-align: center; color: white; position: relative;">
			Copyright <sup>&copy</sup> McKinsey & Company
		</footer>
	</div>
	</div>
	</div>
</body>
</html>
<%}%>