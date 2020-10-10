package com.hsbc.asset.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.model.util.UserFactory;
import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String userCredential=request.getParameter("usercredential");
		String password=request.getParameter("pass");
		
		String role=request.getParameter("role");
		boolean isAdmin=false;
		if(role.equals("admin"))isAdmin=true;
		UserService service = (UserService)UserFactory.getInstance("service");
		
		try {
			
			User user = service.login(userCredential, password,isAdmin);
//			System.out.println(user.getUsername());
			
			HttpSession session = request.getSession();
			session.setAttribute("userKey", user);
			session.setAttribute("isAdmin", true);
			RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
			rd.forward(request, response);
		} catch (AuthenticationException e) {	
			RequestDispatcher rd = request.getRequestDispatcher("loginfailure.jsp");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		}
	}

}
