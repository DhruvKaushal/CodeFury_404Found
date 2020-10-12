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

import com.amp.asset.exception.ServerDownException;
import com.amp.asset.model.utility.Type;
import com.amp.asset.model.service.UserService;
import com.amp.asset.model.utility.FactoryPattern;

/**
 * Servlet implementation class PopularAssetServlet
 */
@WebServlet("/AssetsPageServlet")
public class AssetsPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserService service = (UserService) FactoryPattern.getInstance(Type.SERVICE);
		//List<Asset> popularList = service.fetchPopularAssets();
		List<String> categoryList;
		try {
			categoryList = service.fetchCategoryEmployee();
			session.setAttribute("categoryList", categoryList);
			//response.getWriter().print(categoryList);
			//session.setAttribute("popularList", popularList);
			
			RequestDispatcher rd = request.getRequestDispatcher("selectassetpage.jsp");
			rd.include(request, response);
		} catch (ServerDownException e) {
			response.getWriter().print("<p style='color:red;'>Sorry, our Database is Down. Please try again later.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		
	}

}
