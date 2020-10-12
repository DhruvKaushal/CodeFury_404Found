package com.amp.asset.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

@WebServlet("/GetAllForSearchServlet")
public class GetAllForSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		
	    HttpSession session = request.getSession();
	    
	    List<String> categoryList = service.fetchCategory();
	    List<String> newList = service.fetchName();
	    List<String> nameList = newList.stream().distinct().collect(Collectors.toList()); //eliminating duplicate values while displaying
	    Collections.sort(nameList);                                                       //sorting the list in alphabetic order
	    session.setAttribute("listName", nameList);

	    session.setAttribute("listCategory", categoryList);
		RequestDispatcher rd = request.getRequestDispatcher("searchall.jsp");
		rd.forward(request, response);
	}

	
}
