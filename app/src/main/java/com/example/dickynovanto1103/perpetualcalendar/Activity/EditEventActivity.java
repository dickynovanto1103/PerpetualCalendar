package com.example.dickynovanto1103.perpetualcalendar.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dickynovanto1103.perpetualcalendar.Database.DatabaseHelper;
import com.example.dickynovanto1103.perpetualcalendar.Event;
import com.example.dickynovanto1103.perpetualcalendar.Language;
import com.example.dickynovanto1103.perpetualcalendar.R;

public class EditEventActivity extends AppCompatActivity {

    Button saveButton, deleteButton;
    EditText title, content;
    Language language = Language.getInstance();
    DatabaseHelper databaseHelper;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        databaseHelper = new DatabaseHelper(this);
        setView();
        handleViewLanguage();
        id = getIDFromIntent();
        getDataFromDatabase(id);
    }

    private int getIDFromIntent() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 1);
        System.out.println("id diterima: "+ id);
        return id;
    }

    private void getDataFromDatabase(int id) {
        Cursor data = databaseHelper.getDataWithID(id);
        Event event = new Event(1, "12/12/1190", "title", "content");
        while(data.moveToNext()) {
            event = getEvent(data);
        }
        title.setText(event.getTitle());
        content.setText(event.getContent());
    }

    private void setView() {
        saveButton = findViewById(R.id.event_save);
        deleteButton = findViewById(R.id.event_delete);
        title = findViewById(R.id.event_title);
        content = findViewById(R.id.event_content);
    }

    private void handleViewLanguage() {
        setSaveButton();
        setDeleteButton();
        setContent();
        setTitle();
    }

    private void setSaveButton() {
        String content;
        if(language.getBahasa() == 0){
            content = "Save";
        }else{
            content = "Simpan";
        }
        saveButton.setText(content);
    }

    private void setDeleteButton() {
        String content;
        if(language.getBahasa() == 0){
            content = "Delete";
        }else{
            content = "Hapus";
        }
        deleteButton.setText(content);
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

    public void saveEvent(View v) {
        showToast("saved");
        String newTitle = title.getText().toString();
        String newContent = content.getText().toString();
        Event event = new Event(id, "12/12/12", newTitle, newContent);
        databaseHelper.editData(event);
        if(language.getBahasa() == 0){
            showToast("New event successfully saved");
        }else{
            showToast("Kejadian baru berhasil disimpan");
        }
        Intent intent = new Intent(getApplicationContext(), EventListActivity.class);
        startActivity(intent);
    }

    public void deleteEvent(View v) {
        databaseHelper.deleteData(id);
        if(language.getBahasa() == 0){
            showToast("Event successfully deleted");
        }else{
            showToast("Kejadian berhasil dihapus");
        }
        Intent intent = new Intent(getApplicationContext(), EventListActivity.class);
        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private Event getEvent(Cursor data) {
        String id = data.getString(0);
        String date = data.getString(1);
        String title = data.getString(2);
        String content = data.getString(3);
        System.out.println("id: " + id + " date: "+ date + " title " + title + " content: "+ content);

        Event event = new Event(Integer.parseInt(id), date, title, content);
        return event;
    }
}
