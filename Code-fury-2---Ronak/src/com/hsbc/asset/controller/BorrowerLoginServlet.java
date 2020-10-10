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
import javax.servlet.http.HttpSession;

import com.hsbc.asset.exception.BorrowerNotFound;
import com.hsbc.asset.exception.ServerDown;
import com.hsbc.asset.exception.WrongCredentials;
import com.hsbc.asset.model.beans.Borrower;
import com.hsbc.asset.model.service.AssetService;
import com.hsbc.asset.model.utility.FactoryPattern;
import com.hsbc.asset.model.utility.Type;


@WebServlet("/BorrowerLoginServlet")
public class BorrowerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AssetService service=(AssetService)FactoryPattern.getInstance(Type.SERVICE);
		PrintWriter pw=response.getWriter();
		Object borrower=null;
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		borrower=new Borrower(username,password,dtf.format(now));
		//Borrower b=(Borrower)service.borrowerLogin(borrower);
		
		try
		{
			
				Borrower borrower_info=(Borrower)service.borrowerLogin(borrower);
				pw.print("<html><body><h1>");
				pw.print(borrower_info.toString());
				pw.print("</h1></body></html>");
				HttpSession session = request.getSession();
				session.setAttribute("borrowercopy", borrower_info);
				RequestDispatcher rd = request.getRequestDispatcher("borrowerDashboard.jsp");
				rd.forward(request, response);
			
		} 
		catch(BorrowerNotFound b)
		{
			
			pw.print("<html><body><h1>");
			pw.print(b.getMessage());
			pw.print("</h1></body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("borrowerlogin.html");
			rd.include(request, response);
		}
		catch(WrongCredentials w)
		{
			pw.print("<html><body><h1>");
			pw.print(w.getMessage());
			pw.print("</h1></body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("borrowerlogin.html");
			rd.include(request, response);
		}
		catch(ServerDown s)
		{
			pw.print("<html><body><h1>");
			pw.print(s.getMessage());
			pw.print("</h1></body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("borrowerlogin.html");
			rd.include(request, response);
		}
		
		
	}

}
