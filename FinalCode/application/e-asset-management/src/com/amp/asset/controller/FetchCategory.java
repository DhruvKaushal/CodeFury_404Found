package com.amp.asset.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


@WebServlet("/FetchCategory")
public class FetchCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		List<String> categoryList = new ArrayList<String>();
		categoryList = service.fetchCategory();
		session.setAttribute("categoryList", categoryList);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("assetoverduemanagement.jsp");
		rd.include(request, response);	}



}
