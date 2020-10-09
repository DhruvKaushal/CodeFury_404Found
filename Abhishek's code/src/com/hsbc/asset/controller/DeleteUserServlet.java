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

import com.hsbc.asset.exception.DatabaseDownException;
import com.hsbc.asset.model.beans.Borrower;
import com.hsbc.asset.model.business.UserService;
import com.hsbc.asset.model.util.LayerType;
import com.hsbc.asset.model.util.UserFactory;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter pw = response.getWriter();
		UserService service = (UserService) UserFactory.getInstance(LayerType.SERVICE);
		Borrower user = (Borrower) session.getAttribute("userKey");
		try {
			if(service.banCheck(user.getUserId())) {
				pw.print("<p>You can't delete your profile as you have not returned all your items.<br/>"
						+ "Kindly complete your dues before leaving us!</p>");
				RequestDispatcher rd = request.getRequestDispatcher("loginsuccess.jsp");
				rd.include(request, response);
			}
			else {
				service.deleteUser(user.getUserId());
				session.invalidate();
				response.getWriter().print("<div style='color:green'>User has been deleted successfully.</div>");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
		} catch (DatabaseDownException e) {
			response.getWriter().print("<p style='color:red;'>Sorry, our Database is Down. Please try again later.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		
	}
}
