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
						<li class="active"><a href="Inputs2.jsp">Week Selection</a></li>

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
						<li><a href="OLReport.jsp">Operational Level Report</a></li>
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
							style="padding-top: 2%; width: 70%;">
							<form method="post" class="form-group panel-body" action="Save"
								name="forms">
								<tr>
									<td colspan="4"><h3>Week Selection</h3></td>
								</tr>

								
								<tr>
									<td colspan="3">Please Select Category</td>
									<td><select name="cat" class="form-control"><option
												value="Individual">Individual</option>
											<option value="Cumulative">Cumulative</option>
									</select></td>
								</tr>
								<tr>
									<td colspan="4">Please Select Run Uptill Weeks:</td>
								</tr>
								<tr>
								
									<td>Week-1</td>
									<td><input type="radio" name="week" value="1"></td>
									<td>Week-7</td>
									<td><input type="radio" name="week" value="7"></td>
									</center>
								</tr>
								<tr>
									<td>Week-2</td>
									<td><input type="radio" name="week" value="2"></td>
									<td>Week-8</td>
									<td><input type="radio" name="week" value="8"></td>
								</tr>
								<tr>
									<td>Week-3</td>
									<td><input type="radio" name="week" value="3"></td>
									<td>Week-9</td>
									<td><input type="radio" name="week" value="9"></td>
								</tr>
								<tr>
									<td>Week-4</td>
									<td><input type="radio" name="week" value="4"></td>
									<td>Week-10</td>
									<td><input type="radio" name="week" value="10"></td>
								</tr>
								<tr>
									<td>Week-5</td>
									<td><input type="radio" name="week" value="5"></td>
									<td>Week-11</td>
									<td><input type="radio" name="week" value="11"></td>
								</tr>
								<tr>
									<td>Week-6</td>
									<td><input type="radio" name="week" value="6"></td>
									<td>Week-12</td>
									<td><input type="radio" name="week" value="12"></td>
								</tr>
								
								<tr>
									<td colspan="4" style="text-align: center;"><input
										type="submit" name="submit" value="Submit Values"
										class="btn-primary btn-md btn"></td>
								</tr>
							</form>
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