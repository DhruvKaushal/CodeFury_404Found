package com.amp.asset.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.business.AssetService;
import com.amp.asset.model.util.LayerType;
import com.amp.asset.model.util.UserFactory;
import com.amp.asset.exception.ServerDownException;
import com.amp.asset.exception.DuplicateEmployeeException;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter pw = response.getWriter();
		AssetService service = (AssetService) UserFactory.getInstance(LayerType.SERVICE);
		
		Timestamp signUpDate = new Timestamp(new Date().getTime());
		Employee employee = new Employee(request.getParameter("name"), Long.parseLong(request.getParameter("contact")),
										 request.getParameter("mail"), request.getParameter("user"), 
										 request.getParameter("pass"), signUpDate.toString());
										 
		Employee newEmployee;
		try {
			newEmployee = service.createEmployee(employee);
			System.out.println("servlet-success");
			session.setAttribute("newEmployee" , newEmployee);
			pw.print("<p style=\"color: green;\">You have successfully registered. Your User ID is: </p>" + employee.getEmployeeId());
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		} catch (DuplicateEmployeeException e) {
			response.getWriter().print("<p style='color:red'>User is already registered. Kindly login.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		} catch (ServerDownException e) {
			response.getWriter().print("<p style='color:red;'>Sorry, our Server is Down. Please try again later.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
	}

}
