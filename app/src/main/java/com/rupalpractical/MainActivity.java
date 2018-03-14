package com.rupalpractical;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText edtFirstName,edtLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    SharedPreferences pref;

    @Override
    protected void onResume() {
        super.onResume();

        findReferences();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edtFirstName.getText().toString().equals(null) || edtFirstName.getText().toString().equals("")|| edtLastName.getText().toString().equals(null) || edtLastName.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(), "Please fill your name first", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(getBaseContext(), GenderActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void findReferences() {

        pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        btn = (Button) findViewById(R.id.btnNext);
        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);
        edtFirstName.setText(pref.getString("c_firstname",""));
        edtLastName.setText(pref.getString("c_lastname",""));
    }


    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor edit = pref.edit();
        edit.putString("c_firstname", edtFirstName.getText().toString());
        edit.putString("c_lastname", edtLastName.getText().toString());
        edit.commit();
    }
}


// 11:26
// Requirement : Order Table

// OrderId, PersonName, Gender, Category, SubType, BirthDay.
