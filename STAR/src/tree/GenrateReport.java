package tree;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.Connect;

public class GenrateReport extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println( sdf.format(cal.getTime()) );
		double qtyPerParent = 0;
		double requirement = 0;
		double updatedreq = 0;
		double availability = 0;
		double manufactured = 99999;
		double allocated = 0;
		double stock = 0;

		String code = null;
		String desc = null;
		String parentFG = null;
		String matgrp = null;
		String matgrpdesc = null;
		String matsubgrp = null;
		String matsubgrpdesc = null;
		String unit = null;
		String leadtime = null;
		String category = null;
		String nature_category = null;
		String freq_category = null;
		int week = 0;
		String cate = null;	
		String req=null;
		
		Tree<String> tree = null;
		Node<String> node = null;
		Node<String> Parent = null;
		String parent, child;
		try{
		Connection con = Connect.getconnection();
		PreparedStatement ps11=con.prepareStatement("truncate star.rmshortage");
		ps11.executeQuery();
		ps11.close();
		ps11=con.prepareStatement("truncate star.sfgshortage");
		ps11.executeQuery();
		ps11.close();
		
		PreparedStatement ps = con.prepareStatement("select * from bom");
		PreparedStatement ps1 = con.prepareStatement("select * from demand ");
		PreparedStatement ps2 = con.prepareStatement("select * from data");

		ResultSet rs2 = ps2.executeQuery();
		while (rs2.next()) {
			week=rs2.getInt("week");
			cate = rs2.getString("cat");
		}

		if (cate.equals("Individual")) {
			
			for(int i=1;i<(week+1);i++){
				req="wk"+i;
				ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				requirement = rs1.getDouble(req);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					parent = rs.getString("Parent");
					child = rs.getString("Child");
					desc = rs.getString("Decs");
					qtyPerParent = rs.getDouble("Qty");
					parentFG = rs.getString("FGNo");
					matgrp = rs.getString("Mat_grp");
					matgrpdesc = rs.getString("Mat_grp_desc");
					matsubgrp = rs.getString("Mat_sub_grp");
					matsubgrpdesc = rs.getString("Mat_sub_grp_desc");
					unit = rs.getString("Unit");
					//stock = rs.getDouble("Stock");

					if (parentFG.equals(rs1.getString("demand.FGNo"))) {
						if (parent.equals("Root")) {
							Parent = new Node<String>(parent, 1, requirement, requirement, 0, parent, null,
									manufactured, 0, 0, parentFG, null, null, null,null, null, null,"ZFIN", null, null);
							Parent.addChild(new Node<String>(child, qtyPerParent, requirement, updatedreq, availability,
									code, desc, manufactured, allocated, stock, parentFG, matgrp, matgrpdesc, matsubgrp,
									matsubgrpdesc, unit, leadtime, "ZFIN", nature_category, freq_category));
							tree = new Tree<String>(Parent);
						} else {
							node = tree.findNode(Parent, parent);
							node.addChild(new Node<String>(child, qtyPerParent, 0, updatedreq, availability, code, desc,
									manufactured, allocated, stock, parentFG, matgrp, matgrpdesc, matsubgrp,
									matsubgrpdesc, unit, leadtime, category, nature_category, freq_category));
							tree = new Tree<String>(node);
						}

					} 
				}
				tree.genrateReport(i);
				tree=null;
				Parent=null;
				node=null;
				}
			}
		} else {
			//todo
		}
		ps.close();
		ps1.close();
		ps2.close();
		con.close();
		Calendar cal1 = Calendar.getInstance();
		System.out.println( sdf.format(cal1.getTime()) );
		RequestDispatcher ReqDis = request.getRequestDispatcher("afterRun.jsp");
	 	ReqDis.forward(request, response);
		}
		catch(Exception ex) {
	    	  ex.printStackTrace();
	
				RequestDispatcher ReqDis = request.getRequestDispatcher("Error.jsp");
				ReqDis.forward(request, response);
				
		   }
	}

}
