package com.gavin.controller;

import com.gavin.dao.FeedbackDAO;
import com.gavin.domain.Feedback;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

@WebServlet("/submitFeedback")
public class FeedbackController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int rating = Integer.parseInt(request.getParameter("rating"));
		String content = request.getParameter("text");
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		
		FeedbackDAO dao = new FeedbackDAO();
		Feedback feedback = new Feedback(date, content, rating);
		try {
			dao.insertFeedback(feedback);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("m", "Feedback is submitted, thank you.");
		request.getRequestDispatcher("/feedback.jsp").forward(request, response);
	}
}
