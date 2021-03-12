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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/adminProfiles")
public class AdminProfilesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO dao = new UserDAO();
        String username = request.getParameter("suspend");
        if (username != null) {
            User user = dao.getUser(username);
            user.setSuspended(1 - user.getSuspended());
            dao.updateUser(user);
        }

        String cp = request.getParameter("currentPage");
        int currentPage = 1;
        if (cp != null) {
            currentPage = Integer.parseInt(cp);
        }

        List<User> users = dao.getAllUsers();

        int rows = users.size();

        int nOfPages = rows / 20;
        if (nOfPages % 20 > 0) {
            nOfPages++;
        }

        int start = (currentPage - 1) * 20;
        List<User> pageList = new ArrayList<>();
        while (start < rows && pageList.size() < 20) {
            pageList.add(users.get(start++));
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("users", pageList);

        request.getRequestDispatcher("/adminProfiles.jsp").forward(request, response);
    }
}
