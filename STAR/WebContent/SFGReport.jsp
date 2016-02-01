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
<title>Semi-Finished Good Shortage Report</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body class="panel-body">
	<div class="tab-content">
		<center>
			<section style="width: 100%" class="panel-body">
				<h2>Semi-Finished Good Shortage Report</h2>
				<div style="overflow: scroll; height: 90%; width: 95%">
					<table align="center" class="table table-bordered"
						style="text-align: center; font-weight: lighter;">
						<thead>
							<tr>
								<th style="text-align: center;"><h4>SNO</h4></th>
								<th style="text-align: center;"><h4>Category</h4></th>
								<th style="text-align: center;"><h4>Material Group</h4></th>
								<th style="text-align: center;"><h4>Material Group
										Description</h4></th>
								<th style="text-align: center;"><h4>Material Sub Group</h4></th>
								<th style="text-align: center;"><h4>Material Sub Group
										Description</h4></th>
								<th style="text-align: center;"><h4>FG Part Number</h4></th>
								<th style="text-align: center;"><h4>Component / FG	Description</h4></th>
								<th style="text-align: center;"><h4>Unit</h4></th>
								<th style="text-align: center;"><h4>Requirement</h4></th>
								<th style="text-align: center;"><h4>Availability</h4></th>
								<th style="text-align: center;"><h4>Shortage</h4></th>
								<th style="text-align: center;"><h4>Immediate parent</h4></th>
								<th style="text-align: center;"><h4>Initial Stock</h4></th>
								<th style="text-align: center;"><h4>Manufactured</h4></th>
								<th style="text-align: center;"><h4>Week Number</h4></th>
							</tr>
						</thead>
						<%
		 String sql = "SELECT * FROM star.sfgshortage where Category=?";
			 
				try{ 
							 Connection con = Connect.getconnection();
							 PreparedStatement ps=con.prepareStatement(sql);
							 ps.setString(1, "ZSFG");
							 int i=1;
					 		 ResultSet rs = ps.executeQuery();
					              while( rs.next() )
									{
							 	%><tr>
						<tbody>
							<td><%= i %></td>
							<td><%= rs.getString("Category") %></td>
							<td><%= rs.getString("Material Group") %></td>
							<td><%= rs.getString("Material Group Desc") %></td>
							<td><%= rs.getString("Mat Sub-Group") %></td>
							<td><%= rs.getString("Mat Sub-Group Desc") %></td>
							<td><%= rs.getString("FGNo") %></td>
							<td><%= rs.getString("Node") %></td>
							<td><%= rs.getString("Unit") %></td>
							<td><%= rs.getString("Requirement") %></td>
							<td><%= rs.getString("Availability") %></td>
							<td><%= rs.getString("Shortage") %></td>
							<td><%= rs.getString("Parent") %></td>
							<td><%= rs.getString("Stock") %></td>
							<td><%= rs.getString("Manufactured") %></td>
							<td><%= rs.getString("Week_No") %></td>
						</tbody>
						</tr>
						<%i++;
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
