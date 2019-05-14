package com.example.dickynovanto1103.perpetualcalendar.Activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        listView = findViewById(R.id.event_list);
        databaseHelper = new DatabaseHelper(this);

        populateListView();
    }

    private void populateListView() {
        Cursor data = databaseHelper.getData();
        ArrayList<String> list = new ArrayList<>();
        while(data.moveToNext()) {
            Event event = getEvent(data);
            if(language.getBahasa() == 0) {
                list.add("Date: "+ event.getDateString() + "\nTitle: " + event.getTitle() + "\nContent: " + event.getContent());
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
                Toast.makeText(getApplicationContext(), "halo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Event getEvent(Cursor data) {
        String id = data.getString(0);
        String date = data.getString(1);
        String title = data.getString(2);
        String content = data.getString(3);
        System.out.println("id: " + id + " date: "+ date + " title " + title + " content: "+ content);

        databaseHelper.deleteData(Integer.parseInt(id));
        System.out.println("deleted id: " + id);
        Event event = new Event(Integer.parseInt(id), date, title, content);
        return event;
    }
}
