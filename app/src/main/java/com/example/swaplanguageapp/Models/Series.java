package com.example.swaplanguageapp.Models;

import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;
import java.util.Collection;

public class Series {

    @SerializedName("startDate")
    public OffsetDateTime StartDate;
    @SerializedName("endDate")
    public OffsetDateTime EndDate;
    @SerializedName("events")
    public Collection<CalEvent> Events;

}
