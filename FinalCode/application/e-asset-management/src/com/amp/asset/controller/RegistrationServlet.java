package com.amp.asset.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amp.asset.exception.ContactNoAlreadyExistsException;
import com.amp.asset.exception.EmailAlreadyExistsException;
import com.amp.asset.exception.UsernameAlreadyExistsException;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.service.UserService;
import com.amp.asset.model.utility.FactoryPattern;
import com.amp.asset.model.utility.PasswordEncryptionUtility;
import com.amp.asset.model.utility.Type;
/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String role = request.getParameter("role");
		String number = request.getParameter("contact");
		long contact = Long.parseLong(number);
		String email = request.getParameter("mail");
		String username = request.getParameter("un");
		String password = request.getParameter("ps");
		String passwordConfirm = request.getParameter("pscnf");
		if(!password.equals(passwordConfirm)) {
			request.setAttribute("err", "Sorry, passwords do not match. Try again!");
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
			rd.forward(request, response);
			//throw new IllegalArgumentException("Sorry, passwords do not match. Try again!");
		}
		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		
		//Get current date. Set this as the signup and the first login timestamp
		Timestamp timeIn = new Timestamp(System.currentTimeMillis());
        //System.out.println(timeIn);
        Employee emp = new Employee();
        emp.setEmployeeName(name);
        //user.setRole(role);
        emp.setEmployeeContact(contact);
        emp.setEmployeeEmail(email);
        emp.setEmployeeUsername(username);
        emp.setSignInDate(timeIn);
        emp.setSignUpDate(timeIn);
        
        //System.out.println(timeIn);
        //Generate encrypted password
        String salt = PasswordEncryptionUtility.getSalt(10);
        password = PasswordEncryptionUtility.generateSecurePassword(password, salt);
        emp.setEmployeePassword(password);
        emp.setPasswordSalt(salt);
        try {
			Employee newUser=service.createUser(emp);
		} catch (EmailAlreadyExistsException | UsernameAlreadyExistsException | ContactNoAlreadyExistsException e) {
			// TODO Auto-generated catch block
			request.setAttribute("err", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
			rd.forward(request, response);
		}
        HttpSession session = request.getSession();
        //session.setAttribute("encryptedPasswordSalt", salt);
        
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
        //System.out.println(password);
        //boolean abc = PasswordEncryptionUtility.verifyUserPassword("abef", password, salt);
		
	}

}
