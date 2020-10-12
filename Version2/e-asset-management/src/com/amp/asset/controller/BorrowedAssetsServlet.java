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

import com.amp.asset.exception.NoProductBorrowedException;
import com.amp.asset.model.beans.Asset;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.service.UserService;
import com.amp.asset.model.utility.Type;
import com.amp.asset.model.utility.FactoryPattern;

/**
 * Servlet implementation class BorrowedAssetsServlet
 */
@WebServlet("/BorrowedAssetsServlet")
public class BorrowedAssetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = (UserService) FactoryPattern.getInstance(Type.SERVICE);
		HttpSession session = request.getSession();

		Employee employee = (Employee) session.getAttribute("employeeKey");
		int empId = employee.getEmployeeId();
		System.out.println(empId);
		try {
			List<Asset> allBorrowed = service.fetchAllBorrowed(empId,0);
			session.setAttribute("allBorrowed", allBorrowed);
			
			RequestDispatcher rd = request.getRequestDispatcher("displayborroweditems.jsp");
			rd.forward(request, response);
		} catch (NoProductBorrowedException e) {
			RequestDispatcher rd = request.getRequestDispatcher("employeedashboard.jsp");
			rd.include(request, response);
			response.getWriter().print("<p style='color:red'>" + e.getMessage() + "</p>");
		}
		
	}

}
