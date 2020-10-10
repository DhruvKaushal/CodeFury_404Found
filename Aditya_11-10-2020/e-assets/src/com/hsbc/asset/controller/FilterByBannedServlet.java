package com.hsbc.asset.controller;

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

import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.service.UserService;
import com.hsbc.asset.model.utility.FactoryPattern;
import com.hsbc.asset.model.utility.Type;


@WebServlet("/FilterByBannedServlet")
public class FilterByBannedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterByBannedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		
		String filter = request.getParameter("filter");
		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		List<User> resultUsers = new ArrayList<User>();
		List<User> allUsers = service.fetchAllUsers();
		List<User> bannedUsers = service.fetchBannedUsers(); 
		System.out.println(filter);
		
		if(filter.equals("banned")) {
			resultUsers = bannedUsers;
		}else if(filter.equals("all")) {
			resultUsers = allUsers;
		}else if(filter.equals("notBanned")) {
			allUsers.removeAll(bannedUsers);
			resultUsers = allUsers;
		}	
	
		
		session.setAttribute("userList", resultUsers);
		RequestDispatcher rd = request.getRequestDispatcher("fetchallusers.jsp");
		rd.include(request, response);
		
	}

}
