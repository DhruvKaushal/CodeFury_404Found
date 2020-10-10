package com.hsbc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.exception.DataBaseDown;
import com.hsbc.model.beans.Borrower;
import com.hsbc.model.beans.User;
import com.hsbc.model.service.UserService;
import com.hsbc.model.utility.FactoryPattern;
import com.hsbc.model.utility.Type;


@WebServlet("/registrationServlet")
public class registrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserService service=(UserService)FactoryPattern.getInstance(Type.SERVICE);
		String name=request.getParameter("name");
		String role=request.getParameter("role");
		String contact=request.getParameter("contact");
		long phone=Integer.parseInt(contact);
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Date date=new Date();
		String signup_date_time=date.toString();
		PrintWriter pw=response.getWriter();
		User user=new Borrower(name,role,phone,email,username,password,signup_date_time);
		try
		{
			User user1=service.registration(user);
			pw.print("<html><body><h1>");
			pw.print("Registration is Successful, please make a note of Your Unique id: "+user1.getUser_id());
			pw.print("</h1></body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		catch (DataBaseDown e)
		{
			pw.print("<html><body><h1>");
			pw.print(e.getMessage());
			pw.print("</h1></body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		
	}

}
