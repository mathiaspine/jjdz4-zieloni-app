package com.infoshareacademy.zieloni.servlets;

import com.infoshareacademy.zieloni.services.IUsersDao;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ShowPageViewServlet extends HttpServlet {

    @EJB
    IUsersDao usersRepositoryDao;

    public abstract void start(HttpServletRequest req, HttpServletResponse resp);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        start(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        start(req, resp);
    }

    public static final String TIME_TABLE_BASE = "showTimeTableBase";
    public static final String SHOW_TIME_TABLE = "showTimeTable";
    public static final String SHOW_STATISTICS_USER = "showStatistics";
    public static final String SHOW_BUS_STOPS = "busStops";
    public static final String BUS_ID = "bus_id";
    public static final String DIRECTION_VARIANT = "variant";

    public void resetViewState(HttpServletRequest req) {
        //req.getSession().setAttribute(SHOW_STATISTICS_USER, false);
        //  req.getSession().setAttribute(TIME_TABLE_BASE, false);
    }

    public void setUserList(HttpServletRequest req) {
        try {
            req.setAttribute("userList", usersRepositoryDao.getUsersList());
        } catch (Exception e) {
            log(" brak userów");
        }
    }

    public void showPageView(HttpServletRequest req, HttpServletResponse resp, String path) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        try {
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            log("problem with page: " + e);
        }
    }
}