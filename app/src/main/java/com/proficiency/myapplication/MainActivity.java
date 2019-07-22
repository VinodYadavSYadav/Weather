package com.proficiency.myapplication;



import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.proficiency.myapplication.Voley.Voley_Post;
import com.proficiency.myapplication.Voley.VolleyCallback;
import com.proficiency.myapplication.Voley.VolleySingletonQuee;
import com.proficiency.myapplication.adapter.WeatherAdapter;
import com.proficiency.myapplication.model.Wheatherbean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
RecyclerView recycler_looking;
    public List<Wheatherbean> wheatherbeanList = new ArrayList<>();
    WeatherAdapter mAdapter;
    WeatherDB myDb;
    public  static TextView cur_temp,cur_main,cur_tempmin_max,humidity,cur_name,date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_looking = findViewById(R.id.recycler_looking);
        cur_temp = findViewById(R.id.cur_temp);
        cur_main = findViewById(R.id.cur_main);
        cur_tempmin_max = findViewById(R.id.cur_tempmin_max);
        humidity = findViewById(R.id.humidity);
        cur_name = findViewById(R.id.cur_name);
        date = findViewById(R.id.date);
        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.HORIZONTAL, false);
        recycler_looking.setLayoutManager(mLayoutManager);
        recycler_looking.setItemAnimator(new DefaultItemAnimator());
        myDb = new WeatherDB(this);
        Date d = new Date();
        CharSequence s  = DateFormat.format("EEEE, d MMMM ", d.getTime());
        wheatherbeanList=myDb.getAllData();

        if (!(wheatherbeanList.size()==0)){
                double temp = Double.parseDouble(wheatherbeanList.get(0).getTemp());
                String s2= String.valueOf((Math.round(temp)));
                cur_temp.setText(s2);
                cur_main.setText(wheatherbeanList.get(0).getWheatherMain());
                cur_tempmin_max.setText(wheatherbeanList.get(0).getTemp_min_max());
                humidity.setText(wheatherbeanList.get(0).getHumidity());
                cur_name.setText(wheatherbeanList.get(0).getCity_name());
                date.setText(s);
        }

        mAdapter = new WeatherAdapter(wheatherbeanList,this);
        recycler_looking.setAdapter(mAdapter);


        Voley_Post.crop_posting(this, "", new VolleyCallback() {
            @Override
            public void onSuccessResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("list");

                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String city_Id=jsonObject1.getString("id");
                        String city_name=jsonObject1.getString("name");
                        String wheatherMain=jsonObject1.getJSONArray("weather").getJSONObject(0).getString("main");
                        String temp =jsonObject1.getJSONObject("main").getString("temp");
                        String humidity =jsonObject1.getJSONObject("main").getString("humidity");
                        String temp_min_max =jsonObject1.getJSONObject("main").getString("temp_max")+"/"+jsonObject1.getJSONObject("main").getString("temp_min");


                        if(!myDb.icityIdExists(city_Id)){
                            myDb.insertData(city_Id,city_name,wheatherMain,temp,temp_min_max,humidity);
                        }else {
                            myDb.updateContact(city_Id,city_name,wheatherMain,temp,temp_min_max,humidity);
                        }

                    }

                    wheatherbeanList.clear();
                    wheatherbeanList=myDb.getAllData();
                    mAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });















    }
}
