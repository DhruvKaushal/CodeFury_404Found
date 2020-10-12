package com.amp.asset.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.service.UserService;
import com.amp.asset.model.utility.DbUtility;
import com.amp.asset.model.utility.FactoryPattern;
import com.amp.asset.model.utility.PasswordEncryptionUtility;
import com.amp.asset.model.utility.Type;
import com.hsbc.asset.exception.InvalidContactNumberException;
import com.hsbc.asset.exception.InvalidEmailException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.amp.asset.exception.InvalidContactNumberException;
import com.amp.asset.exception.InvalidEmailException;

/**@author Vishal Tandale
 *
 * @purpose Servlet implementation class Importing JSON Files into DB
 */
@WebServlet("/ImportServlet")
public class ImportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file ;
       
	public void init( ){
	      // Get the file location where it would be stored.
	      filePath = getServletContext().getInitParameter("file-upload"); 
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Check that we have a file upload request
	      isMultipart = ServletFileUpload.isMultipartContent(request);
	      response.setContentType("text/html");
	      java.io.PrintWriter out = response.getWriter( );
	   
	      if( !isMultipart ) {
	         out.println("<html>");
	         out.println("<head>");
	         out.println("<title>Servlet upload</title>");  
	         out.println("</head>");
	         out.println("<body>");
	         out.println("<p>No file uploaded</p>"); 
	         out.println("</body>");
	         out.println("</html>");
	         return;
	      }
	  
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	   
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);
	   
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File("c:\\temp"));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	   
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );
	      String fileName = null;
	      try { 
	         // Parse the request to get file items.
	         List fileItems = upload.parseRequest(request);
		
	         // Process the uploaded file items
	         Iterator i = fileItems.iterator();

	         out.println("<html>");
	         out.println("<head>");
	         out.println("<title>Servlet upload</title>");  
	         out.println("</head>");
	         out.println("<body>");
	         
	         
	         
	         FileItem fi = (FileItem)i.next();
	         	if ( !fi.isFormField () ) {
	               // Get the uploaded file parameters
	              // fieldName = fi.getFieldName();
	               fileName = fi.getName();
	            
	               // Write the file
	               if( fileName.lastIndexOf("\\") >= 0 ) {
	                  file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
	               } else {
	                  file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
	               }
	               fi.write( file );
	            }
		         out.println("</body>");
		         out.println("</html>");
	         } catch(Exception ex) {
	            ex.printStackTrace();
	         }
	      
	    //Creating a JSONParser object
	      JSONParser jsonParser = new JSONParser();
	      try {
	    	  Connection connection = DbUtility.getConnection();
	         //Parsing the contents of the JSON file
	         JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(filePath+fileName));
	         //Retrieving the array of users_information
	         JSONArray jsonArray = (JSONArray) jsonObject.get("users_information");
	         //Insert a row into the emp_master_record table
	 		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);

	         for(Object object : jsonArray) {
	            JSONObject record = (JSONObject) object;
	            
				// getting email and username
	            
	            Employee emp = null;
	            
	            //validation of email address
	            String email = (String) record.get("email"); 
		        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		        
		        String validatedEmail = null;
		        
		        if(email.matches(regex)) {
		        	validatedEmail = email;
		        } else {
			        throw new InvalidEmailException("Please check the format of the email that you have entered");
		        }
		        
		        String username = (String) record.get("username"); 
		          
		        // getting password 
		        String password = (String) record.get("password"); 
		        // generating salt for passwords
		        String salt = PasswordEncryptionUtility.getSalt(10);
		        
		          
		        // getting name from json file
		        String name = (String) record.get("name");
		        
		        
		        String regexContact = "(0/91)?[7-9][0-9]{9}";
		        long contact = (long) record.get("contact");
		        long validatedContact = 0L;
		        String contactString = contact+"";
		        if( contactString.matches(regexContact) ) {
		        	validatedContact = contact;
		        } else {
		        	throw new InvalidContactNumberException();
		        }
	            emp = new Employee(name,validatedContact,validatedEmail,username,password,new Timestamp(new java.util.Date().getTime()),new Timestamp(new java.util.Date().getTime()),salt);
	            //inserting data into user_table
		        
//		        int count = 0;		//getting last last serial number entered into the database
//				PreparedStatement lastIDStmt = connection.prepareStatement("select emp_id from emp_master_record");
//				ResultSet lastIdRs = lastIDStmt.executeQuery();
//				while(lastIdRs.next()) {
//					count++;
//				}
//				 
	           Employee emp1 = service.createUser(emp);
		        
//		        preparedStatementUsersTable.setInt(1, count);	//generating random user id
//		        preparedStatementUsersTable.setString(2, name);
//		        preparedStatementUsersTable.setString(3, username);
//		        preparedStatementUsersTable.setLong(4, contact);
//		        preparedStatementUsersTable.setString(5, email);
//		        preparedStatementUsersTable.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime()));
//		        preparedStatementUsersTable.setString(7, PasswordEncryptionUtility.generateSecurePassword(password, salt));
//		        preparedStatementUsersTable.setString(8, salt);
		        
		        if(emp1!=null) {
		        	 out.println("<script type=\"text/javascript\">");
			         out.println("alert('Data uploaded successfully');");
			         out.println("location='index.jsp';");
			         out.println("</script>");
		        } else {
		        	 out.println("<script type=\"text/javascript\">");
			         out.println("alert('Please check your JSON File');");
			         out.println("location='index.jsp';");
			         out.println("</script>");
		        }
	         }  
	         
	         } catch (FileNotFoundException e) {
	        	   out.println("<script type=\"text/javascript\">");
				   out.println("alert('File not found in the location');");
				   out.println("location='index.jsp';");
				   out.println("</script>");
			      
		     } 
	      	 catch (IOException e) {
		    	 e.getMessage();
		     } 
	         catch (ParseException e) {
		    	   out.println("<script type=\"text/javascript\">");
				   out.println("alert('Please check JSON file');");
				   out.println("location='index.jsp';");
				   out.println("</script>");
			      
		     }
	      	 catch (InvalidEmailException e) {
	      		 out.println("<script type=\"text/javascript\">");
			     out.println("alert('Please check the format of the email that you have entered');");
			     out.println("location='index.jsp';");
			     out.println("</script>");
	      	 }
	         catch (InvalidContactNumberException e) {
	      		 out.println("<script type=\"text/javascript\">");
			     out.println("alert('Contact number you entered is not in valid format');");
			     out.println("location='index.jsp';");
			     out.println("</script>");
	      	 }
	      	 catch (Exception e) {
		         e.printStackTrace();
		         out.println("<script type=\"text/javascript\">");
			      out.println("alert('Please check your json file');");
			      out.println("location='index.jsp';");
			      out.println("</script>");
		      }	      }
	      
	      public void doGet(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, java.io.IOException {

	         throw new ServletException("GET method used with " +
	            getClass( ).getName( )+": POST method required.");
	      }
}
