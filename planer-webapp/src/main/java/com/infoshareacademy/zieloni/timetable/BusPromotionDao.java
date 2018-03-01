package com.infoshareacademy.zieloni.timetable;


import com.infoshareacademy.zieloni.timetable.model.Bus;

import javax.ejb.Local;
import java.util.List;

@Local
public interface BusPromotionDao {

    boolean editBusPromotion(Bus bus);

    Bus getBusById(int id);
    List<Bus> getBusList();
}
