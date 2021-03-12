package com.gavin.controller;

import com.gavin.dao.UserDAO;
import com.gavin.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/checkLogin")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");


		User user = new User(username, password);
		UserDAO dao = new UserDAO();
		

		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");

		
		try {
			user = dao.getUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (user.getFirstname() != null) {
			if (user.getSuspended() == 1) {
				request.setAttribute("errorMessage", "Your account is suspended, please contact Admin.");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("user", user.getUsername());
				session.setAttribute("fullName", user.getFirstname() + " " + user.getLastname());
				rd = request.getRequestDispatcher("/home.jsp");
			}
		} else {
			request.setAttribute("errorMessage", "Error! Invalid username or password, please try again.");
		}
		rd.forward(request, response);
	}

}
