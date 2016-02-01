package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import connection.Connect;

public class UploadRRS extends HttpServlet {
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
	   @SuppressWarnings("null")
	public void doPost(HttpServletRequest request, 
	               HttpServletResponse response) throws ServletException, IOException 
	              {
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
	            readRRS(file);
	            break;
	         
	         }
	        }
	        RequestDispatcher ReqDis = request.getRequestDispatcher("afterRRS.jsp");
	 		ReqDis.forward(request, response);
	 		
	      }
	      catch(Exception ex) {
	    	  ex.printStackTrace();
	
				RequestDispatcher ReqDis = request.getRequestDispatcher("Error.jsp");
				ReqDis.forward(request, response);
				
		   }
}
	private void readRRS(File fileItem) throws IOException, SQLException {
		FileInputStream file = new FileInputStream(fileItem);
	    XSSFWorkbook workbook = new XSSFWorkbook(file);
	    XSSFSheet sheet = workbook.getSheetAt(0);
	    Iterator<Row> rowIterator = sheet.iterator();
	    Connection con = Connect.getconnection();
	    PreparedStatement ps1=con.prepareStatement("TRUNCATE TABLE rrs");
	    ps1.executeUpdate();
	    
	    Row nextRow = rowIterator.next();
	    while (rowIterator.hasNext()) {
	    	nextRow = rowIterator.next();
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	        PreparedStatement ps = con.prepareStatement("INSERT INTO `star`.`rrs` (`FGNo`,`FGType`,`Type`) VALUES(?,?,?)");	        		

	        while (cellIterator.hasNext()) {
	        	Cell cell = cellIterator.next();
	            switch (cell.getCellType()) {
	                case Cell.CELL_TYPE_STRING :
	                	if(cell.getColumnIndex()==0)
	                	{
	                		ps.setString(1, cell.getStringCellValue());
	                		break;
	                	}             
	                	else if(cell.getColumnIndex()==1)
	                	{	                		
	                		 ps.setString(2, cell.getStringCellValue());
	                		 break;
	                	}
	                	else if(cell.getColumnIndex()==2)
		                {	                		
		                	 ps.setString(3, cell.getStringCellValue());
		                	 break;
		                }
	                	
	                case Cell.CELL_TYPE_BLANK:
	                	if(cell.getColumnIndex()==0)
	                	{
	                		ps.setString(1, cell.getStringCellValue());
	                		break;
	                	}             
	                	else if(cell.getColumnIndex()==1)
	                	{	                		
	                		 ps.setString(2, cell.getStringCellValue());
	                		 break;
	                	}
	                	else if(cell.getColumnIndex()==2)
		                {	                		
		                	 ps.setString(3, cell.getStringCellValue());
		                	 break;
		                }
			                
	                case Cell.CELL_TYPE_ERROR:
	                	if(cell.getColumnIndex()==0)
	                	{
	                		ps.setString(1, cell.getStringCellValue());
	                		break;
	                	}             
	                	else if(cell.getColumnIndex()==1)
	                	{	                		
	                		 ps.setString(2, cell.getStringCellValue());
	                		 break;
	                	}
	                	else if(cell.getColumnIndex()==2)
		                {	                		
		                	 ps.setString(3, cell.getStringCellValue());
		                	 break;
		                }
	            }
	        }ps.executeUpdate();
	       
	    }
	    workbook.close();
	    file.close();
		con.close();

		
	}
}
