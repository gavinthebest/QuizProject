package com.gavin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gavin.dao.QuestionDAO;
import com.gavin.dao.QuizDAO;
import com.gavin.domain.Question;
import com.gavin.domain.Quiz;


@WebServlet("/quiz")
public class QuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String selectedQuiz;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean finish = false;
		
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("currentQuiz") == null) {
				session = request.getSession();
				selectedQuiz = (String) session.getAttribute("quiz");
				//System.out.println("Setting Quiz " + selectedQuiz);
				QuestionDAO newQuiz = new QuestionDAO();
				newQuiz.generateQuestions(selectedQuiz);
				session.setAttribute("currentQuiz", newQuiz);
				Timestamp startTime = new Timestamp(System.currentTimeMillis());
				session.setAttribute("startTime", startTime);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		QuestionDAO quiz = (QuestionDAO) session.getAttribute("currentQuiz");
		
		//System.out.println(quiz.questionList.size());
		if (quiz.currentQuestion == 0) {
			Question q = quiz.questionList.get(quiz.currentQuestion);
			//System.out.println(Integer.toString(quiz.currentQuestion));
			session.setAttribute("question", q);
			session.setAttribute("curr", quiz.currentQuestion);
		}
		
		String action = request.getParameter("action");
		//System.out.println("action is: " + action);

		String radio = request.getParameter("answer");
		
		
		quiz.selections.put(quiz.currentQuestion, -1);
		if (radio != null) quiz.selections.put(quiz.currentQuestion, Integer.parseInt(radio));

		
		if ("Next".equals(action)) {
			quiz.currentQuestion++;
			//System.out.println(Integer.toString(quiz.currentQuestion));
			Question q = quiz.questionList.get(quiz.currentQuestion);
			session.setAttribute("question", q);
			session.setAttribute("curr", quiz.currentQuestion);
			int option = quiz.selections.getOrDefault(quiz.currentQuestion, -1);
			session.setAttribute("option", option);
		} else if ("Previous".equals(action)) {
			//System.out.println("You clicked Previous Button");
			quiz.currentQuestion--;
			Question q = quiz.questionList.get(quiz.currentQuestion);
			session.setAttribute("question", q);
			session.setAttribute("curr", quiz.currentQuestion);
			int option = quiz.selections.getOrDefault(quiz.currentQuestion, -1);
			session.setAttribute("option", option);
		} else if ("Submit".equals(action)) {
			finish = true;
			session.setAttribute("currentQuiz", null);
			
			session.setAttribute("qlist", quiz.questionList);
			
			List<Integer> userSelectionsList = new ArrayList<Integer>(10);
			while (userSelectionsList.size() < 10) userSelectionsList.add(-1);
			for (Map.Entry<Integer, Integer> entry : quiz.selections.entrySet()) {
				userSelectionsList.set(entry.getKey(), entry.getValue());
			}
			session.setAttribute("alist", userSelectionsList);
			//session.setAttribute("alist", quiz.selections);
			
			int result = quiz.calculateResult(quiz);
			request.setAttribute("result", result);
			String user = (String) session.getAttribute("user");
			String type = (String) session.getAttribute("quiz");
			Timestamp startTime = (Timestamp) session.getAttribute("startTime");
			Timestamp endTime = new Timestamp(System.currentTimeMillis());
			//Date date = new Date(endTime.getTime());
			request.setAttribute("endTime", endTime);
			String fullName = (String) session.getAttribute("fullName");
			
			QuizDAO dao = new QuizDAO();
			Quiz saveQuiz = new Quiz(type, startTime, endTime, user, fullName, result);
			try {
				dao.insertQuiz(saveQuiz);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			/*try {
				saveQuiz = dao.getQuiz(startTime);
				int qzid = saveQuiz.getQzid();
				for (int i = 0; i < 10; i++) {
					dao.insertChoice(qzid, quiz.questionList.get(i).getQid(), userSelectionsList.get(i));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
			request.getRequestDispatcher("/result.jsp").forward(request, response);

		}
		if (finish != true) {
			response.sendRedirect(request.getContextPath() + "/quiz.jsp");
			//request.getRequestDispatcher("/quiz.jsp").forward(request, response);
		}

	}

}
