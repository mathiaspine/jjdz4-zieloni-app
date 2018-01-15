package com.infoshareacademy.zieloni.servlets;

import com.infoshareacademy.zieloni.dao.UsersRepositoryDao;
import com.infoshareacademy.zieloni.domain.Users;
import com.infoshareacademy.zieloni.services.IRegistrationService;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final String OPEN_STATISTICS_USER = "statisticsUser";

    @EJB
    IRegistrationService registrationService;

    @EJB
    UsersRepositoryDao usersRepositoryDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        init(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        init(req, resp);

    }

    private void init(HttpServletRequest req, HttpServletResponse resp) {
        showStatistic(req);
        removeUser(req);
        registrationService.init(req, resp);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");

        try {
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            log("problem with page: " + e);
        }

    }

    private void showStatistic(HttpServletRequest req) {
        try {
            req.getSession().setAttribute(OPEN_STATISTICS_USER, req.getParameter("statistics_button").equals("statistics"));
        } catch (Exception e) {
            log("statistics_button nie został jeszcze wciśniety");
        }
    }

    private void removeUser(HttpServletRequest req) {

        if (req.getParameter("remove") != null) {
            log(Integer.parseInt(req.getParameter("remove")) + " klikniety remove " + req.getParameter("remove"));
            int id = Integer.parseInt(req.getParameter("remove"));
            Users removedUser = usersRepositoryDao.getUserById(id);
            usersRepositoryDao.removeUser(removedUser);
        }
    }
}