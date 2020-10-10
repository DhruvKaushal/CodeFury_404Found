package com.hsbc.asset.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.asset.exception.EmployeeNotFound;
import com.hsbc.asset.model.bean.Employee;
import com.hsbc.asset.model.service.ModelService;
import com.hsbc.asset.model.utility.UserFactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user=request.getParameter("user");
		String password=request.getParameter("pass");
		ModelService service=(ModelService)UserFactory.getInstance("service");
		Employee employeeLogin=new Employee();
		employeeLogin.setEmail(user);
		employeeLogin.setUserName(user);
		employeeLogin.setPassword(password);
		try {
		
		Employee employeeLoginSuccess = service.Login(employeeLogin);
		HttpSession session = request.getSession();
		session.setAttribute("EmployeeDetails", employeeLoginSuccess);
		service.isBan(employeeLoginSuccess);
		
		RequestDispatcher rd = request.getRequestDispatcher("loginSuccess.jsp");
		rd.forward(request, response);
		
		}
		catch(EmployeeNotFound e) {
			RequestDispatcher rd = request.getRequestDispatcher("loginFailure.jsp");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		}
		
		
	}

}
