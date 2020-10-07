package com.hsbc.asset.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.asset.exception.DatabaseDownException;
import com.hsbc.asset.exception.DuplicateUserException;
import com.hsbc.asset.model.beans.Borrower;
import com.hsbc.asset.model.business.UserService;
import com.hsbc.asset.model.util.LayerType;
import com.hsbc.asset.model.util.UserFactory;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserService service = (UserService) UserFactory.getInstance(LayerType.SERVICE);
		
		Borrower borrower = new Borrower();
		borrower.setName(request.getParameter("name"));
		borrower.setUserName(request.getParameter("user"));
		borrower.setContact(Long.parseLong(request.getParameter("contact")));
		borrower.setEmail(request.getParameter("mail"));
		borrower.setPassword(request.getParameter("pass"));
		 
		Timestamp ts = new Timestamp(new Date().getTime());
		borrower.setSignUpDate(ts.toString());

		Borrower newUser;
		try {
			newUser = service.createUser(borrower);
			session.setAttribute("newUser" , newUser);
			RequestDispatcher rd = request.getRequestDispatcher("registersuccess.jsp");
			rd.include(request, response);
		} catch (DuplicateUserException e) {
			response.getWriter().print("<p style='color:red'>User is already registered. Kindly login.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		} catch (DatabaseDownException e) {
			response.getWriter().print("<p style='color:red;'>Sorry, our Database is Down. Please try again later.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		
	}

}
