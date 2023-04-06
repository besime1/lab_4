package com.example.mainactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TodoDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todomanager";
    private static final String TABLE_TODO = "todotable";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IS_URGENT = "isUrgent";

    public TodoDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TODO + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_IS_URGENT + " BOOLEAN" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }

    void addTodo(TodoList contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_IS_URGENT, contact.getUrgent());
        db.insert(TABLE_TODO, null, values);
        db.close();
    }

    public List<TodoList> getAllTasks() {
        List<TodoList> contactList = new ArrayList<TodoList>();
        // Select All Query

        String selectQuery = "SELECT  * FROM " + TABLE_TODO;

        SQLiteDatabase db = this.getWritableDatabase();
        Log.w("Data Base Version:", String.valueOf(db.getVersion()));
        Cursor cursor = db.rawQuery(selectQuery, null);
        printCursor(cursor);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TodoList todo = new TodoList();
                todo.setId(Integer.parseInt(cursor.getString(0)));
                todo.setName(cursor.getString(1));
                boolean isUrgent = Integer.parseInt(cursor.getString(2)) == 1;
                todo.setUrgent(isUrgent);
                contactList.add(todo);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    void printCursor(Cursor c) {
        Log.w("Number of columns", String.valueOf(c.getColumnCount()));
        Log.w("Columns 0 Name", c.getColumnName(0));
        Log.w("Columns 1 Name", c.getColumnName(1));
        Log.w("Columns 2 Name", c.getColumnName(2));
        Log.w("Number of Records", String.valueOf(c.getCount()));
        Log.w("Number of Rows", String.valueOf(c.getCount()));


    }


    public void delete(TodoList todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TODO, KEY_ID + " = ?",
                new String[] { String.valueOf(todo.getID()) });
        db.close();
    }


}