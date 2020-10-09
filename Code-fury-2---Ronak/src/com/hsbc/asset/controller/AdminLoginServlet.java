package com.hsbc.asset.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.asset.exception.AdminNotFound;
import com.hsbc.asset.exception.BorrowerNotFound;
import com.hsbc.asset.exception.ServerDown;
import com.hsbc.asset.exception.WrongCredentials;
import com.hsbc.asset.model.beans.Admin;
import com.hsbc.asset.model.beans.Borrower;
import com.hsbc.asset.model.service.AssetService;
import com.hsbc.asset.model.utility.FactoryPattern;
import com.hsbc.asset.model.utility.Type;


@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AssetService service=(AssetService)FactoryPattern.getInstance(Type.SERVICE);
		PrintWriter pw=response.getWriter();
		Object admin=null;
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		admin=new Admin(username,password,dtf.format(now));
		
		try
		{
			
				Admin b=(Admin)service.adminLogin(admin);
				
			
		} 
		catch(AdminNotFound a)
		{
			
			pw.print("<html><body><h1>");
			pw.print(a.getMessage());
			pw.print("</h1></body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("adminlogin.html");
			rd.include(request, response);
		}
		catch(WrongCredentials w)
		{
			pw.print("<html><body><h1>");
			pw.print(w.getMessage());
			pw.print("</h1></body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("adminlogin.html");
			rd.include(request, response);
		}
		catch(ServerDown s)
		{
			pw.print("<html><body><h1>");
			pw.print(s.getMessage());
			pw.print("</h1></body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("adminlogin.html");
			rd.include(request, response);
		}
		
		
	}
	

}
