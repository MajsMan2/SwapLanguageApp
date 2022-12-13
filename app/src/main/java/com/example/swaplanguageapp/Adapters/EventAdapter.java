package com.example.swaplanguageapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.swaplanguageapp.CalendarUtils;
import com.example.swaplanguageapp.Models.CalendarEvent;
import com.example.swaplanguageapp.R;
import com.example.swaplanguageapp.Views.EventEditActivity;

import java.util.List;

public class EventAdapter extends ArrayAdapter<CalendarEvent> {

    public EventAdapter(@NonNull Context context, List<CalendarEvent> calEvents) {
        super(context, 0, calEvents);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CalendarEvent calendarEvent = getItem(position);
        if (convertView ==  null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);
        String eventTitle = calendarEvent.getName() + " "+  CalendarUtils.formattedTime(calendarEvent.getTime());
        eventCellTV.setText(eventTitle);
        return convertView;
    }
}