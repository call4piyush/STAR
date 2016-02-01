package controllers;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

import connection.Connect;

import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@SuppressWarnings("serial")
public class UploadDemand extends HttpServlet {
	private boolean isMultipart;
	   private String filePath;
	   private int maxFileSize = 100000 * 1024;
	   private int maxMemSize = 50 * 1024;
	   private File file ;

	   public void init( ){
	      // Get the file location where it would be stored.
	      filePath = 
	             getServletContext().getInitParameter("file-upload"); 
	   }
	   public void doPost(HttpServletRequest request, 
	               HttpServletResponse response) throws ServletException, IOException 
	              {
		   		   
		  //String empid = request.getParameter("empid");
		   int empid = 0;
		   java.io.PrintWriter out;
	      // Check that we have a file upload request
	      isMultipart = ServletFileUpload.isMultipartContent(request);
	      response.setContentType("text/html");
	     // 
	      if( !isMultipart ){
	         return;
	      }
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);
	      // Location to save data that is larger than maxMemSize.
	      //factory.setRepository(new File("d:\\temp"));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );
	      //System.out.println(empid);
	      try{ 
	    	  //out = response.getWriter( );
	      // Parse the request to get file items.
	      List fileItems = upload.parseRequest(request);
		
	      // Process the uploaded file items
	      Iterator i = fileItems.iterator();
	      
	      
	      while ( i.hasNext () ) 
	      {
	         FileItem fi = (FileItem)i.next();
	         if ( !fi.isFormField () )	
	         {
	            // Get the uploaded file parameters
	            String fieldName = fi.getFieldName();
	            String fileName = fi.getName();
	            String contentType = fi.getContentType();
	            boolean isInMemory = fi.isInMemory();
	            long sizeInBytes = fi.getSize();
	            // Write the file
	            if( fileName.lastIndexOf("\\") >= 0 ){
	               file = new File( filePath + 
	               fileName.substring( fileName.lastIndexOf("\\"))) ;
	            }else{
	               file = new File( filePath + 
	               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
	            }
	            fi.write( file ) ;
	            readDemand(file);           
	            break;
	            }
	       }
	  
	      RequestDispatcher ReqDis = request.getRequestDispatcher("afterDemand.jsp");
	 	  ReqDis.forward(request, response);
	 		
	      }
	      catch(Exception ex) {
	    	  ex.printStackTrace();
				RequestDispatcher ReqDis = request.getRequestDispatcher("Error.jsp");
				ReqDis.forward(request, response);
		   }
	   }
	   private void readDemand(File fileItem) throws Exception {
		 	FileInputStream file = new FileInputStream(fileItem);
		    XSSFWorkbook workbook = new XSSFWorkbook(file);
		    XSSFSheet sheet = workbook.getSheetAt(0);
		    Iterator<Row> rowIterator = sheet.iterator();
		    Connection con = Connect.getconnection();
		    PreparedStatement ps1=con.prepareStatement("TRUNCATE TABLE demand");
		    ps1.executeUpdate();
		    
		    Row nextRow = rowIterator.next();
		    while (rowIterator.hasNext()) {
		    	nextRow = rowIterator.next();
		        Iterator<Cell> cellIterator = nextRow.cellIterator();
		        PreparedStatement ps = con.prepareStatement("INSERT INTO `star`.`demand` (`FGNo`, `Wk1`, `Wk2`, `Wk3`, `Wk4`, `Wk5`, `Wk6`, `Wk7`, `Wk8`, `Wk9`, `Wk10`, `Wk11`, `Wk12`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");	        		

		        while (cellIterator.hasNext()) {
		        	Cell cell = cellIterator.next();
		            switch (cell.getCellType()) {
		                case Cell.CELL_TYPE_STRING :
		                	if(cell.getColumnIndex()==0)
		                	{
		                		//FG
		                		ps.setString(1, cell.getStringCellValue());
		                		//System.out.println(cell.getStringCellValue());
		                		break;
		                	}
		                	
		                case Cell.CELL_TYPE_NUMERIC:
		                	 if(cell.getColumnIndex()==1)
		                	{
		                		
		                		 ps.setDouble(2, cell.getNumericCellValue());
		                		 //System.out.println(cell.getNumericCellValue());
		                		break;
		                	}
		                	else if(cell.getColumnIndex()==2)
		                	{
		                		
		                		 ps.setDouble(3, cell.getNumericCellValue());
		                		 //System.out.println(cell.getNumericCellValue());
		                		break;
		                	}
		                	else if(cell.getColumnIndex()==3)
		                	{
		                		
		                		 ps.setDouble(4, cell.getNumericCellValue());
		                		 //System.out.println(cell.getNumericCellValue());
		                		break;
		                	}
		                	else if(cell.getColumnIndex()==4)
		                	{
		                		
		                		 ps.setDouble(5, cell.getNumericCellValue());
		                		 //System.out.println(cell.getNumericCellValue());
		                		break;
		                	}
		                	else if(cell.getColumnIndex()==5)
		                	{
		                		
		                		 ps.setDouble(6, cell.getNumericCellValue());
		                		 //System.out.println(cell.getNumericCellValue());
		                		break;
		                	}
		                	else if(cell.getColumnIndex()==6)
		                	{
		                		
		                		 ps.setDouble(7, cell.getNumericCellValue());
		                		 //System.out.println(cell.getNumericCellValue());
		                		break;
		                	}
		                	else if(cell.getColumnIndex()==7)
		                	{
		                		
		                		 ps.setDouble(8, cell.getNumericCellValue());
		                		 //System.out.println(cell.getNumericCellValue());
		                		break;
		                	}
		                	else if(cell.getColumnIndex()==8)
		                	{
		                		
		                		 ps.setDouble(9, cell.getNumericCellValue());
		                		 //System.out.println(cell.getNumericCellValue());
		                		break;
		                	}
		                	else if(cell.getColumnIndex()==9)
		                	{
		                		
		                		 ps.setDouble(10, cell.getNumericCellValue());
		                		 //System.out.println(cell.getNumericCellValue());
		                		break;
		                	}
		                	else if(cell.getColumnIndex()==10)
		                	{
		                		
		                		 ps.setDouble(11, cell.getNumericCellValue());
		                		 //System.out.println(cell.getNumericCellValue());
		                		break;
		                	}
		                	else if(cell.getColumnIndex()==11)
		                	{
		                		
		                		 ps.setDouble(12, cell.getNumericCellValue());
		                		 //System.out.println(cell.getNumericCellValue());
		                		break;
		                	}
		                	else if(cell.getColumnIndex()==12)
		                	{
		                		
		                		 ps.setDouble(13, cell.getNumericCellValue());
		                		 //System.out.println(cell.getNumericCellValue());
		                		break;
		                	}
		                case Cell.CELL_TYPE_BLANK:
		    	            	if(cell.getColumnIndex()==0)
		                	{
		                		//FG
		                		ps.setString(1, " ");
		                		//System.out.println(cell.getStringCellValue());
		                		break;
		                	}
		    	            	else if(cell.getColumnIndex()==1)
				                	{
				                		
				                		 ps.setDouble(2, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==2)
				                	{
				                		
				                		 ps.setDouble(3, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==3)
				                	{
				                		
				                		 ps.setDouble(4, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==4)
				                	{
				                		
				                		 ps.setDouble(5, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==5)
				                	{
				                		
				                		 ps.setDouble(6, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==6)
				                	{
				                		
				                		 ps.setDouble(7, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==7)
				                	{
				                		
				                		 ps.setDouble(8, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==8)
				                	{
				                		
				                		 ps.setDouble(9, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==9)
				                	{
				                		
				                		 ps.setDouble(10, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==10)
				                	{
				                		
				                		 ps.setDouble(11, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==11)
				                	{
				                		
				                		 ps.setDouble(12, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==12)
				                	{
				                		
				                		 ps.setDouble(13, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
		                case Cell.CELL_TYPE_ERROR:
		                	if(cell.getColumnIndex()==0)
		                	{
		                		//FG
		                		ps.setString(1, " ");
		                		//System.out.println(cell.getStringCellValue());
		                		break;
		                	}
		    	            	else if(cell.getColumnIndex()==1)
				                	{
				                		
				                		 ps.setDouble(2, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==2)
				                	{
				                		
				                		 ps.setDouble(3, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==3)
				                	{
				                		
				                		 ps.setDouble(4, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==4)
				                	{
				                		
				                		 ps.setDouble(5, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==5)
				                	{
				                		
				                		 ps.setDouble(6, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==6)
				                	{
				                		
				                		 ps.setDouble(7, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==7)
				                	{
				                		
				                		 ps.setDouble(8, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==8)
				                	{
				                		
				                		 ps.setDouble(9, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==9)
				                	{
				                		
				                		 ps.setDouble(10, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==10)
				                	{
				                		
				                		 ps.setDouble(11, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==11)
				                	{
				                		
				                		 ps.setDouble(12, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
				                	else if(cell.getColumnIndex()==12)
				                	{
				                		
				                		 ps.setDouble(13, 0);
				                		 //System.out.println(cell.getNumericCellValue());
				                		break;
				                	}
		            }
		           //out.print("&nbsp;&nbsp;&nbsp;&nbsp");
		        }ps.executeUpdate();
		       //out.println("<br>");
		        
		    }
		    workbook.close();
		    file.close();
			con.close();
		    }
	 

}