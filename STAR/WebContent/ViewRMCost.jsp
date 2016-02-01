<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="connection.Connect"%>

<%@page import=" java.text.DecimalFormat"%>
<%  	 
		if(request.getSession().getAttribute("empid")==null)
        {      request.getRequestDispatcher("index.jsp").forward(request, response);   
           }else{
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>View Stock</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body class="panel-body">
	<div class="tab-content">
		<center>
			<section style="width: 100%" class="panel-body">
				<h2>RM & SFG Cost File</h2>
				<div style="overflow: scroll; height: 90%; width: 95%">
					<table align="center" class="table table-bordered"
						style="text-align: center; font-weight: lighter;">
						<thead>
							<tr>
								<th style="text-align: center;"><h4>SNO</h4></th>
								<th style="text-align: center;"><h4>Material</h4></th>
								<th style="text-align: center;"><h4>Price</h4></th>
								
							</tr>
						</thead>
						<%
		 String sql = "select * from rmcost";
			 
				try{ 
							 Connection con = Connect.getconnection();
							 PreparedStatement ps=con.prepareStatement(sql);
							 //Statement s = con.createStatement();
					 		 //ResultSet rs = s.executeQuery("select * from usersession");
					 		 ResultSet rs = ps.executeQuery();
					              while( rs.next() )
									{
							 	%><tr>
						<tbody>
							<td><%= rs.getString("SNO") %></td>
							<td><%= rs.getString("Material") %></td>
							<td><%= rs.getString("Cost") %></td>
						</tbody>
						</tr>
						<%
					 		}
			
				}
				catch(Exception e){
					e.printStackTrace();
					RequestDispatcher ReqDis = request.getRequestDispatcher("Error.jsp");
				ReqDis.forward(request, response);
				}
		%>
					</table>
				</div>
		</center>
	</div>
	</div>
	</section>
</body>
</html>
<%}%>
