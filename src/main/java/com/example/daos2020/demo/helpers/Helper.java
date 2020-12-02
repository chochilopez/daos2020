package com.example.daos2020.demo.helpers;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Helper {
    public static Date getToday(){
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("America/Argentina"));
        Date date = cal.getTime();

        return date;
    }

}
