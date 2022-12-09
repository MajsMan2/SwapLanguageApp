package com.example.swaplanguageapp.Views;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swaplanguageapp.APIClient.APIClient;
import com.example.swaplanguageapp.Adapters.CalendarAdapter;
import com.example.swaplanguageapp.Interfaces.EventService;
import com.example.swaplanguageapp.Interfaces.EventUserService;
import com.example.swaplanguageapp.Interfaces.SeriesService;
import com.example.swaplanguageapp.Models.Event;
import com.example.swaplanguageapp.Models.EventUser;
import com.example.swaplanguageapp.R;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Calendar extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    TextView textView;


    //API
    EventService eventService;
    EventUserService eventUserService;
    SeriesService seriesService;

    TextView responseText;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        textView = findViewById(R.id.dateView);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();

        responseText = findViewById(R.id.calendar);
        eventService = APIClient.getClient().create(EventService.class);

    }





    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYear);
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);

    }

    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i=1; i<=42; i++){
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            }
            else{
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }


    public void nextMonthAction (View view){
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    public void previousMonthAction (View view){
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText)
    {
        if(dayText.equals(dayText)){
            String message = "Selected Date: " + dayText + " " + monthYearFromDate(selectedDate);
            textView.setText(message);
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
}