package Logout;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Logout extends HttpServlet {
  public void doGet( HttpServletRequest req, HttpServletResponse res )
      throws ServletException, IOException {
    res.setContentType( "text/html" );
    
    HttpSession session = req.getSession(false);

   
    if( session != null ) {
      
      synchronized( session ) {
        // invalidating a session destroys it
        session.invalidate();
      }
      RequestDispatcher rs = req.getRequestDispatcher("index.jsp");
 	 rs.forward(req, res);
    }else{
    RequestDispatcher rs = req.getRequestDispatcher("index.jsp");
	 rs.forward(req, res);
    }
    
  }
}
