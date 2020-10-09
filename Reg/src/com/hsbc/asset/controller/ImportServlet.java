package com.hsbc.asset.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

import com.hsbc.asset.model.util.DBUtility;
import com.hsbc.asset.model.util.PasswordEncryptionUtility;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


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
	      //String fieldName = null;
	      //String contentType = null;
	      boolean isInMemory = false;
	      String fileName = null;
	      long sizeInBytes = 0L;
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
	               //contentType = fi.getContentType();
	               isInMemory = fi.isInMemory();
	               sizeInBytes = fi.getSize();
	            
	               // Write the file
	               if( fileName.lastIndexOf("\\") >= 0 ) {
	                  file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
	               } else {
	                  file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
	               }
	               fi.write( file ) ;
	               //out.println("Uploaded Filename: " + fileName + "<br>");
	               //out.println("Uploaded Filesize: " + sizeInBytes + "<br>");
	               //out.println("Uploaded Filepath: " + filePath + "<br>");	
	            }
		         out.println("</body>");
		         out.println("</html>");
	         } catch(Exception ex) {
	            System.out.println(ex);
	         }
	      
	    //Creating a JSONParser object
	      JSONParser jsonParser = new JSONParser();
	      try {
	    	  Class.forName("org.apache.derby.jdbc.EmbeddedDriver");	//comment this
	    	  Connection connection = DriverManager.getConnection("jdbc:derby:C:/Projects/MyDB/eassetDB;create=true", "admin", "admin");	//comment
			  //Connection connection = DBUtility.getConnection();	//uncomment this for final integration
	         //Parsing the contents of the JSON file
	         JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(filePath+fileName));
	         //Retrieving the array of users_information
	         JSONArray jsonArray = (JSONArray) jsonObject.get("users_information");
	         //Insert a row into the MyPlayers table
	         //PreparedStatement preparedStatementUserLogin  = connection.prepareStatement("insert into user_login (email, username, password) values(?,?,?)");
	         //PreparedStatement preparedStatementUserRecord  = connection.prepareStatement("insert into user_record (user_id, name, role, contact, email) values(?,?,?,?,?)");
	         PreparedStatement preparedStatementUsersTable = connection.prepareStatement("insert into users_table (user_id, name, username, contact, email, login_date_time, password, salt) values(?,?,?,?,?,?,?,?)");
	         for(Object object : jsonArray) {
	            JSONObject record = (JSONObject) object;
	            
	            
	            // getting email and username
	            
		        String email = (String) record.get("email"); 
		        
		        String username = (String) record.get("username"); 
		          
		        
		        System.out.println(email); 
		        System.out.println(username); 
		          
		        // getting password 
		        String password = (String) record.get("password"); 
		        // generating salt for passwords
		        String salt = PasswordEncryptionUtility.getSalt(10);
		        
		        System.out.println(password); 
		          
		        
		        String name = (String) record.get("name");
		        
		        System.out.println(name);
		        
		       // String role = (String) record.get("role");
		       // System.out.println(role);
		        
		        long contact = (long) record.get("contact");
		        System.out.println(contact);
		        
		        String user_id = (String) record.get("user_id");
		        
	            //inserting data into user_table
		        preparedStatementUsersTable.setInt(1, new Random().nextInt(1000));	//generating random user id
		        preparedStatementUsersTable.setString(2, name);
		        preparedStatementUsersTable.setString(3, username);
		        preparedStatementUsersTable.setLong(4, contact);
		        preparedStatementUsersTable.setString(5, email);
		        preparedStatementUsersTable.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime()));
		        preparedStatementUsersTable.setString(7, PasswordEncryptionUtility.generateSecurePassword(password, salt));
		        preparedStatementUsersTable.setString(8, salt);
		        preparedStatementUsersTable.executeUpdate(); 
	         }  
	         
	         out.println("Records inserted.....");
	         
	         
	         response.sendRedirect("index.html");
	         Thread.sleep(5000);
		     } catch (FileNotFoundException e) {
		         throw new FileNotFoundException("File is not Present in the directory");
		    	 //e.printStackTrace();
		     } catch (IOException e) {
		    	 throw new FileNotFoundException("File is not Present in the directory");
		    	 //e.printStackTrace();
		     } catch (ParseException e) {
		         e.printStackTrace();
		     } catch (Exception e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }
	      	
	        
	      }
	      
	      public void doGet(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, java.io.IOException {

	         throw new ServletException("GET method used with " +
	            getClass( ).getName( )+": POST method required.");
	      }
}
