package com.hsbc.asset.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.business.UserService;
import com.hsbc.asset.model.util.LayerType;
import com.hsbc.asset.model.util.UserFactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		UserService service = (UserService) UserFactory.getInstance(LayerType.SERVICE);
		User borrower = null;
		try {
			borrower = service.login(email, password);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
		session.setAttribute("userKey", borrower);
		RequestDispatcher rd = request.getRequestDispatcher("loginsuccess.jsp");
		rd.forward(request, response);
	}

}
