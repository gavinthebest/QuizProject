package com.gavin.controller;

import com.gavin.dao.QuestionDAO;
import com.gavin.domain.Question;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminCRUD")
public class AdminCRUDController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method.equals("create")) {
            QuestionDAO dao = new QuestionDAO();
            String type = request.getParameter("type");
            String desc = request.getParameter("desc");
            String a = request.getParameter("a");
            String b = request.getParameter("b");
            String c = request.getParameter("c");
            String d = request.getParameter("d");
            int answerindex = Integer.parseInt(request.getParameter("answerindex"));
            Question question = new Question(type, desc, a, b, c, d, answerindex);
            boolean inserted = false;
            boolean rightIndex = answerindex >= 0 && answerindex <= 3;
            if (rightIndex) dao.insertQuestion(question);
            if (inserted && rightIndex) {
                request.setAttribute("m", "Congratulations! Question is created.");
            } else {
                request.setAttribute("error", "Sorry,something is wrong. Please try again.");
                if (!rightIndex) request.setAttribute("error", "Sorry, index should be between 0 to 3. Please try again.");
            }
            request.getRequestDispatcher("/adminCreate.jsp").forward(request, response);
        } else if (method.equals("update")) {
            QuestionDAO dao = new QuestionDAO();
            int qid = Integer.parseInt(request.getParameter("qid"));
            String type = request.getParameter("type");
            String desc = request.getParameter("desc");
            String a = request.getParameter("a");
            String b = request.getParameter("b");
            String c = request.getParameter("c");
            String d = request.getParameter("d");
            int answerindex = Integer.parseInt(request.getParameter("answerindex"));
            Question question = new Question(qid, type, desc, a, b, c, d, answerindex);
            boolean updated = false;
            boolean rightIndex = answerindex >= 0 && answerindex <= 3;
            if (rightIndex) dao.updateQuestion(question);
            if (updated && rightIndex) {
                request.setAttribute("m", "Congratulations! Question is updated.");
            } else {
                request.setAttribute("error", "Sorry,something is wrong. Please try again.");
                if (!rightIndex) request.setAttribute("error", "Sorry, index should be between 0 to 3. Please try again.");
            }
            request.getRequestDispatcher("/adminUpdate.jsp").forward(request, response);
        } else {
            QuestionDAO dao = new QuestionDAO();
            int qid = Integer.parseInt(request.getParameter("qid"));
            boolean deleted = dao.deleteQuestion(qid);
            if (deleted) {
                request.setAttribute("m", "Congratulations! Question is deleted.");
            } else {
                request.setAttribute("error", "Sorry, question id does not exist. Please try again.");
            }
            request.getRequestDispatcher("/adminDelete.jsp").forward(request, response);
        }
    }
}