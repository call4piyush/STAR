package Register;

import connection.*;
import javax.servlet.http.HttpServlet;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class Register extends HttpServlet {

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		
		String e = request.getParameter("user");
		String p = request.getParameter("pass");
		
		
		
		try {

			Connection con = Connect.getconnection();

			PreparedStatement ps = con.prepareStatement("INSERT INTO registeruser (Emp_id,username,password) VALUES (?,?,?)");
			ps.setString(1, e);
			ps.setString(2, e);
			ps.setString(3, p);
			
			
			ps.executeUpdate();
			request.getRequestDispatcher("index_2.jsp").include(request, response);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		out.close();
	}

}
