package com.example.DTO;

public class EarthquakeResponse {

    private String place;
    private double magnitude;
    private String time;
    private int tsunami;
    private double latitude;
    private double longitude;

    public EarthquakeResponse(String place, double magnitude,
                              String time, int tsunami,
                              double latitude, double longitude) {
        this.place = place;
        this.magnitude = magnitude;
        this.time = time;
        this.tsunami = tsunami;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPlace() { return place; }
    public double getMagnitude() { return magnitude; }
    public String getTime() { return time; }
    public int getTsunami() { return tsunami; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}