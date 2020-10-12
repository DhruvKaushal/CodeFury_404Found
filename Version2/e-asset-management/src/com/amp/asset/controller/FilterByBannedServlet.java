package com.amp.asset.controller;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class FilterByBannedServlet
 */
@WebServlet("/FilterByBannedServlet")
public class FilterByBannedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = request.getSession();
		
		
		String filter = request.getParameter("filter");
		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		List<Employee> resultUsers = new ArrayList<Employee>();
		List<Employee> allUsers = service.fetchAllUsers();
		List<Employee> bannedUsers = service.fetchBannedUsers(); 
		
		
		if(filter.equals("banned")) {
			resultUsers = bannedUsers;
		}else if(filter.equals("all")) {
			resultUsers = allUsers;
		}else if(filter.equals("notBanned")) {
			allUsers.removeAll(bannedUsers);
			resultUsers = allUsers;
		}	
	
		
		session.setAttribute("userList", resultUsers);
		RequestDispatcher rd = request.getRequestDispatcher("fetchalluser.jsp");
		rd.include(request, response);
	}

}
