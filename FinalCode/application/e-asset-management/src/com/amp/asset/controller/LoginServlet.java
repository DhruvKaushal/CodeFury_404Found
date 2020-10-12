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

import com.amp.asset.exception.AuthenticationException;
import com.amp.asset.model.utility.FactoryPattern;
import com.amp.asset.model.utility.Type;
import com.amp.asset.model.beans.Admin;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String userCredential=request.getParameter("usercredential");
		String password=request.getParameter("pass");
		
		String role=request.getParameter("role");
		
		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		HttpSession session = request.getSession();
		try {
			if(role.equals("admin")){
				Admin admin = service.loginAdmin(userCredential, password);
				if(admin!=null) {				
				session.setAttribute("userKey", admin);
				List<Integer> listStat = service.getHomeStats();
			    session = request.getSession();
			    session.setAttribute("listStat", listStat);
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
				//}	
			}
			}
			else {
			Employee emp = service.login(userCredential, password);
			session.setAttribute("employeeKey", emp);
			//service.isBan(emp);

			RequestDispatcher rd = request.getRequestDispatcher("employeedashboard.jsp");
			rd.forward(request, response);
				
			}	
		

		} catch (AuthenticationException e) {	
			RequestDispatcher rd = request.getRequestDispatcher("loginfailure.jsp");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		}
	}

}
