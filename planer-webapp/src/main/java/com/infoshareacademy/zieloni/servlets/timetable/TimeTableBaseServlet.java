package com.infoshareacademy.zieloni.servlets.timetable;


import com.infoshareacademy.zieloni.database.BusDataBase;
import com.infoshareacademy.zieloni.servlets.ShowPageViewServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/time-table")
public class TimeTableBaseServlet extends ShowPageViewServlet {


    @Override
    public void start(HttpServletRequest req, HttpServletResponse resp) {

        log("pokaż listę autobusów");
        resetViewState(req);
        setBusList(req);
        showTimeSchedule(req, resp);
    }

    void setBusList(HttpServletRequest req) {

        try {
            req.setAttribute("busList", BusDataBase.getDataBase());
        } catch (Exception e) {
            log(" nie pobrałem bazy danych");
        }
    }

    private void showTimeSchedule(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute(TIME_TABLE_BASE, true);
        showPageView(req, resp, "/index.jsp");
    }
}
