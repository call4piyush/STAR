package Login;

import java.sql.*;
import connection.Connect;
public class Validate {
	public static boolean checkUser(String empid, String pass) {
		boolean st = false;
		try {

			Connection con = Connect.getconnection();
			PreparedStatement ps = con.prepareStatement("select * from registeruser where Emp_id=? and password=?");
			ps.setString(1, empid);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			st = rs.next();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return st;
	}

	public static String getName(String emp) {
		String name = null;
		try {
			Connection con = Connect.getconnection();
			PreparedStatement ps = con.prepareStatement("select * from registeruser where Emp_id=?");
			ps.setString(1, emp);
			ResultSet rs=ps.executeQuery();  
			while(rs.next()){
			name=rs.getString("username");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;

	}
	public static int getflag(String emp) {
		int flag=0;
		try {
			Connection con = Connect.getconnection();
			PreparedStatement ps = con.prepareStatement("select * from registeruser where Emp_id=?");
			ps.setString(1, emp);
			ResultSet rs=ps.executeQuery();  
			while(rs.next()){
			flag=rs.getInt("flag");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}
}