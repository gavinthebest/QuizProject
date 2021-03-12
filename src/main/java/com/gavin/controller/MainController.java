package com.gavin.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/login", "/register", "/takeExam", "/feedback", "/logout", "/admin", "/adminLogin", "/adminCreate", "/adminDelete", "/adminUpdate" })
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String applicationContextPath = request.getContextPath();

		if (request.getRequestURI().equals(applicationContextPath + "/")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equals(
				applicationContextPath + "/login")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equals(
				applicationContextPath + "/register")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/register.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equals(
				applicationContextPath + "/takeExam")) {
			request.getSession().setAttribute("currentQuiz", null);

			String quiz = request.getParameter("test");
			request.getSession().setAttribute("quiz", quiz);

			//System.out.println(request.getSession().getAttribute("user"));
			if (request.getSession().getAttribute("user") == null) {
				request.setAttribute("errorMessage", "Please log in first to take any quizzes.");
				request.getRequestDispatcher("/login").forward(request,
						response);
				
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/quiz");
				dispatcher.forward(request, response);
			}
		} else if (request.getRequestURI().equals(
				applicationContextPath + "/logout")) {
			request.getSession().invalidate();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
		}
		else if (request.getRequestURI().equals(
				applicationContextPath + "/userPanel")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/userPanel.jsp");
			dispatcher.forward(request, response);
		} 
		else if (request.getRequestURI().equals(
				applicationContextPath + "/feedback")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/feedback.jsp");
			dispatcher.forward(request, response);
		}
		else if (request.getRequestURI().equals(
				applicationContextPath + "/adminLogin")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/adminLogin.jsp");
			dispatcher.forward(request, response);
		}
		else if (request.getRequestURI().equals(
				applicationContextPath + "/adminCreate")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/adminCreate.jsp");
			dispatcher.forward(request, response);
		}
		else if (request.getRequestURI().equals(
				applicationContextPath + "/adminDelete")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/adminDelete.jsp");
			dispatcher.forward(request, response);
		}
		else if (request.getRequestURI().equals(
				applicationContextPath + "/adminUpdate")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/adminUpdate.jsp");
			dispatcher.forward(request, response);
		}
		else if (request.getRequestURI().equals(
				applicationContextPath + "/admin")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/adminHome.jsp");
			dispatcher.forward(request, response);
		}


	}

}
