package com.example.dickynovanto1103.perpetualcalendar;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText dateInput;
    Button submit, teamButton, introButton, languageButton;
    TextView dayDisplayer, perpetualCounting;
    Date date;
    String[] days;
    Language language = Language.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateInput = findViewById(R.id.dateBox);
        submit = findViewById(R.id.button);
        teamButton = findViewById(R.id.goToTeam);
        introButton = findViewById(R.id.goToIntroduction);
        languageButton = findViewById(R.id.goToLanguage);
        dayDisplayer = findViewById(R.id.dayDisplayer);
        perpetualCounting = findViewById(R.id.perpetualCounting);
        setDays();
        setIntroButton();
        setSubmitButton();
        setLanguageButton();
        setTeamButton();
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

    public void show(String text, int color) {
        dayDisplayer.setText(text);
        dayDisplayer.setTextColor(color);
    }

    private int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public void getDay(View v){
        String input = dateInput.getText().toString();
        if(input.isEmpty()){
            String errorMessage = "Date cannot be empty";
            if(language.getBahasa() == 1) {
                errorMessage = "Tanggal tidak boleh kosong";
            }
            show(errorMessage, Color.RED);
        }else{
            if(isDateValid(input, "dd/MM/yyyy")) {
                int dayOfWeek = getDayOfWeek();
                String add = "Day: ";
                if(language.getBahasa() == 1){
                    add = "Hari: ";
                }
                String outputDay = add+days[dayOfWeek];
                getDayPerpetualCalendar();
                show(outputDay, Color.BLACK);
            }else{
                String errorMessage = "Invalid date";
                if(language.getBahasa() == 1) {
                    errorMessage = "Tanggal tidak valid";
                }
                show(errorMessage, Color.RED);
            }
        }
    }

    public void getDayPerpetualCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        month += 10;
        month %= 12;
        if (month == 0) {
            month += 12;

        }
        if(month > 10) {
            year--;
        }
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
}
