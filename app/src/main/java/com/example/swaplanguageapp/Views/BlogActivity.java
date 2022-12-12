package com.example.swaplanguageapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.swaplanguageapp.MainActivity;
import com.example.swaplanguageapp.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BlogActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        getSupportActionBar().hide();


        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.calendar);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

        bottomNavigationView.setSelectedItemId(R.id.news);
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
                        Toast.makeText(getApplicationContext(), "Blog Page", Toast.LENGTH_SHORT).show();
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
}