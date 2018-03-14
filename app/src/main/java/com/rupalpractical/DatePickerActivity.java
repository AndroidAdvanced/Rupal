package com.rupalpractical;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class DatePickerActivity extends AppCompatActivity implements View.OnClickListener {

    DatePicker datePicker;
    Button btnSubmit, btnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        findReferences();


    }

    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();
        builder.append("Current Date: ");
        builder.append((datePicker.getMonth() + 1)+"/");//month is 0 based
        builder.append(datePicker.getDayOfMonth()+"/");
        builder.append(datePicker.getYear());
        return builder.toString();
    }

    void findReferences() {
        datePicker = (DatePicker) findViewById(R.id.datePicker1);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnSubmit) {

            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = pref.edit();
            edit.putString("birthdate", getCurrentDate());
            edit.commit();

            Intent intent = new Intent(this, ShowOrderActivity.class);
            startActivity(intent);
        } else if(view.getId() == R.id.btnPrevious) {
            Intent intent = new Intent(this, SubCategoryActivity.class);
            startActivity(intent);
        }
    }
}
