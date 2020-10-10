package com.amp.asset.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amp.asset.exception.EmployeeFound;
import com.amp.asset.exception.ServerDown;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.service.AssetService;
import com.amp.asset.model.utility.FactoryPattern;
import com.amp.asset.model.utility.Type;


/**
 * Servlet implementation class EmployeeRegistration
 */
@WebServlet("/EmployeeRegistration")
public class EmployeeRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		AssetService service=(AssetService)FactoryPattern.getInstance(Type.SERVICE);
		PrintWriter pw=response.getWriter();
		
		String name=request.getParameter("name");
		String contact=request.getParameter("contact");
		long phone=Long.parseLong(contact);
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		Timestamp signUpDate= new Timestamp(new Date().getTime());
		
		Employee employee=new Employee(name,phone,email,username,password,signUpDate.toString());
		
		try 
		{
			employee=service.registration(employee);
			pw.print("<h1>"+ employee.getEmployeeId()+ "</h1>"); 
			// Please check this line and focus on setAttribute
			
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		} 
		catch (EmployeeFound e)  // Duplicate Entry:
		{
			
			pw.print("<p>"+e.getMessage()+"</p>");
			
			RequestDispatcher rd = request.getRequestDispatcher("employeeRegistration.html");
			rd.include(request, response);
		} 
		catch (ServerDown e)
		{
			
			pw.print("<p>"+e.getMessage()+"</p>");
			RequestDispatcher rd = request.getRequestDispatcher("employeeRegistration.html");
			rd.include(request, response);
		}
		
	}

}
