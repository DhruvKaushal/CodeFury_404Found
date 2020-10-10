package com.hsbc.asset.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.asset.exception.CategoryException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.Transaction;
import com.hsbc.asset.model.service.UserService;
import com.hsbc.asset.model.utility.FactoryPattern;
import com.hsbc.asset.model.utility.Type;

/**
 * Servlet implementation class SearchNameServlet
 */
@WebServlet("/SearchNameServlet")
public class SearchNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
//
//		List<String> listName = service.getNameList();
//		request.setAttribute("listName", listName);
//		RequestDispatcher rd = request.getRequestDispatcher("searchName.jsp");
//		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("name");
		
		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		
		
		List<Transaction> transaction = service.getOrderHistory(userName);
	    HttpSession session = request.getSession();
		session.setAttribute("listOrder", transaction);
	    RequestDispatcher rd = request.getRequestDispatcher("displaytable.jsp");
		rd.forward(request, response);
		
	}

}
