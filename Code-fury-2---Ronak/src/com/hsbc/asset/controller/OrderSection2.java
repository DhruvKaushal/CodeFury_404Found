package com.hsbc.asset.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class OrderSection2
 */
@WebServlet("/OrderSection2")
public class OrderSection2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderSection2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AssetService service=(AssetService)FactoryPattern.getInstance(Type.SERVICE);
		String selectedValue = request.getParameter("category");
		PrintWriter pw=response.getWriter();
		pw.print("<html><body><h1>");
		pw.print(selectedValue);
		pw.print("</h1></body></html>");
		String asset_type=request.getParameter("category");
		HttpSession session = request.getSession();
		session.setAttribute("mycategory",asset_type);
		request.setAttribute("myselect",selectedValue);
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
		}
	}

}
