package com.gavin.controller;

import com.gavin.dao.QuizDAO;
import com.gavin.dao.UserDAO;
import com.gavin.domain.Quiz;
import com.gavin.domain.User;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@WebServlet("/adminResult")
public class AdminResultController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String cp = request.getParameter("currentPage");
        int currentPage = 1;
        if (cp != null) {
            currentPage = Integer.parseInt(cp);
        }

        String user = request.getParameter("user");
        String cat = request.getParameter("cat");
        if (user != null) session.setAttribute("user", user);
        if (session.getAttribute("user") == null) session.setAttribute("user", "all");
        if (cat != null) session.setAttribute("cat", cat);
        if (session.getAttribute("cat") == null) session.setAttribute("cat", "all");

        user = (String) session.getAttribute("user");
        cat = (String) session.getAttribute("cat");

        QuizDAO dao = new QuizDAO();
        List<Quiz> quizList = new ArrayList<>();
        if (user.equals("all") && cat.equals("all")) {
            quizList = dao.listAllQuizzes();
        } else if (cat.equals("all")){
            quizList = dao.listAllQuizzesByUser(user);
        } else if (user.equals("all")) {
            quizList = dao.listAllQuizzesByCat(cat);
        } else quizList = dao.listAllQuizzesByUserAndCat(user, cat);

        String sortBy = request.getParameter("sortBy");
        if (sortBy != null) {
            session.setAttribute("sortBy", sortBy);
        }

        sortBy = (String) session.getAttribute("sortBy");
        if (sortBy.equals("starttime")) {
            Collections.sort(quizList, (a, b) -> b.getStarttime().compareTo(a.getStarttime()));
        } else if (sortBy.equals("fullName")) {
            Collections.sort(quizList, (a, b) -> a.getFullName().compareTo(b.getFullName()));
        } else if (sortBy.equals("type")) {
            Collections.sort(quizList, (a, b) -> a.getType().compareTo(b.getType()));
        }

        UserDAO newDao = new UserDAO();
        List<User> userList =  newDao.getAllUsers();
        request.getSession().setAttribute("userList", userList);


        int rows = quizList.size();

        int nOfPages = rows / 20;
        if (nOfPages % 20 > 0) {
            nOfPages++;
        }

        int start = (currentPage - 1) * 20;
        List<Quiz> pageList = new ArrayList<>();
        while (start < rows && pageList.size() < 20) {
            pageList.add(quizList.get(start++));
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.getSession().setAttribute("qzList", pageList);
        request.getRequestDispatcher("/adminResult.jsp").forward(request, response);
    }

}