package com.amp.asset.controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amp.asset.exception.AdminNotFound;
import com.amp.asset.exception.EmployeeNotFound;
import com.amp.asset.exception.InvalidCredentials;
import com.amp.asset.exception.ServerDown;
import com.amp.asset.model.beans.Admin;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.service.AssetService;
import com.amp.asset.model.utility.FactoryPattern;
import com.amp.asset.model.utility.Type;



/**
 * Servlet implementation class employeeLogin
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AssetService service=(AssetService)FactoryPattern.getInstance(Type.SERVICE);
		PrintWriter pw=response.getWriter();
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String role=request.getParameter("role");
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		//LocalDateTime now = LocalDateTime.now();
		Timestamp signinDate= new Timestamp(new Date().getTime());
		
		if(role.equals("admin"))
		{
			Admin admin=(Admin)FactoryPattern.getInstance(Type.ADMIN);
			admin.setAdminUsername(username);
			admin.setAdminPassword(password);
			admin.setSignInDate(signinDate.toString());
			try 
			{
				admin=service.adminLogin(admin);
				HttpSession session = request.getSession();
				session.setAttribute("admincopy",admin);
				RequestDispatcher rd = request.getRequestDispatcher("adminDashboard.jsp");
				rd.forward(request, response);
			}
			catch (AdminNotFound e)
			{
				pw.print("<html><body><h1>");
				pw.print(e.getMessage());
				pw.print("</h1></body></html>");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
			} 
			catch (ServerDown e)
			{
				
				pw.print("<html><body><h1>");
				pw.print(e.getMessage());
				pw.print("</h1></body></html>");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
			} 
			catch (InvalidCredentials e)
			{
				
				pw.print("<html><body><h1>");
				pw.print(e.getMessage());
				pw.print("</h1></body></html>");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
		}
		else if(role.equals("employee"))
		{
			Employee employee=(Employee)FactoryPattern.getInstance(Type.EMPLOYEE);
			employee.setEmployeeUsername(username);
			employee.setEmployeePassword(password);
			employee.setSignInDate(signinDate.toString());
			try
			{
				employee=service.employeeLogin(employee);
				HttpSession session = request.getSession();
				session.setAttribute("employeecopy", employee);
				RequestDispatcher rd = request.getRequestDispatcher("employeeDashboard.jsp");
				rd.forward(request, response);
				
			}
			catch (EmployeeNotFound e)
			{
				pw.print("<html><body><h1>");
				pw.print(e.getMessage());
				pw.print("</h1></body></html>");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
			} 
			catch (ServerDown e)
			{
				pw.print("<html><body><h1>");
				pw.print(e.getMessage());
				pw.print("</h1></body></html>");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
			catch (InvalidCredentials e)
			{
				pw.print("<html><body><h1>");
				pw.print(e.getMessage());
				pw.print("</h1></body></html>");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
		}
	}

}
