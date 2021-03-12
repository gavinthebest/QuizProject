package com.gavin.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gavin.dao.UserDAO;
import com.gavin.domain.User;

@WebServlet("/checkRegister")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");

		User User = new User(username, firstname, lastname, password);
		UserDAO dao = new UserDAO();
		boolean ifSuccess = false;
		try {
			ifSuccess = dao.insertUser(User);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean exists = false;
		try {
			exists = dao.checkUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("newUser", firstname);

		if (ifSuccess && !exists) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/regSuccess.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/regFailure.jsp");
			dispatcher.forward(request, response);
		}
	}
}
