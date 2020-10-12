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

import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.service.UserService;
import com.amp.asset.model.utility.FactoryPattern;
import com.amp.asset.model.utility.Type;

/**
 * Servlet implementation class FetchAllUsersServlet
 */
@WebServlet("/FetchAllUsersServlet")
public class FetchAllUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		
		List<Employee> users=service.fetchAllUsers();
		// if(admin session ) then -> function 
		HttpSession session = request.getSession();
		session.setAttribute("userList", users);
		RequestDispatcher rd = request.getRequestDispatcher("fetchalluser.jsp");
		rd.forward(request, response);
	}

}
