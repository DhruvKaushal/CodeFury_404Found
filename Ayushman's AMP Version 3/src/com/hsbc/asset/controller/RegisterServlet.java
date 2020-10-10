package com.hsbc.asset.controller;

//Servlet to register a new user.

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.asset.exception.DbDown;
import com.hsbc.asset.model.bean.Employee;
import com.hsbc.asset.model.service.ModelService;
import com.hsbc.asset.model.utility.UserFactory;




@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name");
		String userName=request.getParameter("uname");
		String password=request.getParameter("pass");
		String confirmPassword=request.getParameter("confpass");
		String phone=request.getParameter("contact");
		String email=request.getParameter("email");
		long phoneNumber=Long.parseLong(phone);
		
		ModelService service=(ModelService)UserFactory.getInstance("service");
		Employee emp = new Employee();
		emp.setName(name);
		emp.setUserName(userName);
		emp.setPassword(password);
		emp.setContact(phoneNumber);
		emp.setEmail(email);
		
		if(emp.getPassword().equals(confirmPassword)) {
		try {
		Employee registerNewUser=service.store(emp);
		request.setAttribute("Employee",emp);
		
		RequestDispatcher rd = request.getRequestDispatcher("registerSuccess.jsp");
		rd.forward(request, response);
		
		}catch(DbDown e) {
			RequestDispatcher rd = request.getRequestDispatcher("registerFailure.jsp");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		}
		}
	}

}
