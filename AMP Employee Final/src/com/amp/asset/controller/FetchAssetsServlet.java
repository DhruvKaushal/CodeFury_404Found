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

import com.amp.asset.exception.ServerDownException;
import com.amp.asset.model.beans.Asset;
import com.amp.asset.model.business.AssetService;
import com.amp.asset.model.util.LayerType;
import com.amp.asset.model.util.UserFactory;

/**
 * Servlet implementation class FetchUsersServlet
 */
@WebServlet("/FetchAssetsServlet")
public class FetchAssetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AssetService service = (AssetService) UserFactory.getInstance(LayerType.SERVICE);
		String type = request.getParameter("assets");
		
		try{	
			List<Asset> assetList = service.fetchAllAssets(type);
			List<Integer> idList = new ArrayList<Integer>();
			for(Asset asset : assetList) {
				idList.add(asset.getAssetId());
			}
			session.setAttribute("assetList", assetList);
			session.setAttribute("idList", idList);
			session.setAttribute("currType", type);
		
			RequestDispatcher rd = request.getRequestDispatcher("borrowassetpage.jsp");
			rd.forward(request, response);
		} catch(ServerDownException d) {
			response.getWriter().print("<p style='color:red;'>Sorry, our Database is Down. Please try again later.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
	}

}
