package com.example.swaplanguageapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.swaplanguageapp.Adapters.CalendarAdapter;
import com.example.swaplanguageapp.Adapters.MainAdapter;
import com.example.swaplanguageapp.Models.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    ArrayList<Model> dataList = new ArrayList<>();
    RecyclerView myRv;
    String URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRv = findViewById(R.id.myRv);
        parseApiData();
    }

    public void parseApiData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Log.e("Res : ", response);
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Data");

                    if (jsonArray.length()>0){
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String event = jsonObject1.getString("event");
                            String eventusers = jsonObject1.getString("eventusers");
                            String series = jsonObject1.getString("series");
                            dataList.add(new Model(event, eventusers, series));
                        }
                        MainAdapter adapter = new MainAdapter(dataList, MainActivity.this);
                        myRv.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL, false));
                        myRv.setAdapter(adapter);
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}