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

import com.hsbc.asset.exception.ServerDown;
import com.hsbc.asset.model.service.AssetService;
import com.hsbc.asset.model.utility.FactoryPattern;
import com.hsbc.asset.model.utility.Type;



@WebServlet("/OrderSection")
public class OrderSection extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AssetService service=(AssetService)FactoryPattern.getInstance(Type.SERVICE);
		List<String> category_list;
		try
		{
			category_list = service.getAllCategory();
			request.setAttribute("category",category_list);
			RequestDispatcher rd = request.getRequestDispatcher("orderSection.jsp");
			rd.forward(request,response);
		} 
		catch (ServerDown e)
		{
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AssetService service=(AssetService)FactoryPattern.getInstance(Type.SERVICE);
		HttpSession session = request.getSession();
		
		List<String> category_list;
		try
		{
			category_list = service.getAllCategory();
			session.setAttribute("category",category_list);
			request.setAttribute("category",category_list);
			RequestDispatcher rd = request.getRequestDispatcher("orderSection.jsp");
			rd.include(request,response);
		} 
		catch (ServerDown e)
		{
			e.printStackTrace();
		}
		
		/*AssetService service=(AssetService)FactoryPattern.getInstance(Type.SERVICE);
		String asset_type=request.getParameter("category");
		HttpSession session = request.getSession();
		session.setAttribute("mycategory",asset_type);
		List<String> list;
		try
		{
			list = service.getAllAssets(asset_type);
			session.setAttribute("assetlist", list);
			RequestDispatcher rd = request.getRequestDispatcher("OrderSection2.jsp");
			rd.include(request, response);
		} 
		catch (ServerDown e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
    }
}
