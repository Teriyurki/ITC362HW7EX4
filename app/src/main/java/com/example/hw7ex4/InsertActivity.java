package com.example.hw7ex4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert2);
    }

    public void insert(View view) {
        // Retrieve first name, last name, and email
        Log.w("InsertActivity", "Insert Button Pushed");

        EditText taskET = findViewById(R.id.input_task);
        EditText deadlineET = findViewById(R.id.input_deadline);
        String taskString = taskET.getText().toString();
        String deadlineString = deadlineET.getText().toString();


        //insert into database

        Task task = new Task(0, taskString, deadlineString);
        dbManager.insert(task);
        Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();

        //clear data
        taskET.setText("");
        deadlineET.setText("");


    }

    public void goBack(View view) {
        this.finish();
    }
}
