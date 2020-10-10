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

import com.hsbc.asset.model.service.UserService;
import com.hsbc.asset.model.utility.FactoryPattern;
import com.hsbc.asset.model.utility.Type;

/**
 * Servlet implementation class FetchCategoryServlet
 */
@WebServlet("/FetchCategoryServlet")
public class FetchCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserService service = (UserService) FactoryPattern.getInstance(Type.SERVICE);
		List<String> categoryList = new ArrayList<String>();
		categoryList = service.fetchCategory();
		session.setAttribute("categoryList", categoryList);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("assetoverduemanagement.jsp");
		rd.include(request, response);
	}


}
