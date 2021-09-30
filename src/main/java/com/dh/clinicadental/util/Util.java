package com.dh.clinicadental.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static Timestamp dateToTimestamp(Date date){
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }

    public static Date stringToDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateNuevo = null;
        try {
            dateNuevo = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateNuevo;
    }
}
