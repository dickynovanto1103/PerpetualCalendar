package com.example.dickynovanto1103.perpetualcalendar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dickynovanto1103.perpetualcalendar.Language;
import com.example.dickynovanto1103.perpetualcalendar.R;

public class IntroductionActivity extends AppCompatActivity {
    Button nextButton;
    TextView titleIntro;
    Language language = Language.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        nextButton = findViewById(R.id.nextButton);
        titleIntro = findViewById(R.id.title_intro);
        setIntro();
        setIntroTitle();
        setNextButton();
    }

    public void goToMainActivity(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void setNextButton() {
        String content;
        if(language.getBahasa() == 0) {
            content = "NEXT";
        }else{
            content = "SELANJUTNYA";
        }
        nextButton.setText(content);
    }

    private void setIntroTitle() {
        String content;
        if(language.getBahasa() == 0) {
            content = "Introduction";
        }else {
            content = "Pengantar";
        }
        titleIntro.setText(content);
    }

    private void setIntro() {
        TextView introContent = findViewById(R.id.intro);
        if(language.getBahasa() == 0){
            String content = "Perpetual Calendar is a calendar system that is based on day, date, month, and year. This calendar system is usually used to determine a day for a date. This algorithm also includes the leap year";
            introContent.setText(content);
        }else{
            String content = "Perpetual Calender adalah sistem kalender yang berlaku sepanjang zaman dengan basis hari, tanggal, bulan, dan tahun. Sistem kalender ini biasa digunakan untuk menentukan hari pada suatu tanggal di bulan dan tahun tertentu. Penentuan hari tidak terlepas dari perhitungan tahun kabisat, yaitu penambahan tanggal 4 tahun sekali kecuali kelipatan 100 yang bukan kelipatan 400.";
            introContent.setText(content);
        }
    }
}
