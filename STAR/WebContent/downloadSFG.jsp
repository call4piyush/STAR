<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="connection.Connect"%>
<%@page import="java.io.File" %>
<%@page import="java.io.FileInputStream" %>
<%@page import="java.io.OutputStream" %>
<% 					
String filename="Semi Finished Good Shortage Report.xlsx";
//String filename=(String)session.getAttribute("filename");
String filepath = getServletContext().getInitParameter("file-upload");
response.setContentType("application/xls");   
response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\""); 
File file=new File(filepath + filename);
response.setContentLength((int) file.length());
FileInputStream fileInputStream=new FileInputStream(file);  
            
//FileInputStream fileInputStream = new FileInputStream(file);
OutputStream responseOutputStream = response.getOutputStream();
int bytes;
while ((bytes = fileInputStream.read()) != -1) {
    responseOutputStream.write(bytes);
}
fileInputStream.close();
responseOutputStream.close();
%>