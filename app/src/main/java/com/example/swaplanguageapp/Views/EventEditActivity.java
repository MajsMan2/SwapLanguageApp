package com.example.swaplanguageapp.Views;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swaplanguageapp.CalendarUtils;
import com.example.swaplanguageapp.Models.CalendarEvent;
import com.example.swaplanguageapp.R;

import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity {

    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;
    private LocalTime time;
    String[] items = {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
    AutoCompleteTextView auto_complete_txt;

    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        auto_complete_txt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items);
        auto_complete_txt.setAdapter(adapterItems);
        auto_complete_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));
    }

    private void initWidgets() {
        eventNameET = findViewById(R.id.eventNameET);
        eventTimeTV = findViewById(R.id.eventTimeTV);
        eventDateTV = findViewById(R.id.eventDateTV);
        auto_complete_txt = findViewById(R.id.auto_complete_txt);
    }

    public AutoCompleteTextView getAuto_complete_txt() {
        return auto_complete_txt;
    }

    public void saveEventAction(View view) {
        String eventName = eventNameET.getText().toString();
        AutoCompleteTextView auto_complete_txt = getAuto_complete_txt();
        CalendarEvent calEvent = new CalendarEvent(eventName, CalendarUtils.selectedDate, time, auto_complete_txt);
        CalendarEvent.eventsList.add(calEvent);
        finish();
    }
}