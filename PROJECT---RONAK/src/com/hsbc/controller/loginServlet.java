package com.hsbc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.exception.DataBaseDown;
import com.hsbc.exception.UserNotFound;
import com.hsbc.model.beans.Borrower;
import com.hsbc.model.beans.User;
import com.hsbc.model.service.UserService;
import com.hsbc.model.utility.*;


@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserService service=(UserService)FactoryPattern.getInstance(Type.SERVICE);
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		User user=new Borrower(username,password,dtf.format(now));
		PrintWriter pw=response.getWriter();
		
		try
		{
			User user1=service.login(user);
			User user_info;
			try
			{
				user_info = service.getUserInfo(user1);
				HttpSession session = request.getSession();
				session.setAttribute("usercopy", user_info);
				RequestDispatcher rd = request.getRequestDispatcher("borrowerDashboard.jsp");
				rd.forward(request, response);
			} 
			catch (DataBaseDown e)
			{
				pw.print("<html><body><h1>");
				pw.print(e.getMessage());
				pw.print("</h1></body></html>");
			}
			
			
		} 
		catch (UserNotFound e)
		{
			
			pw.print("<html><body><h1>");
			pw.print(e.getMessage());
			pw.print("</h1></body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		
		
	}

}
