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

    public void getDay(View v){
        String input = dateInput.getText().toString();
        if(input.isEmpty()){
            dayDisplayer.setText("date cannot be empty");
            dayDisplayer.setTextColor(Color.RED);
        }else{
            if(isDateValid(input, "dd/MM/yyyy")) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                dayDisplayer.setText("date: "+input + " day:"+days[dayOfWeek]);
                dayDisplayer.setTextColor(Color.BLACK);
            }else{
                dayDisplayer.setText("invalid date");
                dayDisplayer.setTextColor(Color.RED);
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
