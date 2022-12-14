package com.example.swaplanguageapp.Views;

import static com.example.swaplanguageapp.CalendarUtils.daysInMonthArray;
import static com.example.swaplanguageapp.CalendarUtils.monthYearFromDate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swaplanguageapp.APIClient.APIClient;
import com.example.swaplanguageapp.Adapters.CalendarAdapter;
import com.example.swaplanguageapp.CalendarUtils;
import com.example.swaplanguageapp.Interfaces.EventService;
import com.example.swaplanguageapp.Interfaces.EventUserService;
import com.example.swaplanguageapp.Interfaces.SeriesService;
import com.example.swaplanguageapp.Models.Event;
import com.example.swaplanguageapp.Models.EventUser;
import com.example.swaplanguageapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Calendar extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    //API
    EventService eventService;
    EventUserService eventUserService;
    SeriesService seriesService;
    TextView responseText;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();

        drawerLayout = findViewById(R.id.drawer);
        responseText = findViewById(R.id.calendar);
        eventService = APIClient.getClient().create(EventService.class);

    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYear);
    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);

    }

    public void nextMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    public void previousMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        if (date != null) {
            CalendarUtils.selectedDate = date;
            setMonthView();

        }
    }

    private void getById() {
        //Get event by id
        Event event = new Event();
        Call<List<Event>> call = eventService.getById(event);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                Event event = (Event) response.body();
                OffsetDateTime startDate = event.StartDate;
                OffsetDateTime endDate = event.EndDate;
                Double durationHours = event.DurationHours;
                String title = event.Title;
                String meetingURL = event.MeetingUrl;
                String description = event.Description;
                com.google.firebase.database.core.view.Event.EventType eventType = event.EventType;
                Collection<EventUser> collectionOfUsers = event.eventUser;

                Toast.makeText(getApplicationContext(), startDate + " " + endDate + " " + durationHours + " " + title + " " + meetingURL + " " +
                        description + " " + eventType + " " + collectionOfUsers, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                call.cancel();

            }
        });
    }

    private void createNewEvent() {
        Event event = new Event();
        Call<Event> call = eventService.Create(event);
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                Event event = response.body();
                Toast.makeText(getApplicationContext(), event.Title, Toast.LENGTH_LONG);
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void weeklyAction(View view) {
        Intent intent = new Intent(this, WeekViewActivity.class);
        startActivity(intent);
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
