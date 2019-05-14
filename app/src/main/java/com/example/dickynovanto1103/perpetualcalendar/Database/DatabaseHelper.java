package com.example.dickynovanto1103.perpetualcalendar.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dickynovanto1103.perpetualcalendar.Event;

public class DatabaseHelper extends SQLiteOpenHelper {

    final static private String TABLE_NAME = "event_table";
    final private static String COL0 = "date";
    final private static String COL1 = "title";
    final private static String COL2 = "content";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL0 + " TEXT, " + COL1 + " TEXT, " + COL2 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL0, event.getDateString());
        contentValues.put(COL1, event.getTitle());
        contentValues.put(COL2, event.getContent());

        System.out.println("adding data");
        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + "ID" + " = '" + id + "'";
        System.out.println("delete query: "+ query);
        System.out.println("deleting id: "+id);
        db.execSQL(query);
    }

}
