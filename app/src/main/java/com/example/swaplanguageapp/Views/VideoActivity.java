package com.example.swaplanguageapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swaplanguageapp.MainActivity;
import com.example.swaplanguageapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }
    }