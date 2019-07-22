package com.proficiency.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.proficiency.myapplication.model.Wheatherbean;

import java.util.ArrayList;
import java.util.List;

public class WeatherDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Wheather.db";
    public static final String TABLE_NAME = "wheather_table";
    public static final String COL_1 = "CITY_ID";
    public static final String COL_2 = "CITY_NAME";
    public static final String COL_3 = "WHEATHER_MAIN";
    public static final String COL_4 = "TEMP1";
    public static final String COL_5 = "TEMP_MIN_MAX";
    public static final String COL_6 = "HUMIDITY";
    private List<Wheatherbean> wheatherbeanList = new ArrayList<>();


    public WeatherDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (CITY_ID TEXT,CITY_NAME TEXT,WHEATHER_MAIN TEXT,TEMP1 TEXT,TEMP_MIN_MAX TEXT,HUMIDITY)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String cityId, String cityName,String wheather_MAIN, String temp,String temp_minmax,String humidity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,cityId);
        contentValues.put(COL_2,cityName);
        contentValues.put(COL_3,wheather_MAIN);
        contentValues.put(COL_4,temp);
        contentValues.put(COL_5,temp_minmax);
        contentValues.put(COL_6,humidity);


        long result = db.insert(TABLE_NAME,null ,contentValues);

        System.out.println("insertttttt "+result);

        if(result == -1)
            return false;
        else
            return true;
    }

    public List getAllData() {
        Cursor res = null;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String Query = "select " + "*" + " from " + TABLE_NAME;
            System.out.println("qquueerryy"+Query);
            res = db.rawQuery(Query, null);

            while (res.moveToNext()) {
                int index = res.getColumnIndex(WeatherDB.COL_1);
                int index2 = res.getColumnIndex(WeatherDB.COL_2);
                int index3 = res.getColumnIndex(WeatherDB.COL_3);
                int index4 = res.getColumnIndex(WeatherDB.COL_4);
                int index5 = res.getColumnIndex(WeatherDB.COL_5);
                int index6 = res.getColumnIndex(WeatherDB.COL_6);
                //int cid = cursor.getInt(index);
                String city_id = res.getString(index);
                String city_name = res.getString(index2);
                String wheatherMain = res.getString(index3);
                String temp = res.getString(index4);
                String temp_min_max = res.getString(index5);
                String humidity = res.getString(index6);


                Wheatherbean wheatherbean=new Wheatherbean(city_id,city_name,wheatherMain,temp,temp_min_max,humidity);
                wheatherbeanList.add(wheatherbean);


            }




        }catch (Exception e){

            e.printStackTrace();
            
        }
        return wheatherbeanList;
    }


    public boolean updateContact (String cityId, String cityName,String wheather_MAIN, String temp,String temp_minmax,String humidity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,cityName);
        contentValues.put(COL_3,wheather_MAIN);
        contentValues.put(COL_4,temp);
        contentValues.put(COL_5,temp_minmax);
        contentValues.put(COL_6,humidity);

        long result= db.update(TABLE_NAME, contentValues, "CITY_ID = ? ", new String[] { cityId } );

        return true;
    }




    public boolean icityIdExists(String cityId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,// Selecting Table
                new String[]{COL_3},//Selecting columns want to query
                COL_1 + "=?",
                new String[]{cityId},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }
}
