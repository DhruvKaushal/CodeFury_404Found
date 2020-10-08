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

import com.hsbc.asset.exception.NoProductBorrowed;
import com.hsbc.asset.exception.OrderNotAllowed;
import com.hsbc.asset.model.bean.Employee;
import com.hsbc.asset.model.bean.Item;
import com.hsbc.asset.model.service.ModelService;
import com.hsbc.asset.model.utility.UserFactory;

/**
 * Servlet implementation class ReturnServlet
 */
@WebServlet("/ReturnServlet")
public class ReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelService service=(ModelService)UserFactory.getInstance("service");
		HttpSession session=request.getSession();
		Employee userSession=(Employee)session.getAttribute("EmployeeDetails");
		try {
		List<Item> allBorrowedItems=service.returnItem(userSession);
		request.setAttribute("RegisteredItems", allBorrowedItems);
		RequestDispatcher rd = request.getRequestDispatcher("allRegisteredItems.jsp");
		rd.forward(request, response);
		}catch(NoProductBorrowed e){
			RequestDispatcher rd = request.getRequestDispatcher("loginFailure.jsp");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		}
	}
	
	@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelService service=(ModelService)UserFactory.getInstance("service");
		
		String item=request.getParameter("category");
		int orderId=Integer.parseInt(item);
		
		service.returnProduct(orderId);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("itemReturned.jsp");
		rd.forward(request, response);
		
		}

}
