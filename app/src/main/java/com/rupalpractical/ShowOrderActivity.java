package com.rupalpractical;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShowOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_order);

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

    public void findReferences() {

        /*TextView txtName, txtGender, txtCategory, txtSubcategory;

        txtName = (TextView) findViewById(R.id.txtName);
        txtGender = (TextView) findViewById(R.id.txtGender);
        txtCategory = (TextView) findViewById(R.id.txtCategory);
        txtSubcategory = (TextView) findViewById(R.id.txtSubCategory);
*/
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        ((TextView) findViewById(R.id.txtName)).setText("Customer Name" + pref.getString("c_firstname","") +"  "+pref.getString("c_lastname",""));
        ((TextView) findViewById(R.id.txtGender)).setText("Customer Name" + pref.getString("gender",""));
        ((TextView) findViewById(R.id.txtCategory)).setText("Customer Name" + pref.getString("category",""));
        ((TextView) findViewById(R.id.txtSubCategory)).setText("Customer Name" + pref.getString("subcategory",""));
        ((TextView) findViewById(R.id.txtBirthDate)).setText("Customer Name" + pref.getString("birthdate",""));

     /*   txtName.setText("Customer Name" + pref.getString("customername",""));
        txtGender.setText("Customer Name" + pref.getString("gender",""));
        txtCategory.setText("Customer Name" + pref.getString("category",""));
        txtSubcategory.setText("Customer Name" + pref.getString("subcategory",""));*/
    }
}
