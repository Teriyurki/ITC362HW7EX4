package com.example.hw7ex4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_main);
        showView();
    }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;

        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();

            switch (id) {
                case R.id.action_add:
                    Log.w("MainActivity", "Add Selected");
                    Intent insertIntent = new Intent(this, InsertActivity.class);
                    this.startActivity(insertIntent);
                    return true;
                case R.id.action_delete:
                    Log.w("MainActivity", "Delete Selected");
                    Intent deleteIntent = new Intent(this, DeleteActivity.class);
                    this.startActivity(deleteIntent);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);

            }
        }

        private void showView() {
            ArrayList<Task> taskArray = dbManager.selectAll();
            RelativeLayout layout = new RelativeLayout(this);
            Date currentDate = null;

            // create ScrollView and GridLayout
            if (taskArray.size() > 0) {

                ScrollView scrollView = new ScrollView(this);
                GridLayout grid = new GridLayout(this);
                grid.setRowCount(taskArray.size());
                grid.setColumnCount(3);

                // create arrays of components
                TextView[] ids = new TextView[taskArray.size()];
                TextView[][] taskAndId = new TextView[taskArray.size()][2];

                // retrieve width of screen
                Point size = new Point();
                getDisplay().getRealSize(size);
                int width = size.x;

                // create the TextView for the candy's id
                int i = 0;

                //parse deadline date from string to date
                for (Task task : taskArray) {

                    ids[i] = new TextView(this);
                    ids[i].setGravity(Gravity.CENTER);
                    ids[i].setText("" + task.getId());


                    //if (task.getDeadline(). < currentDate.getTime() )

                    // create the two EditTexts for the candy's name and price
                    taskAndId[i][0] = new TextView(this);
                    taskAndId[i][1] = new TextView(this);
                    taskAndId[i][0].setText(task.getTask());
                    taskAndId[i][1].setText("" + task.getDeadline());
                    taskAndId[i][0]
                            .setInputType(InputType.TYPE_CLASS_NUMBER);
                    taskAndId[i][0].setId(10 * task.getId());
                    taskAndId[i][1].setId(10 * task.getId() + 1);

                    //taskAndId[i][1].
                   // if (taskAndId[i][1].parse)

                    grid.addView(ids[i], width / 10,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    grid.addView(taskAndId[i][0], (int) (width * .5),
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    grid.addView(taskAndId[i][1], (int) (width * .3),
                            ViewGroup.LayoutParams.WRAP_CONTENT);

                    i++;
                }
                scrollView.addView(grid);
                layout.addView(scrollView);

                ButtonHandler bh = new ButtonHandler();

                Button refreshButton = new Button(this);
                refreshButton.setText("Refresh");
                refreshButton.setOnClickListener(bh);


                RelativeLayout.LayoutParams params
                        = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                params.setMargins(0, 0, 0, 50);
                layout.addView(refreshButton, params);

                setContentView(layout);

            }

        }

        private class ButtonHandler implements View.OnClickListener {
            public void onClick(View v) {

                showView();

            }
        }
    }
