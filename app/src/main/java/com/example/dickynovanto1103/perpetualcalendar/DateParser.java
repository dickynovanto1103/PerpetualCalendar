package com.example.dickynovanto1103.perpetualcalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    public DateParser() {}
    public Date parseDate(String dateToValidate, String dateFormat) {
        if(dateToValidate == null){
            return null;
        }

        if(dateToValidate.equals("5/10/1582") || dateToValidate.equals("6/10/1582") || dateToValidate.equals("7/10/1582") || dateToValidate.equals("8/10/1582") || dateToValidate.equals("9/10/1582") || dateToValidate.equals("10/10/1582") || dateToValidate.equals("11/10/1582") || dateToValidate.equals("12/10/1582") || dateToValidate.equals("13/10/1582") || dateToValidate.equals("14/10/1582")) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        Date date;
        try {
            date = sdf.parse(dateToValidate);
            System.out.println("dalam parseDate date: "+ date + " dateToValidate: "+dateToValidate + " dateFormat: "+ dateFormat);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }
}
