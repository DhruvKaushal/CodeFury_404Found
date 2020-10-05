package com.hsbc.asset.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.service.AssetService;
import com.hsbc.asset.model.service.UserService;
import com.hsbc.asset.utility.FactoryPattern;
import com.hsbc.asset.utility.Type;


@WebServlet("/AddAssetsServlet")
public class AddAssetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String assetName = request.getParameter("name");
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		String assetQuantity = request.getParameter("quantity");
		if(assetQuantity == null || assetQuantity == "")
			assetQuantity = "1";
		int quantity = Integer.parseInt(assetQuantity);
		
		
		AssetService service = (AssetService)FactoryPattern.getInstance(Type.ASSETSERVICE);
		
		Asset newAsset = new Asset();
		
		newAsset.setAssetDescription(description);
		newAsset.setAssetName(assetName);
		newAsset.setAssetType(category);
		newAsset.setQuantity(quantity);
		
		Asset asset = service.addAsset(newAsset);
		
	        HttpSession session = request.getSession();
			session.setAttribute("newAsset", newAsset);
	        RequestDispatcher rd = request.getRequestDispatcher("assetadded.jsp");
			rd.forward(request, response);
		
		
		
	}

}
