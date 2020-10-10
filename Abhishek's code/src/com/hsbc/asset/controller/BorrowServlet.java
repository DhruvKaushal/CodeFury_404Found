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

import com.hsbc.asset.exception.DatabaseDownException;
import com.hsbc.asset.exception.ItemUnavailableException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.business.UserService;
import com.hsbc.asset.model.util.LayerType;
import com.hsbc.asset.model.util.UserFactory;

/**
 * Servlet implementation class BorrowServlet
 */
@WebServlet("/BorrowServlet")
public class BorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		UserService service = (UserService) UserFactory.getInstance(LayerType.SERVICE);
		int id = Integer.parseInt(request.getParameter("assetid"));
		try {
			String ans = service.borrowAsset(id);
			List<Asset> assetList = service.fetchAllAssets((String) session.getAttribute("currType"));
			session.setAttribute("ans", ans);
			session.setAttribute("assetList", assetList);
			
			if((boolean) session.getAttribute("isBan")) {
				RequestDispatcher rd = request.getRequestDispatcher("borrowfailureban.jsp");
				rd.include(request, response);
			}
			else {
				//borrow succes here
				RequestDispatcher rd = request.getRequestDispatcher("borrowsuccess.jsp");
				rd.include(request, response);
			}
			
		} catch (ItemUnavailableException e) {
			session.setAttribute("err", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("borrowfailure.jsp");
			rd.include(request, response);
		} catch (DatabaseDownException e) {
			response.getWriter().print("<p style='color:red;'>Sorry, our Database is Down. Please try again later.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
	}

}
