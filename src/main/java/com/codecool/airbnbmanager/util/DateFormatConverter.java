package com.codecool.airbnbmanager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatConverter {

    public static Date convertTimeStampToDate(String dateString) {

        Date date = new Date();

        if (dateString.contains("-")) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");

            try {
                date = formatter.parse(dateString);
            } catch (ParseException e) {
                System.out.println("there is something wrong with the date, again");
                e.printStackTrace();
            }

        } else {
            int timestampDeadline = (int) Long.parseLong(dateString);
            date.setDate(timestampDeadline);
        }

        return date;
    }


}
