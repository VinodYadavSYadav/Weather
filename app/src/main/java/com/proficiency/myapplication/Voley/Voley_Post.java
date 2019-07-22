package com.proficiency.myapplication.Voley;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Voley_Post {
  static   String response1;



    public static String crop_posting(Activity activity, String url,final VolleyCallback callback){


        // Initialize a new StringRequest
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "http://api.openweathermap.org/data/2.5/group?id=1277333,4802333,1273294,1269843,1264733&units=metric&appid=d32091b3739b5fd38c00f4b681ca4004",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with response string
                        callback.onSuccessResponse(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when get error

                    }
                }
        );

        // Add StringRequest to the RequestQueue
        VolleySingletonQuee.getInstance(activity).addToRequestQueue(stringRequest);



        return url;
    }


    }








