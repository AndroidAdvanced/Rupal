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
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GenderActivity extends AppCompatActivity {

    Button btnNext, btnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gender);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    RadioGroup radioGroup;
    RadioButton radioBtnGender;

    @Override
    protected void onResume() {
        super.onResume();

        findReferences();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    SharedPreferences pref;

    void findReferences() {
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        RadioButton radioBtnMale = (RadioButton) findViewById(R.id.radioBtnMale);
        RadioButton radioBtnFemale = (RadioButton) findViewById(R.id.radioBtnFemale);

        if(radioBtnMale.getText().toString().equals(pref.getString("gender",""))) {
            radioBtnMale.setChecked(true);
        } else if(radioBtnFemale.getText().toString().equals(pref.getString("gender",""))) {
            radioBtnFemale.setChecked(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor edit = pref.edit();

        int selectedId = radioGroup.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        radioBtnGender = (RadioButton) findViewById(selectedId);
        edit.putString("gender",radioBtnGender.getText().toString());
        edit.commit();
    }
}
