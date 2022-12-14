package com.example.swaplanguageapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;


import com.example.swaplanguageapp.MainActivity;
import com.example.swaplanguageapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    //    CircleimageView circleimageView;
    TextInputEditText displayNameEdit;
    TextInputEditText displayEmailEdit;
    TextInputEditText displayPhoneEdit;

    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;

    Button updateProfileButton;

    String DISPLAY_NAME = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        displayNameEdit = findViewById(R.id.displayNameEdit);
        displayEmailEdit = findViewById(R.id.displayEmailEdit);
        displayPhoneEdit = findViewById(R.id.displayPhoneEdit);
        drawerLayout = findViewById(R.id.drawer);

        updateProfileButton = findViewById(R.id.updateProfileButton);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);


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




        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Log.d(TAG, "OnCreate: " + user.getDisplayName());
            if (user.getDisplayName() != null) {
                displayNameEdit.setText(user.getDisplayName());
                displayNameEdit.setSelection(user.getDisplayName().length());
                displayEmailEdit.setText(user.getEmail());
                displayEmailEdit.setSelection(user.getEmail().length());
                displayPhoneEdit.setText(user.getPhoneNumber());
                displayPhoneEdit.setSelection(user.getPhoneNumber().length());
            }
        }
    }

    public void updateProfile(View view) {

        view.setEnabled(false);
        DISPLAY_NAME = displayNameEdit.getText().toString();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setDisplayName(DISPLAY_NAME)
                .build();

        firebaseUser.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        view.setEnabled(true);
                        Toast.makeText(ProfileActivity.this, "Succesfully Updated Profile", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        view.setEnabled(true);
                        Log.d(TAG, "onFailure: ", e.getCause());

                    }
                });
    }

}