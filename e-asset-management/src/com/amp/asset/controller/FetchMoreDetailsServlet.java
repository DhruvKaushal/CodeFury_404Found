package com.amp.asset.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amp.asset.model.beans.Transaction;
import com.amp.asset.model.service.UserService;
import com.amp.asset.model.utility.FactoryPattern;
import com.amp.asset.model.utility.Type;

/**
 * Servlet implementation class FetchMoreDetailsServlet
 */
@WebServlet("/FetchMoreDetailsServlet")
public class FetchMoreDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int userId = Integer.parseInt(id);
				
		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		List<Transaction> userTransData = service.fetchUserDetails(userId);
		HttpSession session = request.getSession();
		session.setAttribute("userTransactions", userTransData);
		RequestDispatcher rd = request.getRequestDispatcher("usertransactions.jsp");
		rd.forward(request, response);
	}


}
