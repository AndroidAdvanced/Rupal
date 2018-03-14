package db;

/**
 * Created by rgi-38 on 14/3/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Model.Category;
import Model.SubCategory;

/**
 * Created by Rupal on 9/24/2017.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "shopping";

    // Category table name
    private static final String TABLE_CATEGORY = "Category";
    // Table Quote Columns names
    private static final String CATEGORY_ID = "Cat_Id";
    private static final String CATEGORY_NAME = "Cat_Name";

    // SubCategory table name
    private static final String TABLE_SUBCATEGORY = "SubCategory";
    // Table Quote Columns names
    private static final String SUBCATEGORY_ID = "SubCat_Id";
    private static final String SUBCATEGORY_NAME = "SubCat_Name";
    private static final String Cat_ID_Ref = "Cat_Id";


   /* private static final String TASK_TABLE_CREATE = "create table "
            + TASK_TABLE + " ("
            + TASK_ID + " integer primary key autoincrement, "
            + TASK_TITLE + " text not null, "
            + TASK_NOTES + " text not null, "
            + TASK_DATE_TIME + " text not null,"
            + TASK_CAT + " integer,"
            + " FOREIGN KEY ("+TASK_CAT+") REFERENCES "+CAT_TABLE+"("+CAT_ID+"));";
*/


    private static final String TASK_TABLE_CREATE = "create table "
            + TABLE_SUBCATEGORY + " ("
            + SUBCATEGORY_ID + " integer primary key autoincrement, "
            + SUBCATEGORY_NAME + " text not null, "
            + Cat_ID_Ref + " integer,"
            + " FOREIGN KEY ("+Cat_ID_Ref+") REFERENCES "+TABLE_CATEGORY+"("+CATEGORY_ID+"));";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Category Category Table
    String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
            + CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CATEGORY_NAME + " TEXT"
            +")";
    // Category SubCategory Table
    String CREATE_SUBCATEGORY_TABLE = "CREATE TABLE " + TABLE_SUBCATEGORY + "("
            + SUBCATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SUBCATEGORY_NAME + " TEXT,"
            + Cat_ID_Ref + " INTEGER"
            +")";

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CATEGORY_TABLE);
       // db.execSQL(CREATE_SUBCATEGORY_TABLE);

        db.execSQL(TASK_TABLE_CREATE);
        insertData(db);

        System.out.println("Table Created");
    }

    public ArrayList<Category> getCategoryData() {

        String countQuery = "SELECT * FROM Category";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        String s[] = cursor.getColumnNames();

        ArrayList<Category> listUserScore = new ArrayList<Category>();

        if (cursor.moveToFirst()) {
            do {
                Category userScore = new Category();
                userScore.setCategoryid(cursor.getInt(0));
                userScore.setCategoryname(cursor.getString(1));
                listUserScore.add(userScore);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return  listUserScore;
    }


    public ArrayList<SubCategory> getSubcategoryData(String cat) {

        //String countQuery = "SELECT * FROM SubCategory"  ;
        String countQuery = "SELECT * FROM SubCategory WHERE Cat_Id = " + "'"+cat+"'"  ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        String s[] = cursor.getColumnNames();

        System.out.println("SubCatData Query" + countQuery);

        ArrayList<SubCategory> listUserScore = new ArrayList<SubCategory>();

        if (cursor.moveToFirst()) {
            do {
                SubCategory userScore = new SubCategory();
                System.out.println("SubCatData" + cursor.getInt(0));
                userScore.setSub_categoryid(cursor.getInt(0));
                userScore.setSub_categoryname(cursor.getString(1));
                userScore.setCatid_ref(cursor.getInt(2));
                listUserScore.add(userScore);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return  listUserScore;
    }

    public int getCatId(String cat) {

       // String countQuery = "SELECT * FROM TABLE_CATEGORY"  ;
        String countQuery = "SELECT * FROM Category WHERE Cat_Name = " + "'"+cat+"'"  ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        String s[] = cursor.getColumnNames();

        cursor.moveToFirst();

        int id = cursor.getInt(0);

        cursor.close();
        db.close();

        return  id;
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        // Create tables again
        onCreate(db);
    }


    public void insertData(SQLiteDatabase db) {
        String sql1 = "insert into " + TABLE_CATEGORY + " (" + CATEGORY_ID + ", " + CATEGORY_NAME
                + ") values('101', 'Jeans');";
        db.execSQL(sql1);
        sql1 = "insert into " + TABLE_CATEGORY + " (" + CATEGORY_ID + ", " + CATEGORY_NAME
                + ") values('102', 'T-Shirt');";
        db.execSQL(sql1);
        sql1 = "insert into " + TABLE_CATEGORY + " (" + CATEGORY_ID + ", " + CATEGORY_NAME
                + ") values('103', 'Shoes');";
        db.execSQL(sql1);
        sql1 = "insert into " + TABLE_CATEGORY + " (" + CATEGORY_ID + ", " + CATEGORY_NAME
                + ") values('104', 'Formal');";
        db.execSQL(sql1);


        // Blue Records
        sql1 = "insert into " + TABLE_SUBCATEGORY + " (" + SUBCATEGORY_ID + ", " + SUBCATEGORY_NAME
                + ", " + Cat_ID_Ref + ") values('1001', 'Blue', 101);";
        db.execSQL(sql1);

        sql1 = "insert into " + TABLE_SUBCATEGORY + " (" + SUBCATEGORY_ID + ", " + SUBCATEGORY_NAME
                + ", " + Cat_ID_Ref + ") values('1002', 'Black', 101);";
        db.execSQL(sql1);

        // T-Shirts Records
        sql1 = "insert into " + TABLE_SUBCATEGORY + " (" + SUBCATEGORY_ID + ", " + SUBCATEGORY_NAME
                + ", " + Cat_ID_Ref + ") values('2001', 'M', 102);";
        db.execSQL(sql1);

        sql1 = "insert into " + TABLE_SUBCATEGORY + " (" + SUBCATEGORY_ID + ", " + SUBCATEGORY_NAME
                + ", " + Cat_ID_Ref + ") values('2002', 'L', 102);";
        db.execSQL(sql1);

        sql1 = "insert into " + TABLE_SUBCATEGORY + " (" + SUBCATEGORY_ID + ", " + SUBCATEGORY_NAME
                + ", " + Cat_ID_Ref + ") values('2003', 'XL', 102);";
        db.execSQL(sql1);

        sql1 = "insert into " + TABLE_SUBCATEGORY + " (" + SUBCATEGORY_ID + ", " + SUBCATEGORY_NAME
                + ", " + Cat_ID_Ref + ") values('2004', 'XXL', 102);";
        db.execSQL(sql1);

        // Shoes Records

        sql1 = "insert into " + TABLE_SUBCATEGORY + " (" + SUBCATEGORY_ID + ", " + SUBCATEGORY_NAME
                + ", " + Cat_ID_Ref + ") values('3001', '9', 103);";
        db.execSQL(sql1);

        sql1 = "insert into " + TABLE_SUBCATEGORY + " (" + SUBCATEGORY_ID + ", " + SUBCATEGORY_NAME
                + ", " + Cat_ID_Ref + ") values('3002', '10', 103);";
        db.execSQL(sql1);

        sql1 = "insert into " + TABLE_SUBCATEGORY + " (" + SUBCATEGORY_ID + ", " + SUBCATEGORY_NAME
                + ", " + Cat_ID_Ref + ") values('3003', '11', 103);";
        db.execSQL(sql1);

        sql1 = "insert into " + TABLE_SUBCATEGORY + " (" + SUBCATEGORY_ID + ", " + SUBCATEGORY_NAME
                + ", " + Cat_ID_Ref + ") values('3004', '12', 103);";
        db.execSQL(sql1);


        // Formal Records

        sql1 = "insert into " + TABLE_SUBCATEGORY + " (" + SUBCATEGORY_ID + ", " + SUBCATEGORY_NAME
                + ", " + Cat_ID_Ref + ") values('4001', 'Raymond', 104);";
        db.execSQL(sql1);

        sql1 = "insert into " + TABLE_SUBCATEGORY + " (" + SUBCATEGORY_ID + ", " + SUBCATEGORY_NAME
                + ", " + Cat_ID_Ref + ") values('4002', 'Peter England', 104);";
        db.execSQL(sql1);

        sql1 = "insert into " + TABLE_SUBCATEGORY + " (" + SUBCATEGORY_ID + ", " + SUBCATEGORY_NAME
                + ", " + Cat_ID_Ref + ") values('4003', 'Arrow', 104);";
        db.execSQL(sql1);
    }

    // User Score

    // Getting Contacts Count
    public int getCategoryCount(String cat) {

        int count = 0;
        try {

            String countQuery = "SELECT  * FROM " + TABLE_CATEGORY;
/*
            String countQuery = "SELECT  * FROM " + TABLE_CATEGORY + " WHERE "+CATEGORY  +"="+ cat;
*/
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            count = cursor.getCount();

            cursor.close();
            // return count

        } catch(Exception e) {

        }
        return count;
    }
}