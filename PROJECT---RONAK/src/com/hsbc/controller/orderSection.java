package com.hsbc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.exception.DataBaseDown;
import com.hsbc.model.service.UserService;
import com.hsbc.model.utility.FactoryPattern;
import com.hsbc.model.utility.Type;

/**
 * Servlet implementation class orderSection
 */
@WebServlet("/orderSection")
public class orderSection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderSection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String p=request.getParameter("assest");
		HttpSession session = request.getSession();
		String q=(String) session.getAttribute("mycategory");
		System.out.println("p "+p);
		System.out.println("q "+q);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserService service=(UserService)FactoryPattern.getInstance(Type.SERVICE); 
		
		try
		{
			String asset_type=request.getParameter("category");
			HttpSession session = request.getSession();
			session.setAttribute("mycategory",asset_type);
			List<String>list=service.getAllAsset(asset_type);
			for(int i=0;i<list.size();i++)
			{
				System.out.println(list.get(i));
			}
			session.setAttribute("assestlist", list);
			RequestDispatcher rd = request.getRequestDispatcher("orderSection.jsp");
			rd.include(request, response);
		} 
		catch (DataBaseDown e)
		{
			System.out.println(e.getMessage());
		}
	}

}
