package com.infoshareacademy.zieloni.View;

import com.infoshareacademy.zieloni.Model.BusDTO;
import com.infoshareacademy.zieloni.Model.RecordCourseDTO;
import com.infoshareacademy.zieloni.Utils.FormatTime;
import com.infoshareacademy.zieloni.Utils.IsBusExist;

import java.util.ArrayList;
import java.util.Map;

import static com.infoshareacademy.zieloni.DataBase.BusDataBase.DB;

public class TimeTableView {

    private static final ArrayList<BusDTO> busDB = DB;

    private static int level = 0;
    private static int choiceBus = -1;
    private static int street = -1;
    private static int variant = -1;

    public static void startMenu() {
        System.out.println("#################################################");
        System.out.println("# Wpisz '1' jeśli chcesz zobaczyć rozkład jazdy #");
        System.out.println("#       Wpisz 'exit' aby wyjść z programu       #");
        System.out.println("#################################################");
    }

    public static int choiceBus(String text) {
        if (text.equals("1")) {
            level = 1;
            System.out.println("#################################################");
            System.out.println("#          Wybierz srodek transportu            #");
            System.out.println("#                                               #");
            System.out.println("#   Wpisz 'cofnij' aby wrócić do głownego menu  #");
            System.out.println("#       Wpisz 'exit' aby wyjść z programu       #");
            System.out.println("#################################################");

            StringBuilder allTransportBoard = new StringBuilder();
            int counter = 0;

            for (int i = -1; i < busDB.size(); i++) {
                if (counter % 12 == 0) {
                    allTransportBoard.append("\n");
                    allTransportBoard.append("__________________________________________________________________\n");
                    allTransportBoard.append("\n");
                }else{
                    allTransportBoard.append(busDB.get(i).getBusNumber()+ " | ");

                }
                counter++;
            }

            System.out.println(allTransportBoard);



        } else {
            System.out.println("Wpisz 1 lub exit");
        }
        return level;

    }

    public static int choiceVariant(String txt) {

        if (IsBusExist.check(txt)[0] == 1) {
            int integer = IsBusExist.check(txt)[1];
            level = 2;
            System.out.println("########################################################################################");
            System.out.println("         Wybrałeś " + busDB.get(integer).getTypeOfTransport() + " nr " + busDB.get(integer).getBusNumber());
            System.out.println("                                                                                        ");
            System.out.println("    Wpisz 1) jeśli chcesz jechać w kierunku: " + busDB.get(integer).getBusStops_v2().get(0).getNameOfBusStop());
            System.out.println("    Wpisz 2) jeśli chcesz jechać w kierunku: " + busDB.get(integer).getBusStops_v1().get(0).getNameOfBusStop());
            System.out.println("    Wpisz 'cofnij' aby wrócić do głownego menu                                            ");
            System.out.println("########################################################################################");
            choiceBus = integer;
        } else {
            System.out.println("Nie ma takiego srodka transportu");
        }
        return level;
    }

    public static int showVariantStreet(Integer integer) {
        if (integer.toString().equals("1") || integer.toString().equals("2")) {
            level = 3;
            System.out.println("#################################################");
            System.out.println("#       Wybierz nr porzadkowy przystanku        #");
            System.out.println("#       Wpisz 'exit' aby wyjść z programu       #");
            System.out.println("#   Wpisz 'cofnij' aby wrócić do głownego menu  #");
            System.out.println("#################################################");
            variant = integer;

            if (integer == 1) {
                for (int i = 0; i < busDB.get(choiceBus).getBusStops_v1().size(); i++) {
                    System.out.println(i + ") " + busDB.get(choiceBus).getBusStops_v1().get(i).getNameOfBusStop());
                }
            }
            if (integer == 2) {
                for (int i = 0; i < busDB.get(choiceBus).getBusStops_v2().size(); i++) {
                    System.out.println(i + ") " + busDB.get(choiceBus).getBusStops_v2().get(i).getNameOfBusStop());
                }
            }
        } else {
            System.out.println("Wybierz od 1 do 2");
        }

        return level;
    }

    public static int showTimesForBusStop(int addBus, Integer addInteger, int addVariant) {
        street = addInteger;
        choiceBus = addBus;
        variant = addVariant;
        showTimeTable(street);
        return 0;
    }

    public static int showTimesForBusStop(Integer integer) {
        street = integer;
        showTimeTable(street);
        return level;
    }


    private static void showTimeTable(Integer integer) {
        int busStopArrSize = -1;
        if (variant == 1) {
            busStopArrSize = busDB.get(choiceBus).getBusStops_v1().size();
        }

        if (variant == 2) {
            busStopArrSize = busDB.get(choiceBus).getBusStops_v2().size();
        }

        if (integer > -1 && integer < busStopArrSize) {

            level = 4;
            ArrayList<RecordCourseDTO> courseRecord = null;
            Map<String, ArrayList<String>> map = null;

            if (variant == 1) {
                courseRecord = busDB.get(choiceBus).getCourseRecords_v1();
                map = busDB.get(choiceBus).getColumnsMap_v1();
                System.out.println("___________________________________________________________________\n");
                System.out.println("                     Rozkład " + busDB.get(integer).getTypeOfTransport() + " nr " + busDB.get(choiceBus).getBusNumber() + " na ulicy " + busDB.get(choiceBus).getBusStops_v1().get(street).getNameOfBusStop().toUpperCase());

            } else if (variant == 2) {
                courseRecord = busDB.get(choiceBus).getCourseRecords_v2();
                map = busDB.get(choiceBus).getColumnsMap_v2();
                System.out.println("___________________________________________________________________\n");
                System.out.println("                     Rozkład " + busDB.get(integer).getTypeOfTransport() + " nr" + busDB.get(choiceBus).getBusNumber() + "na ulicy" + busDB.get(choiceBus).getBusStops_v2().get(street).getNameOfBusStop().toUpperCase());
            }

            StringBuilder timeTableView = new StringBuilder();
            int counter = 1;
            for (int i = 0; i < courseRecord.size(); i++) {
                int minutes = 0;
                String symbolOfVariant = courseRecord.get(i).getCourseX0_XX();

                for (int j = 0; j < street; j++) {

                    try {
                        minutes += Integer.valueOf(map.get(symbolOfVariant).get(j));
                    } catch (Exception e) {
                        minutes += 1;
                    }
                }

                try {
                    if (courseRecord.get(i).getCourseX0_XX().split("X")[0].equals("")) {
                        timeTableView.append(FormatTime.dateFromTo(courseRecord.get(i).getDepartureTime() + " " + minutes) + " | ");
                        if (counter % 10 == 0) {
                            timeTableView.append("\n");
                        }
                        counter++;
                    } else {
                        counter = 1;
                        timeTableView.append("\n");
                        timeTableView.append("--------------------------------------------------------------------------------\n");
                        timeTableView.append("                                 " + courseRecord.get(i).getCourseX0_XX() + "\n");
                        timeTableView.append("--------------------------------------------------------------------------------\n");
                    }

                } catch (Exception e) {

                }
            }

            System.out.println(timeTableView);
        } else {
            System.out.println("Wybierz od 0 do " + (busStopArrSize - 1));
        }
    }
}
