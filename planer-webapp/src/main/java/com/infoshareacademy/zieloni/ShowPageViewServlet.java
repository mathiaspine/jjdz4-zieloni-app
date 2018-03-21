package com.infoshareacademy.zieloni;
import com.infoshareacademy.zieloni.events.BusStopDao;
import com.infoshareacademy.zieloni.events.EventsDao;
import com.infoshareacademy.zieloni.events.model.BusStop;
import com.infoshareacademy.zieloni.raport.RestClient;
import com.infoshareacademy.zieloni.registration.UsersDao;
import com.infoshareacademy.zieloni.registration.model.User;
import com.infoshareacademy.zieloni.timetable.BusPromotionDao;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ShowPageViewServlet extends HttpServlet {

    public static final String TIME_TABLE_BASE = "showTimeTableBase";
    public static final String SHOW_TIME_TABLE = "showTimeTable";
    public static final String SHOW_CALENDAR = "showCalendar";
    public static final String ADD_CALENDAR = "addCalendar";
    public static final String LOCATION = "location";
    public static final String SHOW_ABOUT = "showAbout";
    public static final String SHOW_PIE_CHART = "showPieChart";
    public static final String SHOW_COLUMN_CHART = "showColumnChart";
    public static final String SHOW_STATISTICS_USER = "showStatistics";
    public static final String SHOW_BUS_STOPS = "busStops";
    public static final String BUS_ID = "bus_id";
    public static final String DIRECTION_VARIANT = "variant";
    public static final String BUS_PROMOTION_id = "showBusPromotion";

    @EJB
    BusPromotionDao busPromotionDao;
    public static final String RAPORT = "showRaport";

    @EJB
    UsersDao usersRepositoryDao;

    @EJB
    EventsDao eventsDao;

    @EJB
    BusStopDao busStopDao;

    public abstract void start(HttpServletRequest req, HttpServletResponse resp);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        start(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        start(req, resp);
    }

    public User getUserByLogin(String login) {
        try {
            return usersRepositoryDao.getUserByLogin(login);
        } catch (NullPointerException e) {
            log("nie znaleziono uzytkownika o podanym logine " + e);
            return null;
        }
    }

    public void resetViewState(HttpServletRequest req) {
    }

    public void   getBusStopList1(HttpServletRequest req) {
       // return busStopDao.getBusstopList();
         req.setAttribute("location", busStopDao.getBusstopList());
    }
    public void setBusList(HttpServletRequest req) {
            req.setAttribute("buslist", busPromotionDao.getBusList());
    }

    public void setUserList(HttpServletRequest req) {
            req.setAttribute("userList", usersRepositoryDao.getUsersList());
    }

    public void showPageView(HttpServletRequest req, HttpServletResponse resp, String path) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        try {
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            log("problem with page: " + e);
        }
    }

    public void setInfoAboutActivity(HttpServletRequest req, HttpServletResponse resp, String activity) {

        String email = req.getSession().getAttribute("loggedUser").toString();
        User user = getUserByLogin(email);
        RestClient client = new RestClient();
        client.infoAboutUserActivity(user, activity);
    }
}
