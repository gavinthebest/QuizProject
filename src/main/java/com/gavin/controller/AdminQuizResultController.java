package com.gavin.controller;

import com.gavin.dao.QuizDAO;
import com.gavin.domain.Quiz;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminQuizResult")
public class AdminQuizResultController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizDAO dao = new QuizDAO();
        int qzid = Integer.parseInt(request.getParameter("qzid"));
        Quiz quiz = dao.getQuiz(qzid);
        request.setAttribute("quiz", quiz);
        request.getRequestDispatcher("/adminQuizResult.jsp").forward(request, response);
    }
}