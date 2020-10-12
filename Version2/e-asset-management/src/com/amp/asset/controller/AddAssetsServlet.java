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

import com.amp.asset.exception.CategoryException;
import com.amp.asset.model.beans.Asset;
import com.amp.asset.model.service.UserService;
import com.amp.asset.model.utility.FactoryPattern;
import com.amp.asset.model.utility.Type;

/**
 * Servlet implementation class AddAssetServlet
 */
@WebServlet("/AddAssetsServlet")
public class AddAssetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
	List<String> listCatagory = service.getCategoryList();
    HttpSession session = request.getSession();

    session.setAttribute("listCategory", listCatagory);
	RequestDispatcher rd = request.getRequestDispatcher("addasset.jsp");
	rd.forward(request, response);
}       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String assetName = request.getParameter("name");
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		String assetQuantity = request.getParameter("quantity");
		if(assetQuantity == null || assetQuantity == "")
			assetQuantity = "1";
		int quantity = Integer.parseInt(assetQuantity);
		
	    
		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		
		Asset newAsset = new Asset();
		
		newAsset.setAssetDescription(description);
		newAsset.setAssetName(assetName);
		newAsset.setTypeName(category);
		newAsset.setQuantity(quantity);
		
		
		         
		Asset asset = null;
		try {
			asset = service.addAsset(newAsset);
		} catch (CategoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //exception should be handled - Category
		
	    HttpSession session = request.getSession();
		session.setAttribute("assetKey", asset);
	    RequestDispatcher rd = request.getRequestDispatcher("assetadded.jsp");
		rd.forward(request, response);
		
	}

}
