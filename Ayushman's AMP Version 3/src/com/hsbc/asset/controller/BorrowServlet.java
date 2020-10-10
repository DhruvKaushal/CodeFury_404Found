package com.hsbc.asset.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.asset.exception.DbDown;
import com.hsbc.asset.model.service.ModelService;
import com.hsbc.asset.model.utility.UserFactory;

/**
 * Servlet implementation class BorrowServlet
 */
@WebServlet("/BorrowServlet")
public class BorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelService service=(ModelService)UserFactory.getInstance("service");
		try {
		List<String> list=service.category();
		request.setAttribute("listCategory",list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("borrow.jsp");
        dispatcher.forward(request, response);
		}catch(DbDown e) {
			RequestDispatcher rd = request.getRequestDispatcher("loginfailure.jsp");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		}
	}



}
