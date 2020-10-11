package com.amp.asset.controller;

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

import com.amp.asset.exception.CategoryException;
import com.amp.asset.model.beans.Asset;
import com.amp.asset.model.beans.Transaction;
import com.amp.asset.model.service.UserService;
import com.amp.asset.model.utility.FactoryPattern;
import com.amp.asset.model.utility.Type;

@WebServlet("/SearchNameServlet")
public class SearchNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

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
