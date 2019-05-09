package com.example.dickynovanto1103.perpetualcalendar;

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
    Button submit;
    TextView dayDisplayer;
    Date date;
    String[] days = new String[] {"Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateInput = findViewById(R.id.dateBox);
        submit = findViewById(R.id.button);
        dayDisplayer = findViewById(R.id.dayDisplayer);
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
            String errorMessage = "date cannot be empty";
            show(errorMessage, Color.RED);
        }else{
            if(isDateValid(input, "dd/MM/yyyy")) {
                int dayOfWeek = getDayOfWeek();
                String outputDay = "day: "+days[dayOfWeek];
                show(outputDay, Color.BLACK);
            }else{
                String errorMessage = "invalid date";
                show(errorMessage, Color.RED);
            }
        }
    };

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
