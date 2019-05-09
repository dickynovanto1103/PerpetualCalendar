package com.example.dickynovanto1103.perpetualcalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LanguageSelector extends AppCompatActivity {
    Button english, indonesian;
    Language language = Language.getInstance();
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selector);
        english = findViewById(R.id.english);
        indonesian = findViewById(R.id.indonesian);
        intent = new Intent(this, MainActivity.class);
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
