package com.example.swaplanguageapp.Views;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swaplanguageapp.CalendarUtils;
import com.example.swaplanguageapp.Models.CalEvent;
import com.example.swaplanguageapp.Models.CalendarEvent;
import com.example.swaplanguageapp.R;

import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity {

    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;
    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));
    }

    private void initWidgets()
    {
        eventNameET = findViewById(R.id.eventNameET);
        eventTimeTV = findViewById(R.id.eventTimeTV);
        eventDateTV = findViewById(R.id.eventDateTV);
    }

    public void saveEventAction(View view)
    {
        String eventName = eventNameET.getText().toString();
        CalendarEvent calEvent = new CalendarEvent(eventName, CalendarUtils.selectedDate, time);
        CalendarEvent.eventsList.add(calEvent);
        finish();
    }
}
