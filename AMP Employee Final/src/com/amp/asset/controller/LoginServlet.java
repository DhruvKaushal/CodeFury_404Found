package com.amp.asset.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.business.AssetService;
import com.amp.asset.model.util.LayerType;
import com.amp.asset.model.util.UserFactory;
import com.amp.asset.model.util.UserType;
import com.amp.asset.exception.AuthenticationException;
import com.amp.asset.exception.ServerDownException;
import com.amp.asset.exception.EmployeeNotFoundException;

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
		
		String username = request.getParameter("uname");
		String password = request.getParameter("pass");
		//String role = request.getParameter("role");
		AssetService service = (AssetService) UserFactory.getInstance(LayerType.SERVICE);
		Employee employee = null;

		try {
			employee = service.employeeLogin(username, password);
			session.setAttribute("employeeKey", employee);
			
			RequestDispatcher rd = request.getRequestDispatcher("employeedashboard.jsp");
			rd.forward(request, response);
//			if(role.equalsIgnoreCase(UserType.ADMIN.toString())){
//				employee = service.adminLogin(username, password);
//				session.setAttribute("adminKey",admin);
//				RequestDispatcher rd = request.getRequestDispatcher("adminDashboard.jsp");
//				rd.forward(request, response);
//			}
//			else if(role.equalsIgnoreCase(UserType.EMPLOYEE.toString())) {
//				employee = service.employeeLogin(username, password);
//				session.setAttribute("employeeKey", employee);
//				
//				RequestDispatcher rd = request.getRequestDispatcher("employeedashboard.jsp");
//				rd.forward(request, response);
//			}
		} catch (AuthenticationException a) {
			pw.print("<p style='color:red;'>Username or Password is incorrect. Please try again.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		} catch (EmployeeNotFoundException b) {
			pw.print("<p style='color:red;'>Sorry, no such User exists in our Database. Please check credentials again.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		} catch (ServerDownException e) {
			pw.print("<p style='color:red;'>Sorry, our Database is Down. Please try again later.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
	}

}
