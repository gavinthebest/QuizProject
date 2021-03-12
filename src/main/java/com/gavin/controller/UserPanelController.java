package com.gavin.controller;

import com.gavin.dao.QuizDAO;
import com.gavin.domain.Quiz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/userPanel")
public class UserPanelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserPanelController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		//System.out.println("user name is" + username);
		
		QuizDAO dao = new QuizDAO();
		try {
			ArrayList<Quiz> listQuiz = dao.listAllQuizzesByUser(username);
			session.setAttribute("qzList", listQuiz);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/userPanel.jsp");
		rd.forward(request, response);
	}
}
