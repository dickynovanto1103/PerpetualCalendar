package com.example.dickynovanto1103.perpetualcalendar.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dickynovanto1103.perpetualcalendar.Database.DatabaseHelper;
import com.example.dickynovanto1103.perpetualcalendar.Event;
import com.example.dickynovanto1103.perpetualcalendar.Language;
import com.example.dickynovanto1103.perpetualcalendar.R;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    private ListView listView;
    Language language = Language.getInstance();
    Button goToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        listView = findViewById(R.id.event_list);
        databaseHelper = new DatabaseHelper(this);
        goToMain = findViewById(R.id.goToMain);
        handleViewLanguage();
        populateListView();
    }

    private void handleViewLanguage() {
        String content;
        if(language.getBahasa() == 0){
            content = "BACK TO MAIN MENU";
        }else{
            content = "KEMBALI KE MENU UTAMA";
        }
        goToMain.setText(content);
    }

    public void goToMainActivity(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void populateListView() {
        Cursor data = databaseHelper.getData();
        ArrayList<String> list = new ArrayList<>();
        while(data.moveToNext()) {
            Event event = getEvent(data);
            if(language.getBahasa() == 0) {
                list.add(event.getId() + "\nDate: "+ event.getDateString() + "\nTitle: " + event.getTitle() + "\nContent: " + event.getContent());
            }else{
                list.add("Tanggal: "+ event.getDateString() + "\nJudul: " + event.getTitle() + "\nKonten: " + event.getContent());
            }
        }

        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(listAdapter);

        //set onclick listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                System.out.println("text: "+ text + " id: "+id);
                Intent intent = new Intent(getApplicationContext(), EditEventActivity.class);
                intent.putExtra("id", getInt(text));
                System.out.println("hasil parse:" + getInt(text));
                startActivity(intent);
            }
        });
    }

    private int getInt(String text) {
        int bil = 0;
        for(int i=0;i<text.length();i++) {
            char kar = text.charAt(i);
            if(kar >= '0' && kar <= '9') {
                bil *= 10;
                bil += kar - '0';
            }else{
                break;
            }
        }
        return bil;
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
