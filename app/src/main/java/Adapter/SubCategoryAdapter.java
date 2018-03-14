package Adapter;

/**
 * Created by rgi-38 on 14/3/18.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.rupalpractical.R;
import com.rupalpractical.ShowOrderActivity;
import com.rupalpractical.SubCategoryActivity;

import java.util.List;

import Model.Category;
import Model.SubCategory;
import db.DatabaseHandler;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {

    private List<SubCategory> moviesList;

    public CheckBox[] arrCheckBox;

    public class MyViewHolder extends RecyclerView.ViewHolder {
       public CheckBox chkBoxCateogry;

        public MyViewHolder(View view) {
            super(view);
            chkBoxCateogry = (CheckBox) view.findViewById(R.id.chkBoxCateogry);
;        }
    }

    Context mContext;
    public SubCategoryAdapter(List<SubCategory> moviesList, Context mContext) {
        this.moviesList = moviesList;
        this.mContext = mContext;
        arrCheckBox = new CheckBox[moviesList.size()];
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_category, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        SubCategory movie = moviesList.get(position);
        holder.chkBoxCateogry.setText(movie.getSub_categoryname());

        arrCheckBox[position] = holder.chkBoxCateogry;

        holder.chkBoxCateogry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                for(int i = 0 ; i < arrCheckBox.length ; i++) {
                    arrCheckBox[i].setChecked(false);
                }

                if(b) {
                    arrCheckBox[position].setChecked(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public String getSelectedCheckBox() {

        for(int i = 0 ; i < arrCheckBox.length ; i++) {
            if(arrCheckBox[i].isChecked()){
                return arrCheckBox[i].getText().toString();
            }
        }
        return "";
    }

    public void setCheckBoxSelected () {

        for(int i = 0 ; i < arrCheckBox.length ; i++) {

            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext);
            System.out.println("CheckBox" + arrCheckBox[i].getText().toString()+(pref.getString("subcategory","")));

            if(arrCheckBox[i] != null) {
                if(pref.getString("subcategory","").equals(arrCheckBox[i].getText().toString())){
                    arrCheckBox[i].setChecked(true);
                    break;
                }
            }
        }
    }
}
