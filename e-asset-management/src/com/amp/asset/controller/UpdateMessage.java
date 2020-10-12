package com.amp.asset.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amp.asset.model.service.UserService;
import com.amp.asset.model.utility.FactoryPattern;
import com.amp.asset.model.utility.Type;

/**
 * Servlet implementation class UpdateMessage
 */
@WebServlet("/UpdateMessage")
public class UpdateMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String messageId = request.getParameter("messageOrder");
		int id = Integer.parseInt(messageId);
		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		service.updateMessage(id);
	    RequestDispatcher rd = request.getRequestDispatcher("employeedashboard.jsp");
		rd.forward(request, response);
	}

}
