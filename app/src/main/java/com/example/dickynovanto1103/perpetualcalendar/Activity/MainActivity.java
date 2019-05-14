package com.example.dickynovanto1103.perpetualcalendar.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dickynovanto1103.perpetualcalendar.Language;
import com.example.dickynovanto1103.perpetualcalendar.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText dateInput;
    Button submit, addEvent, teamButton, introButton, languageButton, seeEvents;
    TextView dayDisplayer, perpetualCounting;
    Date date;
    String[] days;
    Language language = Language.getInstance();
    final private int EMPTY_DATE = 0;
    final private int INVALID_DATE = 1;
    final private int VALID_DATE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleFindView();
        setView();
    }

    private void handleFindView() {
        dateInput = findViewById(R.id.dateBox);
        submit = findViewById(R.id.button);
        teamButton = findViewById(R.id.goToTeam);
        introButton = findViewById(R.id.goToIntroduction);
        languageButton = findViewById(R.id.goToLanguage);
        dayDisplayer = findViewById(R.id.dayDisplayer);
        perpetualCounting = findViewById(R.id.perpetualCounting);
        addEvent = findViewById(R.id.addEventButton);
        seeEvents = findViewById(R.id.see_event_list);
    }

    private void setView() {
        setDays();
        setIntroButton();
        setSubmitButton();
        setLanguageButton();
        setTeamButton();
        setAddEventButton();
        setSeeEventButton();
    }

    private void setDays() {
        if(language.getBahasa() == 0) {
            days = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        }else {
            days = new String[] {"Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};
        }
    }

    private void setSubmitButton() {
        String content;
        if(language.getBahasa() == 0) {
            content = "Check date";
        }else{
            content = "Cek hari";
        }
        submit.setText(content);
    }

    private void setTeamButton() {
        String content;
        if(language.getBahasa() == 0) {
            content = "Team";
        }else{
            content = "Tim";
        }
        teamButton.setText(content);
    }

    private void setIntroButton() {
        String content;
        if(language.getBahasa() == 0) {
            content = "Introduction";
        }else{
            content = "Pengantar";
        }
        introButton.setText(content);
    }

    private void setLanguageButton() {
        String content;
        if(language.getBahasa() == 0) {
            content = "Language";
        }else{
            content = "Bahasa";
        }
        languageButton.setText(content);
    }

    private void setAddEventButton() {
        String content;
        if(language.getBahasa() == 0){
            content = "Add event";
        }else{
            content = "Tambah kejadian";
        }
        addEvent.setText(content);
    }

    private void setSeeEventButton() {
        String content;
        if(language.getBahasa() == 0){
            content = "See events";
        }else{
            content = "Lihat daftar kejadian";
        }
        seeEvents.setText(content);
    }

    public void show(String text, int color) {
        dayDisplayer.setText(text);
        dayDisplayer.setTextColor(color);
    }

    public void goToTeam(View v) {
        Intent intent = new Intent(getApplicationContext(), TeamIdentityActivity.class);
        startActivity(intent);
    }

    public void goToIntroduction(View v) {
        Intent intent = new Intent(getApplicationContext(), IntroductionActivity.class);
        startActivity(intent);
    }

    public void goToLanguage(View v) {
        Intent intent = new Intent(getApplicationContext(), LanguageSelectorActivity.class);
        startActivity(intent);
    }

    public void goToAddEventPage(View v) {
        Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
        intent.putExtra("date", dateInput.getText().toString());
        startActivity(intent);
    }

    public void goToEventListActivity(View v) {
        Intent intent = new Intent(getApplicationContext(), EventListActivity.class);
        startActivity(intent);
    }

    private int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public void getDay(View v){
        String input = dateInput.getText().toString();
        if(input.isEmpty()){
            String errorMessage = getMessage(EMPTY_DATE);
            show(errorMessage, Color.RED);
            perpetualCounting.setText("");
        }else{
            if(isDateValid(input, "dd/MM/yyyy")) {
                int dayOfWeek = getDayOfWeek();
                String add = getMessage(VALID_DATE);
                String outputDay = add+days[dayOfWeek];
                getDayPerpetualCalendar();
                show(outputDay, Color.BLACK);
                addEvent.setEnabled(true);
            }else{
                String errorMessage = getMessage(INVALID_DATE);
                show(errorMessage, Color.RED);
                perpetualCounting.setText("");
            }
        }
    }

    private String getMessage(int idMessage) {
        String message;
        if(idMessage == EMPTY_DATE) {
            if(language.getBahasa() == 0) {
                message = "Date cannot be empty";
            }else{
                message = "Tanggal tidak boleh kosong";
            }
        }else if(idMessage == INVALID_DATE) {
            if(language.getBahasa() == 0) {
                message = "Invalid date";
            }else{
                message = "Tanggal tidak valid";
            }
        }else {
            if(language.getBahasa() == 0) {
                message = "Day: ";
            }else{
                message = "Hari: ";
            }
        }
        return message;
    }

    public void getDayPerpetualCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int month = getMonth(calendar);
        int year = getYear(calendar, month);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int yearMod100 = year % 100;
        int yearDepan2 = year / 100;

        int hari = day + (int)Math.floor((2.6*month)-0.2) - 2*yearDepan2 + yearMod100 + yearMod100/4 + yearDepan2/4;
        int temp = hari;
        hari %= 7;
        if(hari < 0) {
            hari += 7;
        }
        String initial = "= (" +day + " + floor(2.6 x "+ month + ") - 0.2 - 2 x " + yearDepan2 + " + " + yearMod100 + " + floor(" + yearMod100 + " / 4)" + "+ floor(" + yearDepan2 + " / 4) % 7\n";
        String next = "= (" + day + " + " + (int)Math.floor((2.6*month) - 0.2) + " - " + 2*yearDepan2 + " + " + yearMod100 + " + " + yearMod100 / 4 + " + " + yearDepan2 / 4 + ") % 7\n";
        String next2 = "= (" +temp + ") % 7\n";
        String next3 = ""+hari + " => "+ days[hari];
        perpetualCounting.setText(initial+next+next2+next3);
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

    private boolean isDateValid(String dateToValidate, String dateFormat) {
        if(dateToValidate == null){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            date = sdf.parse(dateToValidate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }
}
