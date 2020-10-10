package com.hsbc.asset.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.service.UserService;
import com.hsbc.asset.model.utility.FactoryPattern;
import com.hsbc.asset.model.utility.Type;


@WebServlet("/FetchAllUsersServlet")
public class FetchAllUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		
		List<User> users=service.fetchAllUsers();
	
		HttpSession session = request.getSession();
		session.setAttribute("userList", users);
		RequestDispatcher rd = request.getRequestDispatcher("fetchallusers.jsp");
		rd.forward(request, response);
	}

}
