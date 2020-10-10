package com.hsbc.asset.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.asset.model.beans.Transaction;
import com.hsbc.asset.model.service.UserService;
import com.hsbc.asset.model.utility.FactoryPattern;
import com.hsbc.asset.model.utility.Type;

/**
 * Servlet implementation class GetOverdueByCategory
 */
@WebServlet("/GetOverdueAssets")
public class GetOverdueAssets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");  
		String name = request.getParameter("name");
		String date = request.getParameter("date");
		
		
		if(date == "")
			date = null;
		if(name == "")
			name = null;

		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		List<Transaction> orderList = service.getOverdueOrders(type, name, date);
		HttpSession session = request.getSession();
	
		session.setAttribute("orderList", orderList);
		
		RequestDispatcher rd = request.getRequestDispatcher("displayoverdueassets.jsp");
		rd.include(request, response);
		
		
		
	}

}
