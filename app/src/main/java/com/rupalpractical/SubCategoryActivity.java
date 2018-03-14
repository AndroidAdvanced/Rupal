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
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.CategoryAdapter;
import Adapter.SubCategoryAdapter;
import Model.Category;
import Model.SubCategory;
import db.DatabaseHandler;

public class SubCategoryActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnNext, btnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    RecyclerView recyclerView;
    SubCategoryAdapter mAdapter;
    SharedPreferences pref;

    @Override
    protected void onResume() {
        super.onResume();

        findReferences();

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        pref.getString("category","");

        DatabaseHandler dbHandler = new DatabaseHandler(this);
        int id = dbHandler.getCatId(pref.getString("category",""));
        ArrayList<SubCategory> listSubCateogry = dbHandler.getSubcategoryData(id+"");

        mAdapter = new SubCategoryAdapter(listSubCateogry, this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        
        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        handler.postDelayed(runnable,50);
    }

        public void onClick(View view) {

            if (view.getId() == R.id.btnNext) {

                if(mAdapter.getSelectedCheckBox().equals("")) {
                    Toast.makeText(this, "Please Select Single Category", Toast.LENGTH_LONG).show();
                } else {

                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putString("subcategory", mAdapter.getSelectedCheckBox());
                    edit.commit();

                    Intent intent = new Intent(this, DatePickerActivity.class);
                    startActivity(intent);
                }

            } else if (view.getId() == R.id.btnPrevious) {
                Intent intent = new Intent(getBaseContext(), CategoryActivity.class);
                startActivity(intent);
            }
        }

    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mAdapter.setCheckBoxSelected();
        }
    };

    public void findReferences() {
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
        edit.putString("subcategory",mAdapter.getSelectedCheckBox());
        edit.commit();
        handler.removeCallbacks(runnable);
    }
}
