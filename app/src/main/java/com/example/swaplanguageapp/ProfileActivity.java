package com.example.swaplanguageapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

//    CircleimageView circleimageView;
    TextInputEditText displayNameEdit;
    Button updateProfileButton;


    String DISPLAY_NAME = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        displayNameEdit = findViewById(R.id.displayNameEdit);
        updateProfileButton = findViewById(R.id.updateProfileButton);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Log.d(TAG, "OnCreate: " + user.getDisplayName());
            if (user.getDisplayName() != null) {
                displayNameEdit.setText(user.getDisplayName());
                displayNameEdit.setSelection(user.getDisplayName().length());
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