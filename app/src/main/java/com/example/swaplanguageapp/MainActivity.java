package com.example.swaplanguageapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.swaplanguageapp.Adapters.MainAdapter;
import com.example.swaplanguageapp.Models.Model;
import com.example.swaplanguageapp.Views.BlogActivity;
import com.example.swaplanguageapp.Views.Calendar;
import com.example.swaplanguageapp.Views.ContactActivity;
import com.example.swaplanguageapp.Views.LoginRegisterActivity;
import com.example.swaplanguageapp.Views.ProfileActivity;
import com.example.swaplanguageapp.Views.ProgressActivity;
import com.example.swaplanguageapp.Views.VideoActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    ArrayList<Model> dataList = new ArrayList<>();
    RecyclerView myRv;
    String URL = "";
    private static final String TAG = "MainActivity";

    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.calendar);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

        bottomNavigationView.setSelectedItemId(R.id.home);
        drawerLayout = findViewById(R.id.drawer);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Toast.makeText(getApplicationContext(), "Home Page", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        return true;
                    case R.id.news:
                        startActivity(new Intent(getApplicationContext(), BlogActivity.class));
                        return true;
                    case R.id.profile:
                        Toast.makeText(getApplicationContext(), "Profile Page", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        return true;
                }
                return false;
            }
        });
        parseApiData();

//        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
//            Intent intent = new Intent(this, LoginRegisterActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }

    public  void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginRegisterActivity.class);
        startActivity(intent);
        finish();
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

    public void logoutClick(View view) {
        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
        AuthUI.getInstance().signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    startLoginActivity();
                } else {
                    Log.e(TAG, "onComplete", task.getException());
                }
            }
        });
    }

    public   static void redirectActivity(Activity activity, Class Class) {
        Intent intent=new Intent(activity,Class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public  void clickCalendar(View view){
        redirectActivity(this,Calendar.class);
    }

    public void onProfileClick(View view) {
        Toast.makeText(getApplicationContext(),"Profile Page",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void onCalendarClick(View view) {
        Toast.makeText(getApplicationContext(),"Calendar Page",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }

    public void onVideosClick(View view) {
        Toast.makeText(getApplicationContext(), "Videos Page", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }

    public void onProgressClick(View view) {
        Toast.makeText(getApplicationContext(), "Progress Page", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ProgressActivity.class);
        startActivity(intent);
    }

    public void onBlogClick(View view) {
        Toast.makeText(getApplicationContext(), "Blog Page", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, BlogActivity.class);
        startActivity(intent);
    }

    public void onContactClick(View view) {
        Toast.makeText(getApplicationContext(), "Contact Page", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

    public void onHomeClick(View view) {
        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}