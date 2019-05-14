package com.example.dickynovanto1103.perpetualcalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event implements Comparable<Event> {
    private int id;
    private String dateString, title, content;
    private Date date;

    public Event(int id, String dateString, String title, String content) {
        this.id = id;
        this.dateString = dateString;
        this.title = title;
        this.content = content;
        this.date = parseDate(this.dateString, "dd/MM/yyyy");
    }

    private Date parseDate(String dateToValidate, String dateFormat) {
        if(dateToValidate == null){
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        Date date;
        try {
            date = sdf.parse(dateToValidate);
        } catch (ParseException e) {
            return null;
        }

        return date;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getDateString() {
        return dateString;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int compareTo(Event event) {
        return this.getDate().before(event.getDate()) ? -1 : 1;
    }
}
