package com.gavin.controller;

import com.gavin.dao.AdminDAO;
import com.gavin.pojo.Admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/checkAdminLogin")
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminLoginController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");


		List<Admin> adminList = new ArrayList<>();
		Admin admin = new Admin(username, password);
		AdminDAO dao = new AdminDAO();
		
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/adminLogin.jsp");


		adminList = dao.getAdmin(admin);

		System.out.println(adminList.size());
		if (adminList.size() == 1) {
			session.setAttribute("admin", username);
			rd = request.getRequestDispatcher("/adminHome.jsp");

		} else {
			request.setAttribute("errorMessage", "Error! Invalid username or password, please try again.");
		}
		rd.forward(request, response);
	}

}
