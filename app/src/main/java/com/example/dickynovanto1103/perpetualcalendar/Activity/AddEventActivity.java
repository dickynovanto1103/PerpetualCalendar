package com.example.dickynovanto1103.perpetualcalendar.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dickynovanto1103.perpetualcalendar.Database.DatabaseHelper;
import com.example.dickynovanto1103.perpetualcalendar.Event;
import com.example.dickynovanto1103.perpetualcalendar.Language;
import com.example.dickynovanto1103.perpetualcalendar.R;

public class AddEventActivity extends AppCompatActivity {

    Button addEvent;
    EditText title, content;
    Language language = Language.getInstance();
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        setView();
        handleViewLanguage();
        databaseHelper = new DatabaseHelper(this);
    }

    private void setView() {
        addEvent = findViewById(R.id.event_submit);
        title = findViewById(R.id.event_title);
        content = findViewById(R.id.event_content);
    }

    private void handleViewLanguage() {
        setAddEvent();
        setContent();
        setTitle();
    }

    private void setAddEvent() {
        String content;
        if(language.getBahasa() == 0){
            content = "Add";
        }else{
            content = "Tambah";
        }
        addEvent.setText(content);
    }

    private void setTitle() {
        String content;
        if(language.getBahasa() == 0){
            content = "Title of event goes here";
        }else{
            content = "Tulis judul kejadian disini";
        }
        title.setHint(content);
    }

    private void setContent() {
        String isi;
        if(language.getBahasa() == 0){
            isi = "Content of event goes here";
        }else{
            isi = "Tulis konten kejadian disini";
        }
        content.setHint(isi);
    }

    public void addEventSubmit(View v) {
        String titleText = title.getText().toString();
        String contentText = content.getText().toString();
        if(titleText.equals("") || contentText.equals("")){
            Toast.makeText(getApplicationContext(), "Text harus terisi", Toast.LENGTH_SHORT).show();
        }else{
            Event event = new Event(1, "12/12/1998", titleText, contentText);
            databaseHelper.addData(event);
        }
    }
}
