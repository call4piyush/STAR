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
<script type="text/javascript">
function alertName(){
alert("MRQ File Uploaded Successfully");
} 
</script> 
</head>

<body class="panel-body" onload="alertName()">
	<div class="card">
		<header>
			<h2 align="center">
				<img src="./images/clogo.jpg" alt="Logo" align="left">
				STAR(Shortage Tracking Assessment &#38; Review Tool) <img
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
						<li class="active"><a href="Inputs.jsp">Master Database</a></li>
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
						<li><a href="OLReport.jsp">Operational Level Report</a></li>
						<li><a href="ELReport.jsp">Executional Level Report</a></li>
						<li><a href="CEOLReport.jsp">CEO level Report</a></li>
						<li><a href="#">Dashboard</a></li>
					</ul>
				</div>
			</div>

			<div class="card1">
				<div class="tab-content">
					<form action="UploadBOM" method="post"
									class="form-group panel-body" enctype="multipart/form-data">
					<center>
						<table class="table-responsive table-condensed"
							style="padding-top: 2%; width: 100%;">

							<tr>
								<td colspan="4"><h3>Master Database</h3></td>
							</tr>
							<tr>
								<td></td>
							</tr>
							<tr>
								
									<td>Overall Bill Of Material (BOM):</td>
									<td><input type="file" accept=".xlsx"  name="BOM" value="Upload" required="required" /></td>
									<td><input type="submit" value="Upload"></td>
									<td><a href="#"
										onclick="window.open('ViewBOM.jsp', 'newwindow', 'width=600, height=600'); return false;">
											<input type="button" value="View">
									</a></td>
								</form>
							</tr>
							<!--<tr>
								<form action="UploadMRQ" method="post" class="form-group panel-body"
									enctype="multipart/form-data"><div>
									<td>Material Receipt Quantity(MRQ):</td>
									<td><input type="file" accept=".xlsx"  name="MRQ" value="Upload" required="required"/></td>
									<td><input type="submit" value="Upload"></td>
									<td><a href="#"><input type="button" value="View"></a></td></div>
								</form>
							</tr>-->
							<tr>
								<form action="UploadRRS" method="post" class="form-group panel-body"
									enctype="multipart/form-data">
									<td><div class="image-upload">Runner Repeater &#38;
											Stranger (RRS) Classification:</td>
									<td><input type="file" accept=".xlsx"  name="RRS" value="Upload" required="required"/></td>
									<td><input type="submit" value="Upload"></td>
									<td><a href="#" onclick="window.open('ViewRRS.jsp', 'newwindow', 'width=600, height=600'); return false;"><input type="button" value="View"></a>
									</td>
								</form>
							</tr>
							<tr>
								<form action="UploadFGCost" method="post" class="form-group panel-body"
									enctype="multipart/form-data">
									<td><div class="image-upload">FG Cost Database:</td>
									<td><input id="file-input3" type="file" accept=".xlsx"  name="FGCOST"
										value="Upload" required="required"/></td>
									<td><input type="submit" value="Upload"></td>
									<td><a href="#" onclick="window.open('ViewFG.jsp', 'newwindow', 'width=600, height=600'); return false;"><input type="button" value="View"></a>
									</td>
								</form>
							</tr>
							<tr>
								
									
									<td><div class="image-upload">RM &#38; SFG Cost
											Database:</td>
									<form action="UploadRMCost" method="post" class="form-group panel-body" enctype="multipart/form-data">
									<td><input type="file" accept=".xlsx"  name="RM" value="Upload" required="required"/></td>
									<td><input type="submit" value="Upload"></td></form>
									<td><a href="#" onclick="window.open('ViewRMCost.jsp', 'newwindow', 'width=600, height=600'); return false;"><input type="button" value="View"></a>
									</td>
								
							</tr>

						</table>
					</center>
					
				</div>
			</div>
		</section>
		<div class="panel-body"></div>
		<footer class="panel-footer"
			style="bottom: 2%; text-align: center; color: white; position: relative;">
			Copyright <sup>&copy</sup> McKinsey &#38; Company
		</footer>
	</div>
	</div>
	</div>
</body>
</html>
<%}%>