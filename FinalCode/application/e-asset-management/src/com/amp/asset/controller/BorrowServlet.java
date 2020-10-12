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

import com.amp.asset.model.beans.Asset;
import com.amp.asset.model.beans.Employee;

import com.amp.asset.model.service.UserService;

import com.amp.asset.model.utility.FactoryPattern;
import com.amp.asset.model.utility.Type;
import com.amp.asset.exception.DuplicateOrderException;
import com.amp.asset.exception.OrderNotAllowedException;
import com.amp.asset.exception.ServerDownException;

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
		
		UserService service = (UserService) FactoryPattern.getInstance(Type.SERVICE);
		int assetId = Integer.parseInt(request.getParameter("assetid"));
		Employee employee = (Employee) session.getAttribute("employeeKey");
		Asset assetStore = new Asset();
		assetStore.setAssetId(assetId);
		assetStore.setTypeName((String) session.getAttribute("currType"));
		try {
			Asset assetStore1 = service.order(assetStore, employee);
			List<Asset> assetList = service.fetchAllAssets((String) session.getAttribute("currType"));
			session.setAttribute("assetList", assetList);
			session.setAttribute("assetStore", assetStore1);

			// Borrow success here
			RequestDispatcher rd = request.getRequestDispatcher("borrowsuccess.jsp");
			rd.include(request, response);
			
		} catch (ServerDownException e) {
			response.getWriter().print("<p style='color:red;'>Sorry, our Server is Down. Please try again later.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		} catch (OrderNotAllowedException e) {
			RequestDispatcher rd = request.getRequestDispatcher("borrowfailure.jsp");
			rd.include(request, response);
			response.getWriter().print("<p style='color:red;'>You are in the Banned Phase. Return previously borrowed assets.<br />\r\n" + 
										"If done, wait for Ban Period to end to borrow anymore assets.</p>");
		} catch (DuplicateOrderException e) {
			RequestDispatcher rd = request.getRequestDispatcher("borrowassetpage.jsp");
			rd.include(request, response);
			response.getWriter().print("<p style='color:red;'>You already have an item of this category! Multiple items of same category not allowed.</p>");
		}
	}

}
