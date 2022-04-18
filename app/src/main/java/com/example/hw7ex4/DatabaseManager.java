package com.example.hw7ex4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "taskDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TASK = "taskTable";
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String DEADLINE = "deadline";


    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //build sql create statement
        String sqlCreate = "create table " + TABLE_TASK + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + TASK;
        sqlCreate += " text, " + DEADLINE + ")";

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop old table if it exists
        db.execSQL("drop table if exists " + TABLE_TASK);
        // Re-create tables
        onCreate(db);
    }

    public void insert(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_TASK;
        sqlInsert += " values( null, '" + task.getTask();
        sqlInsert += "', '" + task.getDeadline() + "' )";


        db.execSQL(sqlInsert);
        db.close();
    }

    public ArrayList<Task> selectAll() {
        String sqlQuery = "select * from " + TABLE_TASK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Task> taskArray = new ArrayList<Task>();
        while (cursor.moveToNext()) {
            Task currentTask
                    = new Task(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));
            taskArray.add(currentTask);
        }
        db.close();
        return taskArray;
    }

    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_TASK;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateById(int taskID, String task) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_TASK;
        sqlUpdate += " set " + TASK + " = '" + task + "'";
        sqlUpdate += " where " + ID + " = " + taskID;

        db.execSQL(sqlUpdate);
        db.close();
    }
}
