package com.example.dickynovanto1103.perpetualcalendar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dickynovanto1103.perpetualcalendar.Language;
import com.example.dickynovanto1103.perpetualcalendar.R;

public class HelpActivity extends AppCompatActivity {

    Button helpButton;
    TextView helpText;
    Language language = Language.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        handleFindView();
        setView();
    }

    private void handleFindView() {
        helpButton = findViewById(R.id.help_button);
        helpText = findViewById(R.id.help_text);
    }

    private void setView() {
        setHelpButton();
        setHelpText();
    }

    private void setHelpButton() {
        String content;
        if(language.getBahasa() == 0) {
            content = "Go to main menu";
        }else{
            content = "Ke menu utama";
        }
        helpButton.setText(content);
    }

    private void setHelpText() {
        String content;
        if(language.getBahasa() == 0) {
            content = "Write date in format of dd/mm/yyyy, for example 31/12/1998 (December 31, 1998)";
        }else{
            content = "Tulis tanggal dalam format dd/mm/yyyy seperti 31/12/1998 untuk 31 Desember 1998";
        }
        helpText.setText(content);
    }


    public void goToMainActivity(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
