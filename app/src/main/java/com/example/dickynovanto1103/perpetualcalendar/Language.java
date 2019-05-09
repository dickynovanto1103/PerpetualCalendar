package com.example.dickynovanto1103.perpetualcalendar;

public class Language {
    public int id;
    private static Language instance = new Language();

    private Language() {}

    public static Language getInstance() {
        return instance;
    }

    public void setBahasa(LanguageList languageList) {
        id = languageList.equals(LanguageList.ENGLISH) ? 0 : 1;
    }

    public int getBahasa() {
        return id;
    }
}
