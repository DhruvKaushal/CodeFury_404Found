package com.hsbc.asset.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.asset.exception.ContactNoAlreadyExistsException;
import com.hsbc.asset.exception.EmailAlreadylExistsException;
import com.hsbc.asset.exception.UsernameAlreadyExistsException;
import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.service.UserService;
import com.hsbc.asset.model.util.UserFactory;
import com.hsbc.asset.model.util.PasswordEncryptionUtility;
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
		UserService service = (UserService)UserFactory.getInstance("service");
		
		//Get current date. Set this as the signup and the first login timestamp
		Timestamp timeIn = new Timestamp(System.currentTimeMillis());
        //System.out.println(timeIn);
        User user = new User();
        user.setName(name);
        //user.setRole(role);
        user.setContactNo(contact);
        user.setEmail(email);
        user.setUsername(username);
        user.setSignup_date_and_time(timeIn);
        user.setLogin_date_and_time(timeIn);
        
        //System.out.println(timeIn);
        //Generate encrypted password
        String salt = PasswordEncryptionUtility.getSalt(10);
        password = PasswordEncryptionUtility.generateSecurePassword(password, salt);
        user.setPassword(password);
        user.setSalt(salt);
        try {
			User newUser=service.createUser(user);
		} catch (EmailAlreadylExistsException | UsernameAlreadyExistsException | ContactNoAlreadyExistsException e) {
			// TODO Auto-generated catch block
			request.setAttribute("err", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
			rd.forward(request, response);
		}
        HttpSession session = request.getSession();
        //session.setAttribute("encryptedPasswordSalt", salt);
        
        RequestDispatcher rd = request.getRequestDispatcher("registersuccess.jsp");
		rd.forward(request, response);
        //System.out.println(password);
        //boolean abc = PasswordEncryptionUtility.verifyUserPassword("abef", password, salt);
		
	}

}
