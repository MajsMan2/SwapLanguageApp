package com.example.swaplanguageapp.Models;

import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Event {
    @SerializedName("allday")
    public boolean AllDay;
    @SerializedName("startDate")
    public OffsetDateTime StartDate;
    @SerializedName("endDate")
    public OffsetDateTime EndDate;
    @SerializedName("durationHours")
    public double DurationHours;
    @SerializedName("title")
    public String Title;
    @SerializedName("isCancelled")
    public boolean IsCancelled;
    @SerializedName("meetingUrl")
    public String MeetingUrl;
    @SerializedName("groupIds")
    public List<UUID> GroupIds;
    @SerializedName("description")
    public String Description;
    @SerializedName("timezoneId")
    public String TimezoneId;
    @SerializedName("eventType  ")
    public com.google.firebase.database.core.view.Event.EventType EventType;
    @SerializedName("users")
    public Collection<EventUser> eventUser;


}
