package com.hsbc.asset.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.asset.exception.BorrowerFound;
import com.hsbc.asset.exception.ServerDown;
import com.hsbc.asset.model.beans.Borrower;
import com.hsbc.asset.model.service.AssetService;
import com.hsbc.asset.model.utility.FactoryPattern;
import com.hsbc.asset.model.utility.Type;




@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AssetService service=(AssetService)FactoryPattern.getInstance(Type.SERVICE);
		Object object=null;
		
		String name=request.getParameter("name");
		String contact=request.getParameter("contact");
		long phone=Integer.parseInt(contact);
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		Date date=new Date();
		String signup_date_time=date.toString();
		
		PrintWriter pw=response.getWriter();
		object=new Borrower(name,phone,email,username,password,signup_date_time);
		try
		{
			object=service.registration(object);
			pw.print("<html><body><h1>");
			Borrower borrower=(Borrower)object;
			pw.print("Registration is Successful, please make a note of Your Unique id: "+borrower.getBorrower_id());
			pw.print("</h1></body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("borrowerlogin.html");
			rd.include(request, response);
		}
		catch(BorrowerFound b)
		{
			pw.print("<html><body><h1>");
			pw.print(b.getMessage());
			pw.print("</h1></body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("registration.html");
			rd.include(request, response);
		}
		catch (ServerDown s)
		{
			pw.print("<html><body><h1>");
			pw.print(s.getMessage());
			pw.print("</h1></body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("borrowerlogin.html");
			rd.include(request, response);
		}
	}

}
