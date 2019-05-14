package com.example.dickynovanto1103.perpetualcalendar;

import java.util.Calendar;
import java.util.Date;

public class PerpetualCalendar {
    Date date;
    Days days = Days.getInstance();

    public PerpetualCalendar(Date date) {
        this.date = date;
    }

    public String getDayPerpetualCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int month = getMonth(calendar);
        int year = getYear(calendar, month);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int yearMod100 = year % 100;
        int yearDepan2 = year / 100;

        int hari = day + (int)Math.floor((2.6*month)-0.2) - 2*yearDepan2 + yearMod100 + yearMod100/4 + yearDepan2/4;
        int temp = hari;
        if(date.before(new Date(1582, 10, 5))) {
            return handleBeforeSpecialDate(day, month, year);
        }
        hari %= 7;
        normalize(hari, 7);

        String initial = "= (" +day + " + floor(2.6 x "+ month + " - 0.2)  - 2 x " + yearDepan2 + " + " + yearMod100 + " + floor(" + yearMod100 + " / 4)" + "+ floor(" + yearDepan2 + " / 4) % 7\n";
        String next = "= (" + day + " + " + (int)Math.floor((2.6*month) - 0.2) + " - " + 2*yearDepan2 + " + " + yearMod100 + " + " + yearMod100 / 4 + " + " + yearDepan2 / 4 + ") % 7\n";
        String next2 = "= (" +temp + ") % 7\n";
        String next3 = "= "+hari + " => "+ days.getDay(hari);
        return initial+next+next2+next3;
    }

    private String handleBeforeSpecialDate(int day, int month, int year) {
        int hari = day + (int)Math.floor((2.6*month)-0.2) + year + year/4 - 2;
        int temp = hari;
        hari %= 7;
        normalize(hari, 7);
        String initial = "= (" +day + " + floor(2.6 x "+ month + " - 0.2) + " + year + " + floor(" + year+ "/4)\n";
        String next = "= (" + day + " + " + (int)Math.floor((2.6*month) - 0.2) + " + " + year + " + " + year/4 + "\n";
        String next2 = "= (" +temp + ") % 7\n";
        String next3 = "= "+hari + " => "+ days.getDay(hari);
        return initial+next+next2+next3;
    }

    private int normalize(int n, int mod) {
        if(n < 0) {
            n += mod;
        }
        return n;
    }

    private int getMonth(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH) + 1;
        month += 10;
        month %= 12;
        if (month == 0) {
            month += 12;
        }
        return month;
    }

    private int getYear(Calendar calendar, int month) {
        int year = calendar.get(Calendar.YEAR);
        if(month > 10) {
            year--;
        }
        return year;
    }
}
