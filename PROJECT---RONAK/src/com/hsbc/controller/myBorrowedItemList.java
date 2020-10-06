package com.hsbc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.exception.DataBaseDown;
import com.hsbc.model.service.UserService;
import com.hsbc.model.utility.FactoryPattern;
import com.hsbc.model.utility.Type;

@WebServlet("/myBorrowedItemList")
public class myBorrowedItemList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserService service=(UserService)FactoryPattern.getInstance(Type.SERVICE);
		PrintWriter pw=response.getWriter();
		try
		{
			List<String>list=service.getAllCategory();
			HttpSession session = request.getSession();
			session.setAttribute("categorylist", list);
			RequestDispatcher rd = request.getRequestDispatcher("orderSection.jsp");
			rd.include(request, response);
		} 
		catch (DataBaseDown e) 
		{
			pw.print("<html><body><h1>");
			pw.print(e.getMessage());
			pw.print("</h1></body></html>");
		}
	}

}
