package com.example.dickynovanto1103.perpetualcalendar;

public class Months {
    Language language = Language.getInstance();
    private String[] month;
    private static Months months = new Months();

    private Months() {
        if(language.getBahasa() == 0) {
            month = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        }else{
            month = new String[] {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
        }
    }
    public static Months getInstance(){return months;}
    public String getMonth(int idx) {
        return month[idx];
    }
}
