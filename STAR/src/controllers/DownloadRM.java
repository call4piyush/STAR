package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import connection.Connect;
@SuppressWarnings("serial")
public class DownloadRM extends HttpServlet
{
	private String filePath;
	public void init( ){
	      // Get the file location where it would be stored.
	      filePath = 
	             getServletContext().getInitParameter("file-upload"); 
	   }
	   @SuppressWarnings("null")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	              { 
		   HttpSession session=null;
		try{
	  createRMShortage();
	  //session.setAttribute("Raw Material Shortage Report.xlsx","filename");
      RequestDispatcher ReqDis = request.getRequestDispatcher("downloadRM.jsp");
      ReqDis.forward(request, response);
   }
catch(Exception ex) {
	  ex.printStackTrace();

		RequestDispatcher ReqDis = request.getRequestDispatcher("Error.jsp");
		ReqDis.forward(request, response);
		
 }
}
	private void createRMShortage() throws SQLException, IOException {
		int i=1;
		   String sql = "select * from rmshortage where `Category`=?";
	      //Create blank workbook
	      XSSFWorkbook workbook = new XSSFWorkbook(); 
	      //Create a blank sheet
	      XSSFSheet spreadsheet = workbook.createSheet("Raw Material Shortage Report");
	      //Create row object
	      XSSFRow row;
	      //This data needs to be written (Object[])
	      Map < String, Object[] > empinfo =new TreeMap < String, Object[] >();
	      empinfo.put( String.valueOf(i), new Object[] {"Category", "Material Group", "Material Group Description", "Material Sub-Group", "Material Sub-Group Description", "Finished Good Number", "Component / FG	Description", "Unit", "Requirement", "Availability", "Shortage", "Immidiate Parent", "Initial Stock","Week No" });
	      Connection con = Connect.getconnection();
	      PreparedStatement ps=con.prepareStatement(sql);
	      ps.setString(1, "ZRAW");
	      ResultSet rs = ps.executeQuery();
	      i++;
	      while( rs.next() )
			{      
	    	  empinfo.put( String.valueOf(i) , new Object[] {rs.getString("Category"),rs.getString("Material Group"),rs.getString("Material Group Desc"),rs.getString("Mat Sub-Group"),rs.getString("Mat Sub-Group Desc"),rs.getString("FGNo"),rs.getString("Node"), rs.getString("Unit"),rs.getString("Requirement") ,rs.getString("Availability") , rs.getString("Shortage") ,rs.getString("Parent") ,rs.getString("Stock"),rs.getString("Week_No")});
	    	  i++;
			}
	      rs.close();
	      ps.close();
	      con.close();
	      Set < String > keyid = empinfo.keySet();
	      int rowid = 0;
	      for (String key : keyid)
	      {
	         row = spreadsheet.createRow(rowid++);
	         Object [] objectArr = empinfo.get(key);
	         int cellid = 0;
	         for (Object obj : objectArr)
	         {
	            Cell cell = row.createCell(cellid++);
	            cell.setCellValue((String)obj);
	         }
	      }
	      //Write the workbook in file system
	      FileOutputStream out = new FileOutputStream(new File(filePath+"Raw Material Shortage Report.xlsx"));
	      workbook.write(out);
	      out.close();
		
	}
}