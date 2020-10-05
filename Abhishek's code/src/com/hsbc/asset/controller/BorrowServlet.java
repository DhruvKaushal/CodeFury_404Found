package com.hsbc.asset.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.asset.exception.ItemUnavailableException;
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
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String ans = service.borrowAsset(id);
			session.setAttribute("ans", ans);
		} catch (ItemUnavailableException e) {
			session.setAttribute("err", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("borrowfailure.jsp");
			rd.include(request, response);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("borrowsuccess.jsp");
		rd.include(request, response);
	}

}
