package com.example.dickynovanto1103.perpetualcalendar.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dickynovanto1103.perpetualcalendar.DateParser;
import com.example.dickynovanto1103.perpetualcalendar.Days;
import com.example.dickynovanto1103.perpetualcalendar.Language;
import com.example.dickynovanto1103.perpetualcalendar.PerpetualCalendar;
import com.example.dickynovanto1103.perpetualcalendar.R;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText dateInput;
    Button submit, addEvent, teamButton, introButton, languageButton, seeEvents, help;
    TextView dayDisplayer, perpetualCounting;
    Date date;
    Language language = Language.getInstance();
    Days days = Days.getInstance();
    final private int EMPTY_DATE = 0;
    final private int INVALID_DATE = 1;
    final private int VALID_DATE = 2;
    final private String DATE_FORMAT = "dd/MM/yyyy";

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
        help = findViewById(R.id.help_button);
    }

    private void setView() {
        setIntroButton();
        setSubmitButton();
        setLanguageButton();
        setTeamButton();
        setAddEventButton();
        setSeeEventButton();
        setHelpButton();
        days.setDay();
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

    private void setHelpButton() {
        String content;
        if(language.getBahasa() == 0){
            content = "Help";
        }else{
            content = "Bantuan";
        }
        help.setText(content);
    }

    public void show(String text, int color) {
        dayDisplayer.setText(text);
        dayDisplayer.setTextColor(color);
        showToast(text);
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

    public void goToHelpActivity(View v) {
        Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
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
            addEvent.setEnabled(false);
        }else{
            DateParser dateParser = new DateParser();
            date = dateParser.parseDate(input, DATE_FORMAT);
            System.out.println("input: "+input + " date: "+ date);
            if(date != null) {
                int dayOfWeek = getDayOfWeek();
                String add = getMessage(VALID_DATE);
                String outputDay = add+days.getDay(dayOfWeek);
                show(outputDay, Color.BLACK);

                handlePerpetualCalendar();
                addEvent.setEnabled(true);
            }else{
                String errorMessage = getMessage(INVALID_DATE);
                show(errorMessage, Color.RED);
                perpetualCounting.setText("");
                addEvent.setEnabled(false);
            }
        }
    }

    private void handlePerpetualCalendar() {
        PerpetualCalendar perpetualCalendar = new PerpetualCalendar(date);
        String result = perpetualCalendar.getDayPerpetualCalendar();
        perpetualCounting.setText(result);
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

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
