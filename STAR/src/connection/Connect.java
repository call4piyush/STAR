package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	
	public static Connection getconnection() {
		
		try{
			String url = "jdbc:mysql://localhost:3306/";
			 String dbName = "star"; 
			 String driver = "com.mysql.jdbc.Driver";
			 String userName = "Piyush";
			 String password = "root";
			 Class.forName(driver).newInstance();
			 Connection con = DriverManager.getConnection(url+dbName,userName,password);
			 return con;
	}
		catch(Exception e){
			System.out.println(e);
	}
		return null;
		
	}
}
