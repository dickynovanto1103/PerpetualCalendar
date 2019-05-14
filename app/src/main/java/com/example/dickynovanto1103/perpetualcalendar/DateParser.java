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

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            return sdf.parse(dateToValidate);
        } catch (ParseException e) {
            return null;
        }
    }
}
