package com.hsbc.asset.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.asset.model.beans.Message;
import com.hsbc.asset.model.service.UserService;
import com.hsbc.asset.model.utility.FactoryPattern;
import com.hsbc.asset.model.utility.Type;

/**
 * Servlet implementation class SendMessage
 */
@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String id = request.getParameter("messageOrder");
		String message = request.getParameter("message");
		System.out.println(id);
		System.out.println(message);
		int orderId = Integer.parseInt(id);
		
		Message m = new Message();
		m.setMessage_content(message);
		m.setOrderId(orderId);
		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		service.sendMessage(m);
		RequestDispatcher rd = request.getRequestDispatcher("displaytable.jsp");
		rd.forward(request, response);

	}

}
