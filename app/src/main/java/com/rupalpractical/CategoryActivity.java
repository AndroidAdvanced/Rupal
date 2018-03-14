package com.rupalpractical;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.CategoryAdapter;
import Model.Category;
import db.DatabaseHandler;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerView;
    Button btnNext, btnPrevious;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    CategoryAdapter mAdapter;
    @Override
    protected void onResume() {
        super.onResume();

        DatabaseHandler dbHandler = new DatabaseHandler(this);

        findReferences();

        ArrayList<Category> arrcat = dbHandler.getCategoryData();

        mAdapter = new CategoryAdapter(arrcat, this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        // horizontal RecyclerView
        // keep movie_list_row.xml width to `wrap_content`
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        },100);*/

        handler.postDelayed(runnable,50);
    }

    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mAdapter.setCheckBoxSelected();
        }
    };


    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnNext) {

            if(mAdapter.getSelectedCheckBox().equals("")) {
                Toast.makeText(this, "Please Select Single Category", Toast.LENGTH_LONG).show();
            } else {
                DatabaseHandler dbHandler = new DatabaseHandler(this);
                dbHandler.getCatId(mAdapter.getSelectedCheckBox());

                pref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("category", mAdapter.getSelectedCheckBox());
                edit.commit();

                Intent intent = new Intent(this, SubCategoryActivity.class);
                startActivity(intent);
            }

        } else if(view.getId() == R.id.btnPrevious) {
            Intent intent = new Intent(getBaseContext(), GenderActivity.class);
            startActivity(intent);
        }
    }

    public void findReferences() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("category",mAdapter.getSelectedCheckBox());
        edit.commit();
        handler.removeCallbacks(runnable);
    }
}
