package com.example.dickynovanto1103.perpetualcalendar;

public class Days {
    Language language = Language.getInstance();
    private String[] day;
    private static Days days = new Days();

    private Days() {
        if(language.getBahasa() == 0) {
            System.out.println("halo inggris");
            day = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        }else{
            System.out.println("halo indo");
            day = new String[] {"Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};
        }
    }

    public static Days getInstance(){
        return days;
    }

    public void setDay() {
        if(language.getBahasa() == 0) {
            System.out.println("halo inggris");
            day = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        }else{
            System.out.println("halo indo");
            day = new String[] {"Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};
        }
    }

    public String getDay(int idx) {
        return day[idx];
    }
}
