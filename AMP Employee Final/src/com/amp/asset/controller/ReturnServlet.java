package com.amp.asset.controller;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amp.asset.model.beans.Asset;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.business.AssetService;
import com.amp.asset.model.util.LayerType;
import com.amp.asset.exception.NoProductBorrowedException;
import com.amp.asset.exception.ServerDownException;
import com.amp.asset.model.util.UserFactory;

/**
 * Servlet implementation class ReturnServlet
 */
@WebServlet("/ReturnServlet")
public class ReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AssetService service = (AssetService) UserFactory.getInstance(LayerType.SERVICE);
		HttpSession session = request.getSession();
		
		String asset = request.getParameter("asset");
		int transId = Integer.parseInt(new StringTokenizer(asset,":").nextToken());
		Employee employee = (Employee) session.getAttribute("employeeKey");
		int empId = employee.getEmployeeId();
		
		try {
			service.returnProduct(transId);
			session.setAttribute("transId", transId);

			List<Asset> allBorrowed = service.fetchAllBorrowed(empId,1);
			session.setAttribute("allBorrowed", allBorrowed);
			
			if(allBorrowed.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("itemreturnedforempty.jsp");
				rd.forward(request, response);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("itemreturned.jsp");
			rd.forward(request, response);
			
		} catch (ServerDownException e) {
			RequestDispatcher rd = request.getRequestDispatcher("employeedashboard.jsp");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		} catch (NoProductBorrowedException e) {
			RequestDispatcher rd = request.getRequestDispatcher("employeedashboard.jsp");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		}
	}

}
