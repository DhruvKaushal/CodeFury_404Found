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

import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.beans.Message;
import com.amp.asset.model.service.UserService;
import com.amp.asset.model.utility.FactoryPattern;
import com.amp.asset.model.utility.Type;


@WebServlet("/GetMessageServlet")
public class GetMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    HttpSession session = request.getSession();
		//Employee emp = (Employee)session.getAttribute("employeeKey");
		//int id = emp.getEmployeeId();
		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		
		List<Message> messageList = service.recieveMessage(100020);
		System.out.println(messageList);

		session.setAttribute("messageList", messageList);
	    RequestDispatcher rd = request.getRequestDispatcher("usermessage.jsp");
		rd.forward(request, response);

	}
}