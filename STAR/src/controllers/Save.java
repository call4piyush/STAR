package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.Connect;

public class Save extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection con = Connect.getconnection();
	    PreparedStatement ps;
		try {
			ps = con.prepareStatement("TRUNCATE TABLE data");
			 ps.executeUpdate();
		
		String cat=req.getParameter("cat");
		String week=req.getParameter("week");
		PreparedStatement ps1 = con.prepareStatement("INSERT INTO `star`.`data` (`cat`,`week`) VALUES  (?,?)");	        		
		ps1.setString(1,cat);
		ps1.setString(2, week);
		ps1.executeUpdate();
		
		RequestDispatcher ReqDis = req.getRequestDispatcher("run.jsp");
	 	ReqDis.forward(req, resp);
		} catch (SQLException e) {
			
			e.printStackTrace();
			RequestDispatcher ReqDis = req.getRequestDispatcher("Error.jsp");
		 	ReqDis.forward(req, resp);
		}
	   
	}
}
