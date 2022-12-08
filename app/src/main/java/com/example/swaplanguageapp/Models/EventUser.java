package com.example.swaplanguageapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class EventUser {

    @SerializedName("userId")
    public UUID UserId;
    @SerializedName("userEmail")
    public String UserEmail;
    @SerializedName("accepted")
    public Boolean Accepted;
    @SerializedName("host")
    public Boolean Host;
    @SerializedName("event")
    public Event Event;
}
