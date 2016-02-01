package Login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			String empid = request.getParameter("emp");
			String password = request.getParameter("pass");

			if (Validate.checkUser(empid, password)) {
				HttpSession session = request.getSession();
				String name = Validate.getName(empid);
				
				session.setAttribute("empid", empid);
				session.setAttribute("name", name);
				
				int flag = Validate.getflag(empid);

				if (flag == 1) {
					RequestDispatcher rs = request.getRequestDispatcher("Inputs.jsp");
					rs.forward(request, response);
				} else {
					RequestDispatcher rs = request.getRequestDispatcher("#");
					rs.forward(request, response);
				}
			} else {
				
				RequestDispatcher rs = request.getRequestDispatcher("index_1.jsp");
				rs.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("<font color='white'>");
			out.println("<center >User id or Password incorrect</center>");
			out.println("</font>");
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			rs.include(request, response);
		}
	}
}