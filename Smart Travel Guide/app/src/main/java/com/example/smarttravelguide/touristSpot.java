package com.example.smarttravelguide;

public class touristSpot {
    private String title, description, weather, video;
    private double latitude, longitude;

    public touristSpot() {
    }

    public touristSpot(String title, String description, String weather, double latitude, double longitude, String video) {
        this.title = title;
        this.description = description;
        this.weather = weather;
        this.latitude = latitude;
        this.longitude = longitude;
        this.video = video;
    }

    public touristSpot(String title) {
        this.title = title;
    }

    public touristSpot(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public touristSpot(String title, String description, String weather, String video) {
        this.title = title;
        this.description = description;
        this.weather = weather;
        this.video = video;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
