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

import com.hsbc.asset.model.utility.FactoryPattern;
import com.hsbc.asset.model.beans.Message;
import com.hsbc.asset.model.service.UserService;
import com.hsbc.asset.model.utility.Type;
import com.hsbc.asset.model.utility.FactoryPattern;


@WebServlet("/GetMessageServlet")
public class GetMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		String userId = request.getParameter("userId");
//		int id = Integer.parseInt(userId);
		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		
		List<Message> messageList = service.recieveMessage(1001);
		
	    HttpSession session = request.getSession();
		session.setAttribute("messageList", messageList);
	    RequestDispatcher rd = request.getRequestDispatcher("usermessage.jsp");
		rd.forward(request, response);

	}
}