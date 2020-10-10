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

import com.hsbc.asset.exception.DbDown;
import com.hsbc.asset.exception.OrderNotAllowed;
import com.hsbc.asset.model.bean.Employee;
import com.hsbc.asset.model.bean.Item;
import com.hsbc.asset.model.service.ModelService;
import com.hsbc.asset.model.utility.UserFactory;


/**
 * Servlet implementation class OrderItem
 */
@WebServlet("/OrderItem")
public class OrderItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String item=request.getParameter("item");
		int index=item.indexOf("&");
		String name=item.substring(0,index);
		String id1=item.substring(index+1);
		int id=Integer.parseInt(id1);
		HttpSession session=request.getSession();
		Employee userSession=(Employee)session.getAttribute("EmployeeDetails");
		Item itemStore=new Item();
		itemStore.setItem_type(name);
		itemStore.setItem_id(id);
		ModelService service=(ModelService)UserFactory.getInstance("service");
		try {
		service.order(itemStore,userSession);
		request.setAttribute("itemDetails",itemStore);
		RequestDispatcher rd = request.getRequestDispatcher("orderPlaced.jsp");
		rd.forward(request, response);
		}catch(OrderNotAllowed e) {
			RequestDispatcher rd = request.getRequestDispatcher("userDenied.jsp");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category=request.getParameter("category");
		ModelService service=(ModelService)UserFactory.getInstance("service");
		try {
		List<Item> itemList=service.getItem(category);
		request.setAttribute("itemCategory",itemList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("borrow.jsp");
        dispatcher.include(request, response);
		}catch(DbDown e) {
			RequestDispatcher rd = request.getRequestDispatcher("loginfailure.jsp");
			request.setAttribute("err", e.getMessage());
			rd.forward(request, response);
		}
	}

}
