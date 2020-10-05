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

import com.hsbc.asset.model.business.UserService;
import com.hsbc.asset.model.util.LayerType;
import com.hsbc.asset.model.util.UserFactory;

/**
 * Servlet implementation class PopularAssetServlet
 */
@WebServlet("/PopularAssetServlet")
public class PopularAssetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserService service = (UserService) UserFactory.getInstance(LayerType.SERVICE);
		//List<Asset> popularList = service.fetchPopularAssets();
		List<String> categoryList = service.fetchCategory();
		session.setAttribute("categoryList", categoryList);
		response.getWriter().print(categoryList);
		//session.setAttribute("popularList", popularList);
		
		RequestDispatcher rd = request.getRequestDispatcher("assetpage.jsp");
		rd.include(request, response);
	}

}
