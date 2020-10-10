package com.hsbc.asset.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.exception.BorrowerNotFoundException;
import com.hsbc.asset.exception.DatabaseDownException;
import com.hsbc.asset.model.beans.Borrower;
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
		PrintWriter pw = response.getWriter();
		
		String email = request.getParameter("mail");
		String password = request.getParameter("pass");
		UserService service = (UserService) UserFactory.getInstance(LayerType.SERVICE);
		Borrower borrower = null;
		
		try {
			borrower = service.login(email, password);
			session.setAttribute("userKey", borrower);
			boolean isBan = service.banCheck(borrower.getUserId());
			session.setAttribute("isBan", isBan);
			if(isBan) {
				RequestDispatcher rd = request.getRequestDispatcher("loginsuccesswithban.jsp");
				rd.include(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("loginsuccess.jsp");
				rd.forward(request, response);
			}
		} catch (AuthenticationException a) {
			pw.print("<p style='color:red;'>Username or Password is incorrect. Please try again.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		} catch (BorrowerNotFoundException b) {
			pw.print("<p style='color:red;'>Sorry, no such User exists in our Database. Please check credentials again.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		} catch (DatabaseDownException e) {
			pw.print("<p style='color:red;'>Sorry, our Database is Down. Please try again later.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
	}

}
