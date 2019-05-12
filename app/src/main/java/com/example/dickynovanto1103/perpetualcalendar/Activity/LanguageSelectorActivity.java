package com.example.dickynovanto1103.perpetualcalendar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dickynovanto1103.perpetualcalendar.Activity.IntroductionActivity;
import com.example.dickynovanto1103.perpetualcalendar.Language;
import com.example.dickynovanto1103.perpetualcalendar.LanguageList;
import com.example.dickynovanto1103.perpetualcalendar.R;

public class LanguageSelectorActivity extends AppCompatActivity {
    Button english, indonesian;
    TextView pilihBahasa;
    Language language = Language.getInstance();
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selector);
        english = findViewById(R.id.english);
        indonesian = findViewById(R.id.indonesian);
        pilihBahasa = findViewById(R.id.pilih_bahasa);
        pilihBahasa.setText("Pilih Bahasa\nChoose Language");
        intent = new Intent(this, IntroductionActivity.class);
    }

    public void setEnglish(View v) {
        language.setBahasa(LanguageList.ENGLISH);
        startActivity(intent);
    }

    public void setIndonesian(View v) {
        language.setBahasa(LanguageList.INDONESIAN);
        startActivity(intent);
    }


}
